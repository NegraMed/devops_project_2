package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.FactureRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class FactureTest{
    @Mock
    FactureRepository factureRepository;
    @InjectMocks
    FactureServiceImpl factureServiceImpl  ;
    @Autowired
    IFactureService factureService;

    Facture Fo = Facture.builder().montantFacture(15).montantRemise(5).build();

    @Test
    public void testAddFacture() {
        List<Facture> Factures = factureService.retrieveAllFactures();
        int expected=Factures.size();
        Facture f = new Facture();
        f.setMontantFacture(15);
        f.setMontantRemise(5);
        
        Facture savedFacture= factureService.addFacture(f);
        assertEquals(expected+1, factureService.retrieveAllFactures().size());
        assertNotNull(savedFacture.getMontantFacture());
       // factureService.dele(savedFacture.getIdFacture());
    }


    @Test
    public void MochAddFacture() {
        Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Fo));
        Facture Facture = factureServiceImpl.retrieveFacture((long) 1);
        assertNotNull(Facture);
        log.info("get Facture"+Facture);
    }
}