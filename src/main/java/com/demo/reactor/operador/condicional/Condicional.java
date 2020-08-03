package com.demo.reactor.operador.condicional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Condicional {

	private static final Logger LOG = LoggerFactory.getLogger(Condicional.class);
	
	public void defaultIfEmpty(){
		//Mono.empty()
		Flux.empty()
			.defaultIfEmpty(new Persona(0, "QWERT", 99))
			.subscribe(x -> LOG.info(x.toString()));
		
	}
	
	public void takeUntil(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.takeUntil(p -> p.getEdad() > 28)
			.subscribe(x -> LOG.info(x.toString()));
	}
	
	public void timeout() throws InterruptedException{
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.delayElements(Duration.ofSeconds(1))
			.timeout(Duration.ofSeconds(2))
			.subscribe(x -> LOG.info(x.toString()));
		
		Thread.sleep(10000);
	}
}
