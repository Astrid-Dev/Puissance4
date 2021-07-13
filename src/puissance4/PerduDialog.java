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

public class PerduDialog extends JDialog{
	
	public PerduDialog(JFrame parent, String title, boolean modal)
	{
		this.setSize(300,  150);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		JLabel photo = new JLabel(new ImageIcon("gameover.jpg"));
		JLabel texte = new JLabel("<html>La partie est termin�e !<br>Il n y a plus de d�placement<html>", JLabel.CENTER);
		texte.setFont(new Font("Algeria", Font.PLAIN, 14));
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
