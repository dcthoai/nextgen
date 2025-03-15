package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.ProductPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPackRepository extends JpaRepository<ProductPack, Integer> {
}
