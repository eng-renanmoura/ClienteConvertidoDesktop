/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.dao.ProdutoDao;
import com.eng.renan.clienteconvertidodesktop.dao.VendaDao;
import com.eng.renan.clienteconvertidodesktop.dao.VendedorDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Item;
import com.eng.renan.clienteconvertidodesktop.modelo.Produto;
import com.eng.renan.clienteconvertidodesktop.modelo.Venda;
import com.eng.renan.clienteconvertidodesktop.modelo.Vendedor;
import com.eng.renan.clienteconvertidodesktop.util.TurnoEnum;
import com.mysql.fabric.xmlrpc.Client;
import static com.oracle.jrockit.jfr.ContentType.Address;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

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
    
    private List<Item> itens = new ArrayList<>();
    
    private VendedorDao vendedorDao = new VendedorDao();
    private List<Vendedor> vendedores = vendedorDao.listaTodos();
    private Venda venda = new Venda();
    
    private ProdutoDao produtoDao = new ProdutoDao();
    private List<Produto> produtos = produtoDao.listaTodos();
    
    public Venda getVenda(){
        return this.venda;
    }

    public List<Item> getItens() {
        return itens;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // if(itemProduto == null){
        InicializarComboVendedor();
        InicializarComboTurno();
        InicializarItens();
      // }
      //  if(itemProduto != null){
            InicializarComboProduto();
      //  }
                 
    }   
    
    public VendaController getVendaController(){
        return this;
    }
    
    public void InicializarItens(){

        produtoNome.setCellValueFactory(new PropertyValueFactory<Item, String>("produtoNomeProperty"));
        produtoValor.setCellValueFactory(new PropertyValueFactory<Item, String>("produtoPrecoProperty"));
        produtoQuantidade.setCellValueFactory(new PropertyValueFactory<Item, String>("quantidade"));
        produtoTotal.setCellValueFactory(new PropertyValueFactory<Item, String>("total"));

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
                       
                    } catch (Exception ex) {
                        Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
          }
        });
        
        tableView.setEditable(true);

//titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
//titleCol.setOnEditCommit(new EventHandler<CellEditEvent>() {
				
  //  @Override
   // public void handle(CellEditEvent t) {
					
  //      ((Book) t.getTableView().getItems().get(
  //          t.getTablePosition().getRow())).setTitle(t.getNewValue());
   // }
//});


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
        VendaDao dao = new VendaDao();
        venda.setItens(this.getItens());
        venda.setTurno(TurnoEnum.fromString(turno.getSelectionModel().getSelectedItem()));
        int vendedorSelecionado = vendedor.getSelectionModel().getSelectedIndex();
        venda.setVendedor(vendedores.get(vendedorSelecionado));
       // vendedor.setLoja(loja.getText());
        
        dao.adiciona(venda);
        //actiontarget.setText("Loja cadastrada");
    }
    
    private void EditarItem(VendaController vendaController) throws Exception {

     
    }
  
  @FXML 
   protected void handleNovoItemAction(ActionEvent event) throws IOException {
       
        Item item = new Item();
        int produtoSelecionado = itemProduto.getSelectionModel().getSelectedIndex();
        item.setProduto(produtos.get(produtoSelecionado));
        item.setQuantidade(new Integer(itemQuantidade.getText()));
        item.setValorUnitario(produtos.get(produtoSelecionado).getPreco());
        item.setVenda(this.getVenda());
        this.getItens().add(item);
        this.InicializarItens();
        
   }
   
    private void InicializarComboProduto(){
        for(Produto p : produtos){
            itemProduto.getItems().add(p.getNome());
        }
    }
}
