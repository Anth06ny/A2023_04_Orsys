package com.amonteiro.a2023_04_orsys;

public class Temp {

    private String toto;

    public Temp(String toto) {
        //assignation
        this.toto = toto;

        //autres
        System.out.println("coucou");
    }

    public Temp() {
        this("bou");
    }
}
