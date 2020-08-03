package com.demo.reactor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.reactor.model.Persona;
import com.demo.reactor.operador.combinacion.Combinacion;
import com.demo.reactor.operador.condicional.Condicional;
import com.demo.reactor.operador.creacion.Creacion;
import com.demo.reactor.operador.error.ErrorOp;
import com.demo.reactor.operador.filtrado.Filtrado;
import com.demo.reactor.operador.matematico.Matematico;
import com.demo.reactor.operador.transformacion.Transformacion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoReactorApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DemoReactorApplication.class);
	
	public void reactor(){
		Mono.just(new Persona(1, "Demo", 28))
			.doOnNext(p -> {
				//logica adicional
				LOG.info("[Reactor] Persona " + p);
			})
			.subscribe(p -> LOG.info("[Reactor] Persona " + p));
	}
	
	public void mono(){
		Mono.just(new Persona(1, "Demo", 28)).subscribe(p -> LOG.info(p.toString()));
	}
	
	public void flux(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux.fromIterable(personas).subscribe(p -> LOG.info(p.toString()));
	}
	
	public void fluxMono(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Demo", 28));
		personas.add(new Persona(4, "Reactor", 29));
		personas.add(new Persona(5, "Java", 30));
		
		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(lista -> LOG.info(lista.toString()));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//reactor();
		//mono();
		//flux();
		//fluxMono();
		//Creacion app = new Creacion();
		//app.range();
		//app.repeat();
		Transformacion app = new Transformacion();
		//app.map();
		//app.flatMap();
		app.groupBy();
		//Filtrado app = new Filtrado();
		//app.filter();
		//app.distinct();
		//app.take();
		//app.takeLast();
		//app.skip();
		//app.skipLast();
		//Combinacion app = new Combinacion();
		//app.merge();
		//app.zip();
		//app.zipWith();
		//ErrorOp app = new ErrorOp();
		//app.retry();
		//app.errorReturn();
		//app.errorResume();
		//app.errorMap();
		//Condicional app = new Condicional();
		//app.defaultIfEmpty();
		//app.takeUntil();
		//app.timeout();
		//Matematico app = new Matematico();
		//app.average();
		//app.count();
		//app.min();
		///app.sum();
		//app.summarizing();
	}

}
