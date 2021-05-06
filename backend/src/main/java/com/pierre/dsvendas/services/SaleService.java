package com.pierre.dsvendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pierre.dsvendas.dto.SaleDTO;
import com.pierre.dsvendas.entities.Sale;
import com.pierre.dsvendas.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	public Page<SaleDTO> findAll(Pageable pageable) {
		Page <Sale> result = saleRepository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
		
		}
	}
