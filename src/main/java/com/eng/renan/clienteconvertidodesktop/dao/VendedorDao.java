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
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.eng.renan.clienteconvertidodesktop.modelo.Vendedor;
import com.eng.renan.clienteconvertidodesktop.util.JPAUtil;

public class VendedorDao implements Serializable{

	private static final long serialVersionUID = 1L;

	
	public void adiciona(Vendedor vendedor) {

            //manager.persist(vendedor);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.persist(vendedor);		
            em.getTransaction().commit();
            em.close();
		
	}


	public void remove(Vendedor vendedor) {

            //manager.remove(manager.merge(vendedor));
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.remove(em.merge(vendedor));		
            em.getTransaction().commit();
            em.close();
                

	}

	public void atualiza(Vendedor vendedor) {

            //manager.merge(vendedor);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.merge(vendedor);		
            em.getTransaction().commit();
            em.close();
		
	}

	public List<Vendedor> buscaPorNome(String nome) {


		String jpql = "select p from Vendedor p where "
				+ " lower(p.nome) like :nome order by p.nome";

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();				
                
		List<Vendedor> lista = em.createQuery(jpql, Vendedor.class)
				.setParameter("nome", nome + "%").getResultList();
		
                em.close();
		return lista; 
	}

	public List<Vendedor> listaTodos() {

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		CriteriaQuery<Vendedor> query = em.getCriteriaBuilder().createQuery(Vendedor.class);
		query.select(query.from(Vendedor.class));

		List<Vendedor> lista = em.createQuery(query).getResultList();

                em.close();
                
		return lista; 
	}
	
	public Vendedor buscaPorId(Long id) {

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		Vendedor vendedor = em.find(Vendedor.class, id);

                em.close();
		return vendedor;
	}
}
