package com.esprit.examen.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.services.StockServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class Testachat {
	@Mock
    StockRepository stockRepository;

    @InjectMocks
	StockServiceImpl stockServiceImpl;
	
	Stock s = new Stock((long)1,"testLibelle1",10,150, null);
	Stock s1 = new Stock((long)2,"testLibelle1",50,100, null);
	Stock s2 = new Stock((long)3,"testLibelle2",5,80, null);
	List<Stock> stock = new ArrayList<Stock>() {{add(s1); add(s2);}}; 
	
	@Test
	public void testGetAllStock() {
		stockServiceImpl.retrieveAllStocks();
		verify(stockRepository).findAll();
	}
	
	@Test
	public void testGetStock() {
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
		assertNotNull(stockServiceImpl.retrieveStock((long)3));	
	}
	
	@Test
	public void testaddStock() {
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(s);
		assertNotNull(stockServiceImpl.addStock(s));
		//verify(stockRepository).save(s);
	}
	
	@Test
	public void testUpdateStock() {
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(s);
		s.setQte(55);
		assertNotNull(stockServiceImpl.updateStock(s));	
		assertEquals(55, s.getQte());
	}
	
	
	@Test
	public void testDeleteStock() {
		stockServiceImpl.deleteStock((long)3);
		verify(stockRepository).deleteById((long)3);
	}
	
	
	
	
	
	
	
	
	
	
}