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
public enum SexoEnum {
    MASCULINO("M"),
    FEMININO("F");
 
    private String value;
     
    private SexoEnum(String value){
        this.value = value;
    }
 
    public String getValue() {
        return value;
    }  
    
    public static SexoEnum fromString(String text) {
    if (text != null) {
      for (SexoEnum b : SexoEnum.values()) {
        if (text.equalsIgnoreCase(b.value)) {
          return b;
        }
      }
    }
    return null;
  }
}
