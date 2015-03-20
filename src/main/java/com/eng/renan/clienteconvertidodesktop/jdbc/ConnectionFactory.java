/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.jdbc;

/**
 *
 * @author renanferreira
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * Uma fábrica de conexões.
 *
 */
public class ConnectionFactory {

    static {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException exc ) {
            exc.printStackTrace();
 
        }
    }
 

    public static Connection getConnection(
            String url,
            String usuario,
            String senha ) throws SQLException {
        return DriverManager.getConnection( url, usuario, senha );
 
    }

    public static Connection getVendaConnection() throws SQLException {
        return getConnection(
                "jdbc:mysql://localhost:3306/vendaconvertida?zeroDateTimeBehavior=convertToNull",
                "root",
                "" );
 
    }
 
}