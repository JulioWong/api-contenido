package com.wong.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wong.entity.Nota;
import com.wong.model.MNota;

@Component("convertidor")
public class Convertidor {
	public List<MNota> convertidorLista(List<Nota> notas){
		List<MNota> mnotas = new ArrayList<>();
		
		for (Nota nota : notas) {
			mnotas.add(new MNota(nota));
		}
		
		return mnotas;
	}
}
