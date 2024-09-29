package fr.doranco.primus_neon.admin.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import fr.doranco.primus_neon.common.entity.Brand;
import fr.doranco.primus_neon.common.entity.Category;
import fr.doranco.primus_neon.common.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateProduct() {
		Brand brand = entityManager.find(Brand.class, 11);
		Category category = entityManager.find(Category.class, 7);
		
		Product product = new Product();
		product.setName("Playstation 5");
		product.setAlias("playstation_5");
		product.setShortDescription("Petite description de Playstation 5");
		product.setFullDescription("Description complete de ");
		
		product.setBrand(brand);
		product.setCategory(category);
		
		product.setPrice(600);
		product.setCost(500);
		product.setEnabled(true);
		product.setInStock(true);
		
		
		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());
		
		Product savedProduct = repo.save(product);
		
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testListAllProducts() {
		Iterable<Product> iterableProducts = repo.findAll();
		
		iterableProducts.forEach(System.out::println);

	}
	
	@Test
	public void testGetProduct() {
		Integer id = 1;
		Product product = repo.findById(id).get();
		System.out.println(product);
		
		assertThat(product).isNotNull();
	}

	@Test
	public void testUpdateProduct() {
		Integer id = 1;
		Product product = repo.findById(id).get();
		product.setPrice(750);

		repo.save(product);
		
		Product updatedProduct = entityManager.find(Product.class, id);

		assertThat(updatedProduct.getPrice()).isEqualTo(750);
	}

	@Test
	public void testDeleteProduct() {
        Integer id = 3;
        repo.deleteById(id);
        
        Optional<Product> result = repo.findById(id);
        
        assertThat(!result.isPresent());
	}
}
