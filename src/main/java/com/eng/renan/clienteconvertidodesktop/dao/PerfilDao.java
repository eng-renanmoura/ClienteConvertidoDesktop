/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.dao;

import com.eng.renan.clienteconvertidodesktop.modelo.Perfil;
import com.eng.renan.clienteconvertidodesktop.util.JPAUtil;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author renanferreira
 */
public class PerfilDao implements Serializable{

	private static final long serialVersionUID = 1L;

        public void adiciona(Perfil perfil) {

            //manager.persist(perfil);

            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.persist(perfil);		
            em.getTransaction().commit();
            em.close();
		
	}
        
        public void remove(Perfil perfil) {

            //manager.remove(manager.merge(perfil));
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.remove(em.merge(perfil));	
            em.getTransaction().commit();
            em.close();
		
	}

	public void atualiza(Perfil perfil) {

            //manager.merge(perfil);
            EntityManager  em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();			
            em.merge(perfil);	
            em.getTransaction().commit();
            em.close();
		
	}
}
