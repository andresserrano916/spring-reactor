package com.demo.reactor.operador.error;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorOp {

	private static final Logger LOG = LoggerFactory.getLogger(ErrorOp.class);
	
	public void retry(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
				.concatWith(Flux.error(new RuntimeException("Un Error")))
				.retry(1)
				.doOnNext(x -> LOG.info(x.toString()))
				.subscribe();
	}
	
	public void errorReturn(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.concatWith(Flux.error(new RuntimeException("Un Error")))
			.onErrorReturn(new Persona(0, "QWERT", 99))
			.subscribe(x -> LOG.info(x.toString()));
	}
	
	public void errorResume(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.concatWith(Flux.error(new RuntimeException("Un Error")))
			.onErrorResume(e -> Mono.just(new Persona(0, "QWERT", 99)))
			.subscribe(x -> LOG.info(x.toString()));
	}
	
	public void errorMap(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.concatWith(Flux.error(new RuntimeException("Un Error")))
			.onErrorMap(e -> new InterruptedException(e.getMessage()))
			.subscribe(x -> LOG.info(x.toString()));
	}
}
