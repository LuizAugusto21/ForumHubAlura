package com.example.ForumHub.services;

import com.example.ForumHub.models.Topico;
import com.example.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico criarTopico(@Valid Topico topico) {
        if (topicoRepository.findByTituloAndMensagem(topico.getTitulo(), topico.getMensagem()).isPresent()) {
            throw new RuntimeException("Tópico duplicado");
        }
        topico.setDataCriacao(LocalDateTime.now());
        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico atualizarTopico(Long id, @Valid Topico topico) {
        if (!topicoRepository.existsById(id)) {
            throw new RuntimeException("Tópico não encontrado");
        }
        topico.setId(id);
        return topicoRepository.save(topico);
    }

    public void deletarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
