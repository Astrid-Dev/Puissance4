package puissance4;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GagneDialog extends JDialog{

	public GagneDialog(JFrame parent, String title, boolean modal, String nom)
	{
		this.setSize(300,  150);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		JLabel photo = new JLabel(new ImageIcon("winner.jpg"));
		JLabel texte = new JLabel("<html>" + nom.toUpperCase() + ",<br>" + "vous avez gagné la partie ! <html>", JLabel.CENTER);
		texte.setFont(new Font("Algeria", Font.ITALIC|Font.BOLD, 14));
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		this.getContentPane().add(photo, BorderLayout.WEST);
		this.getContentPane().add(texte, BorderLayout.CENTER);
		this.getContentPane().add(ok, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
}
