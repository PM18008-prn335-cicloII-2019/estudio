/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Funcion;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sala;

/**
 *
 * @author melvin
 */
public class UtilidadesFacadeTest {


   @Test
    public void testSalasPorFuncion() throws Exception {
        System.out.println(" test salasPorFuncion");
        List<Sala> esperado = new ArrayList<Sala>();
        Sala sala = new Sala();
        sala.setIdSala(1);
        sala.setSala("Max 3D");
        esperado.add(sala);
        Date fecha=new Date(2020,12,2);
        UtilidadesFacade utilidades = new UtilidadesFacade();
        EntityManager mockem = Mockito.mock(EntityManager.class);
        utilidades.setEm(mockem);
        Query mockQ = Mockito.mock(Query.class);
        Mockito.when(mockem.createQuery(Mockito.anyString())).thenReturn(mockQ);
        Mockito.when(mockQ.getResultList()).thenReturn(esperado);
        Mockito.when(mockQ.setParameter(Mockito.anyString(),Mockito.anyString())).thenReturn(mockQ);
        List<Sala> resultado = utilidades.salasPorFuncion(fecha);
        assertEquals(esperado.get(0).getSala(), resultado.get(0).getSala());

    }

    @Test
    public void testFuncionPorDirector() throws Exception {
        System.out.println(" test funcionPorDirector");
        List<Funcion> esperado = new ArrayList<Funcion>();
        Funcion funcion = new Funcion();
        funcion.setIdFuncion(1);
        Date fecha=new Date(2020,12,2);
        funcion.setFecha(fecha);
        esperado.add(funcion);
        UtilidadesFacade utilidades = new UtilidadesFacade();
        EntityManager mockem = Mockito.mock(EntityManager.class);
        utilidades.setEm(mockem);
        Query mockQ = Mockito.mock(Query.class);
        String nombre="Christopher",apellido="Nolan";
        Mockito.when(mockem.createQuery(Mockito.anyString())).thenReturn(mockQ);
        Mockito.when(mockQ.getResultList()).thenReturn(esperado);
        Mockito.when(mockQ.setParameter(Mockito.anyString(),Mockito.anyString())).thenReturn(mockQ);
        List<Funcion> resultado = utilidades.funcionPorDirector(nombre+" "+apellido);
        assertEquals(esperado.get(0).getIdFuncion(), resultado.get(0).getIdFuncion());
    }
}
