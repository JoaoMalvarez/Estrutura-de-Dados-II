public class Motor {
    public int cilindradas;
    public int potencia;

    public Motor(int cilindradas, int potencia) {
        this.cilindradas = cilindradas;
        this.potencia = potencia;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void exibirDadosMotor() {
        System.out.println("Cilindradas: " + cilindradas);
        System.out.println("Potência: " + potencia);
    }
}