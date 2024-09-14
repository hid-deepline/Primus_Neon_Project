package fr.doranco.primus_neon.admin.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.doranco.primus_neon.common.entity.Category;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {
	
	@MockBean
	private CategoryRepository repo;
	
	@InjectMocks
	private CategoryService service;
	
	@Test
	public void testChecUniqueInNewModeReturnDuplicateName() {
		
		Integer id = null;
        String name = "Néon";
        String alias = "neon";
        
        Category category = new Category(id, name, alias);
        
        when(repo.findByName(name)).thenReturn(category);
        when(repo.findByAlias(alias)).thenReturn(null);
        
        String result = service.checkUnique(id, name, alias);
        
        assertThat(result).isEqualTo("DuplicateName");
    
	}
	
	@Test
	public void testChecUniqueInNewModeReturnDuplicateAlias() {

		Integer id = null;
		String name = "Néon";
		String alias = "neon";

		Category category = new Category(id, name, alias);

		when(repo.findByName(name)).thenReturn(null);
		when(repo.findByAlias(alias)).thenReturn(category);

		String result = service.checkUnique(id, name, alias);

		assertThat(result).isEqualTo("DuplicateAlias");

	}
	
	@Test
	public void testChecUniqueInNewModeReturnOK() {

		Integer id = null;
		String name = "Néon";
		String alias = "neon";

		when(repo.findByName(name)).thenReturn(null);
		when(repo.findByAlias(alias)).thenReturn(null);

		String result = service.checkUnique(id, name, alias);

		assertThat(result).isEqualTo("OK");

	}
	
	@Test
	public void testChecUniqueInEditModeReturnDuplicateName() {

		Integer id = 1;
		String name = "Néon";
		String alias = "neon";

		Category category = new Category(2, name, alias);

		when(repo.findByName(name)).thenReturn(category);
		when(repo.findByAlias(alias)).thenReturn(null);

		String result = service.checkUnique(id, name, alias);

		assertThat(result).isEqualTo("DuplicateName");

	}
	
	@Test
	public void testChecUniqueInEditModeReturnDuplicateAlias() {

		Integer id = 1;
		String name = "Néon";
		String alias = "neon";

		Category category = new Category(2, name, alias);

		when(repo.findByName(name)).thenReturn(null);
		when(repo.findByAlias(alias)).thenReturn(category);

		String result = service.checkUnique(id, name, alias);

		assertThat(result).isEqualTo("DuplicateAlias");

	}
	
	@Test
	public void testChecUniqueInEditModeReturnOK() {

		Integer id = 1;
		String name = "Néon";
		String alias = "neon";
		
		Category category = new Category(id, name, alias);

		when(repo.findByName(name)).thenReturn(null);
		when(repo.findByAlias(alias)).thenReturn(category);

		String result = service.checkUnique(id, name, alias);

		assertThat(result).isEqualTo("OK");

	}
	
}
