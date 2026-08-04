package org.aplicacao.multas.Controller;

import org.aplicacao.multas.Entity.StatusProcesso;
import org.aplicacao.multas.Repository.StatusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status-processo")
public class StatusController {

    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public ResponseEntity<List<StatusProcesso>> listarStatus() {
        return ResponseEntity.ok(statusRepository.findAll());
    }
}