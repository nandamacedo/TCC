/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordeplanilhas.entity;

import java.util.Calendar;

/**
 *
 * @author nanda
 */
public class Data {

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int min;
    private int seg;

    public Data() {
        Calendar data = Calendar.getInstance();
        dia = data.get(Calendar.DAY_OF_MONTH);
        mes = data.get(Calendar.MONTH) + 1;
        ano = data.get(Calendar.YEAR);
        hora = data.get(Calendar.HOUR_OF_DAY);
        min = data.get(Calendar.MINUTE);
        seg = data.get(Calendar.SECOND);
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSeg() {
        return seg;
    }

    public void setSeg(int seg) {
        this.seg = seg;
    }

    public String Data2String() {
        return Integer.toString(dia) +  Integer.toString(mes) 
                + Integer.toString(ano) + "_" + Integer.toString(hora)
                + Integer.toString(min) + Integer.toString(seg);
    }
}
