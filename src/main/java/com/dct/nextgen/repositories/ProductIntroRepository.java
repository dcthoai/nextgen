package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.ProductIntro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIntroRepository extends JpaRepository<ProductIntro, Integer> {
}
