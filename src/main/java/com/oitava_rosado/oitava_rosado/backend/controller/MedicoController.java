package com.oitava_rosado.oitava_rosado.backend.controller;

import com.oitava_rosado.oitava_rosado.backend.medico.Medico;
import com.oitava_rosado.oitava_rosado.backend.medico.MedicoRepository;
import com.oitava_rosado.oitava_rosado.backend.medico.MedicoRequestDTO;
import com.oitava_rosado.oitava_rosado.backend.medico.MedicoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<MedicoResponseDTO> getAll() {
        List<MedicoResponseDTO> medicosList = repository.findAll().stream().map(MedicoResponseDTO::new).toList();
        return medicosList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveMedico(@RequestBody MedicoRequestDTO data) {
        Medico medicoData = new Medico(data);
        repository.save(medicoData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updateMedico(@PathVariable Long id, @RequestBody MedicoRequestDTO data) {
        Optional<Medico> optionalMedico = repository.findById(id);
        if (optionalMedico.isPresent()) {
            Medico existingMedico = optionalMedico.get();
            existingMedico.updateFromDTO(data);
            repository.save(existingMedico);
        } else {
            throw new RuntimeException(String.format("Médico %d não encontrado", id));
        }

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Long id) {
        Optional<Medico> optionalMedico = repository.findById(id);
        if (optionalMedico.isPresent()) {
            repository.delete(optionalMedico.get());
        } else {
            throw new RuntimeException(String.format("Medico %d não encontrado", id));
        }
    }
}
