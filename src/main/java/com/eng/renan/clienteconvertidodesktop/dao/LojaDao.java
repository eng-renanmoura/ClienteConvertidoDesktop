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

import com.eng.renan.clienteconvertidodesktop.modelo.Loja;
import com.eng.renan.clienteconvertidodesktop.util.JPAUtil;

public class LojaDao implements Serializable{

	private static final long serialVersionUID = 1L;

	public void adiciona(Loja loja) {

            //manager.persist(loja);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.persist(loja);		
            em.getTransaction().commit();
            em.close();
		
	}


	public void remove(Loja loja) {

            //manager.remove(manager.merge(loja));
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.remove(em.merge(loja));		
            em.getTransaction().commit();
            em.close();
                

	}

	public void atualiza(Loja loja) {

            //manager.merge(loja);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.merge(loja);		
            em.getTransaction().commit();
            em.close();
		
	}

	public List<Loja> buscaPorNome(String nome) {


		String jpql = "select p from Loja p where "
				+ " lower(p.nome) like :nome order by p.nome";

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();				
                
		List<Loja> lista = em.createQuery(jpql, Loja.class)
				.setParameter("nome", nome + "%").getResultList();
		
                em.close();
		return lista; 
	}

	public List<Loja> listaTodos() {

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		CriteriaQuery<Loja> query = em.getCriteriaBuilder().createQuery(Loja.class);
		query.select(query.from(Loja.class));

		List<Loja> lista = em.createQuery(query).getResultList();

                em.close();
                
		return lista; 
	}
	
	public Loja buscaPorId(Long id) {

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		Loja loja = em.find(Loja.class, id);

                em.close();
		return loja;
	}
}
