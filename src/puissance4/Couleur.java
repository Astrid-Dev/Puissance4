package puissance4;

import java.awt.Color;

public enum Couleur {

	VIDE(Color.WHITE),
	ROUGE(Color.RED),
	JAUNE(Color.YELLOW);
	
	private Color coul = null;
	
	Couleur(Color c)
	{
		this.coul = c;
	}
	
	public Color getCouleur()
	{
		return this.coul;
	}
	
}
