package com.oitava_rosado.oitava_rosado.backend.medico;

import com.oitava_rosado.oitava_rosado.backend.paciente.PacienteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String conselho_medico;
    private String uf_conselho;
    private String num_conselho;
    private String cbo;
    private String cpf;
    private String lagradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;

    public Medico(MedicoRequestDTO data) {
        this.updateFromDTO(data);
    }

    public void updateFromDTO(MedicoRequestDTO data) {
        this.nome = data.nome();
        this.conselho_medico = data.conselho_medico();
        this.uf_conselho = data.uf_conselho();
        this.num_conselho = data.num_conselho();
        this.cbo = data.cbo();
        this.cpf = data.cpf();
        this.lagradouro = data.lagradouro();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.uf = data.uf();
        this.cep = data.cep();
        this.telefone = data.telefone();
        this.email = data.email();
    }
}
