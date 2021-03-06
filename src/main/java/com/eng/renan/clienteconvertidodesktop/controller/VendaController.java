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
import com.eng.renan.clienteconvertidodesktop.util.SexoEnum;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class VendaController implements Initializable {

    @FXML private ComboBox<String>  vendedor;
    @FXML private ComboBox<String> turno;
    @FXML private ComboBox<String> clienteSexo;
    @FXML private TextField valorVenda;
    
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> produtoNome;
    @FXML private TableColumn<Item, String> produtoValor;
    @FXML private TableColumn<Item, String> produtoQuantidade;
    @FXML private TableColumn<Item, String> produtoTotal;
    
    @FXML private ComboBox<String> itemProduto;
    @FXML private TextField itemQuantidade;
    @FXML private TextField tempo;
    
    @FXML private TableView<Item> tableViewFalta;
    @FXML private TableColumn<Item, String> produtoFaltaNome;
    @FXML private ComboBox<String> itemProdutoFalta;
    
    /**
     * Initializes the controller class.
     */
    private List<Item> itensEmFalta = new ArrayList<>();
    
    private List<Item> itens = new ArrayList<>();
    
    private VendedorDao vendedorDao = new VendedorDao();
    private List<Vendedor> vendedores = vendedorDao.listaTodos();
    private Venda venda = new Venda();
    
    private ProdutoDao produtoDao = new ProdutoDao();
    private List<Produto> produtos = produtoDao.listaTodos();
    private List<Produto> produtosEmFalta = produtoDao.listaTodos();
    
    public Venda getVenda(){
        return this.venda;
    }

    public List<Item> getItens() {
        return itens;
    }

    public List<Item> getItensEmFalta() {
        return itensEmFalta;
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InicializarComboVendedor();
        InicializarComboTurno();
        InicializarComboClienteSexo();
        InicializarItens();
        InicializarItensEmFalta();
        InicializarComboProduto();
        InicializarComboProdutoEmFalta();             
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

    }
    
    public void InicializarItensEmFalta(){

        produtoFaltaNome.setCellValueFactory(new PropertyValueFactory<Item, String>("produtoNomeProperty"));

        tableViewFalta.getItems().setAll(itensEmFalta);
   
        tableViewFalta.setOnMouseClicked(new EventHandler<MouseEvent>(){
         @Override 
          public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                if(tableViewFalta.getSelectionModel().getSelectedItem() != null) {    
                    TableView.TableViewSelectionModel selectionModel = tableViewFalta.getSelectionModel();

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
    
    private void InicializarComboClienteSexo(){
        for(SexoEnum t : SexoEnum.values()){
            clienteSexo.getItems().add(t.getValue());
        }
    }
    
    @FXML 
    protected void handleLancarVendaButtonAction(ActionEvent event) {
        VendaDao dao = new VendaDao();
        this.getItens().addAll(this.getItensEmFalta());
        venda.setItens(this.getItens());
        venda.setTurno(TurnoEnum.fromString(turno.getSelectionModel().getSelectedItem()));
        venda.setSexoCliente(SexoEnum.fromString(clienteSexo.getSelectionModel().getSelectedItem()));
        int vendedorSelecionado = vendedor.getSelectionModel().getSelectedIndex();
        venda.setVendedor(vendedores.get(vendedorSelecionado));
       // vendedor.setLoja(loja.getText());
        venda.setDataDaVenda(Calendar.getInstance());
        venda.setTempoDaVenda(calendario);
        venda.setValorTotalDaVenda(Double.parseDouble(valorVenda.getText()));
        dao.adiciona(venda);
        //actiontarget.setText("Loja cadastrada");
    }
    
    private void EditarItem(VendaController vendaController) throws Exception {

     
    }
  
  @FXML 
   protected void handleNovoItemAction(ActionEvent event) throws IOException {
        Double totalDaVenda = 0.0;
        Item item = new Item();
        int produtoSelecionado = itemProduto.getSelectionModel().getSelectedIndex();
        item.setProduto(produtos.get(produtoSelecionado));
        item.setQuantidade(new Integer(itemQuantidade.getText()));
        item.setValorUnitario(produtos.get(produtoSelecionado).getPreco());
        item.setVenda(this.getVenda());
        item.setEmFalta(Boolean.FALSE);
        this.getItens().add(item);
        this.InicializarItens();
        for(Item i : this.getItens()){
            totalDaVenda += i.getTotal();
        }
        valorVenda.setText(totalDaVenda.toString());
        
   }
   
     @FXML 
   protected void handleNovoItemFaltaAction(ActionEvent event) throws IOException {
        
        Item item = new Item();
        int produtoSelecionado = itemProdutoFalta.getSelectionModel().getSelectedIndex();
        item.setProduto(produtosEmFalta.get(produtoSelecionado));
        item.setQuantidade(0);
        item.setValorUnitario(produtosEmFalta.get(produtoSelecionado).getPreco());
        item.setVenda(this.getVenda());
        item.setEmFalta(Boolean.TRUE);
        this.getItensEmFalta().add(item);
        this.InicializarItensEmFalta();
        
   }
   
    private void InicializarComboProduto(){
        for(Produto p : produtos){
            itemProduto.getItems().add(p.getNome());
        }
    }
    
    private void InicializarComboProdutoEmFalta(){
        for(Produto p : produtos){
            itemProdutoFalta.getItems().add(p.getNome());
        }
    }
    
    Calendar calendario;
     Timer cronometro;
     DateFormat formato;
    public void iniciaCronometro(){
    	calendario = Calendar.getInstance();  
        cronometro = new Timer();  
        formato = new SimpleDateFormat("HH:mm:ss"); 
        
        calendario.set(0, 0, 0, 00, 00, 00);
        
        TimerTask tarefa = new TimerTask() {  
  
           @Override 
            public void run() { 
            
            calendario.add(Calendar.SECOND, 1);  
            tempo.setText(formato.format(calendario.getTime()));
            
            }  
        };  
        cronometro.scheduleAtFixedRate(tarefa, 0, 1000);  
        this.cronometro = null; 
    }
}
    
    
