package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.ICustomerDemand;
import com.dct.nextgen.entity.CustomerDemand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDemandRepository extends JpaRepository<CustomerDemand, Integer> {

    @Query(
        value = """
            SELECT cd.id, cd.name, cd.fullname, cd.phone, cd.email, cd.status,
                cd.product_id as productId, p.name as productName, cd.created_date as createdDate
            FROM customer_demand cd
            JOIN product p ON cd.product_id = p.id
            WHERE status <> 'DELETED'
                AND (:status IS NULL OR cd.status = :status)
                AND (:phone IS NULL OR cd.phone = :phone)
                AND (:email IS NULL OR cd.email = :email)
                AND (:keyword IS NULL OR (cd.name LIKE :keyword OR cd.fullname LIKE :keyword))
                AND (:fromDate IS NULL OR cd.created_date >= :fromDate)
                AND (:toDate IS NULL OR cd.created_date <= :toDate)
            ORDER BY cd.created_date DESC
        """,
        nativeQuery = true
    )
    Page<ICustomerDemand> findAllWithPaging(
        @Param("status") String status,
        @Param("phone") String phone,
        @Param("email") String email,
        @Param("keyword") String keyword,
        @Param("fromDate") String fromDate,
        @Param("toDate") String toDate,
        Pageable pageable
    );

    @Query(
        value = """
            SELECT cd.name, cd.fullname, cd.phone, cd.email, cd.status, p.name as productName,
                cd.created_date as createdDate,
                cd.last_modified_by as lastModifiedByStr,
                cd.last_modified_date as lastModifiedDateStr
            FROM customer_demand cd
            JOIN product p ON cd.product_id = p.id
            WHERE cd.status <> 'DELETED' AND cd.id = ?1
        """,
        nativeQuery = true
    )
    Optional<ICustomerDemand> findDemandById(Integer demandId);

    @Modifying
    @Query(value = "UPDATE customer_demand cd SET cd.status = ?2 WHERE cd.id = ?1", nativeQuery = true)
    void updateDemandStatusById(Integer demandId, String status);
}
