package com.example.ForumHub.controllers;

import com.example.ForumHub.models.Topico;
import com.example.ForumHub.services.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/topicos")
//@Validated
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<Topico> getAllTopicos() {
        return topicoService.listarTopicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoById(@PathVariable Long id) {
        return topicoService.buscarTopicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Topico> createTopico(@Valid @RequestBody Topico topico) {
        Topico createdTopico = topicoService.criarTopico(topico);
        return ResponseEntity.ok(createdTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> updateTopico(@PathVariable Long id, @Valid @RequestBody Topico topico) {
        Topico updatedTopico = topicoService.atualizarTopico(id, topico);
        return ResponseEntity.ok(updatedTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}

