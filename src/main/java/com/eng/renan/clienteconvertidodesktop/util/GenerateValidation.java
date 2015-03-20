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
import java.util.Date;
import java.util.UUID;
 
public class GenerateValidation {
 
    public static String keyValidation() {
 
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase().replace('-', 'X')
                + String.valueOf((new Date()).getTime());
    }
}