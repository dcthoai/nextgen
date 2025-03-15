package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.ProductPackCompare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPackCompareRepository extends JpaRepository<ProductPackCompare, Integer> {
}
