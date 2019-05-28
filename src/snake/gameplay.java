package snake;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class gameplay extends JPanel implements KeyListener,ActionListener{
	private ImageIcon title;
	private int[] snakexlenght=new int[750];
	private int[] snakeylenght=new int[750];
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	private boolean right=false;
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;
	private ImageIcon upmouth;
	private Timer timer;
	private int delay=100;
	private ImageIcon snakebody;
	private int snakelenght=3;
	private int move=0;
	public gameplay(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		
	}
	public void paint(Graphics g)
	{
		if(move==0){
			snakexlenght[2]=50;
			snakexlenght[1]=75;
			snakexlenght[0]=100;
			snakeylenght[2]=100;
			snakeylenght[1]=100;
			snakeylenght[0]=100;
			
		}
		//draw title image borders
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		//draw title image
		title=new ImageIcon("snaketitle.jpg");
		title.paintIcon(this, g, 25, 11);
		//draw border for play area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		//draw background for the gameplay
		g.setColor(Color.BLACK);
		g.fillRect(	25, 75, 850, 575);
		//startmouth
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);
		for(int a=0;a<snakelenght;a++)
		{
			if(a==0 && right)
			{
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
			}
			if(a==0 && left)
			{
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
			}
			if(a==0 && up)
			{
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
			}
			if(a==0 && down)
			{
				downmouth=new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
			}
			if(a!=0)
			{
					snakebody=new ImageIcon("snakeimage.png");
					snakebody.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
			}
				
		}

		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int r=snakelenght-1;r>=0;r--)
			{
				snakeylenght[r+1]=snakeylenght[r];
			}
			for(int r=snakelenght;r>=0;r--)
			{
				if(r==0)
				{
				snakexlenght[r]=snakexlenght[r]+25;
				}
				else
				{
				snakexlenght[r]=snakexlenght[r-1];
				}
				if(snakexlenght[r]>850)
				{
					snakexlenght[r]=25;
				}
			}
			repaint();
		}
		if(left)
		{
			for(int r=snakelenght-1;r>=0;r--)
			{
				snakeylenght[r+1]=snakeylenght[r];
			}
			for(int r=snakelenght;r>=0;r--)
			{
				if(r==0)
				{
				snakexlenght[r]=snakexlenght[r]-25;
				}
				else
				{
				snakexlenght[r]=snakexlenght[r-1];
				}
				if(snakexlenght[r]<25)
				{
					snakexlenght[r]=850;
				}
			}
				repaint();
			
		}
		if(up)
		{
			for(int r=snakelenght-1;r>=0;r--)
			{
				snakexlenght[r+1]=snakexlenght[r];
			}
			for(int r=snakelenght;r>=0;r--)
			{
				if(r==0)
				{
				snakeylenght[r]=snakeylenght[r]-25;
				}
				else
				{
				snakeylenght[r]=snakeylenght[r-1];
				}
				if(snakeylenght[r]<75)
				{
					snakeylenght[r]=650;
				}
			}
				repaint();
			
		}
			
		if(down)
		{
			for(int r=snakelenght-1;r>=0;r--)
			{
				snakexlenght[r+1]=snakexlenght[r];
			}
			for(int r=snakelenght;r>=0;r--)
			{
				if(r==0)
				{
				snakeylenght[r]=snakeylenght[r]+25;
				}
				else
				{
				snakeylenght[r]=snakeylenght[r-1];
				}
				if(snakeylenght[r]>625)
				{
					snakexlenght[r]=75;
				}
			}
				repaint();
			}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			move++;
			right=true;
			if(!left)
			{
				right=true;
			}
			else
			{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			move++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			move++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
			}
			right=false;
			left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			move++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				down=false;
				up=true;
			}
			right=false;
			left=false;
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
