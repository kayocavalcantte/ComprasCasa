public abstract class Automovel {
    private int nome;
    private int modelo;
    private int placa;

    public Automovel(int nome, int modelo, int placa) {
        this.nome = nome;
        this.modelo = modelo;
        this.placa = placa;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Automovel{" +
                "nome=" + nome +
                ", modelo=" + modelo +
                ", placa=" + placa +
                '}';
    }
}
