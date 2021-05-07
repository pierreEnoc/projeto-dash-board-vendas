package com.pierre.dsvendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pierre.dsvendas.dto.SaleDTO;
import com.pierre.dsvendas.dto.SaleSuccessDTO;
import com.pierre.dsvendas.dto.SaleSumDTO;
import com.pierre.dsvendas.services.SaleService;

@RestController
@RequestMapping(value = "/sales")                
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public ResponseEntity<Page<SaleDTO>> findAll(Pageable pageable) {
		Page<SaleDTO> list  = saleService.findAll(pageable);
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping(value = "/amount-by-seller")
	public ResponseEntity<List<SaleSumDTO>> amountGroupedBySeller() {
		List<SaleSumDTO> list  = saleService.amountGroupedBySeller();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/success-by-seller")
	public ResponseEntity<List<SaleSuccessDTO>> successroupedBySeller() {
		List<SaleSuccessDTO> list  = saleService.successroupedBySeller();
		return ResponseEntity.ok(list);
	}

}
