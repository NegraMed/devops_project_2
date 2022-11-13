package com.esprit.examen.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.esprit.examen.entities.Produit;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProduitServiceTest {


    @Autowired
    IProduitService ps;
    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Produit> listProduits = ps.retrieveAllProduits();
        Assertions.assertEquals(0, listProduits.size());
    }

}