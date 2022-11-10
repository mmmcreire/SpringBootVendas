package io.vendasSB.domain.repository;

import io.vendasSB.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO cliente (nome) VALUES (?) ";
    private static String SELECT_ALL = "SELECT * FROM cliente";
    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ?";
    private static String DELETE = "DELETE FROM cliente WHERE id = ?";
    private static String SELECT_BY_NAME = "SELECT * FROM cliente WHERE nome LIKE ?";
    private static String SELECT_BY_ID = "SELECT * FROM cliente WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update( INSERT, new Object[]{cliente.getNome()} );
        return cliente;
    }


    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(),
                cliente.getId()
                });
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
    return  jdbcTemplate.query(SELECT_BY_NAME,
                                new Object[]{"%" + nome + "%"},
                                getClientRowMapper());
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, getClientRowMapper());
    }

    private static RowMapper<Cliente> getClientRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }


}
