package prova_14;

public class Carrello {
    private int id;
    private double totale;

    public Carrello(int id) {
        this.id = id;
        this.totale = 0.0;
    }

    public int getId() {
        return id;
    }

    public double getTotale() {
        return totale;
    }

    public void aggiungiProdotto(double prezzo) {
        totale += prezzo;
    }

    public void rimuoviProdotto(double prezzo) {
        totale -= prezzo;
    }
}
