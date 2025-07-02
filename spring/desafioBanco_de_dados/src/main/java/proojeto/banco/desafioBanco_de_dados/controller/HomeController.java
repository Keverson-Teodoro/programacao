package proojeto.banco.desafioBanco_de_dados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;



@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("msnBemVindo", "Bem vindo á biblioteca");
        return "minhapagina";
        
    }
    
}
