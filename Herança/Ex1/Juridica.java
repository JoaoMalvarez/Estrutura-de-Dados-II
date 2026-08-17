public class Juridica extends Pessoa {
    public String cnpj;

    public Juridica (int identificador, String nome, String cnpj) {
        super(identificador, nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}