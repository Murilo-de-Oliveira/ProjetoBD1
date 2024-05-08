package br.inatel.Model;
import java.util.Date;

public class Pedido {
    private int idPedido;
    private String data;
    private int clienteId;
    private int empregadoId;

    public Pedido(int idPedido, String data, int clienteId, int empregadoId) {
        this.idPedido = idPedido;
        this.data = data;
        this.clienteId = clienteId;
        this.empregadoId = empregadoId;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getData() {
        return data;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getEmpregadoId() {
        return empregadoId;
    }
}
