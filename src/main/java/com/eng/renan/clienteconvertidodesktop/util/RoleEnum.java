/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.util;

/**
 *
 * @author renanferreira
 */
public enum RoleEnum {
     
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_COMMON("ROLE_COMMON");
 
    private String value;
     
    private RoleEnum(String value){
        this.value = value;
    }
 
    public String getValue() {
        return value;
    }    
}
