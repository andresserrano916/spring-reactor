package com.demo.reactor.operador.transformacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Transformacion {

	private static final Logger LOG = LoggerFactory.getLogger(Transformacion.class);
	
	public void map(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.map(p -> {
				p.setEdad(p.getEdad() + 10);
				return p;
			})
			.subscribe(p -> LOG.info(p.toString()));
		
//		Flux<Integer> fx = Flux.range(0, 10);
//		Flux<Integer> fx2 = fx.map(x -> x + 10);
//		fx2.subscribe(x -> LOG.info("X: " + x));
	}
	
	public void flatMap(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.flatMap(p -> {
				p.setEdad(p.getEdad() + 10);
				return Mono.just(p);
			})
			.map(p -> {
				p.setNombres(p.getNombres() + " Curso");
				return p;
			})
			.subscribe(p -> LOG.info(p.toString()));
	}
	
	public void groupBy() throws InterruptedException{
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(1, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas)
			.groupBy(Persona::getIdPersona)
			.flatMap(idFlux -> idFlux.collectList())
			.subscribe(x -> LOG.info(x.toString()));
		
		
		Mono<String> p1 = Mono.just("prueba1"+ new Date());
		
		
		Mono<String> p2 = Mono.defer(() -> {
			return Mono.just("prueba2"+ new Date());
		});
		
		p1.subscribe(System.out::println);
		
		Thread.sleep(5000);
		p1.subscribe(System.out::println);
		
		Thread.sleep(5000);
		
		p2.subscribe(System.out::println);
		Thread.sleep(5000);
		p2.subscribe(System.out::println);
	}
	
}
