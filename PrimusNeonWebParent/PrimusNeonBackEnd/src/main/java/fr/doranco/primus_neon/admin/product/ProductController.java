package fr.doranco.primus_neon.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.doranco.primus_neon.admin.brand.BrandService;
import fr.doranco.primus_neon.common.entity.Brand;
import fr.doranco.primus_neon.common.entity.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/products")
	public String listAll(Model model) {
		List<Product> listProducts = productService.listAll();
		
		model.addAttribute("listProducts", listProducts);
		
		return "products/products";
	}
	
	@GetMapping("/products/new")
	public String newProduct(Model model) {
		List<Brand> listBrands = brandService.listAll();
		
		Product product = new Product();
		product.setEnabled(true);
		product.setInStock(true);
		
		model.addAttribute("product", product);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("pageTitle", "Créer un nouveau produit");

		return "products/product_form";
	}
	
	@PostMapping("/products/save")
	public String saveProduct(Product product) {
		System.out.println("Nom du produit: " + product.getName());
		System.out.println("ID de la marque: " + product.getBrand().getId());
		System.out.println("ID de la catégorie: " + product.getCategory().getId());
		
		return "redirect:/products";
	}

}
