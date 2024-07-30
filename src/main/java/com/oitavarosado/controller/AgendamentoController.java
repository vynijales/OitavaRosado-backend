package com.oitavarosado.controller;

import com.oitavarosado.agendamento.Agendamento;
import com.oitavarosado.agendamento.AgendamentoRepository;
import com.oitavarosado.agendamento.AgendamentoRequestDTO;
import com.oitavarosado.agendamento.AgendamentoResponseDTO;
import com.oitavarosado.medico.Medico;
import com.oitavarosado.medico.MedicoRepository;
import com.oitavarosado.medico.MedicoResponseDTO;
import com.oitavarosado.paciente.Paciente;
import com.oitavarosado.paciente.PacienteRepository;
import com.oitavarosado.paciente.PacienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<AgendamentoResponseDTO> getAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public AgendamentoResponseDTO createAgendamento(@RequestBody AgendamentoRequestDTO requestDTO) {
        Optional<Medico> medicoOpt = medicoRepository.findById(requestDTO.medico_id());
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(requestDTO.paciente_id());

        if (medicoOpt.isEmpty() || pacienteOpt.isEmpty()) {
            throw new IllegalArgumentException("Medico or Paciente not found");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setMotivo(requestDTO.motivo());
        agendamento.setData(requestDTO.data());
        agendamento.setHora(requestDTO.hora());
        agendamento.setLocal(requestDTO.local());
        agendamento.setObservacoes(requestDTO.observacoes());
        agendamento.setMedico(medicoOpt.get());
        agendamento.setPaciente(pacienteOpt.get());

        Agendamento savedAgendamento = agendamentoRepository.save(agendamento);
        return convertToResponseDTO(savedAgendamento);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public AgendamentoResponseDTO updateAgendamento(@PathVariable Long id, @RequestBody AgendamentoRequestDTO requestDTO) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
        if (optionalAgendamento.isEmpty()) {
            throw new RuntimeException(String.format("Agendamento %d não encontrado", id));
        }

        Agendamento agendamento = optionalAgendamento.get();

        Optional<Medico> medicoOpt = medicoRepository.findById(requestDTO.medico_id());
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(requestDTO.paciente_id());

        if (medicoOpt.isEmpty() || pacienteOpt.isEmpty()) {
            throw new IllegalArgumentException("Medico or Paciente not found");
        }

        agendamento.setMotivo(requestDTO.motivo());
        agendamento.setData(requestDTO.data());
        agendamento.setHora(requestDTO.hora());
        agendamento.setLocal(requestDTO.local());
        agendamento.setObservacoes(requestDTO.observacoes());
        agendamento.setMedico(medicoOpt.get());
        agendamento.setPaciente(pacienteOpt.get());

        Agendamento updatedAgendamento = agendamentoRepository.save(agendamento);
        return convertToResponseDTO(updatedAgendamento);
    }

    private AgendamentoResponseDTO convertToResponseDTO(Agendamento agendamento) {
        MedicoResponseDTO medicoResponseDTO = new MedicoResponseDTO(agendamento.getMedico());
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO(agendamento.getPaciente());

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getMotivo(),
                agendamento.getData(),
                agendamento.getHora(),
                agendamento.getLocal(),
                agendamento.getObservacoes(),
                medicoResponseDTO,
                pacienteResponseDTO
        );
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable Long id) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
        if (optionalAgendamento.isPresent()) {
            agendamentoRepository.delete(optionalAgendamento.get());
        } else {
            throw new RuntimeException(String.format("Paciente %d não encontrado", id));
        }
    }
}