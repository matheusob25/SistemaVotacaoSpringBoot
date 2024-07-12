package com.matheusob25.sistemavotacaospringboot.entities.enums;

public enum Cargo {
    PRESIDENTE(1),
    GOVERNADOR(2),
    PREFEITO(3),
    VEREADOR(4);
    private int codigoDoCargo;

    Cargo(int n){
        this.codigoDoCargo = n;
    }
    public int getCodigoDoCargo() {
        return codigoDoCargo;
    }
    public static Cargo valueOf(int codigoDoCargo) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.getCodigoDoCargo() == codigoDoCargo) {
                return cargo;
            }
        }
        throw new IllegalArgumentException("Código de cargo inválido");
    }
}
