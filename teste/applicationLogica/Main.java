package API_ler_arquivo_Json;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import API_ler_arquivo_Json.model.entities.Usuario;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/home/youx/Documentos/programacao/teste/applicationLogica/usuariosReduzidos.json");

        List<String[]> pessoasDoArquivo = new ArrayList<>();
        List<String> pessoasSemJson = new ArrayList();

        List<Map<String, Object>> users = new ArrayList<>();
        
        Scanner scam = null;

        try{
            
            scam = new Scanner(file);

            while(scam.hasNextLine()){
                

                String verValor = (String) scam.nextLine();
                String valorSemNada = verValor.replace("{}", "");
                String valorSemOutroParentese = valorSemNada.replace("{", "");
                String maisUmValorSemParentese = valorSemOutroParentese.replace("}", "");
                String outroooo = maisUmValorSemParentese.replace("]", "");
                String outroSemVirgula = outroooo.replace(",", "");
                String outroSemEspaco = outroSemVirgula.replace(",", "");
                String valorSemNome = outroSemEspaco.replace("nome", "");
                String valorSemIdade = valorSemNome.replace("idade", "");
                String valorSemCidade = valorSemIdade.replace("Cidade", "");
                String valorSemDoisPontos = valorSemCidade.replace(":", "");
                String valorSemAspas = valorSemDoisPontos.replace("\"", "");
                String valorSemCminusculo = valorSemAspas.replace("c", "");
                String valorSemMinimoDeEspaco = valorSemCminusculo.stripLeading();

                pessoasDoArquivo.add(valorSemMinimoDeEspaco.split("\\R{2,}"));
                
    
            }
            

            for(String[] bloco : pessoasDoArquivo){ 
                for(String linha : bloco){

                    if(linha != null && !linha.isBlank()){
                        pessoasSemJson.add(linha);   
                        
                    }
  
                }
                
            }

            
            for (int posicao = 0; posicao < pessoasSemJson.size(); posicao += 3) {
                if(posicao + 2 < pessoasSemJson.size()){

                    String nome = pessoasSemJson.get(posicao +1);
                    int idade = Integer.parseInt(pessoasSemJson.get(posicao +2));
                    String cidade = pessoasSemJson.get(posicao +3);

                    users.add(criarPessoa(nome, idade, cidade));
                    // System.out.println("Nome: " + nome + " - idade: " + idade + " - cidade: " + cidade);
                }
                
            }

            for(Map<String, Object> usuario : users){
                System.out.println(usuario);
            }
            

        }

        catch (FileNotFoundException as){
            System.out.println("Error opening file: ");
        }

        finally{
            if(scam == null){
                scam.close();
                System.out.println("Não foi possivel ler");
            
            }
        }

       
}



    public static Map<String, Object> criarPessoa(String nome, int idade, String cidade){
        Map<String, Object> pessoa = new HashMap<>();

        pessoa.put("nome", nome);
        pessoa.put("idade", idade);
        pessoa.put("cidade", cidade);

        return pessoa;
        
        

    }


    public static Boolean ehNumero(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    
    
}
