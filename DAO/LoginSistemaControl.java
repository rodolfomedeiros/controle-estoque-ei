package DAO;

import Util.ConnectionFactory;
import Util.ConnectionFactoryException;
import View.PrincipalView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginSistemaControl {
    public boolean verificarUsuario(String login, String password) throws SQLException {
        try(Connection con = new ConnectionFactory().getConnection()){
            String sql = "SELECT * from Administrador WHERE password='"+login+"' AND login='"+password+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                new ConnectionFactory().connectionClose(con,stmt,result);
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"Usuario ou Senha incorretos, tente outra vez!");
            }
        }catch(ConnectionFactoryException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
