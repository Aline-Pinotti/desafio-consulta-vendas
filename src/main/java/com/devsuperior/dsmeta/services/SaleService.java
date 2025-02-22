package com.devsuperior.dsmeta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

//import java.time.ZoneId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
//import java.time.Instant;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> findSales(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate lastYear = today.minusYears(1L);

		return repository.findSales(minDate.equals("") ? lastYear : LocalDate.parse(minDate),
				maxDate.equals("") ? today : LocalDate.parse(maxDate), name, pageable);
	}

	public Page<SaleSummaryDTO> findSalesSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate lastYear = today.minusYears(1L);

		return repository.findSalesSummary(minDate.equals("") ? lastYear : LocalDate.parse(minDate),
				maxDate.equals("") ? today : LocalDate.parse(maxDate), pageable);
	}
}
