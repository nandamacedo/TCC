/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author nanda
 */
public class Filme {

    private final StringProperty titulo;
    private final StringProperty ano;
    private final IntegerProperty salas;
    private final IntegerProperty publico;
    private final DoubleProperty renda;
    private final StringProperty UF;
    
    public Filme(String titulo, String ano, Integer salas, Integer publico, Double renda, String UF){
        this.titulo = new SimpleStringProperty(titulo);
        this.ano = new SimpleStringProperty(ano);
        this.salas = new SimpleIntegerProperty(salas);
        this.publico = new SimpleIntegerProperty(publico);
        this.renda = new SimpleDoubleProperty(renda);
        this.UF = new SimpleStringProperty(UF);
    }
    


    public String getTitulo() {
        return titulo.get();
    }

    public String getAno() {
        return ano.get();
    }

    public Integer getSalas() {
        return salas.get();
    }

    public Integer getPublico() {
        return publico.get();
    }

    public Double getRenda() {
        return renda.get();
    }

    public String getUF() {
        return UF.get();
    }

    public void setTitulo(String valor) {
        titulo.set(valor);
    }

    public void setAno(String year) {
        ano.set(year);
    }

    public void setSalas(Integer sala) {
        salas.set(sala);
    }

    public void setPublico(Integer numero) {
        publico.set(numero);
    }

    public void setRenda(Double rendas) {
        renda.set(rendas);
    }

    public void setUF(String UFs) {
        UF.set(UFs);
    }

    public StringProperty tituloProperty(){return titulo;}
    public StringProperty anoProperty(){return ano;}
    public IntegerProperty salasProperty(){return salas;}
    public IntegerProperty publicoProperty(){return publico;}
    public DoubleProperty rendaProperty(){return renda;}
    public StringProperty UFProperty(){return UF;}
}
