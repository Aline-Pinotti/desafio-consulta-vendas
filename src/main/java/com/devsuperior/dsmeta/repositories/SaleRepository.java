package com.devsuperior.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface SaleRepository extends JpaRepository<Sale, Long> {

        @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
                        +
                        "FROM Sale obj " +
                        "WHERE obj.date BETWEEN :minDate AND :maxDate " +
                        "AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%') ")
        Page<SaleReportDTO> findSales(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

        @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name as sellerName, sum(amount) as total) "
                        +
                        "FROM Sale obj " +
                        "WHERE obj.date BETWEEN :minDate AND :maxDate " +
                        "GROUP BY obj.seller.name")
        Page<SaleSummaryDTO> findSalesSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
