package com.keepcoding.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keepcoding.appweb.entity.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
	Usuario findByUsernameAndPassword(String username, String password);
	

}