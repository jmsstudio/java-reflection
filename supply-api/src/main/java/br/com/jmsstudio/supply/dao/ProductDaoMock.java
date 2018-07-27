package br.com.jmsstudio.supply.dao;

import br.com.jmsstudio.supply.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductDaoMock implements ProductDao {
	private static final List<Product> PRODUCT_LIST =
			Arrays.asList(new Product("Product 1", 20.0, "Brand 1"),
					new Product("Product 2", 30.0, "Brand 1"),
					new Product("Product 3", 40.0, "Brand 2"));
	
	public List<Product> list() {
		return PRODUCT_LIST;
	}
	
	public Product getProduct(Integer id) {
		return PRODUCT_LIST.get(id);
	}
}
