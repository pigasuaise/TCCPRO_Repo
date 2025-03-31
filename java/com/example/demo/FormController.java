package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FormController {

    BCryptPasswordEncoder PassEncoder = new BCryptPasswordEncoder();

    @GetMapping("/index")
    public String mostrarForm() {
        return "index";
    }

    @GetMapping("/registro")  // Agora aceita GET
    public String registroForm() {
        return "registro";
     }

    @PostMapping("/ProcessLogin")
    public String postMethodName(@RequestParam String user, @RequestParam String password) {
        System.out.println("Usuario recebido: " + user);
        System.out.println("Senha recebida: " + password);
        return "index";
    }

    @PostMapping("/registro")
    public String showRegistro(@RequestParam String Usuario, @RequestParam String CPF, @RequestParam String dataNasc, @RequestParam String Email, @RequestParam String password, @RequestParam String password2, RedirectAttributes redirectAttributes) {
        
        // Encriptação da senha!
        String senhaHash = PassEncoder.encode(password2);

        // Verificando se a senha coincide!
        // Caso negativo vai enviar um GET para o HTML que vai imprimir uma escrita com base
        // na primeira clausa do redirectAttibutes. Tipo uma variavel!
        if(!password.equals(password2)) {
            redirectAttributes.addFlashAttribute("loginErro", "Erro! As senhas não coincidem!");
            // Caso não seja igual, vai redirecionar para a pagina novamente, no caso a de registro
            return "redirect:/registro";
        }
        
        System.out.println("Usuario: " + Usuario);
        System.out.println("CPF: " + CPF);
        System.out.println("Data de Nascimento: " + dataNasc);
        System.out.println("Email: " + Email);
        System.out.println("Hash da senha: " + senhaHash);
        return "sucessoReg";
    }
    
    
    
}
