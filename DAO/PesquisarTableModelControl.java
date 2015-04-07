package DAO;

import Util.TableModel;
import JavaBeans.Equipamento;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PesquisarTableModelControl extends AbstractTableModel implements TableModel<Equipamento>{

    private List<Equipamento> linhas;
    
    private String[] colunas = new String[]{"Número de Série", "Nome", "Preço", "Peso", "Código","Sessão","Nome Responsável","Telefone","Endereco"};
    
    public PesquisarTableModelControl(List<Equipamento> l){
        linhas = new ArrayList(l);
    }

    public PesquisarTableModelControl() {
        linhas = new ArrayList();
    }
    
    public Equipamento getEquipamento(int linhaIndice){
        return linhas.get(linhaIndice);
    }
    
    @Override
    public void add(Equipamento equipamento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(int linha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpar() {
        
        linhas.clear();
        
        fireTableDataChanged();
        
    }
    
    @Override
    public void listar(List<Equipamento> equipamentos) {
        
        linhas.addAll(equipamentos);
        
        fireTableRowsInserted(0, getRowCount() - 1);
    }
    
    @Override
    public boolean isEmpty(){
        return linhas.isEmpty();
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Equipamento equipamento = linhas.get(rowIndex);
        
        switch(columnIndex){
            case 0: return equipamento.getNumSerie();
            case 1: return equipamento.getNomeEquip();
            case 2: return equipamento.getPreco();
            case 3: return equipamento.getPeso();
            case 4: return equipamento.getLocal().getCodigoPateleira();
            case 5: return equipamento.getLocal().getNumSessao();    
            case 6: return equipamento.getResponsavel().getNome();
            case 7: return equipamento.getResponsavel().getTelefone();
            case 8: return equipamento.getResponsavel().getEndereco();
            default: throw new IndexOutOfBoundsException("ColumnIdex out of Bounds");
        }
    }

}
