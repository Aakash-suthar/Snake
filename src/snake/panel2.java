package snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class panel2 extends JPanel{
	private JButton b1;
	private JButton b2;
	private JLabel label,label2;
	private int h=6;
	private int h2=1;
	private String[] spe={"GOD","Ultra","Mega","Very Hard","HARD","Medium","Normal","Bachkanda","Noob","Mat Khel"};
	private String[] level={"  1","  2","  3","  4"};
	@SuppressWarnings("rawtypes")
	private JComboBox li;
	@SuppressWarnings("rawtypes")
	private JComboBox l2;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public panel2(){
		setLayout(null);
		setBackground(Color.BLACK);
		b1=new JButton("START");
		b2=new JButton("EXIT");
		li=new JComboBox(spe);
		l2=new JComboBox(level);
		li.setBackground(Color.WHITE);
		b1.setBackground(Color.GREEN);
		l2.setForeground(Color.BLACK);
		l2.setBackground(Color.WHITE);
		b2.setBackground(Color.GREEN);
		label=new JLabel("Choose Speed : ");
		label.setForeground(Color.ORANGE);
		label2=new JLabel("Choose Level : ");
		label2.setForeground(Color.ORANGE);
		b1.setFont(new Font("Serif",Font.BOLD,20));
		b1.setBounds(80, 80, 180, 40);
		b2.setFont(new Font("Serif",Font.BOLD,20));
		b2.setBounds(95, 130, 150, 40);
		label.setFont(new Font("Serif",Font.BOLD,14));
		label2.setFont(new Font("Serif",Font.BOLD,14));
		label.setBounds(240,0,100,40);
		label2.setBounds(10,0,100,40);
		li.setBounds(240, 30, 80, 20);
		l2.setBounds(10, 30, 50, 20);
		l2.setVisible(true);
		li.setVisible(true);
		b1.setVisible(true);
		b2.setVisible(true);
		label2.setVisible(true);
		setSize(350,350);
		li.setSelectedIndex(6);
		l2.setSelectedIndex(0);
		li.addItemListener(
				new ItemListener(){//speed selection
					
					public void itemStateChanged(ItemEvent f) {
						//if(f.getStateChange()==ItemEvent.SELECTED)
						//{
							h=li.getSelectedIndex();
						//}
					}
				});
		l2.addItemListener(new ItemListener(){//new level selection
			public void itemStateChanged(ItemEvent u) {
				h2=l2.getSelectedIndex()+1;
			}
			
		});
		b1.addActionListener(new handel());
		b2.addActionListener(new handel());
		add(b1);
		add(label2);
		add(b2);
		add(l2);
	    add(li);
	    add(label);
	}
	private class handel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton r=(JButton)e.getSource();
			if(r.equals(b1))
			{
			JFrame g=new JFrame();
			g.setContentPane(new gameplay2(h,h2));
			g.setVisible(true);
			g.setResizable(false);
			g.setSize(350, 350);
			g.setLocationRelativeTo(null);
			}
			if(r.equals(b2)){
			  System.exit(0);
			}
				
			
		}

		
	}

}
