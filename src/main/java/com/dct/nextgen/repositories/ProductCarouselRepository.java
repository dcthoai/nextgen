package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IProductCarouselDTO;
import com.dct.nextgen.entity.ProductCarousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductCarouselRepository extends JpaRepository<ProductCarousel, Integer> {

    @Query(value = "SELECT pc.url FROM product_carousel pc WHERE pc.product_id = ?1", nativeQuery = true)
    Set<String> findAllCarouselImages(Integer productId);

    @Query(value = "SELECT pc.id, pc.url FROM product_carousel pc WHERE pc.product_id = ?1", nativeQuery = true)
    List<IProductCarouselDTO> findByProductId(Integer productId);

    @Modifying
    @Query(value = "DELETE FROM product_carousel pc WHERE pc.id IN (?1)", nativeQuery = true)
    void deleteAllByIDs(Iterable<Integer> carouselIds);
}
