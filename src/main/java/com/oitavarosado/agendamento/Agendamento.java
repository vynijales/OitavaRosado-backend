package com.oitavarosado.agendamento;

import com.oitavarosado.medico.Medico;
import com.oitavarosado.medico.MedicoRepository;
import com.oitavarosado.paciente.Paciente;
import com.oitavarosado.paciente.PacienteRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "agendamentos")
@Entity(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private LocalDate data;
    private LocalTime hora;
    private String local;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Transient
    private MedicoRepository medicoRepository;

    @Transient
    private PacienteRepository pacienteRepository;

    public Agendamento(AgendamentoRequestDTO data, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.updateFromDTO(data);
    }

    public void updateFromDTO(AgendamentoRequestDTO data) {
        this.motivo = data.motivo();
        this.data = data.data();
        this.hora = data.hora();
        this.local = data.local();
        this.observacoes = data.observacoes();
        this.medico = medicoRepository.findById(data.medico_id()).orElseThrow(() -> new IllegalArgumentException("Medico not found"));
        this.paciente = pacienteRepository.findById(data.paciente_id()).orElseThrow(() -> new IllegalArgumentException("Paciente not found"));
    }
}