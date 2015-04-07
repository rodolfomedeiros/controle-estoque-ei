package View;

import DAO.LoginSistemaControl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LoginSistemaView extends JFrame{
    private JTextField login;
    private JTextField password;
    private JLabel usuario;
    private JLabel senha;
    private JButton entrar;
    private JPanel painelLogin;
    
    public LoginSistemaView(){
        super("Sistema de Estoque");
        login = new JTextField(20);
        password = new JPasswordField(20);
        usuario = new JLabel("Usuário:");
        senha = new JLabel("Senha:");
        entrar = new JButton("Entrar");
        entrar.setForeground(Color.red);
        entrar.addActionListener(listener);
        entrar.setBackground(Color.black);
        
        Box boxLogin = Box.createVerticalBox();
        
        boxLogin.add(usuario);
        boxLogin.add(login);
        boxLogin.add(senha);
        boxLogin.add(password);
        boxLogin.setBackground(Color.BLACK);
        boxLogin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Acesso", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, Font.getFont("serif"), Color.blue));
        
        painelLogin = new JPanel(new GridBagLayout());
        painelLogin.setBackground(Color.lightGray);
        
        HelpView.addItem(painelLogin, boxLogin, 0, 0, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(painelLogin, entrar, 0, 1, 1, 1, GridBagConstraints.CENTER);
        
        getContentPane().add(painelLogin);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HelpView.configurarFrame(this); 
    }
    
    public String getLogin(){
        return login.getText();
    }
    public String getPassword(){
        return password.getText();
    }
    
    ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Object event = e.getSource();
                if(event == entrar){
                    try {
                        if(new LoginSistemaControl().verificarUsuario(getLogin(),getPassword())){
                            new PrincipalView();
                            dispose();
                        }
                        
                    } catch (SQLException ex) {
                        
                    }
                }   
            }     
        };
}
