package com.wong.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.wong.entity.Nota;

@Controller
@RequestMapping("/nota")
public class ClienteRest {

	private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWxpbyJ9.6gAhvGZUzHQX3-GcYR38WfKfMCoUjKjcBcTzpWSuXCwaQLqmTHOxSbgw1WR_cR0TR_K7VNRiDSs4Jcmk6kPodA";
	
	@GetMapping("/all")
	public ModelAndView devolvertodos() {
		ModelAndView mav = new ModelAndView("template");
		
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Nota[]> notasEntity = rest.exchange("http://localhost:8080/v1/notas", HttpMethod.GET, entity, Nota[].class);
		
		Nota[] notas = notasEntity.getBody();
		mav.addObject("notas", notas);
		return mav;
	}
}
