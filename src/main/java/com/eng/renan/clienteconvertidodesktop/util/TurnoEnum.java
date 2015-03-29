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
public enum TurnoEnum {
    
    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite");
 
    private String value;
     
    private TurnoEnum(String value){
        this.value = value;
    }
 
    public String getValue() {
        return value;
    }  
    
    public static TurnoEnum fromString(String text) {
    if (text != null) {
      for (TurnoEnum b : TurnoEnum.values()) {
        if (text.equalsIgnoreCase(b.value)) {
          return b;
        }
      }
    }
    return null;
  }
}
