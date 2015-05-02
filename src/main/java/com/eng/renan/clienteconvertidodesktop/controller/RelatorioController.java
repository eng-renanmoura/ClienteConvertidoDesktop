/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.controller;

import com.eng.renan.clienteconvertidodesktop.util.relatorio.Person;
import com.eng.renan.clienteconvertidodesktop.util.relatorio.Report;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * FXML Controller class
 *
 * @author renanferreira
 */
public class RelatorioController implements Initializable {

  @FXML private Button botaoRelatorio;
    /**
     * Initializes the controller class.
     */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
        // TODO
  }    
    
  @FXML
  private void handleRelatorioAction(final ActionEvent event)
    throws Exception{
        export();
  }
    
  private void export() {

    Report report = new Report("reports/Example.jasper", botaoRelatorio.getScene().getWindow());
    report.export("Export report", "Report " + ".pdf", getReportParams(), new JRBeanCollectionDataSource(getReportData()));
  }

  private void print() {
    Report report = new Report("reports/Example.jasper", botaoRelatorio.getScene().getWindow());
    report.print(getReportParams(), new JRBeanCollectionDataSource(getReportData()));
  }

  private void printPreview() {
    Report report = new Report("reports/Example.jasper", botaoRelatorio.getScene().getWindow());
    report.printPreview(getReportParams(), new JRBeanCollectionDataSource(getReportData()));
  }
    
  private Map<String, Object> getReportParams() {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("COMPANY_NAME", "Company inc.");
    return paramMap;
  }

  private List<Person> getReportData() {
    List<Person> items = new ArrayList<>();
    items.add(new Person("Alan", "Smithee"));
    items.add(new Person("John", "Doe"));
    items.add(new Person("Average", "Joe"));
    items.add(new Person("Hong", "Gildong"));
    items.add(new Person("Joe", "Bloggs"));
    items.add(new Person("Joe", "Shmoe"));
    items.add(new Person("John Q.", "Public"));
    items.add(new Person("Luther", "Blissett"));
    items.add(new Person("Rudolf", "Lingens"));
    items.add(new Person("Tommy", "Atkins"));
    items.add(new Person("Richard", "Roe"));
    items.add(new Person("John", "Smith"));
    items.add(new Person("King", "Kong"));
    items.add(new Person("Ping", "Pong"));
    items.add(new Person("Sing", "Song"));
    return items;
  }
}
