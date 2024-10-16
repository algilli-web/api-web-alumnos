package com.keepcoding.appweb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepcoding.appweb.entity.Usuario;
import com.keepcoding.appweb.repository.UsuarioRepository;
import com.keepcoding.appweb.service.UsuarioService;



@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listaUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario obtenerUsuarioPorID(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario obtenerUsuarioPorUsernameYPassword(String username, String password) {
		return usuarioRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public boolean existeUsuarioPorUsername(String username) {
		return usuarioRepository.findByUsername(username) != null;
	}
	
	
}