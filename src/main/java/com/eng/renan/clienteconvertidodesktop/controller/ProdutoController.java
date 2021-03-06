/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.dao.ProdutoDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Produto;
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
public class ProdutoController implements Initializable {
     @FXML private Text actiontarget;
     
     @FXML
     private TextField nome;
      
     @FXML
     private TextField desc;
     
     @FXML
     private TextField preco;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML 
    protected void handleSubmitButtonAction(ActionEvent event) {
        Produto produto = new Produto();
        produto.setNome(nome.getText());
        produto.setDescricao(desc.getText());
        produto.setPreco(Double.parseDouble(preco.getText()));
        ProdutoDao dao = new ProdutoDao();
        dao.adiciona(produto);
        nome.clear();
        desc.clear();
        preco.clear();
        actiontarget.setText("Produto cadastrado");
    }
}
