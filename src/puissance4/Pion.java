package puissance4;

public class Pion {

	private int x;
	private int y;
	
	private final int TAILLEPION = 50;
	private Couleur typePion = null;
	
	public Pion(int x, int y, Couleur typ)
	{
		this.x = x;
		this.y = y;
		this.typePion = typ;
	}
	
	public Couleur getTypePion()
	{
		return this.typePion;
	}
	
	public void setTypePion(Couleur c)
	{
		this.typePion = c;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getTaillePion()
	{
		return this.TAILLEPION;
	}
}
