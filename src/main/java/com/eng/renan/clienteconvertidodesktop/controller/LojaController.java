/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.dao.LojaDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Loja;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class LojaController implements Initializable {

     @FXML private Text actiontarget;
     
     @FXML
     private TextField nome;
      
     @FXML
     private TextField desc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    protected void handleSubmitButtonAction(ActionEvent event) {
        Loja loja = new Loja();
        loja.setNome(nome.getText());
        loja.setDescricao(desc.getText());
        LojaDao dao = new LojaDao();
        dao.adiciona(loja);
        actiontarget.setText("Loja cadastrada");
    }
    
}
