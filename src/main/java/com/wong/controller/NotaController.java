package com.wong.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wong.entity.Nota;
import com.wong.model.MNota;
import com.wong.service.NotaService;

@RestController
@RequestMapping("/v1")
public class NotaController {
	
	@Autowired
	@Qualifier("servicio")
	NotaService service;
	
	@PutMapping("/nota")
	public boolean agregarNota(@RequestBody @Valid Nota nota) {
		return service.crear(nota);
	}
	
	@PostMapping("/nota")
	public boolean actualizarNota(@RequestBody @Valid Nota nota) {
		return service.actualizar(nota);
	}
	
	@DeleteMapping("/nota/{id}/{nombre}")
	public boolean borrarNota(@PathVariable("id") long id,
			@PathVariable("nombre") String nombre) {
		return service.borrar(nombre, id);
	}
	
	@GetMapping("/notas")
	public List<MNota> obtenerNotas(Pageable pageable) {
		return service.obtenerPorPaginacion(pageable);
	}
}
