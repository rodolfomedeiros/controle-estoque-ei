package View;

import DAO.GerarComprovantePDF;
import DAO.VenderControl;
import DAO.VenderTableModelControl;
import JavaBeans.Equipamento;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class VenderView extends JFrame{
    private List<Equipamento> retorno;
    
    private double valorTotal = 0.0;
    
    private JFrame janelaAnterior;
    
    private JPanel painelPesquisar;
    private JPanel painelPrincipal;
    private JPanel adicionar;
    private JPanel painelVenda;
    private JPanel finalizar;
    
    private JTable tabelaPrincipal;
    private JTable tabelaVenda;
    
    private VenderTableModelControl modelPrincipal;
    private VenderTableModelControl modelVenda;
    
    private JButton buttonAdd;
    private JButton buttonBuscar;
    private JButton buttonVoltar;
    private JButton buttonFinalizar;
    private JButton buttonCancelar;
    
    private JLabel total;
    
    private JTextField pesquisarPeca;
    private JTextField comprador;
    private JTextField totalText;
    
    public VenderView(JFrame janelaAnterior){
        super("Vender");
        this.janelaAnterior = janelaAnterior;
        janelaAnterior.setVisible(false);
        
        getContentPane().setLayout(new GridBagLayout());
        
        painelPesquisar = new JPanel(new GridBagLayout());
        
        pesquisarPeca = new JTextField(20);      
        
        Box p1 = Box.createVerticalBox();
        p1.add(new JLabel("Nome/Número Série",JLabel.CENTER));
        p1.add(pesquisarPeca);
        p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Peça", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, Font.getFont("serif"), Color.blue));
        HelpView.addItem(painelPesquisar, p1, 0, 0, 1, 1, GridBagConstraints.CENTER);       
        
        buttonBuscar = new JButton("Buscar Item");
        buttonBuscar.addActionListener(listener);
        HelpView.addItem(painelPesquisar, buttonBuscar, 0, 1, 1, 1, GridBagConstraints.CENTER);        
        
        painelPrincipal = new JPanel(new GridBagLayout());
        retorno = new VenderControl().buscarTodosBD();
        tabelaPrincipal = new JTable();
        modelPrincipal = new VenderTableModelControl();
        tabelaPrincipal.setModel(getModelPrincipal());
        HelpView.addItem(painelPrincipal, new JScrollPane(tabelaPrincipal), 0, 0, 1, 1, 100.0, 100.0, GridBagConstraints.CENTER);       
        
        adicionar = new JPanel(new GridBagLayout());
        buttonAdd = new JButton("Adicionar Item");
        buttonAdd.addActionListener(listener);
        HelpView.addItem(adicionar, buttonAdd, 0, 0, 1, 1, GridBagConstraints.CENTER);
        
        painelVenda = new JPanel(new GridBagLayout());
        tabelaVenda = new JTable();
        modelVenda = new VenderTableModelControl();
        tabelaVenda.setModel(getModelVenda());
        HelpView.addItem(painelVenda, new JScrollPane(tabelaVenda), 0, 0, 1, 1, 100.0, 100.0, GridBagConstraints.CENTER);
        
        finalizar = new JPanel(new GridBagLayout());
        buttonFinalizar = new JButton("Finalizar Venda");
        buttonFinalizar.addActionListener(listener);
        buttonCancelar = new JButton("Cancelar Item");
        buttonCancelar.addActionListener(listener);
        buttonVoltar = new JButton("Voltar Principal");
        buttonVoltar.addActionListener(listener);
        
        Box infor = Box.createVerticalBox();
        total = new JLabel("Valor Total(R$):");
        totalText = new JTextField(20);
        totalText.setText(String.valueOf(getValorTotal()));
        totalText.setEditable(false);
        comprador = new JTextField(20);
        infor.add(total);
        infor.add(totalText);
        infor.add(new JLabel("Comprador:"), Color.WHITE);
        infor.add(comprador);
        
        HelpView.addItem(finalizar, buttonFinalizar, 0, 0, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(finalizar, buttonCancelar, 1, 0, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(finalizar, buttonVoltar, 2, 0, 1, 1, GridBagConstraints.CENTER);
        HelpView.addItem(finalizar, infor, 3, 0, 1, 1, GridBagConstraints.CENTER);
        
       
        painelPesquisar.setBackground(Color.lightGray);
        adicionar.setBackground(Color.lightGray);
        finalizar.setBackground(Color.lightGray);
        
        HelpView.addItem((JPanel)getContentPane(), painelPesquisar, 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER);
        HelpView.addItem((JPanel)getContentPane(), painelPrincipal, 0, 1, 1, 1, 100.0, 100.0, GridBagConstraints.CENTER);
        HelpView.addItem((JPanel)getContentPane(), adicionar, 0, 2, 1, 1, 100.0, 0, GridBagConstraints.CENTER);
        HelpView.addItem((JPanel)getContentPane(), painelVenda, 0, 3, 1, 1, 100.0, 100.0, GridBagConstraints.CENTER);
        HelpView.addItem((JPanel)getContentPane(), finalizar, 0, 4, 1, 1, 100.0, 0, GridBagConstraints.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HelpView.configurarFrame(this);
        
    }
    
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event = e.getSource();
            if(event == buttonBuscar){
                retorno = new ArrayList(new VenderControl().pesquisar(pesquisarPeca.getText()));
                pesquisarPeca.setText(null);
                getModelPrincipal().listar(retorno);
            }else if(event == buttonAdd){
                getModelVenda().add(getModelPrincipal().getEquipamento(tabelaPrincipal.getSelectedRow()));
                setValorTotal(new VenderControl().valorTotal(getModelVenda().getEquipamentos()));
                totalText.setText(String.valueOf(getValorTotal()));
            }else if(event == buttonVoltar){
                janelaAnterior.setVisible(true);
                dispose();
            }else if(event == buttonCancelar){
                getModelVenda().remove(tabelaVenda.getSelectedRow());
                setValorTotal(new VenderControl().valorTotal(getModelVenda().getEquipamentos()));
                totalText.setText(String.valueOf(getValorTotal()));
            }else if(event == buttonFinalizar){
                try {
                    boolean retorno = new GerarComprovantePDF().GerarPDF((ArrayList<Equipamento>) getModelVenda().getEquipamentos(), comprador.getText(), getValorTotal());
                    if(retorno){
                        new VenderControl().removerItensBD((ArrayList<Equipamento>) getModelVenda().getEquipamentos());
                        java.awt.Desktop.getDesktop().open(new File(comprador.getText()+".pdf"));
                        show();
                    }else JOptionPane.showMessageDialog(null, "INFORME O NOME DO COMPRADOR!");
                } catch (DocumentException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    };
    
    private VenderTableModelControl getModelPrincipal(){
        return modelPrincipal;
    }
    private VenderTableModelControl getModelVenda(){
        return modelVenda;
    }
    private double getValorTotal(){
        return valorTotal;
    }
    private void setValorTotal(double valor){
        valorTotal = valor;
    }
}
