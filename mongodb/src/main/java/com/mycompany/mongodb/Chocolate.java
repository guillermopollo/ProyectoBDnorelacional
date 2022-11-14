/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mongodb;

/**
 *
 * @author willy
 */
public class Chocolate {
   String cod; //Código del chocolate
    String nombre; //Nombre del chocoalte
     String tipoChoco; //Tipo de chocolate (Blanco, Negro, Puro...)
     String tipoAlme; //Tipo de almendar que lleva (if null = "Ninguno")
     String tipoRell; //Tipo de relleno que lleva
    String desc; //Descripción del chocolate
    public Chocolate(String cod, String nombre, String tipoChoco, String tipoAlme, String tipoRell, String desc) {
        this.cod = cod;
        this.nombre = nombre;
        this.tipoChoco = tipoChoco;
        this.tipoAlme = tipoAlme;
        this.tipoRell = tipoRell;
        this.desc = desc;
    }
    public Chocolate(){}

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoChoco() {
        return tipoChoco;
    }

    public void setTipoChoco(String tipoChoco) {
        this.tipoChoco = tipoChoco;
    }

    public String getTipoAlme() {
        return tipoAlme;
    }

    public void setTipoAlme(String tipoAlme) {
        this.tipoAlme = tipoAlme;
    }

    public String getTipoRell() {
        return tipoRell;
    }

    public void setTipoRell(String tipoRell) {
        this.tipoRell = tipoRell;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
  


}
