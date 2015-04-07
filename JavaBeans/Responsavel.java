package JavaBeans;

public class Responsavel {
    private String nome;
    private String endereco;
    private String telefone;

    public Responsavel(String nome, String telefone, String endereco){
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
