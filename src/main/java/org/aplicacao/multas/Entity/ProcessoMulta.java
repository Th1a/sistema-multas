package org.aplicacao.multas.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "processo_multa")
public class ProcessoMulta {

    public ProcessoMulta(Long id, String identificador, String localAtual, String localAnterior, String observacao, StatusProcesso statusProcesso , LocalDateTime dataAtualizacao) {
        this.id = id;
        this.identificador = identificador;
        this.localAtual = localAtual;
        this.localAnterior = localAnterior;
        this.observacao = observacao;
        this.statusProcesso = statusProcesso;
        this.dataAtualizacao = dataAtualizacao;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;

    private String localAtual;

    private String localAnterior;

    private String observacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;



    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusProcesso statusProcesso;


    public ProcessoMulta() {

    }
    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getLocalAtual() {
        return localAtual;
    }

    public void setLocalAtual(String localAtual) {
        this.localAtual = localAtual;
    }

    public String getLocalAnterior() {
        return localAnterior;
    }

    public void setLocalAnterior(String localAnterior) {
        this.localAnterior = localAnterior;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusProcesso getStatusProcesso() {
        return statusProcesso;
    }

    public void setStatusProcesso(StatusProcesso statusProcesso) {
        this.statusProcesso = statusProcesso;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }





}
