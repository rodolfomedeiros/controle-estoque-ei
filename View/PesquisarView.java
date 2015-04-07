package View;

import DAO.PesquisarControl;
import DAO.PesquisarTableModelControl;
import JavaBeans.Equipamento;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PesquisarView extends JFrame{
    private List<Equipamento> retorno;    
    
    private JTextField pesquisarPeca;
    private JTextField pesquisarResp;
    
    private JButton pesquisarP;
    private JButton pesquisarR;
    private JButton voltar;
    
    private JTable tabela;

    private JFrame janelaAnterior;
    
    private JPanel painelPesquisa;
    private JPanel painelTabela;
    private JPanel painelVoltar;
    
    public PesquisarView(JFrame janelaAnterior){
        super("Pesquisar");
        this.janelaAnterior = janelaAnterior;
        janelaAnterior.setVisible(false);
        
        //PAINEL DE PESQUISA
        painelPesquisa = new JPanel(new GridBagLayout());
        painelPesquisa.setBackground(Color.lightGray);
        
        pesquisarPeca = new JTextField(20);
        pesquisarResp = new JTextField(20);
        
        Box p1 = Box.createVerticalBox();
        p1.add(new JLabel("Nome/Número Série",JLabel.CENTER));
        p1.add(pesquisarPeca);
        p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Peça", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, Font.getFont("serif"), Color.blue));
        HelpView.addItem(painelPesquisa, p1, 0, 0, 1, 1, GridBagConstraints.NORTH);       
        
        Box p2 = Box.createVerticalBox();
        p2.add(new JLabel("Nome",JLabel.CENTER));
        p2.add(pesquisarResp);
        p2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Responsável", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, Font.getFont("serif"), Color.red));
        HelpView.addItem(painelPesquisa, p2, 1, 0, 1, 1, GridBagConstraints.NORTH);
        
        pesquisarP = new JButton("Pesquisar Peça");
        pesquisarP.addActionListener(listener);
        HelpView.addItem(painelPesquisa, pesquisarP, 0, 1, 1, 1, GridBagConstraints.CENTER);
        
        pesquisarR = new JButton("Pesquisar Responsavel");
        pesquisarR.addActionListener(listener);
        HelpView.addItem(painelPesquisa, pesquisarR, 1, 1, 1, 1, GridBagConstraints.CENTER);
        
        //PAINEL DA TABELA
        tabela = new JTable();
        tabela.setCellSelectionEnabled(false);
        tabela.setRowSelectionAllowed(true);
        tabela.setModel(new PesquisarTableModelControl());
        
        painelTabela = new JPanel(new GridBagLayout());
        HelpView.addItem(painelTabela, new JScrollPane(tabela), 0, 0, 1, 1, 100.0, 100.0, GridBagConstraints.CENTER);   
        
        //PAINEL DE VOLTAR
        painelVoltar = new JPanel();
        voltar = new JButton("Voltar");
        voltar.addActionListener(listener);
        painelVoltar.add(voltar, BorderLayout.CENTER);
        
        //FRAME
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.darkGray);
        HelpView.addItem((JPanel) getContentPane(), painelPesquisa, 0, 0, 1, 1, 100.0, 0, GridBagConstraints.CENTER);
        HelpView.addItem((JPanel) getContentPane(), painelTabela, 0, 1, 1, 1, 100.0, 100.0, GridBagConstraints.CENTER);
        HelpView.addItem((JPanel) getContentPane(), painelVoltar, 0, 2, 1, 1, 100.0, 0, GridBagConstraints.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HelpView.configurarFrame(this);
        
    }
    
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event = e.getSource();
            if(event == pesquisarP){
                retorno = new ArrayList(new PesquisarControl().pesquisar(pesquisarPeca.getText(),1));
                pesquisarPeca.setText(null);
                tabela.setModel(new PesquisarTableModelControl(retorno));
                    
            }else if(event == pesquisarR){
                    retorno = new PesquisarControl().pesquisar(pesquisarResp.getText(),2);
                    pesquisarResp.setText(null);
                    tabela.setModel(new PesquisarTableModelControl(retorno));
                    
            }else if(event == voltar){
                    janelaAnterior.setVisible(true);
                    dispose();
                }
            }
    };
    
}
