package app.controller;

import app.entity.Pagamentos;
import app.service.PagamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class PagamentosController {

    @Autowired
    private PagamentosService service;

    @PostMapping("/save")
    public ResponseEntity<String> save (@RequestBody Pagamentos pagamentos){

        try {
            String message = this.service.save(pagamentos);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("erro" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update (@RequestParam Long id, @RequestBody Pagamentos pagamentos){

        try {String message = this.service.update(id, pagamentos);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete (@RequestParam Long id){
        try {
            String message = this.service.delete(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<String>("erro"+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Pagamentos>> listAll(){
        try{
            List<Pagamentos> lista = this.service.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
