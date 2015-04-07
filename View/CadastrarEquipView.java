package View;

import DAO.CadastrarEquipControl;
import JavaBeans.Equipamento;
import JavaBeans.Local;
import JavaBeans.Responsavel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

public class CadastrarEquipView extends JFrame {
    private Equipamento equipamento;
    
    private JTextField nomeEquip;
    private JTextField preco;
    private JTextField peso;
    private JTextField numSerie;
    
    private JTextField codigo;
    private JTextField sessao;
    
    private JTextField nome;
    private JFormattedTextField telefone;
    private JTextField endereco;
    
    private JFrame janelaAnterior;
    
    private JPanel painelNovo;
    
    private JButton okButton;
    private JButton voltar;
    
    public CadastrarEquipView(JFrame janelaAnterior) throws ParseException{
        super("Cadastrar");
        this.janelaAnterior = janelaAnterior;
        janelaAnterior.setVisible(false);
        painelNovo = new JPanel(new GridBagLayout());
        
        painelNovo.setBackground(Color.lightGray);
        
        JLabel titulo = new JLabel("Novo Cadastro Equipamento",JLabel.CENTER);
        titulo.setBorder(BorderFactory.createBevelBorder(1, Color.red, Color.red));
        HelpView.addItem(painelNovo, titulo, 0, 0, 2, 1, GridBagConstraints.CENTER);
        
        //Label de cadastramento
        HelpView.addItem(painelNovo, new JLabel("Nome Equipamento"), 0, 1, 1, 1, GridBagConstraints.NORTH);
        HelpView.addItem(painelNovo, new JLabel("Peso"), 0, 2, 1, 1, GridBagConstraints.NORTH);
        HelpView.addItem(painelNovo, new JLabel("Preço"), 0, 3, 1, 1, GridBagConstraints.NORTH);
        HelpView.addItem(painelNovo, new JLabel("Número de Série"), 0, 4, 1, 1, GridBagConstraints.NORTH);
        
        nomeEquip = new JTextField(20);
        peso = new JTextField(20);
        peso.addKeyListener(keyEvent);
        preco = new JTextField(20);
        preco.addKeyListener(keyEvent);
        numSerie = new JTextField(20);
        
        HelpView.addItem(painelNovo, nomeEquip, 1, 1, 1, 1, GridBagConstraints.NORTH);
        HelpView.addItem(painelNovo, peso, 1, 2, 1, 1, GridBagConstraints.NORTH);
        HelpView.addItem(painelNovo, preco, 1, 3, 1, 1, GridBagConstraints.NORTH);
        HelpView.addItem(painelNovo, numSerie, 1, 4, 1, 1, GridBagConstraints.NORTH);
        
        codigo = new JTextField(10);
        sessao = new JTextField(10);
        sessao.addKeyListener(keyEvent);
        
        Box boxLocal = Box.createVerticalBox();
        
        boxLocal.add( new JLabel("Código"));
        boxLocal.add(codigo);
        boxLocal.add(new JLabel("Sessão"));
        boxLocal.add(sessao);
        boxLocal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Local", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, Font.getFont("serif"), Color.blue));
        HelpView.addItem(painelNovo, boxLocal, 0, 5, 1, 1, GridBagConstraints.CENTER);
        
        nome = new JTextField(20);
        MaskFormatter formatTelefone = new MaskFormatter("(##) ####-####");
        formatTelefone.setValidCharacters("0123456789");
        telefone = new JFormattedTextField(formatTelefone);
        endereco = new JTextField(20);
        
        Box boxResponsavel = Box.createVerticalBox();
        
        boxResponsavel.add( new JLabel("Nome"));
        boxResponsavel.add(nome);
        boxResponsavel.add(new JLabel("Telefone"));
        boxResponsavel.add(telefone);
        boxResponsavel.add(new JLabel("Endereço"));
        boxResponsavel.add(endereco);
        boxResponsavel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Responsavel", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, Font.getFont("serif"), Color.blue));
        HelpView.addItem(painelNovo, boxResponsavel, 1, 5, 1, 1, GridBagConstraints.CENTER);
        
        okButton = new JButton("Cadastrar");
        okButton.addActionListener(listener);
        HelpView.addItem(painelNovo, okButton, 0, 6, 2, 1, GridBagConstraints.CENTER);
        
        voltar = new JButton("Voltar");
        voltar.addActionListener(listener);
        HelpView.addItem(painelNovo, voltar, 0, 7, 2, 1, GridBagConstraints.CENTER);
        
        getContentPane().add(painelNovo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HelpView.configurarFrame(this);
    }
    
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event = e.getSource();
            if(event == okButton){
                try {
                    setEquipamento(
                            new Equipamento(getNomeEquip(), getPeso(), getPreco(), getNumSerie(), new Local(getCodigo(),getSessao()), new Responsavel(getNome(), getTelefone(), getEndereco()))
                    );
                    if(new CadastrarEquipControl().cadastrarEquipamento(getEquipamento())){
                        JOptionPane.showMessageDialog(null, "CADASTRADO COM SUCESSO!!!");
                    }else{
                        JOptionPane.showMessageDialog(null, "NÃO FOI CADASTRADO!!!");
                    }
                    show();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }else if(event == voltar){
                    janelaAnterior.setVisible(true);
                    dispose();   
                }
        }
    };

    KeyAdapter keyEvent = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent evt){
            String caracteres="0987654321.";
            if(!caracteres.contains(evt.getKeyChar()+"")){
                evt.consume();
            }
        }
    };
    
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getNomeEquip() {
        return this.nomeEquip.getText();
    }

    public double getPreco() {
        return Double.parseDouble(this.preco.getText());
    }

    public double getPeso() {
        return Double.parseDouble(this.peso.getText());
    }

    public String getNumSerie() {
        return this.numSerie.getText();
    }

    public String getCodigo() {
        return this.codigo.getText();
    }

    public int getSessao() {
        return Integer.parseInt(this.sessao.getText());
    }

    public String getNome() {
        return this.nome.getText();
    }

    public String getTelefone() {
        return this.telefone.getText();
    }

    public String getEndereco() {
        return this.endereco.getText();
    }
}
