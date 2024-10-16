package com.keepcoding.appweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepcoding.appweb.entity.Usuario;
import com.keepcoding.appweb.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorUsernameYPassword(username, password);
        if (usuario != null) {
        	System.out.print("usuario activo" + usuario.getActivo());
        	if(usuario.getActivo() == false ) {
            	model.addAttribute("error", "Usuario inactivo");
            	return "login";
        	}
            session.setAttribute("usuario", usuario);
            return "redirect:/alumnos";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "registro";
    }
    
    

    @PostMapping("/registro")
    public String registroUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            if (usuarioService.existeUsuarioPorUsername(usuario.getUsername())) {
                model.addAttribute("error", "Error al registrar, usuario existente");
                return "registro";
            }
            usuario.setActivo(true);
            usuarioService.guardarUsuario(usuario);
            return "redirect:/usuario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar, usuario existente ");
            return "registro";
        }
    }
    
    @GetMapping("/usuario")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listaUsuarios());
        return "usuario";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
}