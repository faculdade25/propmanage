package app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Repository.AnuncioRepository;
import app.Repository.LogRepository;
import app.entity.Anuncio;
import app.entity.Log;
import app.service.AnuncioService;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin("*")
public class AnuncioController {

	@Autowired
	private AnuncioService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Anuncio anuncio){
		try {
			String message = this.service.save(anuncio);
			return new ResponseEntity<String> (message, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("erro" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestParam Long id, @RequestBody Anuncio anuncio){
		try {String message = this.service.update(id, anuncio);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>( "erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete, {id}")
	public ResponseEntity<String> delete (@RequestParam Long id){
		try {String message = this.service.delete(id);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>( "erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Anuncio>> listAll (){
		try {List<Anuncio> lista = this.service.listAll();
		return new ResponseEntity<> (lista, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		}
	}
	
}
