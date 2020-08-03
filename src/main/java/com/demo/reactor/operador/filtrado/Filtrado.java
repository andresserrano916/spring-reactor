package com.demo.reactor.operador.filtrado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;

import reactor.core.publisher.Flux;

public class Filtrado {

	private static final Logger LOG = LoggerFactory.getLogger(Filtrado.class);
	
	public void filter(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.filter(p -> p.getEdad() > 28)
			.subscribe(p -> LOG.info(p.toString()));
	}

	public void distinct(){
		Flux.fromIterable(Arrays.asList(1,1,2,2))
			.distinct()
			.subscribe(p -> LOG.info(p.toString()));
		
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(1, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.distinct()
			.subscribe(p -> LOG.info(p.toString()));
	}
	
	public void take(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(2, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.take(2)
			.subscribe(p -> LOG.info(p.toString()));
	}
	
	public void takeLast(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(2, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.takeLast(2)
			.subscribe(p -> LOG.info(p.toString()));
	}
	
	public void skip(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(2, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.skip(1)
			.subscribe(p -> LOG.info(p.toString()));
	}
	
	public void skipLast(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(2, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.skipLast(1)
			.subscribe(p -> LOG.info(p.toString()));
	}
}
