package com.esprit.examen.services;


import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
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
public class ProduitTest {
    @Mock
    ProduitRepository ProduitRepository;
    @InjectMocks
    ProduitServiceImpl ProduitServiceImpl;
    @Autowired
    IProduitService ProduitService;

    Produit p = Produit.builder().codeProduit("azeze").libelleProduit("eeeee").prix(15).build();

    @Test
    public void testAddProduits() {
        List<Produit> Produits = ProduitService.retrieveAllProduits();
        int expected = Produits.size();
        Produit p = new Produit();
        p.setLibelleProduit("Produit test");
        p.setPrix(10);
        Produit prod = ProduitService.addProduit(p);
        assertEquals(expected + 1, ProduitService.retrieveAllProduits().size());
        assertNotNull(prod.getCodeProduit());
        ProduitService.deleteProduit(prod.getIdProduit());
    }

    @Test
    public void MockAddStock() {
        Mockito.when(ProduitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
        Produit p = ProduitServiceImpl.retrieveProduit((long) 2);
        assertNotNull(p);
        log.info("get prod" + p.toString());
    }
}

