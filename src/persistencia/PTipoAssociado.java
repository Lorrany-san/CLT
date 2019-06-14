/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.ETipoAssociado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HeuberLima
 */
public class PTipoAssociado {

    public void incluir(ETipoAssociado tipo) throws SQLException {

        //Cria o sql a ser executado
        String sql = "INSERT INTO tipoassociado (descricao, valormensalidade)"
                + " VALUES (?,?)";

        //Cria a conexão com o banco a partir da classe utilitária
        Connection cnn = util.Conexao.getConexao();
        //Cria o procedimento armazanado a partir do sql gerado
        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores a serem injetados no código sql
        prd.setString(1, tipo.getDescricao());
        prd.setDouble(2, tipo.getValorMensalidade());

        //Executa o sql contra o banco
        prd.execute();

        String sql2 = "SELECT currval('tipoassociado_codigo_seq') as codigo ";
        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql2);
        if (rs.next()) {
            tipo.setCodigo(rs.getInt("codigo"));
        }

    }

    public void alterar(ETipoAssociado tipo) throws SQLException {

        //Cria o sql a ser executado
        String sql = "UPDATE tipoassociado SET"
                + " descricao = ? , "
                + " valormensalidade = ?"
                + " WHERE codigo = ?;";

        //Cria a conexão com o banco a partir da classe utilitária
        Connection cnn = util.Conexao.getConexao();
        //Cria o procedimento armazanado a partir do sql gerado
        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores a serem injetados no código sql
        prd.setString(1, tipo.getDescricao());
        prd.setDouble(2, tipo.getValorMensalidade());
        prd.setInt(3, tipo.getCodigo());

        //Executa o sql contra o banco
        prd.execute();

    }

    public void excluir(int codigo) throws SQLException {

        //Cria o sql a ser executado
        String sql = "DELETE FROM tipoassociado"
                + " WHERE codigo = ?;";

        //Cria a conexão com o banco a partir da classe utilitária
        Connection cnn = util.Conexao.getConexao();
        //Cria o procedimento armazanado a partir do sql gerado
        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores a serem injetados no código sql
        prd.setInt(1, codigo);

        //Executa o sql contra o banco
        prd.execute();

    }

    public ETipoAssociado consultar(int codigo) throws SQLException {

        String sql = "SELECT codigo, descricao, valormensalidade "
                + " FROM tipoassociado WHERE codigo = ?;";

        Connection cnn = util.Conexao.getConexao();
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, codigo);

        ResultSet rs = prd.executeQuery();

        ETipoAssociado tipo = new ETipoAssociado();

        if (rs.next()) {
            tipo.setCodigo(rs.getInt("codigo"));
            tipo.setDescricao(rs.getString("descricao"));
            tipo.setValorMensalidade(rs.getInt("valormensalidade"));
        }

        return tipo;

    }

    public ArrayList<ETipoAssociado> listar() throws SQLException {

        String sql = "SELECT codigo, descricao, valormensalidade "
                + " FROM tipoassociado ORDER BY descricao;";

        Connection cnn = util.Conexao.getConexao();
        Statement prd = cnn.createStatement();

        ResultSet rs = prd.executeQuery(sql);

        ArrayList<ETipoAssociado> lista = new ArrayList<ETipoAssociado>();

        while (rs.next()) {
            ETipoAssociado tipo = new ETipoAssociado();
            tipo.setCodigo(rs.getInt("codigo"));
            tipo.setDescricao(rs.getString("descricao"));
            tipo.setValorMensalidade(rs.getInt("valormensalidade"));
            lista.add(tipo);
        }

        return lista;

    }

}
