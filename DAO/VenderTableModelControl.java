package DAO;

import Util.TableModel;
import JavaBeans.Equipamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VenderTableModelControl extends AbstractTableModel implements TableModel<Equipamento>{
    
    private List<Equipamento> linhas;
    
    private String[] colunas = new String[]{"Número Série", "Peça", "Peso", "Preço"};
    
    public VenderTableModelControl(List<Equipamento> linhas){
        this.linhas = new ArrayList(linhas);
    }

    public VenderTableModelControl() {
        this.linhas = new ArrayList();
    }
    
    public Equipamento getEquipamento(int linhaIndice){
        return linhas.get(linhaIndice);
    }
    
    public List getEquipamentos(){
        return linhas;
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
            case 2: return equipamento.getPeso();
            case 3: return equipamento.getPreco();
            default: throw new IndexOutOfBoundsException("ColumnIdex out of Bounds");
        }
    }

    @Override
    public void add(Equipamento item) {
        linhas.add(item);
        
        int ultimoIndex = getRowCount() - 1;
        
        fireTableRowsInserted(ultimoIndex, ultimoIndex);
    }

    @Override
    public void remove(int linha) {
        linhas.remove(linha);
        
        fireTableRowsDeleted(linha, linha);
    }

    @Override
    public void limpar() {
        
        linhas.clear();
        
        fireTableDataChanged();
    }
    
    @Override
    public void listar(List<Equipamento> equipamentos) {
        limpar();
        
        linhas.addAll(equipamentos);
        
        fireTableRowsInserted(0, getRowCount()-1);
    }
    
}
