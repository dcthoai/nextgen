package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.ProductCarousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCarouselRepository extends JpaRepository<ProductCarousel, Integer> {
}
