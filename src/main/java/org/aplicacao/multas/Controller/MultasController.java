package org.aplicacao.multas.Controller;

import org.aplicacao.multas.Entity.ProcessoMulta;
import org.aplicacao.multas.Repository.CaixaRepository;
import org.aplicacao.multas.Repository.MultasRepository;
import org.aplicacao.multas.Service.CaixaService;
import org.aplicacao.multas.Service.MultasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/multas")
public class MultasController {


    private  final MultasRepository multasRepository;
    private final MultasService multasService;


    public MultasController (MultasRepository multasRepository, MultasService multasService ) {
        this.multasRepository = multasRepository;
        this.multasService = multasService;

    }

   @PostMapping
    public ResponseEntity<?> criarProcessoMulta(@RequestBody CriarMultaDTO criarMultaDTO) {
        var retornarMultaSalva = multasService.CriarNovoProcessoMulta(criarMultaDTO);
        if (retornarMultaSalva == null) {
            throw new RuntimeException("Error ,  o Processo não foi criado não foi craida");
        }
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/{identificador}")
   public ResponseEntity<ProcessoMulta> buscarProcessoMulta(@PathVariable ("identificador") String identificador){

        var buscarMulta = multasRepository.findByIdentificador(identificador);

        if(buscarMulta.isPresent()){
            return ResponseEntity.ok(buscarMulta.get());
        }
        else{
            return  ResponseEntity.notFound().build();
        }

   }
   @GetMapping
   public ResponseEntity<List<ProcessoMulta>> listProcessoMulta(){return ResponseEntity.ok(multasRepository.findAll());}


   @DeleteMapping("/{identificador}")
    public ResponseEntity<?> deletarProcessoMulta(@PathVariable("identificador") String identificador ){

        multasService.excluirProcessoMulta(identificador);
        return ResponseEntity.noContent().build();
   }
   @PatchMapping("/{identificador}")
    public ResponseEntity <Void> alterarProcessoMulta (@PathVariable("identificador") String identificador ,
                                                       @RequestBody AlterarProcessoMulta alterarProcessoMulta){
        multasService.alterarProcessoMulta(identificador, alterarProcessoMulta);
        return ResponseEntity.noContent().build();

   }

   @PatchMapping("/{identificador}/caixa/{nomeCaixa}")
   public   ResponseEntity <Void> arquivarEmCaixa(
           @PathVariable String identificador,
           @PathVariable String nomeCaixa ){
        multasService.arquivarEmCaixa(identificador,nomeCaixa);
        return ResponseEntity.noContent().build();
   }





}
