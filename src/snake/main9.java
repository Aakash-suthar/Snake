package snake;

import java.awt.Color;

import javax.swing.*;

public class main9 {

	public static int first=0;
	public static void main(String[] e) {
		JFrame f=new JFrame();
		//f.setBounds(50,30,350,310);
		f.setBackground(Color.DARK_GRAY);
		f.setVisible(true);
		f.setSize(350, 300);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		if(first==0)
		{
		f.setContentPane(new panel2());
		}
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
