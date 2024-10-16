package com.keepcoding.appweb.service;

import java.util.List;

import com.keepcoding.appweb.entity.Alumno;



public interface AlumnoService {
	
	public List<Alumno> listarAlumnos();
	
	public Alumno guardarAlumno(Alumno alumno);
	
	public Alumno obtenerAlumnoPorId(Long id);
	
	public Alumno actualizarAlumno(Alumno alumno);
	
	public void eliminarAlumno(Long id);

}