package br.com.jmsstudio.supply.controller;

import br.com.jmsstudio.supply.dao.ProductDaoMock;
import br.com.jmsstudio.supply.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductController {
	
	private ProductDaoMock productDao;

	public ProductController() {
		productDao = new ProductDaoMock();
	}
	
	public List<Product> list() {
		return productDao.list();
	}
	
	public List<Product> filter(String name) {
		return productDao.list().stream()
							.filter(product -> product.getName().toLowerCase().startsWith(name.toLowerCase()))
							.collect(Collectors.toList());
	}
	
	public List<Product> filter(String name, String brand) {
		return productDao.list().stream()
							.filter(product ->
								product.getName().toLowerCase().startsWith(name.toLowerCase())
								&& product.getBrand().equalsIgnoreCase(brand)
							)
							.collect(Collectors.toList());
	}
}
