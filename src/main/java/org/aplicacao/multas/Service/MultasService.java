package org.aplicacao.multas.Service;


import jakarta.transaction.Transactional;
import org.aplicacao.multas.Controller.AlterarProcessoMulta;
import org.aplicacao.multas.Controller.CriarMultaDTO;
import org.aplicacao.multas.Entity.Caixa;
import org.aplicacao.multas.Entity.ProcessoMulta;
import org.aplicacao.multas.Entity.StatusProcesso;
import org.aplicacao.multas.Repository.CaixaRepository;
import org.aplicacao.multas.Repository.MultasRepository;
import org.aplicacao.multas.Repository.StatusRepository;

import java.awt.font.NumericShaper;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class MultasService {
    CaixaRepository caixaRepository;
    MultasRepository multasRepository;
    StatusRepository statusRepository;

    public MultasService(MultasRepository multasRepository, StatusRepository statusRepository, CaixaRepository caixaRepository) {
        this.multasRepository = multasRepository;
        this.statusRepository = statusRepository;
        this.caixaRepository = caixaRepository;
    }

    public Long CriarNovoProcessoMulta(CriarMultaDTO criarMultaDTO) {

        if (multasRepository.existsByIdentificador(criarMultaDTO.identificador())) {
            throw new RuntimeException("Esse ID  já existe !");
        }

        var status = statusRepository.findByIdentificador("Aguardando Defesa e Apuração")
                .orElseThrow(() -> new RuntimeException("StatusProcesso não encontrado para o identificador: "));


        var entity = new ProcessoMulta();

        entity.setIdentificador(criarMultaDTO.identificador());
        entity.setObservacao(criarMultaDTO.observacao());
        entity.setStatusProcesso(status);


        var salvarMultas = multasRepository.save(entity);

        String apenasNumeros = criarMultaDTO.identificador().replaceAll("[^0-9]", "");
        if(!apenasNumeros.isEmpty()){
            int numero = Integer.parseInt(apenasNumeros);
            if(numero >=  10000){
                vincularMultasNaCaixaAutomaticamente(salvarMultas.getIdentificador());
            }
        }




        return salvarMultas.getId();




    }

    public Optional<ProcessoMulta> findByIdentificador(String identificador) {
        return multasRepository.findByIdentificador(identificador);
    }


    public List<ProcessoMulta> listarTodosProcessosDeMultas() {
        return multasRepository.findAll();
    }

    public void alterarProcessoMulta(String identificador, AlterarProcessoMulta alterarProcessoMulta) {
        var multaid = multasRepository.findByIdentificador(identificador);

        if (multaid.isPresent()) {
            var multaExistis = multaid.get();
            if (alterarProcessoMulta.localAtual() != null) {
                multaExistis.setLocalAtual(alterarProcessoMulta.localAtual());
            }
            if (alterarProcessoMulta.localAnterior() != null) {
                multaExistis.setLocalAnterior(alterarProcessoMulta.localAnterior());
            }
            if (alterarProcessoMulta.observacao() != null) {
                multaExistis.setObservacao(alterarProcessoMulta.observacao());
            }
            if (alterarProcessoMulta.statusProcesso() != null) {
                var status = statusRepository.findById(alterarProcessoMulta.statusProcesso())
                        .orElseThrow((() -> new RuntimeException("Status Invalido")));

                multaExistis.setStatusProcesso(status);
            }
            multasRepository.save(multaExistis);
        }

    }

    public void excluirProcessoMulta(String identificador) {
        var deletemulta = multasRepository.findByIdentificador(identificador);

        if (deletemulta.isPresent()) {

            multasRepository.delete(deletemulta.get());
        } else {
            throw new RuntimeException("Esse processo não existe!");
        }
    }

    public void arquivarEmCaixa(String identificador, String nomeCaixa) {
        var multa = multasRepository.findByIdentificador(identificador)
                .orElseThrow(() -> new RuntimeException("Processo não exsitente"));

        var caixa = caixaRepository.findByNome(nomeCaixa)
                .orElseThrow(() -> new RuntimeException("Caixa não existente"));
        multa.setCaixa(caixa);
        multasRepository.save(multa);

    }

    public void vincularMultasNaCaixaAutomaticamente(String identificador) {

        var multa = multasRepository.findByIdentificador(identificador)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        String apenasNumeros = identificador.replaceAll("[^0-9]", "");
        System.out.println("Apenas números: " + apenasNumeros); // ← debug

        int numero = Integer.parseInt(apenasNumeros);
        System.out.println("Número: " + numero); // ← debug

        int dezena = (numero / 1000) * 1000;
        int centena = (numero % 1000) / 100;
        String nomeCaixa = dezena + "." + centena;
        System.out.println("Nome da caixa: " + nomeCaixa); // ← debug

        var caixa = caixaRepository.findByNome(nomeCaixa)
                .orElseGet(() -> {
                    System.out.println("Criando caixa: " + nomeCaixa); // ← debug
                    var novaCaixa = new Caixa();
                    novaCaixa.setNome(nomeCaixa);
                    return caixaRepository.save(novaCaixa);
                });

        System.out.println("Caixa encontrada/criada: " + caixa.getNome()); // ← debug
        multa.setCaixa(caixa);
        multasRepository.save(multa);


    }
}