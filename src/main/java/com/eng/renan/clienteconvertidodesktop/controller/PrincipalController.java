package com.eng.renan.clienteconvertidodesktop.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.eng.renan.clienteconvertidodesktop.dao.VendedorDao;
import com.eng.renan.clienteconvertidodesktop.modelo.Vendedor;
import java.awt.event.InputEvent;
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class PrincipalController implements Initializable {

  @FXML
  private MenuBar menuBar;

  @FXML private TableView<Vendedor> tableView;
  @FXML private TableColumn<Vendedor, String> VendedorNome;
  @FXML private TableColumn<Vendedor, String> VendedorAtivo;
  /**
   * Handle action related to "About" menu item.
   * 
   * @param event Event on "About" menu item.
   */
  @FXML
  private void handleLojaAction(final ActionEvent event)
  throws Exception{
     Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/Loja.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Cadastro de Loja");
     stage.setScene(scene);
     stage.show();
  }
  
  @FXML
  private void handleProdutoAction(final ActionEvent event)
  throws Exception{
          Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/Produto.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Cadastro de Produto");
     stage.setScene(scene);
     stage.show();
  }
  
    @FXML
  private void handleVendedorAction(final ActionEvent event)
  throws Exception{
          Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/Vendedor.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Cadastro de Vendedor");
     stage.setScene(scene);
     stage.show();
  }
  
  @FXML
  private void handleAboutAction(final ActionEvent event)
  throws Exception{
      Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Teste");
     stage.setScene(scene);
     stage.show();
  }
  
  @FXML
   private void handleVendaAction(final ActionEvent event) throws Exception{
     
        provideVendaFunctionality();
  }

  /**
   * Handle action related to input (in this case specifically only responds to
   * keyboard event CTRL-A).
   * 
   * @param event Input event.
   */
  @FXML
  public void handleKeyInput(final InputEvent event)
  {
    
  }

  /**
   * Perform functionality associated with "About" menu selection or CTRL-A.
   */
  private void provideVendaFunctionality()
  throws Exception {
     Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/Venda.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Lançar Venda");
     stage.setScene(scene);
     stage.show();
        
  }


 @Override
 public void initialize(java.net.URL arg0, ResourceBundle arg1) {
   menuBar.setFocusTraversable(true);
  
   VendedorNome.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("nome"));
   VendedorAtivo.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("id"));

   tableView.getItems().setAll(parseVendedorList());
   
   tableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
       @Override 
        public void handle(MouseEvent event) {
            Vendedor vendedor;
            if (event.getClickCount() == 2) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {    
                    TableViewSelectionModel selectionModel = tableView.getSelectionModel();

                    vendedor = (Vendedor) selectionModel.getSelectedItem();
                    System.out.println(vendedor.getNome());
                    
                    try {
                        provideVendaFunctionality();
                    } catch (Exception ex) {
                        Logger.getLogger(PrincipalController.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }
    });
  
 }   
 
  private List<Vendedor> parseVendedorList(){
        VendedorDao vendedorDao = new VendedorDao();
        return vendedorDao.listaTodos();
 }

}
