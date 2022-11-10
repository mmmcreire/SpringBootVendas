package io.vendasSB.domain.repository;

import io.vendasSB.domain.entity.Cliente;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> 25ddc03bd9437b4cbecd31d4c7a32292a4084bda
import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer > {

<<<<<<< HEAD
    List<Cliente> findByNameLike(String nome);

    boolean existsByNome(String nome);
}
=======
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find( Cliente.class, id );
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
        String jpql = " SELECT c FROM Cliente c WHERE c.nome LIKE :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
}
>>>>>>> 25ddc03bd9437b4cbecd31d4c7a32292a4084bda
