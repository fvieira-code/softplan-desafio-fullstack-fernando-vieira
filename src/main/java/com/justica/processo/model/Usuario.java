package com.justica.processo.model;

import com.justica.processo.model.domain.DominioStatusObjeto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 100)
    private String id;

    @Size(max = 100, message = "entity.usuario.nome.size.message")
    @Column(name = "nomeusu", nullable = false, length = 100)
    private String nomeUsuario;

    @Size(max = 100, message = "entity.usuario.email.size.message")
    @Column(name = "emailusu", nullable = false, length = 100)
    private String emailUsuario;

    @Size(max = 100, message = "entity.usuario.senha.size.message")
    @Column(name = "senhausu", nullable = false, length = 100)
    private String senhaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "statususu", length = 10, insertable = true, updatable = true)
    private DominioStatusObjeto statusUsuario;

    @PrePersist
    public void prePersist() {
        if (Strings.isEmpty(this.id)) {
            this.id = UUID.randomUUID().toString();
        }
    }
}