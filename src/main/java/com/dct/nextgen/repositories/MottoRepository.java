package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.Motto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MottoRepository extends JpaRepository<Motto, Integer> {
}
