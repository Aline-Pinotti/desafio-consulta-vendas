package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	// minDate=2022-05-01&maxDate=2022-05-31&name=odinson // defaultValue =
	// "#{T(java.time.LocalDateTime).now()}")

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(// teste utilizando spring expression language
			@RequestParam(name = "minDate", defaultValue = "") String minDate,
			@RequestParam(name = "maxDate", defaultValue = "") String maxDate,
			@RequestParam(name = "name", defaultValue = "") String name,
			Pageable pageable) {

		return ResponseEntity.ok(service.findSales(minDate, maxDate, name, pageable));
	}/*
		 * Dica: receba todos os dados como String no controller, e faça os tratamentos
		 * das datas acima,
		 * instanciando os objetos LocalDate, no service.
		 */

	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary() {
		// TODO
		return null;
	}
}