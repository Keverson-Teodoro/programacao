import Model.entitie.Cliente;
import Model.entitie.Endereco;
import Model.entitie.Pedido;
import Model.entitie.Produto;
import Model.model_enum.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<Produto> listaDeProdutos = new ArrayList<>();
        List<Pedido> listaDePedidos = new ArrayList<>();

        // instanciando os clientes
        Cliente cliente1 = new Cliente(1, "Pedro Gamer", "pedrogamer@gmail.com");
        Cliente cliente2 = new Cliente(2, "Gabriel Gomes", "gabrielgomes@gmail.com");
        Cliente cliente3 = new Cliente(3, "Maria Peixoto", "mariapeixoto@gmail.com");
        Cliente cliente4 = new Cliente(4, "Lucia Gaio", "lucia244@gmail.com");
        Cliente cliente5 = new Cliente(5, "Gustavo Pereira", "gustavopereira4@gmail.com");
        Cliente cliente6 = new Cliente(6, "Paulo Rocha", "paulorocha56@gmail.com");


        // instanciando os produtos
        Produto albumFrankOcean = new Produto(1, "Album Blonde", 200);
        Produto albumBillieEllish = new Produto(2, "Album Hit me hard and soft", 150);
        Produto vinil = new Produto(3, "Vinil", 400);
        Produto agulhaVinil = new Produto(4, "Agulha vinil", 50);
        Produto albumTyler = new Produto(5, "Album IGOTR", 400);
        Produto albumSteveLacy = new Produto(6, "Album the lo-fis", 300);


        // colocando os produtos na lista de produtos;
        listaDeProdutos.add(albumFrankOcean);
        listaDeProdutos.add(albumBillieEllish);
        listaDeProdutos.add(vinil);
        listaDeProdutos.add(agulhaVinil);
        listaDeProdutos.add(albumTyler);
        listaDeProdutos.add(albumSteveLacy);


        // criando uma lista de produtos do pedido 1
        List<Produto> listaDeProdutosDoPedido1 = new ArrayList<>();
        listaDeProdutosDoPedido1.add(agulhaVinil);
        listaDeProdutosDoPedido1.add(vinil);
        listaDeProdutosDoPedido1.add(albumFrankOcean);
        Pedido pedido1 = new Pedido(listaDeProdutosDoPedido1);

        pedido1.setCliente(cliente1);
        pedido1.setId(1);
        pedido1.setSatustusDoPedido(OrderStatus.EM_ANDAMENTO);
        Endereco endereco1 = new Endereco("Magalhães", "250A", "São Paulo", "MG");
        pedido1.setEnderecoDeEntrega(endereco1);

        listaDePedidos.add(pedido1);


        while(true){
            System.out.println("  MENU: ");
            System.out.println("[1] buscar pedido por id");
            System.out.println("[2] ver valor total do pedido");
            System.out.println("[3] buscar pedido por status");
            System.out.println("[4] sair");
            System.out.print("Digite: ");
            int escolhaMenu = input.nextInt();

            if(escolhaMenu == 1){
                System.out.print("Digite o id: ");
                long busca = input.nextLong();
                Pedido pedidoBuscado = null;

                for(Pedido pedido : listaDePedidos){
                    if(pedido.getId() == busca){
                        pedidoBuscado = pedido;
                        System.out.println(pedidoBuscado);
                    }
                    else if(pedidoBuscado == null){
                        System.out.println("Pedido não encontrado!! ");
                    }
                }

                // tratar inexistente
            }
            else if(escolhaMenu == 2){
                System.out.print("Digite o id para ver o total do pedido: ");
                long busca = input.nextLong();
                Pedido pedidoBuscado = null;

                for(Pedido pedido : listaDePedidos){
                    if(pedido.getId() == busca){
                        System.out.println(pedido.valorTotal());
                    }
                }
            }
            else if(escolhaMenu == 3){
                System.out.print("Digite o status: ");
                input.nextLine();
                String statusDigitado = String.valueOf(OrderStatus.valueOf(input.nextLine()));
                OrderStatus status = OrderStatus.valueOf(statusDigitado);
                Pedido pedidoBuscado = null;

                for(Pedido pedido : listaDePedidos){
                    if(pedido.getSatustusDoPedido() == status){
                        System.out.println(pedido);
                    }
                }

            }
            else if(escolhaMenu == 4){
                break;
            }





        }

//        System.out.print("Bsuque o pedido por id: ");
//        long busca = input.nextLong();
//        Pedido pedidoBuscado = null;
//
//        for(Pedido pedido : listaDePedidos){
//            if(pedido.getId() == busca){
//                pedidoBuscado = pedido;
//            }
//        }

//        System.out.println(pedidoBuscado);











    }
}