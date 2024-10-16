package com.keepcoding.appweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keepcoding.appweb.entity.Alumno;
import com.keepcoding.appweb.service.AlumnoService;



@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping({"/alumnos"})
	public String index(Model modelo) {
		modelo.addAttribute("alumnos", alumnoService.listarAlumnos());
		return "alumno";
	}
	
	@GetMapping("/alumnos/new")
	public String newAlumnoForm(Model modelo) {
		Alumno alumno = new Alumno();
		modelo.addAttribute("alumno", alumno);
		return "generar_alumno";
	}
	
	@PostMapping("/alumno")
	public String saveAlumno(@ModelAttribute("alumno") Alumno alumno) {
		alumnoService.guardarAlumno(alumno);
		return "redirect:/alumnos";
	}
	
	@GetMapping("/alumno/editar/{id}")
	public String updateAlumnoForm(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("alumno_update", alumnoService.obtenerAlumnoPorId(id));
		return "modificar_alumno";
	}
	
	@PostMapping("/alumno/{id}")
	public String updateAlumno(@PathVariable Long id, @ModelAttribute("alumno_update") Alumno alumno) {
	 Alumno alumnoExistente = alumnoService.obtenerAlumnoPorId(id);
	 alumnoExistente.setId(id);
	 alumnoExistente.setNombre(alumno.getNombre());
	 alumnoExistente.setApellido(alumno.getApellido());
	 alumnoExistente.setTelefono(alumno.getTelefono());
	 alumnoExistente.setEmail(alumno.getEmail());
	 alumnoExistente.setDni(alumno.getDni());
	 alumnoExistente.setFechaNac(alumno.getFechaNac());
	 
	 alumnoService.actualizarAlumno(alumnoExistente);
	 return "redirect:/alumnos";
	}
	
	@GetMapping("/alumno/delete/{id}")
	public String deleteAlumno(@PathVariable Long id) {
		alumnoService.eliminarAlumno(id);
		return "redirect:/alumnos";
		
	}

	

}