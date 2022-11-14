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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.services.OperateurServiceImpl;







@RunWith(MockitoJUnitRunner.class)
public class OperateurTest {


OperateurRepository operateurRepository=Mockito.mock(OperateurRepository.class);

@InjectMocks
private OperateurServiceImpl operateurService;

Operateur operateur = Operateur.builder().idOperateur(1L).nom("test").build();
@Test
public void testRetrieveOperateur() {

//Operateur categorie = new Operateur("test","test","test");
//categorie.setIdOperateur(1L);
	

when(operateurRepository.findById(any())).thenReturn(Optional.of(operateur));
operateurService.retrieveOperateur(1L);
Assert.assertNotNull(operateur);

System.out.println(operateur);
System.out.println(" testRetrieveOperateur-> CA MARCHE  !!!!!");

}

@Test
public void createOperateurTest()
{

Operateur operateur2 = Operateur.builder().idOperateur(1L).nom("test").build();
operateur2.setIdOperateur(2L);

operateurService.addOperateur(operateur2);
verify(operateurRepository, times(1)).save(operateur2);
System.out.println(operateur2);
System.out.println(" createOperateurTest -> CA MARCHE  !!!!!");
}

@Test
public void getAllOperateurTest()
{
List<Operateur> Operlist = new ArrayList<Operateur>() {

{
add(Operateur.builder().idOperateur(1L).nom("wassim").build());
add(Operateur.builder().idOperateur(1L).nom("hamma").build());
add(Operateur.builder().idOperateur(1L).nom("mouna").build());
}};


when(operateurService.retrieveAllOperateurs()).thenReturn(Operlist);
//test
List<Operateur> oprList = operateurService.retrieveAllOperateurs();
assertEquals(3,oprList.size());
System.out.println(" getAllOperateurTest MARCHE !!!! ");

}

@Test
public void TestDeleteOperateur(){

Operateur operateur1 = Operateur.builder().idOperateur(1L).nom("wassim").build();
operateur1.setIdOperateur(7L);



Mockito.lenient().when(operateurRepository.findById(operateur1.getIdOperateur())).thenReturn(Optional.of(operateur1));

operateurService.deleteOperateur(7L);
verify(operateurRepository).deleteById(operateur1.getIdOperateur());

System.out.println(operateur1);
System.out.println("  TestDeleteOperateur MARCHE ");
}

}