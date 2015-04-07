package DAO;

import JavaBeans.Equipamento;
import JavaBeans.Local;
import JavaBeans.Responsavel;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenderControl {
    public ArrayList buscarTodosBD(){
        try(Connection con = new ConnectionFactory().getConnection()){
            
            String sql = "SELECT * FROM `equipamentos`";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet r = stmt.executeQuery();
            ArrayList retorno = new ArrayList<>();

            while(r.next()){
                retorno.add(new Equipamento(r.getString("nomeEquipamento"), r.getDouble("peso"), r.getDouble("preco"), r.getString("numSerie"), new Local(r.getString("codigo"),r.getInt("sessao")), new Responsavel(r.getString("nome"), r.getString("telefone"), r.getString("endereco"))));
            }
            if(!r.next()){
                new ConnectionFactory().connectionClose(con, stmt, r);
                return retorno;
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<Equipamento> pesquisar(String pesquisa){
        try(Connection con = new ConnectionFactory().getConnection()){
                        
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
        }catch (SQLException e) {
            System.out.println("Dando Erro " + e);
        }
        return null;
    }
    
    public boolean removerItensBD(ArrayList<Equipamento> equipamento){
        try(Connection con = new ConnectionFactory().getConnection()){
            String sql = "DELETE FROM equipamentos WHERE numSerie=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            int i;
            for(i = 0;i < equipamento.size(); i++){
                stmt.setString(1, equipamento.get(i).getNumSerie());
                stmt.execute();
            }
            if(i == equipamento.size()){
                new ConnectionFactory().connectionClose(con,stmt);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public double valorTotal(List<Equipamento> equipamentos){
        double valorTotal = 0.0;
        int i;
        for(i=0;i<equipamentos.size();i++){
            valorTotal+=equipamentos.get(i).getPreco();
        }
        
        if(i == equipamentos.size()) return valorTotal;
        
        return 0.0;
    }
}
    
