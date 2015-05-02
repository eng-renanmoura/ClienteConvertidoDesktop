/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eng.renan.clienteconvertidodesktop.modelo;

/**
 *
 * @author renanferreira
 */
import com.eng.renan.clienteconvertidodesktop.util.SexoEnum;
import com.eng.renan.clienteconvertidodesktop.util.TurnoEnum;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="venda")
    private List<Item> itens = new ArrayList<Item>();
    @OneToOne
    private Vendedor vendedor;       
    private TurnoEnum turno;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataDaVenda;   
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar tempoDaVenda;
    private SexoEnum sexoCliente;
    private Double valorTotalDaVenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public TurnoEnum getTurno() {
        return turno;
    }

    public void setTurno(TurnoEnum turno) {
        this.turno = turno;
    }
    
    public Calendar getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Calendar dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public Calendar getTempoDaVenda() {
        return tempoDaVenda;
    }

    public void setTempoDaVenda(Calendar tempoDaVenda) {
        this.tempoDaVenda = tempoDaVenda;
    }

    public SexoEnum getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(SexoEnum sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public Double getValorTotalDaVenda() {
        return valorTotalDaVenda;
    }

    public void setValorTotalDaVenda(Double valorTotalDaVenda) {
        this.valorTotalDaVenda = valorTotalDaVenda;
    }

}
