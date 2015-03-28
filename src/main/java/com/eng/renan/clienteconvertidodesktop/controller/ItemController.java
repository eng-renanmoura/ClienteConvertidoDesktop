/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.dao.ProdutoDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Item;
import com.eng.renan.clienteconvertidodesktop.modelo.Produto;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class ItemController implements Initializable {

   
    @FXML private ComboBox<String> itemProduto;
    @FXML private TextField itemQuantidade;
    /**
     * Initializes the controller class.
     */
    
    List<Item> itens = new ArrayList<Item>();
    
    ProdutoDao produtoDao = new ProdutoDao();
    List<Produto> produtos = produtoDao.listaTodos();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InicializarComboProduto();           
    }   
    

    private void InicializarComboProduto(){
        for(Produto p : produtos){
            itemProduto.getItems().add(p.getNome());
        }
    }
    
    
    @FXML 
    protected void handleNovoItemAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Venda.fxml"));
        Parent root = (Parent)loader.load();
        VendaController vendaController = (VendaController)loader.getController();
        
        Item item = new Item();
        int produtoSelecionado = itemProduto.getSelectionModel().getSelectedIndex();
        item.setProduto(produtos.get(produtoSelecionado));
        item.setQuantidade(new Integer(itemQuantidade.getText()));
        item.setValorUnitario(produtos.get(produtoSelecionado).getPreco());
        item.setVenda(vendaController.getVenda());
        vendaController.getItens().add(item);
        vendaController.InicializarItens();
    }
    
}
