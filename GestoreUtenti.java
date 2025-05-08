package prova_14;

import java.io.*;
import java.util.*;

public class GestoreUtenti {

    private static final String FILE_PATH = "utenti.txt";
    private static List<Utente> utenti = new ArrayList<>();

    public static void caricaUtenti() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Utente utente = new Utente(parts[0], parts[1], parts[2], parts[3]);
                    utenti.add(utente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salvaUtenti() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Utente utente : utenti) {
                writer.write(utente.getEmail() + "," + utente.getPassword() + "," + utente.getNome() + "," + utente.getCognome());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Utente login(String email, String password) {
        for (Utente utente : utenti) {
            if (utente.getEmail().equals(email) && utente.getPassword().equals(password)) {
                return utente;
            }
        }
        return null;
    }

    public static boolean registra(String email, String password, String nome, String cognome) {
        if (getUtenteByEmail(email) != null) {
            return false; 
        }
        Utente utente = new Utente(email, password, nome, cognome);
        utenti.add(utente);
        salvaUtenti();
        return true;
    }

    // Recupera utente tramite email
    public static Utente getUtenteByEmail(String email) {
        for (Utente utente : utenti) {
            if (utente.getEmail().equals(email)) {
                return utente;
            }
        }
        return null;
    }
}
