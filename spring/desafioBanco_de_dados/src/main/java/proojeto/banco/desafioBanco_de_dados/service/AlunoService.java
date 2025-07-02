package proojeto.banco.desafioBanco_de_dados.service;

import java.util.List;

import proojeto.banco.desafioBanco_de_dados.model.Aluno;
import proojeto.banco.desafioBanco_de_dados.repository.UsuarioRepository;

public class AlunoService {
    private final UsuarioRepository usuarioRepository;

    public AlunoService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    

    public List<Aluno> getAll(){
        return usuarioRepository.findAll();
    }


    // public static Aluno adicionarRepositorio(Aluno aluno){
    //     usuarioRepository.adicionar(aluno);

    // }
}
