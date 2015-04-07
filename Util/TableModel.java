package Util;

import java.util.List;

public interface TableModel<E> {
    public void add(E item);
    public void remove(int linha);
    public void limpar();
    public void listar(List<E> item);
    public boolean isEmpty();
}
