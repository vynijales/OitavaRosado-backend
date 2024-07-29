package com.oitava_rosado.oitava_rosado.backend.paciente;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "pacientes")
@Entity(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sexo;
    private Date data_nascimento;
    private String cpf;
    private String rg;
    private String orgao_emissor;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;
    private String observacoes;

    public Paciente(PacienteRequestDTO data) {
        this.updateFromDTO(data);
    }

    public void updateFromDTO(PacienteRequestDTO data) {
        this.nome = data.nome();
        this.sexo = data.sexo();
        this.data_nascimento = data.data_nascimento();
        this.cpf = data.cpf();
        this.rg = data.rg();
        this.orgao_emissor = data.orgao_emissor();
        this.logradouro = data.logradouro();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.uf = data.uf();
        this.cep = data.cep();
        this.telefone = data.telefone();
        this.email = data.email();
        this.observacoes = data.observacoes();
    }
}
