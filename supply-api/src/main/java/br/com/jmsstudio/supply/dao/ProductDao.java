package br.com.jmsstudio.supply.dao;

import br.com.jmsstudio.supply.model.Product;

import java.util.List;

public interface ProductDao {
	List<Product> list();
	Product getProduct(Integer id);
}
