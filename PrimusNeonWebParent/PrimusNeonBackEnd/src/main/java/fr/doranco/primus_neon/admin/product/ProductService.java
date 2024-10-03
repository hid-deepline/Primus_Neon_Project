package fr.doranco.primus_neon.admin.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.doranco.primus_neon.common.entity.Product;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> listAll() {
		return (List<Product>) repo.findAll();
	}

	public Product save(Product product) {

		if (product.getId() == null) {
			product.setCreatedTime(new Date());
		}

		if (product.getAlias() == null || product.getAlias().isEmpty()) {
			String defaultAlias = product.getName().replaceAll(" ", "-");
			product.setAlias(defaultAlias);
		} else {
			product.setAlias(product.getAlias().replaceAll(" ", "-"));
		}

		product.setUpdatedTime(new Date());

		return repo.save(product);
	}
	
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew = (id == null || id == 0);
		
		Product productByName = repo.findByName(name);
		
		if (isCreatingNew) {
			if (productByName != null) 
				return "Dupliqué";
			
		} else {
			if (productByName != null && productByName.getId() != id) {
				return "Dupliqué";
			}
			
		}
		
		return "OK";
	}
	
	public void updateProductEnabledStatus(Integer id, boolean enabled) {
		repo.updateEnabledStatus(id, enabled);
	}
	
	public void delete(Integer id) throws ProductNotFoundException {
		Long countById = repo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new ProductNotFoundException("Impossible de trouver un produit avec l'ID " + id);
		}
		
		repo.deleteById(id);
	}

}
