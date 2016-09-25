package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/ControleEstoqueEI","root","");
        } catch (SQLException e) {
            throw new ConnectionFactoryException();
        }
    }
    
    public void connectionClose(Connection conn) {

    try {
        if (conn != null) {
            conn.close();
            System.out.println("Fechada a conexão com o banco de dados");
        }

    } catch (Exception e) {
        System.out.println("Não foi possível fechar a conexão com o banco de dados " + e.getMessage());
    }
}

    public void connectionClose(Connection conn, PreparedStatement stmt) {

        try {
            if (conn != null) {
                connectionClose(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }


        } catch (Exception e) {
            System.out.println("Não foi possível fechar o statement " + e.getMessage());
        }
    }

    public void connectionClose(Connection conn, PreparedStatement stmt, ResultSet rs) {

        try {
            if (conn != null || stmt != null) {
               connectionClose(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }
}
