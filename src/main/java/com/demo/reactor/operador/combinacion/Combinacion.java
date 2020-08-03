package com.demo.reactor.operador.combinacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.reactor.model.Persona;
import com.demo.reactor.model.Venta;

import reactor.core.publisher.Flux;

public class Combinacion {

	private static final Logger LOG = LoggerFactory.getLogger(Combinacion.class);
	
	public void merge(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		List<Persona> personas2 = new ArrayList<>();
		personas2.add(new Persona(2, "Andres", 28));
		personas2.add(new Persona(3, "Code", 29));
		personas2.add(new Persona(6, "Spring", 30));
		
		List<Venta> ventas = new ArrayList<>();
		ventas.add(new Venta(1, LocalDateTime.now()));
		
		Flux<Persona> fx = Flux.fromIterable(personas);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);
		Flux<Venta> fx3 = Flux.fromIterable(ventas);
		
		Flux.merge(fx, fx2, fx3)
			.subscribe(p -> LOG.info(p.toString()));
	}

	public void zip(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		List<Persona> personas2 = new ArrayList<>();
		personas2.add(new Persona(2, "Andres", 28));
		personas2.add(new Persona(3, "Code", 29));
		personas2.add(new Persona(6, "Spring", 30));
		
		List<Venta> ventas = new ArrayList<>();
		ventas.add(new Venta(1, LocalDateTime.now()));
		ventas.add(new Venta(2, LocalDateTime.now()));
		
		Flux<Persona> fx = Flux.fromIterable(personas);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);
		Flux<Venta> fx3 = Flux.fromIterable(ventas);
		
		Flux.zip(fx, fx3, (p1, p2) -> {
				LOG.info(p1.toString());
				LOG.info(p2.toString());
				return String.format("Flux1: %s, Flux3: %s", p1, p2);
			})
			.subscribe(x -> LOG.info(x));
		
		/*Flux.zip(fx, fx2, fx3)
			.subscribe(x -> LOG.info(x.toString()));*/
	}
	
	public void zipWith(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		List<Persona> personas2 = new ArrayList<>();
		personas2.add(new Persona(2, "Andres", 28));
		personas2.add(new Persona(3, "Code", 29));
		personas2.add(new Persona(6, "Spring", 30));
		
		List<Venta> ventas = new ArrayList<>();
		ventas.add(new Venta(1, LocalDateTime.now()));
		
		Flux<Persona> fx = Flux.fromIterable(personas);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);
		Flux<Venta> fx3 = Flux.fromIterable(ventas);
		
		/*fx.zipWith(fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2))
			.subscribe(x -> LOG.info(x.toString()));*/
		
		fx.zipWith(fx3, (p1, v1) -> String.format("Flux1: %s, Flux3: %s", p1, v1))
		.subscribe(x -> LOG.info(x.toString()));
	}
}
