package fr.doranco.primus_neon.admin.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import fr.doranco.primus_neon.common.entity.Product;

public interface productRepository extends PagingAndSortingRepository<Product, Integer> {

}
