package prova_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Utente utenteAutenticato;
    private List<Prodotto> listaProdotti;  

    private JTextField emailField;
    private JPasswordField passwordField;

    private JTextField regEmailField;
    private JPasswordField regPasswordField;
    private JTextField regNomeField;
    private JTextField regCognomeField;
  
    private JButton btnVisualizzaProdotti;
    private JButton btnVisualizzaOrdiniAttivi;
    private JButton btnEffettuaOrdine;
    private JButton btnModificaDati;
    private JButton btnLogout;

    public Main() {
        
        GestoreUtenti.caricaUtenti();
        GestoreProdotti.getProdotti(); 
        GestoreOrdini.getOrdini();

        frame = new JFrame("Gestione Utenti e Ordini");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

       
        cardPanel.add(creaLoginPanel(), "Login");
        cardPanel.add(creaRegisterPanel(), "Register");
        cardPanel.add(creaMainMenuPanel(), "MainMenu");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel creaLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                Utente utente = GestoreUtenti.login(email, password);
                if (utente != null) {
                    utenteAutenticato = utente; // Salva l'utente autenticato
                    JOptionPane.showMessageDialog(frame, "Login riuscito!");
                    cardLayout.show(cardPanel, "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(frame, "Email o password errati.");
                }
            }
        });

        JButton registerButton = new JButton("Registrati");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Register");
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        return panel;
    }

    private JPanel creaRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel emailLabel = new JLabel("Email:");
        regEmailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        regPasswordField = new JPasswordField();
        JLabel nomeLabel = new JLabel("Nome:");
        regNomeField = new JTextField();
        JLabel cognomeLabel = new JLabel("Cognome:");
        regCognomeField = new JTextField();

        JButton registerButton = new JButton("Registrati");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = regEmailField.getText();
                String password = new String(regPasswordField.getPassword());
                String nome = regNomeField.getText();
                String cognome = regCognomeField.getText();

                boolean success = GestoreUtenti.registra(email, password, nome, cognome);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Registrazione completata!");
                    cardLayout.show(cardPanel, "Login");
                } else {
                    JOptionPane.showMessageDialog(frame, "Errore durante la registrazione.");
                }
            }
        });

        panel.add(emailLabel);
        panel.add(regEmailField);
        panel.add(passwordLabel);
        panel.add(regPasswordField);
        panel.add(nomeLabel);
        panel.add(regNomeField);
        panel.add(cognomeLabel);
        panel.add(regCognomeField);
        panel.add(registerButton);

        return panel;
    }

    private JPanel creaMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        btnVisualizzaProdotti = new JButton("Visualizza Prodotti");
        btnVisualizzaProdotti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaProdotti();
            }
        });

        btnVisualizzaOrdiniAttivi = new JButton("Visualizza Ordini Attivi");
        btnVisualizzaOrdiniAttivi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaOrdiniAttivi();
            }
        });

        btnEffettuaOrdine = new JButton("Effettua Nuovo Ordine");
        btnEffettuaOrdine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaNuovoOrdine();
            }
        });

        btnModificaDati = new JButton("Modifica Dati");
        btnModificaDati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaDati();
            }
        });

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        panel.add(btnVisualizzaProdotti);
        panel.add(btnVisualizzaOrdiniAttivi);
        panel.add(btnEffettuaOrdine);
        panel.add(btnModificaDati);
        panel.add(btnLogout);

        return panel;
    }

    private void visualizzaProdotti() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Prodotto prodotto : GestoreProdotti.getProdotti()) {
            JButton prodottoButton = new JButton(prodotto.getNome() + " - €" + prodotto.getPrezzo());
            prodottoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Hai selezionato: " + prodotto.getNome());
                }
            });
            panel.add(prodottoButton);
        }

        JOptionPane.showMessageDialog(frame, panel, "Seleziona un Prodotto", JOptionPane.PLAIN_MESSAGE);
    }

    private void visualizzaOrdiniAttivi() {
        // Mostra gli ordini attivi dell'utente
        if (utenteAutenticato != null) {
            List<Ordine> ordini = GestoreOrdini.getOrdiniAttivi(utenteAutenticato.getId());
            StringBuilder sb = new StringBuilder();
            for (Ordine ordine : ordini) {
                sb.append(ordine.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "Ordini Attivi", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void effettuaNuovoOrdine() {
        // Effettua un nuovo ordine
        if (utenteAutenticato != null) {
            JOptionPane.showMessageDialog(frame, "Nuovo ordine effettuato!");
        }
    }

    private void modificaDati() {
        
        String nuovoNome = JOptionPane.showInputDialog(frame, "Inserisci nuovo nome:");
        String nuovoCognome = JOptionPane.showInputDialog(frame, "Inserisci nuovo cognome:");

        utenteAutenticato.setNome(nuovoNome);
        utenteAutenticato.setCognome(nuovoCognome);
        GestoreUtenti.salvaUtenti();

        JOptionPane.showMessageDialog(frame, "Dati modificati con successo!");
    }

    private void logout() {
        
        utenteAutenticato = null;
        cardLayout.show(cardPanel, "Login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
