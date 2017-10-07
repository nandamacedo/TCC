/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author nanda
 */
public class Atores {

    private final StringProperty ator;
    private final StringProperty papel;
    private final StringProperty sigla;

    public Atores(String ator, String papel, String sigla){
        this.ator = new SimpleStringProperty(ator);
        this.papel = new SimpleStringProperty(papel);
        this.sigla = new SimpleStringProperty(sigla);
    }
    public String getAtor() {
        return ator.get();
    }
    public String getPapel() {
        return papel.get();
    }
    public String getSigla() {
        return sigla.get();
    }
    public void setAtor(String valor) {
        ator.set(valor);
    }
    public void setPapel(String valor) {
        papel.set(valor);
    }
    public void setSigla(String valor) {
        sigla.set(valor);
    }
    public StringProperty atorProperty(){return ator;}
    public StringProperty papelProperty(){return papel;}
    public StringProperty siglaProperty(){return sigla;}
}
