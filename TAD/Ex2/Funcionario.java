public class Funcionario {
    // atributos
    private String nome;
    private String sobrenome;
    private float salarioMensal;

    // construtores
    public Funcionario(String nome, String sobrenome, float salarioMensal) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        if (salarioMensal < 0) 
            throw new RuntimeException("Salário mensal não pode ser negativo");
        this.salarioMensal = salarioMensal;
    }

    // getters

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public float getSalarioMensal() {
        return salarioMensal;
    }

    // setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setSalarioMensal(float salarioMensal) {
        if (salarioMensal < 0) 
            throw new RuntimeException("Salário mensal não pode ser negativo");
        this.salarioMensal = salarioMensal;
    }
    
    // métodos pedidos
    public void aumentarSalario() {
        salarioMensal = salarioMensal * 1.10f; // "Aumentar o sal´ario do funcion´ario em 10%"
    }

    public float salarioAnual() {
        float x;
        x = salarioMensal * 13;
        return x;
    }
}