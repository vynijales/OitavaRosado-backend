package com.oitavarosado.agendamento;

import com.oitavarosado.medico.MedicoResponseDTO;
import com.oitavarosado.paciente.PacienteResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoResponseDTO(Long id, String motivo, LocalDate data, LocalTime hora, String local, String observacoes, MedicoResponseDTO medico, PacienteResponseDTO paciente) {
}
