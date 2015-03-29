/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.util;

/**
 *
 * @author renanferreira
 */
import com.eng.renan.clienteconvertidodesktop.jdbc.ConnectionFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
 

public class ReportUtil {
 
    public static final String TEMPLATE = "/reports/usuarios.jrxml";
    
    public void geraRelatorio(HashMap parametrosRelatorio) throws Exception {
         
      //  StreamedContent arquivoRetorno = null;
 
        try {
            Connection conexao = ConnectionFactory.getVendaConnection();
            InputStream reportStream = this.getClass().getResourceAsStream(TEMPLATE);
            JasperDesign jd = JRXmlLoader.load(reportStream);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametrosRelatorio, conexao);
             
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
 
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
             
           // arquivoRetorno = new DefaultStreamedContent(bais, "pdf", "users.pdf");
             
        } catch (JRException e) {
            e.printStackTrace();
            throw new Exception("Não foi possível gerar o relatório.", e);
        } 
       // return arquivoRetorno;
    }
 
    private Connection getConexao() throws Exception {
        java.sql.Connection conexao = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/myimage");
            conexao = (java.sql.Connection) ds.getConnection();
        } catch (NamingException e) {
            throw new Exception("Não foi possível encontrar o nome da conexão do banco.", e);
        } catch (SQLException e) {
            throw new Exception("Ocorreu um erro de SQL.", e);
        }
        return conexao;
    }
}