package com.tradetask.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tradetask.controller.exception.ResourceNotFoundException;
import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RestController
@RequestMapping(value = "/v1")
public class TradeRestController {
	private static Logger logger = LogManager.getLogger(TradeRestController.class);
	@Autowired
	TradeService tradeService;

	@PostMapping(path = "/saveOneTrade", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Trade> addTrade(@Valid @RequestBody Trade trade, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		logger.info("inside newTrade(@RequestBody Trade trade) method ");

		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (trade == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Trade>(HttpStatus.NOT_IMPLEMENTED);
		}
		this.tradeService.saveTrade(trade);
		headers.setLocation(ucBuilder.path("/v1/{id}").buildAndExpand(trade.getTradeId()).toUri());
		return new ResponseEntity<Trade>(trade, HttpStatus.CREATED);
	}

	@PostMapping(path = "/savAllTrade", consumes = "application/json", produces = "application/json")
	public List<Trade> addTrade(@RequestBody List<Trade> trades) {
		logger.info("inside newTrade(@RequestBody Trade trade) method ");
		return tradeService.saveAll(trades);
	}

	@PutMapping(path = "/trades/{id}", consumes = "application/json", produces = "application/json")
	public Trade updateTrade(@PathVariable(value = "id") int id, @RequestBody Trade tradeDetails)
			throws DataAccessException {
		logger.info(
				"inside updateTrade(@PathVariable(value = \"id\") int id, @RequestBody Trade tradeDetails) method ");
		Trade trade = tradeService.findById(id);

		trade.setAmount(tradeDetails.getAmount());
		trade.setBankname(tradeDetails.getBankname());
		trade.setComments(tradeDetails.getComments());
		trade.setCurrency(tradeDetails.getCurrency());
		trade.setLocation(tradeDetails.getLocation());
		Trade updatedtrade = null;
		try {
			updatedtrade = tradeService.saveTrade(trade);
		} catch (Exception e) {
			logger.error(
					"Error occured updateTrade(@PathVariable(value = \"id\") int id, @RequestBody Trade tradeDetails) ",
					e);
		}
		return updatedtrade;
	}

	@DeleteMapping("/trades/{tradeId}")
	public ResponseEntity<Void> deleteTrade(@PathVariable(value = "tradeId") int tradeId)
			throws ResourceNotFoundException {
		logger.info("inside deleteTrade(@PathVariable(value = \"id\") int id) method ");

		Trade trade = tradeService.findById(tradeId);
		if (trade == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		this.tradeService.delete(tradeId);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String hello() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority role : authorities) {
			System.out.println(role);
		}
		System.out.println("User Name : " + name);
		return "hello";
	}

}
