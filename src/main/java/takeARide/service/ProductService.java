package takeARide.service;

import java.util.List;

import takeARide.entity.Category;
import takeARide.entity.Producer;
import takeARide.entity.Product;
import takeARide.form.SearchForm;

public interface ProductService {
List<Product> listAllProducts(int page, int limit);
	
	int countAllProducts();
	
	List<Product> listProductsByCategory(String categoryUrl, int page, int limit);
	
	int countProductsByCategory(String categoryUrl);
	
	List<Category> listAllCategories();
	
	List<Producer> listAllProducers();
	
	List<Product> listProductsBySearchForm(SearchForm searchForm, int page, int limit);
	
	int countProductsBySearchForm(SearchForm searchForm);
}
