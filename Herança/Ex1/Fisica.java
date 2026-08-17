public class Fisica extends Pessoa {
    public String rg;
    public String cpf;

    public Fisica (int identificador, String nome, String rg, String cpf) {
        Pessoa(identificador, nome);
        this.rg = rg;
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}