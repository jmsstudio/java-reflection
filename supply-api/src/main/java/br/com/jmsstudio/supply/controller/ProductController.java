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
	
	public List<Product> lista() {
		return productDao.list();
	}
	
	public List<Product> filtra(String nome) {
		return productDao.list().stream()
							.filter(produto -> produto.getName().toLowerCase().startsWith(nome.toLowerCase()))
							.collect(Collectors.toList());
	}
	
	public List<Product> filtra(String nome, String marca) {
		return productDao.list().stream()
							.filter(produto -> 
								produto.getName().toLowerCase().startsWith(nome.toLowerCase())
								&& produto.getBrand().equalsIgnoreCase(marca)
							)
							.collect(Collectors.toList());
	}
}
