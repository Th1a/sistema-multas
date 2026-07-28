package org.aplicacao.multas.Controller;


import org.aplicacao.multas.Entity.Caixa;
import org.aplicacao.multas.Repository.CaixaRepository;
import org.aplicacao.multas.Service.CaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixas")

public class CaixaController {

    private CaixaRepository caixaRepository;
    private CaixaService caixaService;

    public CaixaController (CaixaRepository caixaRepository , CaixaService caixaService){
        this.caixaRepository = caixaRepository;
        this.caixaService = caixaService;

    }

    @PostMapping
    public ResponseEntity<?> CriarCaixa(@RequestBody CriarCaixaDTO caixadto){
        var salvarCaixas = caixaService.criarCaixa(caixadto);
        if(salvarCaixas == null){
            throw new RuntimeException("Erro ao criar caixa");
        }
        return ResponseEntity.noContent().build();
    }

@GetMapping
    public ResponseEntity<List<Caixa>> ListarCaixas(){


        return ResponseEntity.ok(caixaRepository.findAll());
}

@DeleteMapping("/{nome}")
    public ResponseEntity<?> deletarCaixa (@PathVariable ("nome") String nome) {

    caixaService.excluirCaixa(nome);
    return ResponseEntity.noContent().build();
}



}
