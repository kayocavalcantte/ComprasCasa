import Interface.Locomocao;

public abstract class Automovel implements Locomocao {
    private String marca;
    private String  modelo;
    private String  placa;

    public Automovel(String nome, String modelo, String placa) {
        this.marca = nome;
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getNome() {
        return marca;
    }

    public void setNome(String nome) {
        this.marca = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Automovel{" +
                "marca=" + marca +
                ", modelo=" + modelo +
                ", placa=" + placa +
                '}';
    }
}
