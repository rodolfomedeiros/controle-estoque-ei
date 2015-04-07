package DAO;

import JavaBeans.Equipamento;
import Util.ConnectionFactory;
import JavaBeans.Local;
import JavaBeans.Responsavel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PesquisarControl {
    
    public ArrayList<Equipamento> pesquisar(String pesquisa, int index){
        try(Connection con = new ConnectionFactory().getConnection()){
            if(index == 1){
                        
                String sql = "SELECT * FROM `equipamentos` WHERE nomeEquipamento=? || numSerie=?";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, pesquisa);
                stmt.setString(2, pesquisa);

                ResultSet r = stmt.executeQuery();
                ArrayList<Equipamento> retorno = new ArrayList();

                while(r.next()){
                    retorno.add(new Equipamento(r.getString("nomeEquipamento"), r.getDouble("peso"), r.getDouble("preco"), r.getString("numSerie"), new Local(r.getString("codigo"),r.getInt("sessao")), new Responsavel(r.getString("nome"), r.getString("telefone"), r.getString("endereco"))));
                }
                if(!r.next()){
                    new ConnectionFactory().connectionClose(con, stmt, r);
                    return retorno;
                }
            }else if(index == 2){

                String sql = "SELECT * FROM `equipamentos` WHERE nome=?";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, pesquisa);

                ResultSet r = stmt.executeQuery();
                ArrayList<Equipamento> retorno = new ArrayList();

                while(r.next()){
                    retorno.add(new Equipamento(r.getString("nomeEquipamento"), r.getDouble("peso"), r.getDouble("preco"), r.getString("numSerie"), new Local(r.getString("codigo"),r.getInt("sessao")), new Responsavel(r.getString("nome"), r.getString("telefone"), r.getString("endereco"))));
                }
                if(!r.next()){
                    new ConnectionFactory().connectionClose(con, stmt, r);
                    return retorno;
                }
            }
        } catch (SQLException e) {
            System.out.println("Dando Erro " + e);
        }
        return null;
    }
}
