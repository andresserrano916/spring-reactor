package com.demo.reactor.operador.matematico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;

import reactor.core.publisher.Flux;

public class Matematico {

	private static final Logger LOG = LoggerFactory.getLogger(Matematico.class);
	
	public void average(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.collect(Collectors.averagingInt(Persona::getEdad))
			.subscribe(x -> LOG.info(x.toString()));
	}
	
	public void count(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.count()
			.subscribe(x -> LOG.info("Cantidad: " + x));
	}
	
	public void min(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
			.subscribe(p -> LOG.info(p.get().toString()));
	}
	
	public void sum(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.collect(Collectors.summingInt(Persona::getEdad))
			.subscribe(p -> LOG.info("Suma: " + p));
	}
	
	public void summarizing(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.collect(Collectors.summarizingInt(Persona::getEdad))
			.subscribe(p -> LOG.info("Resumen: " + p));
	}
}
