package com.tradetask.service.Impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tradetask.entity.Trade;
import com.tradetask.repository.TradeRepo;

public class TradeServiceImplTest {

	@InjectMocks
	TradeServiceImpl tradeServiceImpl = new TradeServiceImpl();

	@Mock
	TradeRepo tradeRepo;

	@Mock
	List<Trade> listTrades;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindByIdWhenTradeIsIsNotPresent() {

		Assert.assertEquals(null, tradeServiceImpl.findById(123).getTradeId());
	}

	@Test
	public void testFindByIdWhenTradeIsIsPresent() {
		when(tradeRepo.findById(123)).thenReturn(null);
		Assert.assertEquals(null, tradeServiceImpl.findById(123).getTradeId());
	}

	@Test
	public void testFindAll() {
		when(tradeRepo.findAll()).thenReturn(listTrades);
		Assert.assertEquals(listTrades.size(), tradeServiceImpl.FindAll().size());
	}
}
