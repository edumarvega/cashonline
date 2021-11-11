package com.broker.cashonline.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.broker.cashonline.entity.Loan;
import com.broker.cashonline.exception.ResourceNotFoundException;
import com.broker.cashonline.repository.LoanRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class LoanController {
	
	private static Logger logger = LoggerFactory.getLogger(LoanController.class);

	@Autowired
	private LoanRepository loanRepository;

	@GetMapping("/loans")
	public ResponseEntity<Map<String, Object>> getAllLoansPage(@RequestParam(required = false) String user_id,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {


			logger.debug("inside LoanController.getAllLoansPage() method");
			List<Loan> loans = new ArrayList<Loan>();
			Pageable pagingSort = this.getPagingSort(page, size);
			Page<Loan> pageLoans;
			
			if (user_id == null)
				pageLoans = this.loanRepository.findAll(pagingSort);
			else
				pageLoans = this.loanRepository.findByUserId(Long.valueOf(user_id), pagingSort);

			loans = pageLoans.getContent();

			if (loans.isEmpty()) {
				logger.warn("No se encntraron resultados.");
				throw new ResourceNotFoundException("No se encntraron resultados.");
			}

			Map<String, Object> response = new HashMap<>();
			response.put("items", loans);
			response.put("paging", new com.broker.cashonline.pagination.Page(pageLoans.getNumber(), pageLoans.getTotalPages(), pageLoans.getTotalElements()));

			return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	private Pageable getPagingSort(int page, int size) {
		
		List<Order> orders = new ArrayList<Order>();
		String[] sort = new String[2];
		sort[0] = "id";
		sort[1] = "asc";
		orders.add(new Order(this.getSortDirection(sort[1]), sort[0]));
		Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
		
		return pagingSort;
		
	}
	
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

}
