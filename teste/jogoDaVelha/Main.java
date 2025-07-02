package jogoDaVelha;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.print("Qual o nome do jogador? ");
        input.nextLine();
        String nome = input.nextLine();

        List<String> lista = new ArrayList<>(9);
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");
        lista.add(" ");

        System.out.println(lista);

        List<Integer> posicoesNumeradas = new ArrayList<>();
        posicoesNumeradas.add(0);
        posicoesNumeradas.add(1);
        posicoesNumeradas.add(2);
        posicoesNumeradas.add(3);
        posicoesNumeradas.add(4);
        posicoesNumeradas.add(5);
        posicoesNumeradas.add(6);
        posicoesNumeradas.add(7);
        posicoesNumeradas.add(0);
        

        
 
        


    }





}