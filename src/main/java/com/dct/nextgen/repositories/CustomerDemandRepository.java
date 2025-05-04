package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.CustomerDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDemandRepository extends JpaRepository<CustomerDemand, Integer> {


}
