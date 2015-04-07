package JavaBeans;

public class Equipamento {
    private String nomeEquip;
    private double peso;
    private double preco;
    private String numSerie;
    public Local local;
    public Responsavel responsavel;

    public Equipamento(String nomeEquip, double peso, double preco, String numSerie, Local local, Responsavel responsavel){
        setNomeEquip(nomeEquip);
        setLocal(local);
        setNumSerie(numSerie);
        setPeso(peso);
        setPreco(preco);
        setResponsavel(responsavel);
    }

    public Equipamento() {
    }
    
    public String getNomeEquip() {
        return nomeEquip;
    }

    public void setNomeEquip(String nome) {
        this.nomeEquip = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}

