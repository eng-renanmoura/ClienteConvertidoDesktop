/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.dao.VendaDao;
import com.eng.renan.clienteconvertidodesktop.dao.VendedorDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Item;
import com.eng.renan.clienteconvertidodesktop.modelo.Venda;
import com.eng.renan.clienteconvertidodesktop.modelo.Vendedor;
import com.eng.renan.clienteconvertidodesktop.util.TurnoEnum;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class VendaController implements Initializable {

    @FXML private ComboBox<String>  vendedor;
    @FXML private ComboBox<String> turno;
    @FXML private TextField ValorVenda;
    
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> produtoNome;
    @FXML private TableColumn<Item, String> produtoValor;
    @FXML private TableColumn<Item, String> produtoQuantidade;
    @FXML private TableColumn<Item, String> produtoTotal;
    
    @FXML private ComboBox<String> itemProduto;
    @FXML private TextField itemQuantidade;
    /**
     * Initializes the controller class.
     */
    
    private List<Item> itens = new ArrayList<Item>();
    
    private VendedorDao vendedorDao = new VendedorDao();
    private List<Vendedor> vendedores = vendedorDao.listaTodos();
    private Venda venda = new Venda();
    
    public Venda getVenda(){
        return this.venda;
    }

    public List<Item> getItens() {
        return itens;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InicializarComboVendedor();
        InicializarComboTurno();
        InicializarItens();
                 
    }   
    
    public void InicializarItens(){
        produtoNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        produtoValor.setCellValueFactory(new PropertyValueFactory<Item, String>("valor"));
        produtoQuantidade.setCellValueFactory(new PropertyValueFactory<Item, String>("quantidade"));
        produtoTotal.setCellValueFactory(new PropertyValueFactory<Item, String>("produtoTotal"));

        tableView.getItems().setAll(itens);
   
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
         @Override 
          public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {    
                    TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();

                   // vendedor = (Vendedor) selectionModel.getSelectedItem();
                    //System.out.println(vendedor.getNome());
                }else{
                    try {
                        ItemNovo();
                    } catch (Exception ex) {
                        Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
          }
        });
    }
    
    private void InicializarComboVendedor(){
        for(Vendedor v : vendedores){
            vendedor.getItems().add(v.getNome());
        }
    }
    
    private void InicializarComboTurno(){
        for(TurnoEnum t : TurnoEnum.values()){
            turno.getItems().add(t.getValue());
        }
    }
    
    @FXML 
    protected void handleLancarVendaButtonAction(ActionEvent event) {
        venda.setItens(null);
        venda.setTurno(TurnoEnum.MANHA);
        venda.setVendedor(null);
       // vendedor.setLoja(loja.getText());
        VendaDao dao = new VendaDao();
        dao.adiciona(venda);
        //actiontarget.setText("Loja cadastrada");
    }
    
    private void ItemNovo() throws Exception {
        System.out.println("OI");
     for(Item i : itens){
         System.out.println(i.getProduto().getNome());
     }
     Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/ItemVenda.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Adicionar Item");
     stage.setScene(scene);
     stage.show();
  }
    
}
