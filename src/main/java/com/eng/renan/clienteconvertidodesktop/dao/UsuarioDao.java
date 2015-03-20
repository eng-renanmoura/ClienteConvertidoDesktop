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

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.eng.renan.clienteconvertidodesktop.modelo.Usuario;
import com.eng.renan.clienteconvertidodesktop.util.JPAUtil;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean existe(Usuario usuario) {
           // Query query = manager.createQuery("select u from Usuario u where u.login = :pLogin and u.senha = :pSenha")
	//					.setParameter("pLogin", usuario.getLogin())
	//					.setParameter("pSenha", usuario.getSenha());
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();	
		Query query = em.createQuery("select u from Usuario u where u.login = :pLogin and u.senha = :pSenha")
						.setParameter("pLogin", usuario.getLogin())
						.setParameter("pSenha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();
                
                em.close();
                
		return encontrado;
	}
        
        public void adiciona(Usuario usuario) {

		//manager.persist(usuario);
                
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();	
	        em.persist(usuario);	
	        em.getTransaction().commit();
                em.close();
	}
        
        public void remove(Usuario usuario) {

                //manager.remove(manager.merge(usuario));
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();	
                em.remove(em.merge(usuario));	
                em.getTransaction().commit();
                em.close();

	}

	public void atualiza(Usuario usuario) {

		//manager.merge(usuario);
                EntityManager  em = new JPAUtil().getEntityManager();
                em.getTransaction().begin();	
                em.merge(usuario);
                em.getTransaction().commit();
                em.close();
		
	}
}