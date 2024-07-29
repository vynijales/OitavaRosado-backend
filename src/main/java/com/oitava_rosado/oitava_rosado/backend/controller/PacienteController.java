package com.oitava_rosado.oitava_rosado.backend.controller;

import com.oitava_rosado.oitava_rosado.backend.paciente.Paciente;
import com.oitava_rosado.oitava_rosado.backend.paciente.PacienteRepository;
import com.oitava_rosado.oitava_rosado.backend.paciente.PacienteRequestDTO;
import com.oitava_rosado.oitava_rosado.backend.paciente.PacienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public List<PacienteResponseDTO> getAll() {
        List<PacienteResponseDTO> pacienteList = repository.findAll().stream().map(PacienteResponseDTO::new).toList();
        return pacienteList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void savePaciente(@RequestBody PacienteRequestDTO data) {
        Paciente pacienteData = new Paciente(data);
        repository.save(pacienteData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updatePaciente(@PathVariable Long id, @RequestBody PacienteRequestDTO data) {
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente existingPaciente = optionalPaciente.get();
            existingPaciente.updateFromDTO(data);
            repository.save(existingPaciente);
        } else {
            throw new RuntimeException(String.format("Paciente %d não encontrado", id));
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if (optionalPaciente.isPresent()) {
            repository.delete(optionalPaciente.get());
        } else {
            throw new RuntimeException(String.format("Paciente %d não encontrado", id));
        }
    }
}
