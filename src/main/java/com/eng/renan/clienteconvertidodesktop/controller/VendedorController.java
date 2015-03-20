/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.dao.LojaDao;
import com.eng.renan.clienteconvertidodesktop.dao.VendedorDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Loja;
import com.eng.renan.clienteconvertidodesktop.modelo.Vendedor;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class VendedorController implements Initializable {
     @FXML private Text actiontarget;
     
     @FXML
     private TextField nome;
      
     @FXML 
     private ComboBox<String> loja;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LojaDao lojaDao = new LojaDao();
        List<Loja> lojas = lojaDao.listaTodos();
        
        ObservableList<String> options = 
                FXCollections.observableArrayList(
                    "Codigo",
                    "Nome",
                    "Cidade"
                );

        loja.setItems(options);
    }  
    
        @FXML 
    protected void handleSubmitButtonAction(ActionEvent event) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(nome.getText());
       // vendedor.setLoja(loja.getText());
        VendedorDao dao = new VendedorDao();
        dao.adiciona(vendedor);
        actiontarget.setText("Loja cadastrada");
    }
    
}
