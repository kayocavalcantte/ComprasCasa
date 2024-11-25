public class Carro extends Automovel implements Transportavel{
    private int lugar;

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public Carro(String marca, String modelo, String placa, int lugar) {
        super(marca, modelo, placa);
        this.lugar = lugar;
    }


    @Override
    public void tantosLugares() {
        System.out.println("O Carro possui " + getLugar() + " lugar(es)");
    }
}
