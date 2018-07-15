package takeARide.service.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import takeARide.entity.Account;
import takeARide.entity.Order;
import takeARide.entity.OrderItem;
import takeARide.entity.Product;
import takeARide.exceptions.AccessDeniedException;
import takeARide.exceptions.BlockedUserExeption;
import takeARide.exceptions.InternalServerErrorException;
import takeARide.exceptions.ResourceNotFoundException;
import takeARide.form.ProductForm;
import takeARide.jdbc.JDBCUtils;
import takeARide.jdbc.ResultSetHandler;
import takeARide.jdbc.ResultSetHandlerFactory;
import takeARide.model.CurrentAccount;
import takeARide.model.ShoppingCart;
import takeARide.model.ShoppingCartItem;
import takeARide.model.SocialAccount;
import takeARide.service.OrderService;

class OrderServiceImpl implements OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	private static final ResultSetHandler<Product> productResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Account> accountResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);
	private final ResultSetHandler<Order> orderResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);
	private final ResultSetHandler<List<OrderItem>> orderItemListResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER);
	private final ResultSetHandler<List<Order>> ordersResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);
	private final ResultSetHandler<Integer> countResultSetHandler = ResultSetHandlerFactory.getCountResultSetHandler();

	private final DataSource dataSource;

	public OrderServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
		try (Connection c = dataSource.getConnection()) {
			Product product = JDBCUtils.select(c,
					"select p.*, c.name as category, pr.name as producer from product p, producer pr, category c "
							+ "where c.id=p.id_category and pr.id=p.id_producer and p.id=?",
					productResultSetHandler, productForm.getIdProduct());
			if (product == null) {
				throw new InternalServerErrorException("Product not found by id=" + productForm.getIdProduct());
			}
			shoppingCart.addProduct(product, productForm.getCount());
			shoppingCart.setFullName(productForm.getFullName());
			shoppingCart.setPassportSeries(productForm.getPassportSeries());
			if (productForm.getNeedDriver() == 1) {
				shoppingCart.setNeedDriver(true);
			} else {
				shoppingCart.setNeedDriver(false);
			}

		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart) {
		shoppingCart.removeProduct(form.getIdProduct(), form.getCount());
	}

	@Override
	public String serializeShoppingCart(ShoppingCart shoppingCart) {
		StringBuilder res = new StringBuilder();
		for (ShoppingCartItem item : shoppingCart.getItems()) {
			res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
		}
		if (res.length() > 0) {
			res.deleteCharAt(res.length() - 1);
		}
		return res.toString();
	}

	@Override
	public ShoppingCart deserializeShoppingCart(String string) {
		ShoppingCart shoppingCart = new ShoppingCart();
		String[] items = string.split("\\|");
		for (String item : items) {
			try {
				String data[] = item.split("-");
				int idProduct = Integer.parseInt(data[0]);
				int count = Integer.parseInt(data[1]);
				addProductToShoppingCart(new ProductForm(idProduct, count), shoppingCart);
			} catch (RuntimeException e) {
				LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
			}
		}
		return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
	}

	@Override
	public CurrentAccount authentificate(SocialAccount socialAccount) {
		try (Connection c = dataSource.getConnection()) {
			Account account = JDBCUtils.select(c, "select * from account where email=?", accountResultSetHandler,
					socialAccount.getEmail());
			if (account == null) {
				account = new Account(socialAccount.getName(), socialAccount.getEmail());
				account = JDBCUtils.insert(c, "insert into account values (nextval('account_seq'),?,?)",
						accountResultSetHandler, account.getName(), account.getEmail());
				c.commit();
			}
			return account;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	@Override
	public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount, HttpServletRequest req) {
		if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
			throw new InternalServerErrorException("shoppingCart is null or empty");
		}
		if(isBlocked(currentAccount.getEmail())) {
			throw new BlockedUserExeption("You are Blocked!");
		}
		try (Connection c = dataSource.getConnection()) {
			System.out.println("Привет");
			System.out.println(shoppingCart.getFullName());
			Order order = JDBCUtils.insert(c, "insert into \"order\" values(nextval('order_seq'),?,?,?,?,?,?)",
					orderResultSetHandler, currentAccount.getId(), new Timestamp(System.currentTimeMillis()),
					shoppingCart.getPassportSeries(), shoppingCart.getFullName(),
					String.valueOf(shoppingCart.isNeedDriver()),currentAccount.getEmail());
			JDBCUtils.insertBatch(c, "insert into order_item values(nextval('order_item_seq'),?,?,?)",
					toOrderItemParameterList(order.getId(), shoppingCart.getItems()));
			c.commit();
			return order.getId();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items) {
		List<Object[]> parametersList = new ArrayList<>();
		for (ShoppingCartItem item : items) {
			parametersList.add(new Object[] { idOrder, item.getProduct().getId(), item.getCount() });
		}
		return parametersList;
	}

	@Override
	public Order findOrderById(long id, CurrentAccount currentAccount) {
		try (Connection c = dataSource.getConnection()) {
			Order order = JDBCUtils.select(c, "select * from \"order\" where id=?", orderResultSetHandler, id);
			if (order == null) {
				throw new ResourceNotFoundException("Order not found by id: " + id);
			}
			if (!order.getIdAccount().equals(currentAccount.getId())) {
				throw new AccessDeniedException(
						"Account with id=" + currentAccount.getId() + " is not owner for order with id=" + id);
			}
			List<OrderItem> list = JDBCUtils.select(c,
					"select o.id as oid, o.id_order as id_order, o.id_product, o.count, p.*, c.name as category, pr.name as producer from order_item o, product p, category c, producer pr "
							+ "where pr.id=p.id_producer and c.id=p.id_category and o.id_product=p.id and o.id_order=?",
					orderItemListResultSetHandler, id);
			order.setItems(list);
			return order;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	@Override
	public void addNewProduct(String fullName, String producer, String category, String price, String description,
			String id, String filePath) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("insert into product values(?,?,?,?,?,?,?)")) {
				ps.setInt(1, Integer.parseInt(id));
				ps.setString(2, fullName);
				ps.setString(3, description);
				ps.setString(4, "/media/" + filePath);
				ps.setInt(5, Integer.parseInt(price));
				ps.setInt(6, Integer.parseInt(category));
				ps.setInt(7, Integer.parseInt(producer));
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't insert SQL request: " + e.getMessage(), e);
		}

	}

	@Override
	public List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit) {
		int offset = (page - 1) * limit;
		try (Connection c = dataSource.getConnection()) {
			List<Order> orders = JDBCUtils.select(c,
					"select * from \"order\" where id_account=? order by id desc limit ? offset ?",
					ordersResultSetHandler, currentAccount.getId(), limit, offset);
			return orders;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}
	
	@Override
	public List<Order> listAllOrders( int page, int limit) {
		int offset = (page - 1) * limit;
		try (Connection c = dataSource.getConnection()) {
			List<Order> orders = JDBCUtils.select(c,
					"select * from \"order\" order by id desc limit ? offset ?",
					ordersResultSetHandler,  limit, offset);
			return orders;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}
	
	
	@Override
	public int countMyOrders(CurrentAccount currentAccount) {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select count(*) from \"order\" where id_account=?", countResultSetHandler,
					currentAccount.getId());
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteProduct(String fullName) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("delete from product where name=?")) {
				ps.setString(1, fullName);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't delete from dataBase: " + e.getMessage(), e);
		}

	}

	@Override
	public void updateProduct(String fullName, String description, String price, String id) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c
					.prepareStatement("update product set name=?,description=?,price=? where id=?")) {
				ps.setString(1, fullName);
				ps.setString(2, description);
				ps.setInt(3, Integer.parseInt(price));
				ps.setInt(4, Integer.parseInt(id));
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't update product: " + e.getMessage(), e);
		}

	}

	@Override
	public boolean isBlocked(String email) {
		try (Connection c = dataSource.getConnection()) {
			if (JDBCUtils.selectForAcconut(c, "select * from account where email=?", email) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't validate account: " + e.getMessage(), e);
		}
	}

	@Override
	public void blockUser(String email) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("update account set is_blocked=? where email=?")) {
				ps.setInt(1, 1);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't block user: " + e.getMessage(), e);
		}

	}
	@Override
	public void UnblockUser(String email) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("update account set is_blocked=? where email=?")) {
				ps.setInt(1, 0);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't unBlock user: " + e.getMessage(), e);
		}

	}

	@Override
	public boolean isManager(String email) {
		try (Connection c = dataSource.getConnection()) {
			if (JDBCUtils.selectForManagerAcconut(c, "select * from account where email=?", email) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't validate account: " + e.getMessage(), e);
		}
	}

	@Override
	public void rejectOrder(int idOrder,String message) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("update \"order\" set is_rejected=? where id=?")) {
				ps.setString(1, message);
				ps.setInt(2, idOrder);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't reject Order: " + e.getMessage(), e);
		}

		
	}
	@Override
	public void setCheckedForOrder(int idOrder) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("update \"order\" set is_checked=? where id=?")) {
				ps.setInt(1, 1);
				ps.setInt(2, idOrder);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't update Order: " + e.getMessage(), e);
		}

		
	}
	@Override
	public void doneDamagedOrder(int idOrder,String message) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("update \"order\" set message=? where id=?")) {
				ps.setString(1, message);
				ps.setInt(2, idOrder);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't reject Order: " + e.getMessage(), e);
		}

		
	}

	@Override
	public void setManager(String email) {
		try (Connection c = dataSource.getConnection()) {
			try (PreparedStatement ps = c.prepareStatement("update account set is_manager=? where email=?")) {
				ps.setInt(1, 1);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			c.commit();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't set manager: " + e.getMessage(), e);
		}

	}

}