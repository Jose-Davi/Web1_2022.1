package com.example.ProjetowebAutenticacao.repository;

import com.example.ProjetowebAutenticacao.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {


    public Optional<Usuario> findByUsuTxLogin(String login);

     Usuario findByUsuTxLoginAndUsuTxSenha(String login, String senha);


   /* @Query(nativeQuery = true, value = "select perfil * from usuario usu inner join perfil perf" +
            "on perf.usu_nr_id = usu.usu_nr_id where perf.per_nr_id=?1")
    List<Usuario> getByPerfil(Integer usu_nr_id);*/
}
