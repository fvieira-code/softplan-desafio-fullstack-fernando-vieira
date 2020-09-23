package com.justica.processo.repository;

import com.justica.processo.model.Assunto;
import com.justica.processo.model.Usuario;
import com.justica.processo.model.domain.DominioStatusObjeto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, String> {
    boolean existsByEmailUsuarioLike(String emailUsuario);
    Optional<Usuario> findByEmailUsuarioLikeAndStatusUsuario(String emailUsuario, DominioStatusObjeto statusObjeto);
    Optional<Usuario> findByNomeUsuarioLikeAndStatusUsuario(String nomeUsuario, DominioStatusObjeto statusObjeto);
}
