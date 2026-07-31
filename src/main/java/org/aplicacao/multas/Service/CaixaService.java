package org.aplicacao.multas.Service;

import org.aplicacao.multas.Controller.CriarCaixaDTO;
import org.aplicacao.multas.Entity.Caixa;
import org.aplicacao.multas.Repository.CaixaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    CaixaRepository caixaRepository;


    public CaixaService(CaixaRepository caixaRepository ) {
        this.caixaRepository = caixaRepository;

    }

    public Integer criarCaixa(CriarCaixaDTO caixadto) {

        if(caixaRepository.existsByNome(caixadto.nome())){
            System.out.println("Essa caixa já existe! ");

            throw  new RuntimeException("Caixa invalida");
        }
        var entity = new Caixa();

        entity.setNome(caixadto.nome());

        var caixa = caixaRepository.save(entity);
        return caixa.getId();



    }
    public void excluirCaixa (String nome){
        var deletarCaixa = caixaRepository.findByNome(nome);

        if (deletarCaixa.isPresent()){
            caixaRepository.delete(deletarCaixa.get());
        }
        else{
            throw new RuntimeException("Está caixa não existe !");
        }

    }

    public Optional <Caixa> findByNome(String nome) {



        return  caixaRepository.findByNome(nome.trim().replaceAll(" ", " "));
    }

    public List<Caixa> listarCaixas() {
        return caixaRepository.findAll();
    }



}

