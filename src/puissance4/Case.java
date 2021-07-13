package puissance4;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Case extends JPanel{

	private Pion pion = null;
	
	public Case(Couleur c)
	{
		super();
		pion = new Pion(0,  0,  c);
		this.setBackground(new Color(50,  50,  255));
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(pion.getTypePion().getCouleur());
		g.fillOval(pion.getX(), pion.getY(), pion.getTaillePion(), pion.getTaillePion());
	}
	
	public Pion getPion()
	{
		return this.pion;
	}
	
	public void setPion(Couleur c)
	{
		this.pion.setTypePion(c);
	}
}
