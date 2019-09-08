
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Funcion;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sala;



/**
 *
 * @author melvin
 */
public class UtilidadesFacade implements Serializable {
    
    EntityManagerFactory EMF = Persistence.createEntityManagerFactory("cinePU");
    EntityManager em=EMF.createEntityManager();
    

    public EntityManager getEntityManager() {
        return this.em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
    /**
     * Listar las salas que posean asientos Reclinables
     * @return
     * @throws Exception 
     */
    public List<Sala> tipoAsiento() throws Exception {
        if (em != null) {
           try {
                Query query = em.createQuery("SELECT s FROM Sala s INNER JOIN s.asientoSalaList al ON al.asiento.atributoAsientoList.caracteristicaAsiento.caracteristica=Reclinable");
                List tipoAsiento = query.getResultList();
                return tipoAsiento;
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
    
    /**
     * Listar funciones de películas que tengan como director a Christopher
     * Nolan
     * @param director
     * @return 
     */
    public List<Funcion> funcionPorDirector(String director){
        try {
            if (director != null) {
                String[] separado = director.split("\\s");
                String nombre = separado[0].toUpperCase().charAt(0) + separado[0].substring(1, separado[0].length()).toLowerCase();
                String apellido = separado[1].toUpperCase().charAt(0) + separado[1].substring(1, separado[1].length()).toLowerCase();
                Query query = em.createQuery("SELECT f FROM Funcion f where f.idPelicula.idDirector.nombre=:nombre AND f.idPelicula.idDirector.apellido=:apellido").setParameter("nombre", nombre).setParameter("apellido", apellido);
                List Funciones = query.getResultList();
                return Funciones;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Listar las salas que tengan función después del 1 de septiembre del
     * presente año
     * @param fecha
     * @return 
     */
    public List<Sala> salasPorFuncion(Date fecha){
        if (em != null) {
            try {
                Query query = em.createQuery("SELECT s FROM Sala s INNER JOIN s.asientoSalaList asl where asl.boletoList.idFuncion.fecha <:date");
                query.setParameter("date", fecha);
                List salasPorFuncion = query.getResultList();
                return salasPorFuncion;
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
   
    
    
}

    
    

