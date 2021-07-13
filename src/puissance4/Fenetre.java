package puissance4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Fenetre extends JFrame{

	
	private static Case[][] palette = new Case[6][7];
	private JPanel panneau = new JPanel();
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu jeu = new JMenu("Jeu");
	private JMenuItem nouvellePartie = new JMenuItem("Nouvelle partie"),
			enregistrerPartie = new JMenuItem("Enregistrer la partie"),
			chargerPartie = new JMenuItem("Charger une partie"),
			quitter = new JMenuItem("Quitter");
	
	private JButton col1 = new JButton("Col 1"),
			col2 = new JButton("Col 2"),
			col3 = new JButton("Col 3"),
			col4 = new JButton("Col 4"),
			col5 = new JButton("Col 5"),
			col6 = new JButton("Col 6"),
			col7 = new JButton("Col 7");
	
	private JPanel panAcceuil = new JPanel(),
			panJeu = new JPanel(),
			panNom = new JPanel();
	
	public static int[] maxCol = {6, 6, 6, 6, 6, 6, 6};
	
	private static boolean aGagne = false, jeuTermine = false;
	
	private JTextField joueur1 = new JTextField(),
			joueur2 = new JTextField();
	
	private JButton retour = new JButton("Retour"),
			commencer = new JButton("Commencer");
	
	private String nomJoueur1 = "",
			nomJoueur2 = "";
	
	private int numJoueur = 1;
	
	private boolean estNouvellePartie;
	
	private final Color couleurJoueur1 = Color.yellow,
			couleurJoueur2 = Color.RED;
	
	private static JLabel consigne = new JLabel("", JLabel.CENTER);
	
	private boolean[] statutBoutons = {true, true, true, true, true, true,true};
	
	private Couleur couleurJoueur = null;
	
	public Fenetre(boolean b)
	{
		this.setTitle("Puissance 4");
		this.setSize(500,  500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.estNouvellePartie = b;
		initMenuBar();
		initComponent();
		initListener();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(InstantiationException e ) {}
		catch(ClassNotFoundException e) {}
		catch(UnsupportedLookAndFeelException e) {}
		catch(IllegalAccessException e) {}
		
		this.setVisible(true);
	}
	
	private void initComponent()
	{
		panneau.setLayout(new GridLayout(6, 7));
		panneau.setPreferredSize(new Dimension(420, 360));
		panneau.setBackground(Color.BLUE);
		
		initialse();
		
		for(int i = 0; i < palette.length; i++)
		{
			for(int j = 0; j < palette[i].length; j++)
			{
				panneau.add(palette[i][j]);
			}
		}
		
		JPanel panBouton = new JPanel();
		GridLayout gl = new GridLayout(1, 7);
		gl.setHgap(3);
		gl.preferredLayoutSize(new Container());
		
		panBouton.setLayout(gl);
		panBouton.add(col1);
		panBouton.add(col2);
		panBouton.add(col3);
		panBouton.add(col4);
		panBouton.add(col5);
		panBouton.add(col6);
		panBouton.add(col7);
		
		Font font = new Font("Algerian", Font.PLAIN, 10);
		col1.setFont(font);
		col2.setFont(font);
		col3.setFont(font);
		col4.setFont(font);
		col5.setFont(font);
		col6.setFont(font);
		col7.setFont(font);
		
		panJeu.setLayout(null);
		JPanel panConsigne = new JPanel();
		panConsigne.add(consigne);
		panConsigne.setBounds(0, 0, this.getWidth(), 30);
		panConsigne.setBackground(Color.black);
		panJeu.add(panConsigne);
		panneau.setBounds(40,  40,  420,  360);
		panJeu.add(panneau);
		panBouton.setBounds(30, 415, 440, 25);
		panJeu.add(panBouton);
		
		JLabel lab1 = new JLabel("Nom du joueur 1"),
				lab2 = new JLabel("Nom du joueur 2"),
				lab3 = new JLabel(new ImageIcon("jaune.jpg")),
				lab4 = new JLabel(new ImageIcon("rouge.jpg"));
		
		panNom.setLayout(null);
		lab1.setBounds(100, 150, 100, 30);
		panNom.add(lab1);
		joueur1.setBounds(220, 150, 150, 30);
		panNom.add(joueur1);
		lab3.setBounds(390, 150, 30, 30);
		panNom.add(lab3);
		
		lab2.setBounds(100, 220, 100, 30);
		panNom.add(lab2);
		joueur2.setBounds(220, 220, 150, 30);
		panNom.add(joueur2);
		lab4.setBounds(390, 220, 30, 30);
		panNom.add(lab4);
		
		retour.setBounds(200, 290, 80, 20);
		panNom.add(retour);
		commencer.setBounds(310,  290,  110,  20);
		panNom.add(commencer);
		
		if(estNouvellePartie)
		{
			this.getContentPane().add(panNom);
		}
		else
		{
			JLabel lab = new JLabel(new ImageIcon("acceuil.jpg"));
			panAcceuil.setLayout(null);
			lab.setBounds(50, 99, 400, 262);
			panAcceuil.setBackground(new Color(128, 255, 128));
			panAcceuil.add(lab);
			this.getContentPane().add(panAcceuil);
		}
		
		
	}
	
	private void initMenuBar()
	{
		menuBar.add(jeu);
		jeu.add(nouvellePartie);
		jeu.add(enregistrerPartie);
		jeu.add(chargerPartie);
		jeu.add(quitter);
		
		this.setJMenuBar(menuBar);
	}
	
	private void initListener()
	{
		nouvellePartie.addActionListener(new NouvellePartieListener());
		
		retour.addActionListener(new RetourListener());
		commencer.addActionListener(new CommencerListener());
		
		col1.addActionListener(new BoutonListener());
		col2.addActionListener(new BoutonListener());
		col3.addActionListener(new BoutonListener());
		col4.addActionListener(new BoutonListener());
		col5.addActionListener(new BoutonListener());
		col6.addActionListener(new BoutonListener());
		col7.addActionListener(new BoutonListener());
	}
	
	private class NouvellePartieListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dispose();
			new Fenetre(true);
		}
		
	}
	
	private class RetourListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dispose();
			new Fenetre(false);
		}
		
	}
	
	private class CommencerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(joueur1.getText().equals("") || joueur2.getText().equals(""))
			{
				if(joueur1.getText().equals("") && joueur2.getText().equals(""))
				{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Veuillez renseigner le noms de chaque joueur!", "Champs des noms vides", JOptionPane.ERROR_MESSAGE);
				}
				else if(joueur1.getText().equals("") && !joueur2.getText().equals(""))
				{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Veuillez renseigner le nom du joueur 1!", "Champs des noms vides", JOptionPane.ERROR_MESSAGE);
				}
				else if(!joueur1.getText().equals("") && joueur2.getText().equals(""))
				{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Veuillez renseigner le nom du joueur 2!", "Champs des noms vides", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else if(!joueur1.getText().equals("") && !joueur2.getText().equals(""))
			{
				if(joueur1.getText().equals(joueur2.getText()))
				{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Veuillez renseigner des noms différents pour les joueurs!", "Noms identiques", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					nomJoueur1 = joueur1.getText();
					nomJoueur2 = joueur2.getText();
					getContentPane().removeAll();
					getContentPane().add(panJeu);
					revalidate();
					
					apreteBoutonEtConsigne();
					setConsigne();
				}
			}
		}
		
	}
	
	private void setConsigne()
	{
		if(numJoueur == 1)
		{
			consigne.setText(nomJoueur1.toUpperCase() + ", c'est à vous de jouer !");
		}
		else {
			consigne.setText(nomJoueur2.toUpperCase() + ", c'est à vous de jouer !");
		}
		consigne.setFont(new Font("Times new Roman", Font.BOLD|Font.ITALIC, 14));
	}
	
	private void apreteBoutonEtConsigne()
	{
		if(numJoueur == 1)
		{
			changeCouleurBouton(couleurJoueur1);
		}
		else if(numJoueur == 2)
		{
			changeCouleurBouton(couleurJoueur2);
		}
	}
	
	private void changeCouleurBouton(Color c)
	{
		col1.setBackground(c);
		col2.setBackground(c);
		col3.setBackground(c);
		col4.setBackground(c);
		col5.setBackground(c);
		col6.setBackground(c);
		col7.setBackground(c);
		consigne.setForeground(c);
	}
	
	public static void setPionDuTableau(Couleur p, int i, int j)
	{
		palette[i][j].getPion().setTypePion(p);;
	}
	
	public static Pion getPionDuTableau(int i, int j)
	{
		return palette[i][j].getPion();
	}
	
	public static boolean getAGagne() {
		return aGagne;
	}
	
	public static boolean getJeuTermine() {
		return jeuTermine;
	}
	
	public static void setAGagne(boolean b) {
		aGagne = b;
	}
	
	public static void setJeuTermine(boolean b) {
		jeuTermine = b;
	}
	
	private class BoutonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int col = 0;
			
			if(numJoueur == 1)
			{
				couleurJoueur = Couleur.JAUNE;
				numJoueur = 2;
			}
			else {
				couleurJoueur = Couleur.ROUGE;
				numJoueur = 1;
			}
			
			final int plafond = -1;
			if((JButton)arg0.getSource() == col1)
			{
				col = 0;
			}
			else if((JButton)arg0.getSource() == col2)
			{
				col = 1;
			}
			else if((JButton)arg0.getSource() == col3)
			{
				col = 2;
			}
			else if((JButton)arg0.getSource() == col4)
			{
				col = 3;
			}
			else if((JButton)arg0.getSource() == col5)
			{
				col = 4;
			}
			else if((JButton)arg0.getSource() == col6)
			{
				col = 5;
			}
			else if((JButton)arg0.getSource() == col7)
			{
				col = 6;
			}
			desactiveBoutons();
			
			setPionDuTableau(couleurJoueur, 0, col);
			panneau.repaint();
			palette[0][col].repaint();
			
			for(int i = 0; i < maxCol[col]-1; i++)
			{
				Couleur temp = getPionDuTableau(i, col).getTypePion();
				getPionDuTableau(i,  col).setTypePion(getPionDuTableau(i+1, col).getTypePion());
				getPionDuTableau(i+1,  col).setTypePion(temp);
				panneau.repaint();
				palette[i][col].repaint();
				
			}
			maxCol[col] -= 1;
			if(maxCol[col] == 0) {
				statutBoutons[col] = false;
			}
			panneau.repaint();
			
			if(!gameOver() && !joueurAGagne(col))
			{
				setConsigne();
				apreteBoutonEtConsigne();
				activeBoutons();
			}
			else if(gameOver())
			{
				consigne.setText("Aucun vainqueur, Partie terminée !");
				consigne.setForeground(Color.ORANGE);
				new PerduDialog(null,  "", true);
				desactiveJeu();
			}
			else if(joueurAGagne(col))
			{
				String nom = "";
				if(numJoueur == 2) {
					nom = nomJoueur1;
				}
				else {
					nom = nomJoueur2;
				}
				consigne.setText(nom + ", est le vainqueur de cette partie !");
				consigne.setForeground(Color.green);
				new GagneDialog(null,  "", true, nom);
			}			
			
		}
		
	}
	
	
	private void desactiveBoutons() {
		col1.setEnabled(false);
		col2.setEnabled(false);
		col3.setEnabled(false);
		col4.setEnabled(false);
		col5.setEnabled(false);
		col6.setEnabled(false);
		col7.setEnabled(false);
	}
	
	private void activeBoutons() {
		col1.setEnabled(statutBoutons[0]);
		col2.setEnabled(statutBoutons[1]);
		col3.setEnabled(statutBoutons[2]);
		col4.setEnabled(statutBoutons[3]);
		col5.setEnabled(statutBoutons[4]);
		col6.setEnabled(statutBoutons[5]);
		col7.setEnabled(statutBoutons[6]);
		
	}
	
	public static void initialse()
	{
		for(int i = 0; i < palette.length; i++)
		{
			for(int j = 0; j < palette[0].length; j++)
			{
				palette[i][j] = new Case(Couleur.VIDE);
			}
		}
		maxCol[0] = 6;
		maxCol[1] = 6;
		maxCol[2] = 6;
		maxCol[3] = 6;
		maxCol[4] = 6;
		maxCol[5] = 6;
		maxCol[6] = 6;
	}
	
	private boolean gameOver() {
		if((maxCol[0] == maxCol[1]) && (maxCol[1] == maxCol[2]) && (maxCol[2] == maxCol[3]) && (maxCol[3] == maxCol[4]) && (maxCol[4] == maxCol[5]) && (maxCol[5] == maxCol[6] && (maxCol[0] == 0)))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean joueurAGagne(int c)
	{
		boolean result = false;
		int ligne = maxCol[c];
		int col = c;
		if((maxCol[c] <= 2) && ((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne+1][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne+2][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne+3][col].getPion().getTypePion() == couleurJoueur)))
		{
			result = true;
		}
		else
		{
			if((c == 0) && ((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+3].getPion().getTypePion() == couleurJoueur)))
			{
				result = true;
			}
			else if((c == 1) && (((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+3].getPion().getTypePion() == couleurJoueur)) || ((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur))))
			{
				result = true;
			}
			
			else if(
					(c == 2 || c == 3) && (
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+3].getPion().getTypePion() == couleurJoueur)) || 
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur)) || 
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur)) 
					))
			{
				result = true;
			}
			
			else if(
					(c == 4) && ( 
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur)) || 
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur)) 
					))
			{
				result = true;
			}
			
			else if(
					(c == 5) && ( 
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col+1].getPion().getTypePion() == couleurJoueur)) 
					))
			{
				result = true;
			}
			
			else if(
					(c == 6) && ( 
					((palette[ligne][col].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-1].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-2].getPion().getTypePion() == couleurJoueur) && (palette[ligne][col-3].getPion().getTypePion() == couleurJoueur)) 
					))
			{
				result = true;
			}
		}
			
		return result;
	}
	
	private void desactiveJeu() {
		for(int i = 0; i < palette.length; i++)
		{
			for(int j = 0; j < palette[i].length; j++)
			{
				palette[i][j].setEnabled(false);
			}
		}
		panneau.setEnabled(false);
	}
	
}
