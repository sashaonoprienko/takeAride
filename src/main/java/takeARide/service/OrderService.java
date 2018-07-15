package takeARide.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import takeARide.entity.Order;
import takeARide.form.ProductForm;
import takeARide.model.CurrentAccount;
import takeARide.model.ShoppingCart;
import takeARide.model.SocialAccount;

public interface OrderService {
	void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
	
	void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart);
	
	String serializeShoppingCart(ShoppingCart shoppingCart);
	
	ShoppingCart deserializeShoppingCart(String string);

	CurrentAccount authentificate(SocialAccount socialAccount);
	
	long makeOrder(ShoppingCart shoppingCart,CurrentAccount currentAccount,HttpServletRequest req);
	
    Order findOrderById(long id, CurrentAccount currentAccount);
	
	List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit);
	
	int countMyOrders(CurrentAccount currentAccount);
	
	public void addNewProduct(String fullName,String producer,String category,String price,String description,String id,String filePath);
	
	public void deleteProduct(String fullName);
	
	public void updateProduct(String fullName,String description,String price,String id);
	
	public  boolean isBlocked(String email);
	
	public void blockUser(String email);
	
	public  boolean isManager(String email);

	List<Order> listAllOrders( int page, int limit);
	
	public void rejectOrder(int idOrder,String message);
	
	public void doneDamagedOrder(int idOrder,String message);
	
	public void setManager(String email);

	void UnblockUser(String email);

	void setCheckedForOrder(int idOrder);

	
	
}
