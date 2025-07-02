import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[][] jogoDaVelha = new char[3][3];

        for (int i = 0; i < jogoDaVelha.length; i++) {
            for (int j = 0; j < jogoDaVelha.length; j++) {
                jogoDaVelha[i][j] = ' ';
            }
        }

        // System.out.println("[0] [1] [2]");
        // System.out.println("[3] [4] [5]");
        // System.out.println("[6] [7] [8]");
        
        System.out.println("Escolha a casa: ");


        for (int linha = 0; linha < 3+1; linha++) {

            System.out.println();
            System.out.println("[0] [1] [2]");
            System.out.println("[3] [4] [5]");
            System.out.println("[6] [7] [8]");
            System.out.println();
            
            
            for (int i = 0; i < jogoDaVelha.length; i++) {
                for (int j = 0; j < jogoDaVelha.length; j++) {
                    System.out.print("[" + jogoDaVelha[i][j] + "]");
                }
                System.out.println();
                
            }
            

            System.out.print("Vez de X: ");
            int posicao = input.nextInt();

            if (posicao == 0) {
                jogoDaVelha[0][0] = 'X';
                
            } else if (posicao == 1) {
                jogoDaVelha[0][1] = 'X';
                System.out.println(jogoDaVelha[0][1]);
            } else if (posicao == 2) {
                jogoDaVelha[0][2] = 'X';
            } else if (posicao == 3) {
                jogoDaVelha[1][0] = 'X';
            } else if (posicao == 4) {
                jogoDaVelha[1][1] = 'X';
            } else if (posicao == 5) {
                jogoDaVelha[1][2] = 'X';
            } else if (posicao == 6) {
                jogoDaVelha[2][0] = 'X';
            } else if (posicao == 7) {
                jogoDaVelha[2][1] = 'X';
            } else if (posicao == 8) {
                jogoDaVelha[2][2] = 'X';
            }

            System.out.println();
            System.out.println("[0] [1] [2]");
            System.out.println("[3] [4] [5]");
            System.out.println("[6] [7] [8]");
            System.out.println();

            for (int i = 0; i < jogoDaVelha.length; i++) {
                for (int j = 0; j < jogoDaVelha.length; j++) {
                    System.out.print("[" + jogoDaVelha[i][j] + "]");
                }
                System.out.println();
                
            }


            System.out.print("Vez de O: ");
            posicao = input.nextInt();

            if (posicao == 0) {
                jogoDaVelha[0][0] = 'O';
            } else if (posicao == 1) {
                jogoDaVelha[0][1] = 'O';
            } else if (posicao == 2) {
                jogoDaVelha[0][2] = 'O';
            } else if (posicao == 3) {
                jogoDaVelha[1][0] = 'O';
            } else if (posicao == 4) {
                jogoDaVelha[1][1] = 'O';
            } else if (posicao == 5) {
                jogoDaVelha[1][2] = 'O';
            } else if (posicao == 6) {
                jogoDaVelha[2][0] = 'O';
            } else if (posicao == 7) {
                jogoDaVelha[2][1] = 'O';
            } else if (posicao == 8) {
                jogoDaVelha[2][2] = 'O';
            }
        }

        System.out.println();
            System.out.println("[0] [1] [2]");
            System.out.println("[3] [4] [5]");
            System.out.println("[6] [7] [8]");
            System.out.println();
            
            
            for (int i = 0; i < jogoDaVelha.length; i++) {
                for (int j = 0; j < jogoDaVelha.length; j++) {
                    System.out.print("[" + jogoDaVelha[i][j] + "]");
                }
                System.out.println();
                
            }

        System.out.print("Vez de X: ");
            int posicao = input.nextInt();

            if (posicao == 0) {
                jogoDaVelha[0][0] = 'X';
            } else if (posicao == 1) {
                jogoDaVelha[0][1] = 'X';
            } else if (posicao == 2) {
                jogoDaVelha[0][2] = 'X';
            } else if (posicao == 3) {
                jogoDaVelha[1][0] = 'X';
            } else if (posicao == 4) {
                jogoDaVelha[1][1] = 'X';
            } else if (posicao == 5) {
                jogoDaVelha[1][2] = 'X';
            } else if (posicao == 6) {
                jogoDaVelha[2][0] = 'X';
            } else if (posicao == 7) {
                jogoDaVelha[2][1] = 'X';
            } else if (posicao == 8) {
                jogoDaVelha[2][2] = 'X';
            }

            System.out.println();
            System.out.println("[0] [1] [2]");
            System.out.println("[3] [4] [5]");
            System.out.println("[6] [7] [8]");
            System.out.println();

            for (int i = 0; i < jogoDaVelha.length; i++) {
                for (int j = 0; j < jogoDaVelha.length; j++) {
                    System.out.print("[" + jogoDaVelha[i][j] + "]");
                }
                System.out.println();
                
            }

        
        

        if(jogoDaVelha[0][0] == 'X' && jogoDaVelha[0][1] == 'X' && jogoDaVelha[0][1] == 'X'){
            System.out.println("O X venceu!!!");
        }
        if(jogoDaVelha[1][0] == 'X' && jogoDaVelha[1][1] == 'X' && jogoDaVelha[1][2] == 'X'){
            System.out.println("O X venceu!!!");
        }
        if(jogoDaVelha[2][0] == 'X' && jogoDaVelha[2][1] == 'X' && jogoDaVelha[2][2] == 'X'){
            System.out.println("O X venceu!!!");
        }

        if(jogoDaVelha[0][0] == 'X' && jogoDaVelha[1][1] == 'X' && jogoDaVelha[2][2] == 'X'){
            System.out.println("O X venceu na diagonal!!!");
        }

        if(jogoDaVelha[0][2] == 'X' && jogoDaVelha[1][1] == 'X' && jogoDaVelha[0][0] == 'X'){
            System.out.println("O X venceu na diagonal!!!");
        }

        if(jogoDaVelha[0][2] == 'X' && jogoDaVelha[1][2] == 'X' && jogoDaVelha[2][2] == 'X'){
            System.out.println("O X venceu na ultima fileira");
        }

        if(jogoDaVelha[0][1] == 'X' && jogoDaVelha[1][1] == 'X' && jogoDaVelha[2][1] == 'X'){
            System.out.println("O X venceu na fileira do meio!!!");
        }

        if(jogoDaVelha[0][0] == 'X' && jogoDaVelha[1][0] == 'X' && jogoDaVelha[2][0] == 'X'){
            System.out.println("O X venceu na primeira fileira");
        }

        


        if(jogoDaVelha[0][0] == 'O' && jogoDaVelha[0][1] == 'O' && jogoDaVelha[0][1] == 'O'){
            System.out.println("O venceu!!!");
        }
        if(jogoDaVelha[1][0] == 'O' && jogoDaVelha[1][1] == 'O' && jogoDaVelha[1][2] == 'O'){
            System.out.println("O venceu!!!");
        }
        if(jogoDaVelha[2][0] == 'O' && jogoDaVelha[2][1] == 'O' && jogoDaVelha[2][2] == 'O'){
            System.out.println("O venceu!!!");
        }

        if(jogoDaVelha[0][0] == 'O' && jogoDaVelha[1][1] == 'O' && jogoDaVelha[2][2] == 'O'){
            System.out.println("O venceu na diagonal!!!");
        }

        if(jogoDaVelha[0][2] == 'O' && jogoDaVelha[1][1] == 'O' && jogoDaVelha[0][0] == 'O'){
            System.out.println("O venceu na diagonal!!!");
        }

        if(jogoDaVelha[0][2] == 'O' && jogoDaVelha[1][2] == 'O' && jogoDaVelha[2][2] == 'O'){
            System.out.println("O venceu na ultima fileira");
        }

        if(jogoDaVelha[0][1] == 'O' && jogoDaVelha[1][1] == 'O' && jogoDaVelha[2][1] == 'O'){
            System.out.println("O venceu na fileira do meio!!!");
        }

        if(jogoDaVelha[0][0] == 'O' && jogoDaVelha[1][0] == 'O' && jogoDaVelha[2][0] == 'O'){
            System.out.println("O venceu na primeira fileira");
        }

        
    }    
}
