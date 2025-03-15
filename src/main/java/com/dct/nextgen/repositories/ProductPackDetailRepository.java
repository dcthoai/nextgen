package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.ProductPackDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPackDetailRepository extends JpaRepository<ProductPackDetail, Integer> {
}
