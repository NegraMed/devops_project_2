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

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class FournisseurTest{
    @Mock
    FournisseurRepository fournisseurRepository;
    @InjectMocks
    FournisseurServiceImpl fournisseurServiceImpl  ;
    @Autowired
    IFournisseurService fournisseurService;

    Fournisseur Fo = Fournisseur.builder().code("xxx").libelle("amine").build();

    @Test
    public void testAddFournisseur() {
        List<Fournisseur> Fournisseurs = fournisseurService.retrieveAllFournisseurs();
        int expected=Fournisseurs.size();
        Fournisseur f = new Fournisseur();
        f.setLibelle("aabbb");
        f.setCode("test2");
        
        Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
        assertEquals(expected+1, fournisseurService.retrieveAllFournisseurs().size());
        assertNotNull(savedFournisseur.getLibelle());
        fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
    }


    @Test
    public void MochAddFournisseur() {
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Fo));
        Fournisseur fournisseur = fournisseurServiceImpl.retrieveFournisseur((long) 1);
        assertNotNull(fournisseur);
        log.info("get fournisseur"+fournisseur);
    }
}