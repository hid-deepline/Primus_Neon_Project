package fr.doranco.primus_neon.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.doranco.primus_neon.admin.category.CategoryService;

import fr.doranco.primus_neon.common.entity.Brand;
import fr.doranco.primus_neon.common.entity.Category;

@Controller
public class BrandController {

	@Autowired
	private BrandService service;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/brands")
	public String listAll(Model model) {
		List<Brand> listBrands = service.listAll();
		model.addAttribute("listBrands", listBrands);

		return "brands/brands";
	}

	@GetMapping("/brands/new")
	public String newBrand(Model model) {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("brand", new Brand());
		model.addAttribute("pageTitle", "Créer une nouvelle marque");

		return "brands/brand_form";
	}

}