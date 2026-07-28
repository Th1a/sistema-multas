package org.aplicacao.multas.Controller;

import org.aplicacao.multas.Entity.StatusProcesso;

public record AlterarProcessoMulta(String localAtual, String localAnterior, String observacao, Long statusProcesso) {
}
