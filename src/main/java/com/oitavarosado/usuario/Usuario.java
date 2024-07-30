package com.oitavarosado.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "usuarios")
@Entity(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String login;
    private String password;


    public Usuario(UsuarioRequestDTO data) {this.updateFromDTO(data);}

    public void updateFromDTO(UsuarioRequestDTO data) {
        this.login = data.login();
        this.password = data.password();
    }
}
