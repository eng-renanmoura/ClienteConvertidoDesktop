package com.eng.renan.clienteconvertidodesktop.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.InputEvent;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class PrincipalController implements Initializable {

@FXML
  private MenuBar menuBar;

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
     provideAboutFunctionality();
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
  private void provideAboutFunctionality()
  throws Exception {
     Stage stage = new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
     Scene scene = new Scene(root);
     scene.getStylesheets().add("/styles/Styles.css");
        
     stage.setTitle("Teste");
     stage.setScene(scene);
     stage.show();
        
  }


 @Override
 public void initialize(java.net.URL arg0, ResourceBundle arg1) {
   menuBar.setFocusTraversable(true);
   
 }   

}
