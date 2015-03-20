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

import com.eng.renan.clienteconvertidodesktop.modelo.Produto;
import com.eng.renan.clienteconvertidodesktop.util.JPAUtil;

public class ProdutoDao implements Serializable{

	private static final long serialVersionUID = 1L;

	public void adiciona(Produto produto) {

            //manager.persist(produto);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.persist(produto);		
            em.getTransaction().commit();
            em.close();
		
	}


	public void remove(Produto produto) {

            //manager.remove(manager.merge(produto));
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.remove(em.merge(produto));		
            em.getTransaction().commit();
            em.close();
                

	}

	public void atualiza(Produto produto) {

            //manager.merge(produto);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.merge(produto);		
            em.getTransaction().commit();
            em.close();
		
	}

	public List<Produto> buscaPorNome(String nome) {


		String jpql = "select p from Produto p where "
				+ " lower(p.nome) like :nome order by p.nome";

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();				
                
		List<Produto> lista = em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome + "%").getResultList();
		
                em.close();
		return lista; 
	}

	public List<Produto> listaTodos() {

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		CriteriaQuery<Produto> query = em.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));

		List<Produto> lista = em.createQuery(query).getResultList();

                em.close();
                
		return lista; 
	}
	
	public Produto buscaPorId(Long id) {

                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();
                
		Produto produto = em.find(Produto.class, id);

                em.close();
		return produto;
	}
}
