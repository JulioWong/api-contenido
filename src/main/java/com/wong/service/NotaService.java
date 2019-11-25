package com.wong.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wong.converter.Convertidor;
import com.wong.entity.Nota;
import com.wong.model.MNota;
import com.wong.repository.NotaRepository;

@Service("servicio")
public class NotaService {
	@Autowired
	@Qualifier("repositorio")
	private NotaRepository repositorio;
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	private static final Log logger = LogFactory.getLog(NotaService.class);
	
	
	public boolean crear(Nota nota) {
		logger.info("Creando nota");
		try {
			repositorio.save(nota);
			logger.info("Nota creada");
			return true;
			
		}catch (Exception e) {
			logger.info("Hubo un error");
			return false;
		}
	}
	
	public boolean actualizar(Nota nota) {
		try {
			repositorio.save(nota);
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean borrar(String nombre, long id) {
		try {
			Nota nota = repositorio.findByNombreAndId(nombre, id);
			repositorio.delete(nota);
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	
	public List<MNota> obtener() {
		return convertidor.convertidorLista(repositorio.findAll());
	}
	
	public MNota obtenerPorNombreTitulo(String nombre, String titulo) {
		return new MNota(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	
	public List<MNota> obtenerTitulo(String titulo) {
		return convertidor.convertidorLista(repositorio.findByTitulo(titulo));
	}
	
	
	public List<MNota> obtenerPorPaginacion(Pageable pageable) {
		return convertidor.convertidorLista(repositorio.findAll(pageable).getContent());
	}
	
}
