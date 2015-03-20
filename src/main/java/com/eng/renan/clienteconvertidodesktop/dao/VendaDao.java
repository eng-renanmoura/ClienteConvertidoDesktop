/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.dao;

/**
 *
 * @author renanferreira
 */
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import com.eng.renan.clienteconvertidodesktop.modelo.Venda;
import com.eng.renan.clienteconvertidodesktop.util.JPAUtil;
import java.io.Serializable;
import java.util.List;

public class VendaDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public void adiciona(Venda venda) {
            //manager.persist(venda);
            
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();			
                em.persist(venda);		
                em.getTransaction().commit();
                em.close();
		
	}
	
	public List<Venda> listaTodos() {
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();				
            
		CriteriaQuery<Venda> query = em.getCriteriaBuilder().createQuery(Venda.class);
		query.select(query.from(Venda.class));

		List<Venda> lista = em.createQuery(query).getResultList();

                em.close();
                
		return lista; 
	}
	
	public int contaTodos() {
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();	
                
		long result = (Long) em.createQuery("select count(n) from Venda n").getSingleResult();
		
                em.close();
                
		return (int) result;
	}

	public List<Venda> listaTodosPaginada(int firstResult, int maxResults) {
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		CriteriaQuery<Venda> query = em.getCriteriaBuilder().createQuery(Venda.class);
		query.select(query.from(Venda.class));

		List<Venda> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

                em.close();
                 
		return lista;
	}

}
