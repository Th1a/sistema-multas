package org.aplicacao.multas.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "status_processo")
public class StatusProcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String etapa;
    private String identificador ;

    public StatusProcesso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }



    public StatusProcesso(Long id, String etapa, String identificador) {
        this.id = id;
        this.etapa = etapa;
        this.identificador = identificador;
    }
}
