package prova_14;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class GestoreProdotti {

    private static final String FILE_PATH = "ordini.txt";
    private static List<String> ordini = new ArrayList<>();

    public static void caricaOrdini() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ordini.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salvaOrdini() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String ordine : ordini) {
                writer.write(ordine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aggiungiOrdine(String ordine) {
        ordini.add(ordine);
        salvaOrdini();
    }

    public static List<String> getOrdini() {
        return ordini;
    }

	public static Prodotto[] getProdotti() {
		
		return null;
	}
}
