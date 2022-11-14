package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.esprit.examen.entities.Produit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.services.CategorieProduitServiceImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class CategorieProduitServiceImplMock {

	@Mock
	CategorieProduitRepository categorieProduitRepository;
	
	@InjectMocks
	CategorieProduitServiceImpl categorieProduitService;

	@Autowired
	ICategorieProduitService iCategorieProduitService;
	
	CategorieProduit cp = new CategorieProduit((long) 1, "abc","cat1", null);

	@Test
	public void testAddCategories() {
		List<CategorieProduit> categorieProduits = iCategorieProduitService.retrieveAllCategorieProduits();
		int expected = categorieProduits.size();
		CategorieProduit c = new CategorieProduit();
		c.setCodeCategorie("Produit test");
		c.setLibelleCategorie("zezez");
		CategorieProduit cat = iCategorieProduitService.addCategorieProduit(c);
		assertEquals(expected + 1, iCategorieProduitService.retrieveAllCategorieProduits().size());
		assertNotNull(cat.getLibelleCategorie());
		iCategorieProduitService.deleteCategorieProduit(cat.getIdCategorieProduit());
	}

	@Test
	public void MockAddStock() {
		Mockito.when(categorieProduitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cp));
		CategorieProduit c = categorieProduitService.retrieveCategorieProduit((long) 2);
		assertNotNull(c);
		log.info("get categorie" + c.toString());
	}
}