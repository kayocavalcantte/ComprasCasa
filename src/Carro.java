public class Carro extends Automovel implements Transportavel{

    private boolean transportavel;
    public Carro(int nome, int modelo, int placa) {
        super(nome, modelo, placa);
    }

    @Override
    public boolean ehTransportavel() {
        return transportavel;
    }
}
