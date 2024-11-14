public class Morador extends Pessoa implements Comprar{

    public Morador(String nome, String idade) {
        super(nome, idade);
    }

    @Override
    public boolean comprouAlgo() {
        return false;
    }
}
