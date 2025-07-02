package proojeto.banco.desafioBanco_de_dados.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proojeto.banco.desafioBanco_de_dados.model.Aluno;
import proojeto.banco.desafioBanco_de_dados.repository.UsuarioRepository;
import org.springframework.ui.Model;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/usuarios")
public class AlunoController {

    private final UsuarioRepository usuarioRepository;


    AlunoController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @GetMapping("/aluno")
    public String listar(Model model){

        System.out.println("Recuperando alunos");
        List<Aluno> alunos = usuarioRepository.findAll();

        List<String> nomeAlunos = new ArrayList<String>();

        for (Aluno aluno : alunos) {
            nomeAlunos.add(aluno.getNome());
        }


        model.addAttribute("retornar", nomeAlunos);

        // List<Aluno> pessoas = new ArrayList<>();
        // for (Aluno aluno : usuarioRepository.findAll()){
        //     pessoas.add(aluno);
            
        // }
        return "alunos";
        

        
    }
    

    
}
