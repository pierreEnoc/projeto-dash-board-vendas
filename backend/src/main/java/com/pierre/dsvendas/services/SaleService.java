package com.pierre.dsvendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pierre.dsvendas.dto.SaleDTO;
import com.pierre.dsvendas.dto.SaleSuccessDTO;
import com.pierre.dsvendas.dto.SaleSumDTO;
import com.pierre.dsvendas.entities.Sale;
import com.pierre.dsvendas.repositories.SaleRepository;
import com.pierre.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	
	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable) {
		sellerRepository.findAll();
		Page <Sale> result = saleRepository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
		
		}
		 
		@Transactional(readOnly = true)
		public List<SaleSumDTO> amountGroupedBySeller() {
			return saleRepository.amountGroupedBySeller();
		}
		
		@Transactional(readOnly = true)
		public List<SaleSuccessDTO> successroupedBySeller() {
			return saleRepository.successroupedBySeller();
		}
	}
