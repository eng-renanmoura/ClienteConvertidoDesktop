/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.modelo;

/**
 *
 * @author renanferreira
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
 
@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_user", allocationSize = 1, initialValue = 1)
public class Usuario implements Serializable{  
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;    
    private Boolean active;
    private String validation;
     
    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "user_permission",
               uniqueConstraints = { @UniqueConstraint (columnNames = {"id", "permission" })},
               joinColumns = @JoinColumn(name = "id"))
    @Column(name = "permission", length = 40)
    @Cascade(CascadeType.DELETE)
    private Set<String> permissions = new HashSet<>();    
 
    public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    public String getSenha() {
        return senha;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }
 
    public Boolean isActive() {
        return active;
    }
 
    public void setActive(Boolean active) {
        this.active = active;
    }    
 
    public Set<String> getPermissions() {
        return permissions;
    }
 
    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
 
    public Boolean getActive() {
        return active;
    }
 
    public String getValidation() {
        return validation;
    }
 
    public void setValidation(String validation) {
        this.validation = validation;
    }    
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }    
}