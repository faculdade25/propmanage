package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import app.entity.Contrato;
import app.service.ContratoService;



@RestController
@RequestMapping("/api/contratos")
@CrossOrigin("*")
public class ContratoController {


	@Autowired
	private ContratoService service;

	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Contrato con){
		try {
			String message = this.service.save(con);
			return new ResponseEntity<String> (message, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("erro" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestParam Long id, @RequestBody Contrato con){
		try {String message = this.service.update(id, con);
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
	public ResponseEntity<List<Contrato>> listAll (){
		try {List<Contrato> lista = this.service.listAll();
		return new ResponseEntity<> (lista, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		}
	}

}
