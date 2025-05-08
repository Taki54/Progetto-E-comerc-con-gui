package prova_14;

import java.util.*;

public class GestoreOrdini {
    private static Map<Integer, Ordine> ordini = new HashMap<>();

    public static double calcolaTotaleOrdini(int idUtente) {
        return ordini.values().stream()
                .filter(o -> o.getIdUtente() == idUtente)
                .mapToDouble(Ordine::getTotale)
                .sum();
    }

    public static void visualizzaOrdiniAttivi(int idUtente) {
        ordini.values().stream()
                .filter(o -> o.getIdUtente() == idUtente && !o.isCompletato())
                .forEach(System.out::println);
    }

    public static void aggiungiOrdine(Ordine ordine) {
        ordini.put(ordine.getId(), ordine);
    }

    public static boolean disdiciOrdine(int idOrdine, int idUtente) {
        Ordine ordine = ordini.get(idOrdine);
        if (ordine != null && ordine.getIdUtente() == idUtente && !ordine.isCompletato()) {
            ordine.setCompletato(true);
            return true;
        }
        return false;
    }

	public static void getOrdini() {
		//
		
	}

	public static List<Ordine> getOrdiniAttivi(int id) {
		
		return null;
	}
}
