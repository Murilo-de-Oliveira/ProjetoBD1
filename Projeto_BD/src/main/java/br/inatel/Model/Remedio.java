package br.inatel.Model;

import java.util.Date;

public class Remedio {
    private int id;
    private String nomeRemedio;
    private String fabricante;
    private String tipoRemedio;
    private int estoque;
    private String validade;
    private double preco;

    public Remedio(int id, String nomeRemedio, String fabricante, String tipoRemedio, int estoque, String validade, double preco) {
        this.id = id;
        this.nomeRemedio = nomeRemedio;
        this.fabricante = fabricante;
        this.tipoRemedio = tipoRemedio;
        this.estoque = estoque;
        this.validade = validade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getTipoRemedio() {
        return tipoRemedio;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getValidade() {
        return validade;
    }

    public double getPreco() {
        return preco;
    }
}
