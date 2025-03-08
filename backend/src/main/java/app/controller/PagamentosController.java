package app.controller;

import app.entity.Pagamentos;
import app.entity.dto.PagamentoDTO;
import app.entity.dto.PagamentoResumoDTO;
import app.service.PagamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/proximo")
    public ResponseEntity<PagamentoDTO> getProximoPagamento(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(service.getProximoPagamento(email));
    }

    @GetMapping("/ultimos")
    public ResponseEntity<List<PagamentoDTO>> getUltimosPagamentos(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(service.getUltimosPagamentos(email));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PagamentoDTO>> getTodosPagamentos(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(service.getTodosPagamentos(email));
    }

    @GetMapping("/total-pagos")
    public ResponseEntity<BigDecimal> getTotalPagamentosPagos(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        BigDecimal total = service.getTotalPagamentosPagosNoMes(email);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total-pendentes")
    public ResponseEntity<BigDecimal> getTotalPagamentosPendentes(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        BigDecimal total = service.getTotalPagamentosPendentesNoMes(email);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<PagamentoResumoDTO>> getPagamentosPendentes(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        List<PagamentoResumoDTO> pagamentos = service.getPagamentosPendentes(email);
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/predio/todos")
    public ResponseEntity<List<Pagamentos>> getTodosPagamentosPredio(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        List<Pagamentos> pagamentos = service.getTodosPagamentosDoPredio(email);
        return ResponseEntity.ok(pagamentos);
    }


}
