package com.demo.reactor.operador.creacion;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Creacion {

	private static final Logger LOG = LoggerFactory.getLogger(Creacion.class);
	
	public void justFrom(){
		Mono.just(new Persona(1, "Demo", 28));
	}
	
	public void empty(){
		Mono.empty();
		Flux.empty();
	}
	
	public void range(){
		Flux.range(0, 3)
			.doOnNext(i -> LOG.info("i: " + i))
			.subscribe();
	}
	
	public void repeat(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
//		Flux.fromIterable(personas)
//			.repeat(3)
//			.subscribe(p -> LOG.info(p.toString()));
		
		Mono.just(new Persona(1, "Demo", 28))
			.repeat(3)
			.subscribe(p -> LOG.info(p.toString()));
	}
}
