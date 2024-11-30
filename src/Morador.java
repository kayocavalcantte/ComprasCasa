import Interface.Comprar;

public class Morador extends Pessoa implements Comprar {
    private boolean comprou = false;

    public boolean isComprou() {
        return comprou;
    }

    public void setComprou(boolean comprou) {
        this.comprou = comprou;
    }

    public Morador(String nome, int idade, boolean comprou) {
        super(nome, idade);
        this.comprou = comprou;
    }

    @Override
    public String comprouAlgo() {
        if (comprou != false){
            return "O morador fez uma compra";
        }else {
            return "O morador nao comprou nada";
        }
    }
}
