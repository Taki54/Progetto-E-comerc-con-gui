package prova_14;

public class Utente {
    private int id;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private int idCarrello;

    public Utente(String email, String password, String nome, String cognome) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.idCarrello = idCarrello;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Email: " + email + ", Nome: " + nome + ", Cognome: " + cognome;
    }
}
