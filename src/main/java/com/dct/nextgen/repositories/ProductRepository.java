package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IProductDTO;
import com.dct.nextgen.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT p.id, p.name FROM product p", nativeQuery = true)
    Page<IProductDTO> findAllWithPaging(Pageable pageable);

    @Query(value = "SELECT p.id, p.name FROM product p LIMIT 10", nativeQuery = true)
    List<IProductDTO> findAllNonPaging();

    @Query(value = "SELECT p.id, p.name FROM Product p WHERE p.id = ?1")
    Optional<IProductDTO> findProductById(Integer productId);
}
