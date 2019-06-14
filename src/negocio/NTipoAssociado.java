/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidade.ETipoAssociado;
import java.sql.SQLException;
import java.util.ArrayList;
import persistencia.PTipoAssociado;

/**
 *
 * @author HeuberLima
 */
 public class NTipoAssociado {
    
    
    PTipoAssociado persistencia;
    
    public NTipoAssociado() {
       persistencia = new PTipoAssociado();
    }

    public void salvar(ETipoAssociado tipo) throws SQLException {
        if (tipo.getDescricao().isEmpty() || tipo.getValorMensalidade() == 0 ) {
            throw new IllegalArgumentException("É necessario preencher todos os campos para Salvar.");
               
        }
     
        
        if (tipo.getCodigo() == 0) {
           persistencia.incluir(tipo);
        } else {
             persistencia.alterar(tipo);
        }
    }
    
    public void excluir(int codigo) throws SQLException{
       
        persistencia.excluir(codigo);
    }
    
    public ETipoAssociado consultar(int codigo) throws SQLException{
        return  persistencia.consultar(codigo);
    }
    
    public ArrayList<ETipoAssociado> listar() throws SQLException{
        return persistencia.listar();
    }
    
}
