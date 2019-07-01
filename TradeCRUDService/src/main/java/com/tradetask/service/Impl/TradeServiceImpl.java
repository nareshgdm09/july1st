package com.tradetask.service.Impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tradetask.entity.Trade;
import com.tradetask.repository.TradeRepo;
import com.tradetask.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	private static Logger logger = LogManager.getLogger(TradeServiceImpl.class);
	@Autowired
	TradeRepo repository;

	@Override
	public Trade saveTrade(Trade trade) throws DataAccessException {
		logger.info("inside saveTrade(Trade trade) method ");
		return repository.save(trade);
	}

	@Override
	public List<Trade> FindAll() {
		logger.info("inside List<Trade> getAllTrades() method ");
		return repository.findAll();
	}

	@Override
	public Trade findById(int tradeId) {
		logger.info("inside Optional<Trade> findById(int tradeId) method ");
		return repository.findById(tradeId).get();
	}

	@Override
	public List<Trade> saveAll(List<Trade> trades) {
		logger.info("inside List<Trade> saveAll(List<Trade> trades) method ");
		return repository.saveAll(trades);
	}

	@Override
	public void delete(int tradeId) {
		logger.info("inside delete(int tradeId) method ");
		repository.deleteById(tradeId);
	}

	@Override
	public void delete(List<Trade> trades) {
		logger.info("inside delete(List<Trade> trades) method ");
		repository.deleteAll(trades);

	}

}
