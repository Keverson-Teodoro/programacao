package desafio.chat.desafio.chat.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import desafio.chat.desafio.chat.service.UsuarioService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("/mostrar")
public class UsuarioControler {
    private final UsuarioService usuarioService;

    public UsuarioControler(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    
    

    @GetMapping("/nome")
    public String mostrarNome(Model model) throws IOException {
        
        List<String> listaDePessoas = usuarioService.pessoasComMaisDeVinte();

        model.addAttribute("mostrarNaTela", listaDePessoas);

        return "pessoasMostrar";
    }
    
}
