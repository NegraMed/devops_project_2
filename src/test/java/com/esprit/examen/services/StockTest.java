package com.esprit.examen.services;


import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class StockTest {
    @Mock
    StockRepository stockRepository;
    @InjectMocks
    StockServiceImpl stockServiceImpl;
    @Autowired
    IStockService stockService;

    Stock s = new Stock("stock test",10,100);

    @Test
    public void testAddStock() {
        List<Stock> stocks = stockService.retrieveAllStocks();
        int expected=stocks.size();
        Stock s = new Stock();
        s.setLibelleStock("stock test");
        s.setQte(10);
        s.setQteMin(100);
        Stock savedStock= stockService.addStock(s);
        assertEquals(expected+1, stockService.retrieveAllStocks().size());
        assertNotNull(savedStock.getLibelleStock());
        stockService.deleteStock(savedStock.getIdStock());
    }
    @Test
    public void MockAddStock() {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Stock stock = stockServiceImpl.retrieveStock((long) 2);
        assertNotNull(stock);
        log.info("get stock"+stock);
    }
}