package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrincipalView extends JFrame{
    
    private JPanel painel;
    private JPanel painelMod; 
    
    private JButton cadastrarEquip;
    private JButton vender;
    private JButton pesquisar;
    private JButton sair;
    
    public PrincipalView(){
        super("Controle de Estoque EI");
        
        cadastrarEquip = new JButton("Cadastrar Equipamentos");
        cadastrarEquip.addActionListener(event);
        vender = new JButton("Vender");
        vender.addActionListener(event);
        pesquisar = new JButton("Pesquisar");
        pesquisar.addActionListener(event);
        sair = new JButton("Sair");
        sair.addActionListener(event);
        
        painel = new JPanel(new GridBagLayout());
        painelMod = new JPanel(new GridBagLayout());

        painel.setBackground(Color.DARK_GRAY);
        painelMod.setBackground(Color.lightGray);
        
        HelpView.addItem(painelMod, new JLabel(new ImageIcon("src\\icon\\logo01.png")), 0, 0, 1, 1, GridBagConstraints.CENTER);
        
        HelpView.addItem(painel, cadastrarEquip, 0, 0, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(painel, vender, 0, 1, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(painel, pesquisar, 0, 2, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(painel, sair, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
        getContentPane().add(painel, BorderLayout.WEST);
        getContentPane().add(painelMod, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HelpView.configurarFrame(this);
        
    }
    
    public JFrame getJanela(){
        return this;
    }
    public JPanel getPainelMod(){
        return this.painelMod;
    }
        
    ActionListener event = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object evento = e.getSource();
            if(evento == cadastrarEquip){
                try {
                    new CadastrarEquipView(getJanela());
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }else
                if(evento == pesquisar){
                    new PesquisarView(getJanela());
                }else 
                    if(evento == vender){
                        new VenderView(getJanela());
                    }else if(evento == sair){
                        new LoginSistemaView();
                        dispose();
                        
                    }
        }
    };
}
