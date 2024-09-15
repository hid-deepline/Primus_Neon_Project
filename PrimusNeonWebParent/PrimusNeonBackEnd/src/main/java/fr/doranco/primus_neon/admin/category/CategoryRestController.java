package fr.doranco.primus_neon.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping("/categories/check_unique")
	public String checkUnique(@RequestParam(name ="id", required = false) Integer id, @RequestParam(name = "name") String name,
			@RequestParam(name = "alias") String alias) {
		
		return service.checkUnique(id, name, alias);
	}

}
