package desafio.chat.desafio.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import desafio.chat.desafio.chat.model.Pessoa;
import desafio.chat.desafio.chat.repository.UsuarioRepository;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class UsuarioService {
    

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }




    public void escritor() throws IOException{

        FileWriter fileWriter = new FileWriter("/home/youx/Documentos/programacao/spring/desafio.chat/src/main/java/desafio/chat/arquivos/usuariosDoBanco.json");

        BufferedWriter escritor = new BufferedWriter(fileWriter);

        List<Pessoa> novosUsuarios = usuarioRepository.findAll();
        for(Pessoa usuario : novosUsuarios){
            escritor.write(usuario.toString());

            
        }


    }
    

    public List<String> pessoasComMaisDeVinte() throws FileNotFoundException{
        int numeroDePessoasMais20 = 0;
        Map<String, Object> pessoasDicionario = new HashMap<>();

        File file = new File("/home/youx/Documentos/programacao/spring/desafio.chat/src/main/java/desafio/chat/arquivos/usuarios_100k.json");

        List<Object> pessoasDoArquivo = new ArrayList<>();
        Scanner scam = null;
        String nome = "";
        int idade = 0;
        String cidade = "";

        while(scam.hasNextLine()){

            pessoasDoArquivo.add(scam.nextLine());
            if(scam.nextLine() == "nome"){
                nome = scam.nextLine();
            }
            

        }




        List<String> usuarioss = new ArrayList<String>();

        for(Object usuario : pessoasDoArquivo){

    

            String usuarioEmString = usuario.toString();
            usuarioss.add(usuarioEmString);
        }

        return usuarioss;
        


    }
    

    

    

    public List<String> listaDeUsuario(){
        
        List<String> nomeDosUsuarios = new ArrayList<>();
        List<Pessoa> usuarios = usuarioRepository.findAll();

        for(Pessoa usuario : usuarios){

            nomeDosUsuarios.add(usuario.getNome());
        }

        return nomeDosUsuarios;
    }

   

}
