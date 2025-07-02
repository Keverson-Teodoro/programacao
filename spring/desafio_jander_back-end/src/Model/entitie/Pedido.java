package Model.entitie;

import Model.model_enum.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private long id;
    private Cliente cliente;
    private List<Produto> produtoList;


    private Endereco enderecoDeEntrega;
    private OrderStatus satustusDoPedido;

    public Pedido(List<Produto> lista){
        this.produtoList = lista;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }





    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public OrderStatus getSatustusDoPedido() {
        return satustusDoPedido;
    }

    public void setSatustusDoPedido(OrderStatus satustusDoPedido) {
        this.satustusDoPedido = satustusDoPedido;
    }

    public double valorTotal(){
        double sum = 0;
        for(Produto produto : produtoList){
            sum =+ produto.getPreco();
        }
        return sum;
    }

    public StringBuilder retornarNomes(){
        StringBuilder stringBuilder = new StringBuilder();

       produtoList.forEach(produto -> {
           stringBuilder.append(produto.getName());
       });
       return stringBuilder;

    }

    @Override
    public String toString(){
        return "========================================"
                + "\n"
                + "Dados do cliente: "
                + "\n"
                + "======================================"
                + "\n"
                + "id: "
                + cliente.getId()
                + "\n"
                + "Nome: "
                + cliente.getName()
                + "\n"
                + "Email: "
                + cliente.getEmail()
                + "\n"
                + "=========================================="
                + "\n"
                + "DADOS DO PEDIDO: "
                + "\n"
                + "=========================================="
                + "\n"
                + " - Id: "
                + getId()
                + "\n"
                + " - Endereço: "
                + "\n"
                + " - Cidade: "
                + enderecoDeEntrega.getCidade()
                + "\n"
                + " - Estado: "
                + enderecoDeEntrega.getEstado()
                + "\n"
                + " - Rua: "
                + enderecoDeEntrega.getRua()
                + "\n"
                + " - Numero: "
                + enderecoDeEntrega.getNumero()
                + "\n"
                + " - Status do pedido: "
                + satustusDoPedido
                + "\n"
                + " - Quantidade de itens: "
                + produtoList.size()
                + "\n"
                + " - valor total: R$"
                + valorTotal()
                +"\n"
                + " - Intens pedidos: "
                + retornarNomes();



    }


}
