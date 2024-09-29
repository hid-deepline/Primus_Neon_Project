package fr.doranco.primus_neon.admin.brand;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.doranco.primus_neon.common.entity.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
public class BrandService {
	public static final int BRANDS_PER_PAGE = 1;

	@Autowired
	private BrandRepository repo;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Brand> listAll() {
		return (List<Brand>) repo.findAll();
	}
	
	public Page<Brand> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
        Pageable pageable = PageRequest.of(pageNum - 1, BRANDS_PER_PAGE, sort);
        
		if (keyword != null) {
			return repo.findAll(keyword, pageable);
		}
		
        return repo.findAll(pageable);
	}
	
	public Brand save(Brand brand) {
		return repo.save(brand);
	}
	
	public Brand get(Integer id) throws BrandNotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new BrandNotFoundException("Impossible de trouver une marque avec ID " + id);
		}
	}
	
	@Transactional
	public void delete(Integer id) throws BrandNotFoundException {
        try {
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
            Long countById = repo.countById(id);
            if (countById == null || countById == 0) {
                throw new BrandNotFoundException("Impossible de trouver une marque avec l'ID " + id);
            }
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Gérer toute exception liée à l'intégrité référentielle ici
            throw e;
        } finally {
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
        }
    }
	
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew = (id == null || id == 0);
		
		Brand brandByName = repo.findByName(name);
		
		if (isCreatingNew) {
			if (brandByName != null) 
				return "Dupliqué";
			
		} else {
			if (brandByName != null && brandByName.getId() != id) {
				return "Dupliqué";
			}
			
		}
		
		return "OK";
	}
}