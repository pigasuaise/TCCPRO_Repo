package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void registrarUsuario(String nome, String cpf, String dataNasc, String email, String senha) {
        // 1. Hash the password
        String senhaHash = passwordEncoder.encode(senha);
        
        // 2. Create and populate user
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setDataNasc(dataNasc);
        usuario.setEmail(email);
        usuario.setSenhaHash(senhaHash);
        
        // 3. Save to database
        usuarioRepository.save(usuario);
    }
}