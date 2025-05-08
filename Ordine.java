package prova_14;

public class Ordine {
    private static int contatore = 1;
    private int id;
    private int idProdotto;
    private int quantita;
    private int idUtente;
    private boolean completato;

    public Ordine(int idProdotto, int quantita, int idUtente) {
        this.id = contatore++;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
        this.idUtente = idUtente;
        this.completato = false;
    }

    public int getId() {
        return id;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public boolean isCompletato() {
        return completato;
    }

    public void setCompletato(boolean completato) {
        this.completato = completato;
    }

    public double getTotale() {
        return quantita * 10.0;
    }

    @Override
    public String toString() {
        return "Ordine ID: " + id + ", Prodotto ID: " + idProdotto + ", Quantità: " + quantita + ", Completato: " + completato;
    }
}
