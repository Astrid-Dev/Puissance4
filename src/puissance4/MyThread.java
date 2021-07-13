package puissance4;

public class MyThread extends Thread{
	
	public void run()
	{
		System.out.println("debut");
		try {
			this.sleep(1000);
			System.out.println("Fin");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
