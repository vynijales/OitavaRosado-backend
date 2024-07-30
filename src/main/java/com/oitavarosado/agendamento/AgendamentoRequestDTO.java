package com.oitavarosado.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoRequestDTO(String motivo, LocalDate data, LocalTime hora, String local, String observacoes, Long medico_id, Long paciente_id) {

}