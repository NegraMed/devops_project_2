/*package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.ReglementRepository;
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
public class ReglementTest{
    @Mock
    ReglementRepository reglementRepository;
    @InjectMocks
    OperateurServiceImpl operateurServiceImp  ;
    @Autowired
    IOperateurService operateurService;

    Operateur op = Operateur.builder().nom("aaa").prenom("bbbb").password("eeee").build();

    @Test
    public void testAddOperateur() {
        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();
        int expected=operateurs.size();
        Operateur o = new Operateur();
        o.setNom("test");
        o.setPrenom("test2");
        o.setPassword("test3");
        Operateur savedOperateur= operateurService.addOperateur(o);
        assertEquals(expected+1, operateurService.retrieveAllOperateurs().size());
        assertNotNull(savedOperateur.getNom());
        operateurService.deleteOperateur(savedOperateur.getIdOperateur());
    }


    @Test
    public void MockAddOperateur() {
        Mockito.when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op));
        Operateur operateur = operateurServiceImp.retrieveOperateur((long) 1);
        assertNotNull(operateur);
        log.info("get operateur"+operateur);
    }
}*/