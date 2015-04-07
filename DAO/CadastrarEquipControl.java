package DAO;

import JavaBeans.Equipamento;
import Util.ConnectionFactory;
import Util.ConnectionFactoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CadastrarEquipControl {
    
    public boolean cadastrarEquipamento(Equipamento equip) throws SQLException{
        try(Connection con = new ConnectionFactory().getConnection()){
            
            String sql = "INSERT INTO `equipamentos`(`nomeEquipamento`, `peso`, `preco`, `numSerie`, `codigo`, `sessao`, `nome`, `telefone`, `endereco`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, equip.getNomeEquip());
            stmt.setDouble(2, equip.getPeso());
            stmt.setDouble(3, equip.getPreco());
            stmt.setString(4, equip.getNumSerie());
            stmt.setString(5, equip.local.getCodigoPateleira());
            stmt.setInt(6, equip.local.getNumSessao());
            stmt.setString(7, equip.responsavel.getNome());
            stmt.setString(8, equip.responsavel.getTelefone());
            stmt.setString(9, equip.responsavel.getEndereco());
            
            boolean result = stmt.execute();
            
            new ConnectionFactory().connectionClose(con, stmt);
            
            if(!result){
                return true;
            }
        } catch (ConnectionFactoryException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
