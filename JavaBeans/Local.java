package JavaBeans;

public class Local {
    private String codigoPateleira;
    private int numSessao;

    public Local(String codigoPateleira, int numSessao){
        setCodigoPateleira(codigoPateleira);
        setNumSessao(numSessao);
    }
    public String getCodigoPateleira() {
        return codigoPateleira;
    }

    public void setCodigoPateleira(String codigoPateleira) {
        this.codigoPateleira = codigoPateleira;
    }

    public int getNumSessao() {
        return numSessao;
    }

    public void setNumSessao(int numSessao) {
        this.numSessao = numSessao;
    }
    
}
