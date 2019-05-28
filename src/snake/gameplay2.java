package snake;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.*;
@SuppressWarnings("serial")
public class gameplay2 extends JPanel implements KeyListener,ActionListener{
	File fil=new File("coin.mp3");
	File fil2=new File("er.wav");
	Clip cli,cl2;
	private int cc,c3=3,uu;
	Color[] c={Color.RED,Color.BLUE,Color.ORANGE,Color.LIGHT_GRAY,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.PINK,Color.YELLOW};
	private int first=0,sec=0;
	private Timer t;
	private int lev=1;
	private int score=0;
	private int snakelenght=3;
	private int[] snakex=new int[750];
	private int[] snakey=new int[750];
	private int move=0;
	private boolean right=false;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	private boolean escape=false;
	private int[] pickx={10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100,105,110,115,120,125,130,135,140,145,150,155,160,165,170,175,180,185,190,195
			,200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320};
	private int[] picky={10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100,105,110,115,120,125,130,135,140,145,150,155,160,165,170,175,180,185,190,195
			,200,205,210,215,220,225,230,235,240};
	private Random r=new Random();
	private int xpos=r.nextInt(62);
	private int ypos=r.nextInt(45);
	private int delay;
	public gameplay2(int j,int j2){
		try{
		 cli=AudioSystem.getClip();
			cli.open(AudioSystem.getAudioInputStream(fil));
			cl2=AudioSystem.getClip();
			cl2.open(AudioSystem.getAudioInputStream(fil2));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
		setSize(500, 500);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		lev=j2;
		System.out.println(lev);
		uu=j;
		delay=j*10;
		t=new Timer(delay,this);
		t.start();
		
	}
	
	public void paint(Graphics g)
	{
		if(move==0)//start point
		{
			snakex[0]=40;
			snakey[0]=40;
			snakex[1]=35;
			snakey[1]=40;
			snakex[2]=30;
			snakey[2]=40;
			
		}
		if(snakelenght>c3)//background color change
		{  cc=r.nextInt(8);
		g.setColor(c[cc]);
		c3+=1;		}
		g.fillRect(10, 10, 320, 240);//background set rectangle
		//walls color	
		if(lev==4)//level 4 walls
		{
			g.setColor(Color.WHITE);
			g.fillRect(10, 80, 30, 5);//x=10,15,20,25,30,y=80
			g.fillRect(40, 55, 5, 30);//x=40,y=55,60,65,70,75,80,85
			g.fillRect(45, 55, 30, 5);//x=45,50,55,60,75,y=55
			g.fillRect(75, 35, 5, 25);//x=75,y=35,40,45,50,55,60
			g.fillRect(80, 35, 30, 5);//x=80,85,90,95,100,105,110,y=35
			g.fillRect(110, 10, 5, 30);//x=110,y=10,15,20,25,30,35,40
			//second part
			g.fillRect(10, 120, 60, 5);//x=10,15,20,25,30,35,40,45,50,55,60,65,70,y=120//same1
			g.fillRect(65, 100, 5, 25);//x=65,y=100,105,110,115,120,125
			g.fillRect(70, 100, 30, 5);//x=70,75,80,85,90,95,100,y=100//same5
			g.fillRect(100, 80, 5, 25);//x=100,y=80,85,90,95,100,105
			g.fillRect(105, 80, 50, 5);//x=105,110,115,120,125,130,135,140,145,150,155y=80//same2
			g.fillRect(150, 10, 5, 70);//x=150,y=10,15,20,25,30,35,40,45,50,55,60,65,70,75,80//same3
			//sidebox
			g.fillRect(200, 10, 5, 70);//x=200,y=10,15,20,25,30,35,40,45,50,55,60,65,70,75,80//same3
			g.fillRect(200,80,130,5);//x=200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320,325,330,y=80//same6
			//downpart
			g.fillRect(10, 170, 60, 5);//x=10,15,20,25,30,35,40,45,50,60,65,70,y=170//same1
			g.fillRect(70, 170, 5, 30);//x=70,y=170,175,180,185,190,195,200
			g.fillRect(70, 195, 30, 5);//x=70,75,80,85,90,95,100,y=195//same5
			g.fillRect(100, 195, 5, 20);//x=100,y=195,200,210,215
			g.fillRect(105, 210, 45, 5);//x=105,110,115,120,125,130,135,140,145,150,y=210//same2
			g.fillRect(150, 210, 5, 40);//x=150,y=210,215,220,225,230,235,240,245,250//same7
			//downright box
			g.fillRect(200, 210, 130, 5);//x=200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320,325,330,y=210//same6
			g.fillRect(200, 210, 5, 40);//x=200,y=210,215,220,225,230,235,240,245,250//same7//finish
			//mid box
			g.fillRect(130, 120, 200, 5);//x=130,135,140,145,150,155,160,165,270,275,180,185,190,195,200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320,325,330,y=120//same4
		    g.fillRect(130, 125, 5, 50);//x=130,y=125,130,135,140,145,150,155,160,165,170,175
		    g.fillRect(130, 170, 200, 5);//x=130,130,135,140,145,150,155,160,165,270,275,180,185,190,195,200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320,325,330,y=170//same4
		}
		
		if(lev==3)//level 3 walls
		{ 	//outline rect color
			g.setColor(Color.GREEN);
			g.drawRect(89, 10, 11, 60);
			g.drawRect(99, 59, 230, 11);
			g.drawRect(10, 109, 100, 11);
			g.drawRect(99, 120, 11, 129);
			g.drawRect(180, 109, 149, 11);
			g.drawRect(179, 109, 11, 140);
			g.setColor(Color.WHITE);
			g.fillRect(90, 10, 10, 60);//x=90,95;y=10,15,20,25,35,40,45,50,55,60
			g.fillRect(100, 60, 230, 10);//x=100,105,110,115,120,125,130,135,140,145,150,155,160,165,170,175,180,185,190,195,200,205,210,215,220,225,y=60,65
			g.fillRect(10, 110, 100, 10);//x=10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,y=110,115
			g.fillRect(100, 120, 10, 130);//x=100,105,y=120,125,130,135,140,145,150,155,160,165,175,180,195,200,205,210,215,220,230,235,240,245
			g.fillRect(180, 110, 150, 10);//x=180,185,190,195,200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320,325,y=110,105
			g.fillRect(180, 120, 10, 130);//x=180,185,y=120,125,130,135,140,145,150,155,160,165,175,180,195,200,205,210,215,220,230,235,240,245
		}
		
		if(lev==2)//level 2 walls
		{
			g.setColor(Color.WHITE);
			
		for(int xx=40;xx<=300;xx+=80)//x=40,41,120,121,200,201,280,281
		for(int yy=50;yy<=200;yy+=50)//y=50,51,100,101,150,151,200,201,250,251//making walls
		{
		g.fillRect(xx,yy,10,10);
		}
		}
		g.setColor(Color.WHITE);
		
		for(int i=snakelenght;i>=0;i--)
		{
			if(i!=0)
			{
		      g.fillRect(snakex[i], snakey[i], 5, 5);
			}
		   if(i==0)
		   {
			   g.setColor(Color.ORANGE);
			   g.fillRect(snakex[0], snakey[0], 5, 5);
		   }
		}
		if(lev==1)//level 1 gameover
		{
		for(int i=snakelenght-1;i>=1;i--)
		{
			if(snakex[i]==snakex[0] && snakey[i]==snakey[0])
			{
				gameover(g);
			
		   }
		}
		}
		if(lev==2)//level 2 gameover
		{
		for(int i=snakelenght-1;i>=1;i--)
		{
			if((snakex[i]==snakex[0] && snakey[i]==snakey[0])||((snakex[0]==40 || snakex[0]==45 ||snakex[0]==120||snakex[0]==125||snakex[0]==200||snakex[0]==205||snakex[0]==280||snakex[0]==285) && (snakey[0]==50||snakey[0]==55||snakey[0]==100||snakey[0]==105||snakey[0]==150||snakey[0]==155||snakey[0]==200||snakey[0]==205||snakey[0]==250||snakey[0]==255)))
			{
				gameover(g);
			}
			
		   }
		}
		if(lev==3)//level 3 gameover
		{
		for(int i=snakelenght-1;i>=1;i--)
		{
			if((snakex[i]==snakex[0] && snakey[i]==snakey[0])||
((snakex[0]==90||snakex[0]==95)&&(snakey[0]==10||snakey[0]==15||snakey[0]==20||snakey[0]==25||snakey[0]==35||snakey[0]==40||snakey[0]==45||snakey[0]==50||snakey[0]==55||snakey[0]==60||snakey[0]==65))||
((snakex[0]==100||snakex[0]==105||snakex[0]==110||snakex[0]==115||snakex[0]==120||snakex[0]==125||snakex[0]==130||snakex[0]==135||snakex[0]==140||snakex[0]==145||snakex[0]==150||snakex[0]==155||snakex[0]==160||snakex[0]==165||snakex[0]==170||snakex[0]==175||snakex[0]==180||snakex[0]==185||snakex[0]==190||snakex[0]==195||snakex[0]==200||snakex[0]==205||snakex[0]==210||snakex[0]==215||snakex[0]==220||snakex[0]==225||snakey[0]==230||snakex[0]==235||snakex[0]==240||snakex[0]==245||snakex[0]==250||snakex[0]==255||snakex[0]==260||snakex[0]==265||snakex[0]==270||snakex[0]==275||snakex[0]==280||snakex[0]==285||snakex[0]==290||snakex[0]==295||snakex[0]==300||snakex[0]==305||snakex[0]==310||snakex[0]==315||snakex[0]==320||snakex[0]==325)&&(snakey[0]==60||snakey[0]==65))||   
((snakex[0]==10||snakex[0]==15||snakex[0]==20||snakex[0]==25||snakex[0]==30||snakex[0]==35||snakex[0]==40||snakex[0]==45||snakex[0]==50||snakex[0]==55||snakex[0]==60||snakex[0]==65||snakex[0]==70||snakex[0]==75||snakex[0]==80||snakex[0]==85||snakex[0]==90||snakex[0]==95)&&(snakey[0]==110||snakey[0]==115))||
((snakex[0]==100||snakex[0]==105)&&(snakey[0]==110||snakey[0]==115||snakey[0]==120||snakey[0]==125||snakey[0]==130||snakey[0]==135||snakey[0]==140||snakey[0]==145||snakey[0]==150||snakey[0]==155||snakey[0]==160||snakey[0]==165||snakey[0]==175||snakey[0]==180||snakey[0]==195||snakey[0]==200||snakey[0]==205||snakey[0]==210||snakey[0]==215||snakey[0]==220||snakey[0]==230||snakey[0]==235||snakey[0]==240||snakey[0]==245))||
((snakex[0]==180||snakex[0]==185||snakex[0]==190||snakex[0]==195||snakex[0]==200||snakex[0]==205||snakex[0]==210||snakex[0]==215||snakex[0]==220||snakex[0]==225||snakex[0]==230||snakex[0]==235||snakex[0]==240||snakex[0]==245||snakex[0]==250||snakex[0]==255||snakex[0]==260||snakex[0]==265||snakex[0]==270||snakex[0]==275||snakex[0]==280||snakex[0]==285||snakex[0]==290||snakex[0]==295||snakex[0]==300||snakex[0]==305||snakex[0]==310||snakex[0]==315||snakex[0]==320||snakex[0]==325)&&(snakey[0]==110||snakey[0]==115))||
((snakex[0]==180||snakex[0]==185)&&(snakey[0]==120||snakey[0]==125||snakey[0]==130||snakey[0]==135||snakey[0]==140||snakey[0]==145||snakey[0]==150||snakey[0]==155||snakey[0]==160||snakey[0]==165||snakey[0]==175||snakey[0]==180||snakey[0]==195||snakey[0]==200||snakey[0]==205||snakey[0]==210||snakey[0]==215||snakey[0]==220||snakey[0]==225||snakey[0]==230||snakey[0]==235||snakey[0]==240||snakey[0]==245)))
			{
				gameover(g);
			}
			
		   }
		}
		if(lev==4)//level 4 gameover
		{
		for(int i=snakelenght-1;i>=1;i--)
			if((snakex[i]==snakex[0] && snakey[i]==snakey[0]))
			{
				gameover(g);
			}
		if(((snakex[0]==10||snakex[0]==15||snakex[0]==20||snakex[0]==25||snakex[0]==30||snakex[0]==35)&&snakey[0]==80)||
				    ((snakex[0]==40)&&(snakey[0]==55||snakey[0]==60||snakey[0]==65||snakey[0]==70||snakey[0]==75||snakey[0]==80))||
					((snakex[0]==45||snakex[0]==50||snakex[0]==55||snakex[0]==60||snakex[0]==65||snakex[0]==70||snakex[0]==75)&&(snakey[0]==55))||
                    ((snakex[0]==75)&&(snakey[0]==35||snakey[0]==40||snakey[0]==45||snakey[0]==50||snakey[0]==55))||
					((snakex[0]==80||snakex[0]==85||snakex[0]==90||snakex[0]==95||snakex[0]==100||snakex[0]==105||snakex[0]==110)&&(snakey[0]==35))||
					((snakex[0]==110)&&(snakey[0]==10||snakey[0]==15||snakey[0]==20||snakey[0]==25||snakey[0]==30||snakey[0]==35))||
					((snakex[0]==65)&&(snakey[0]==100||snakey[0]==105||snakey[0]==110||snakey[0]==115||snakey[0]==120))||
					((snakex[0]==100)&&(snakey[0]==80||snakey[0]==85||snakey[0]==90||snakey[0]==95||snakey[0]==100))||
					((snakex[0]==100)&&(snakey[0]==195||snakey[0]==200||snakey[0]==210||snakey[0]==215))||
					((snakex[0]==130)&&(snakey[0]==125||snakey[0]==130||snakey[0]==135||snakey[0]==140||snakey[0]==145||snakey[0]==150||snakey[0]==155||snakey[0]==160||snakey[0]==165||snakey[0]==170)))
		           {
			        gameover(g);
			     }
		for(int y=10;y<=70;y+=5)//same1
			if((snakex[0]==y && snakey[0]==120)||(snakex[0]==y && snakey[0]==170))
			{
				gameover(g);
			}
		for(int y=105;y<=150;y+=5)//same2
			if((snakex[0]==y&&snakey[0]==80)||(snakex[0]==y&&snakey[0]==210))
			{
				gameover(g);
			}
		for(int y=10;y<=80;y+=5)//same3
			if((snakex[0]==150&&snakey[0]==y)||(snakex[0]==200&&snakey[0]==y))
			{
				gameover(g);
			}
		for(int y=130;y<=330;y+=5)//same4
			if((snakex[0]==y&&snakey[0]==120)||(snakex[0]==y&&snakey[0]==170))
			{
				gameover(g);
			}
		for(int y=70;y<=100;y+=5)//same5
			if((snakex[0]==y&&snakey[0]==195)||(snakex[0]==y&&snakey[0]==100))
			{
				gameover(g);
			}
		for(int y=200;y<=330;y+=5)//same6
			if((snakex[0]==y&&snakey[0]==80)||(snakex[0]==y&&snakey[0]==210))
			{
				gameover(g);
			}
		for(int y=210;y<=250;y+=5)//same7
			if((snakex[0]==200&&snakey[0]==y)||(snakex[0]==150&&snakey[0]==y))
			{
				gameover(g);
			}
		}
		if(escape)//escape button
		{
			t.stop();
			g.setColor(Color.BLUE);
			g.setFont(new Font("Serif",Font.BOLD,50));
			g.drawString("Game Paused", 10, 150);
		}
		if(snakex[0]==pickx[xpos] && snakey[0]==picky[ypos])//snake food score
		{
			switch(uu)
			{
			case 8:
				score+=2;
				break;
			case 7:
				score+=3;
				break;
			case 6:
				score+=4;
				break;
			case 5:
				score+=5;
				break;
			case 4:
				score+=6;
				break;
			case 3:
				score+=7;
				break;
			case 2:
				score+=8;
				break;
			case 1:
				score+=9;
				break;
			case 0:
				score+=10;
				break;
				default :
					score+=2;
					break;
			}
			snakelenght+=1;
			xpos=r.nextInt(62);
			ypos=r.nextInt(45);
			if(sec==0)
			{cli.loop(0);
			sec++;
			}
			else
			{
				cli.loop(1);}
		}	
		if(lev==2)//level 2 food checking
		{
			if(((pickx[xpos]==40 || pickx[xpos]==45 ||pickx[xpos]==120||pickx[xpos]==125||pickx[xpos]==200||pickx[xpos]==205||pickx[xpos]==280||pickx[xpos]==285) && (picky[ypos]==50||picky[ypos]==55||picky[ypos]==100||picky[ypos]==105||picky[ypos]==150||picky[ypos]==155||picky[ypos]==200||picky[ypos]==205||picky[ypos]==250||picky[ypos]==255)))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			     if(((pickx[xpos]==40 || pickx[xpos]==45 ||pickx[xpos]==120||pickx[xpos]==125||pickx[xpos]==200||pickx[xpos]==205||pickx[xpos]==280||pickx[xpos]==285) && (picky[ypos]==50||picky[ypos]==55||picky[ypos]==100||picky[ypos]==105||picky[ypos]==150||picky[ypos]==155||picky[ypos]==200||picky[ypos]==205||picky[ypos]==250||picky[ypos]==255)))
					{
			    	 xpos=r.nextInt(62);
				     ypos=r.nextInt(45);
					}
				}
		}
		if(lev==3) //level 3 food checking  	
		{
			// x=90,95;y=10,15,20,25,35,40,45,50,55,60,65
			for(int y=10;y<=65;y+=5)	
			if(((pickx[xpos]==90)&&(picky[ypos]==y))||((pickx[xpos]==95)&&(picky[ypos]==y)))
			{
			     xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
				}
			//x=100,105,110,115,120,125,130,135,140,145,150,155,160,165,170,175,180,185,190,195,200,205,210,215,220,225,y=60,65  
		     for(int y2=100;y2<=225;y2+=5)
			 if(((pickx[xpos]==y2)&&(picky[ypos]==60))||((pickx[xpos]==y2)&&(picky[ypos]==65)))   
			 {
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			 }                                                     
		//x=10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,y=110,115
		for(int y3=10;y3<=95;y3+=5)
			 if(((pickx[xpos]==y3)&&(picky[ypos]==110))||((pickx[xpos]==y3)&&(picky[ypos]==115)))   
			 {
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			 }//x=100,105,y=120,125,130,135,140,145,150,155,160,165,175,180,195,200,205,210,215,220,230,235,240,245
		for(int y4=120;y4<=245;y4+=5)
			 if(((pickx[xpos]==100)&&(picky[ypos]==y4))||((pickx[xpos]==105)&&(picky[ypos]==y4)))  
			 {
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			 }
		//x=180,185,190,195,200,205,210,215,220,225,230,235,240,245,250,255,260,265,270,275,280,285,290,295,300,305,310,315,320,325,y=110,115
		for(int y5=180;y5<=325;y5+=5)
			 if(((pickx[xpos]==y5)&&(picky[ypos]==110))||((pickx[xpos]==y5)&&(picky[ypos]==115)))  
			 {
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			 }
		//x=180,185,y=120,125,130,135,140,145,150,155,160,165,175,180,195,200,205,210,215,220,230,235,240,245
		for(int y5=180;y5<=325;y5+=5)
			 if(((pickx[xpos]==180)&&(picky[ypos]==y5))||((pickx[xpos]==185)&&(picky[ypos]==y5)))  
			 {
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			 }
		}
		if(lev==4)//level 4 food check
		{
			if(((pickx[xpos]==10||pickx[xpos]==15||pickx[xpos]==20||pickx[xpos]==25||pickx[xpos]==30||pickx[xpos]==35)&&picky[ypos]==80)||
				    ((pickx[xpos]==40)&&(picky[ypos]==55||picky[ypos]==60||picky[ypos]==65||picky[ypos]==70||picky[ypos]==75||picky[ypos]==80))||
					((pickx[xpos]==45||pickx[xpos]==50||pickx[xpos]==55||pickx[xpos]==60||pickx[xpos]==65||pickx[xpos]==70||pickx[xpos]==75)&&(picky[ypos]==55))||
                    ((pickx[xpos]==75)&&(picky[ypos]==35||picky[ypos]==40||picky[ypos]==45||picky[ypos]==50||picky[ypos]==55))||
					((pickx[xpos]==80||pickx[xpos]==85||pickx[xpos]==90||pickx[xpos]==95||pickx[xpos]==100||pickx[xpos]==105||pickx[xpos]==110)&&(picky[ypos]==35))||
					((pickx[xpos]==110)&&(picky[ypos]==10||picky[ypos]==15||picky[ypos]==20||picky[ypos]==25||picky[ypos]==30||picky[ypos]==35))||
					((pickx[xpos]==65)&&(picky[ypos]==100||picky[ypos]==105||picky[ypos]==110||snakey[0]==115||picky[ypos]==120))||
					((pickx[xpos]==100)&&(picky[ypos]==80||picky[ypos]==85||picky[ypos]==90||picky[ypos]==95||picky[ypos]==100))||
					((pickx[xpos]==100)&&(picky[ypos]==195||picky[ypos]==200||picky[ypos]==210||picky[ypos]==215))||
					((pickx[xpos]==130)&&(picky[ypos]==125||picky[ypos]==130||picky[ypos]==135||picky[ypos]==140||picky[ypos]==145||picky[ypos]==150||picky[ypos]==155||snakey[0]==160||picky[ypos]==165||picky[ypos]==170)))
		           {
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			     }
		for(int y=10;y<=70;y+=5)//same1
			if((pickx[xpos]==y && picky[ypos]==120)||(pickx[xpos]==y && picky[ypos]==170))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		for(int y=105;y<=150;y+=5)//same2
			if((pickx[xpos]==y&&picky[ypos]==80)||(pickx[xpos]==y&&picky[ypos]==210))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		for(int y=10;y<=80;y+=5)//same3
			if((pickx[xpos]==150&&picky[ypos]==y)||(pickx[xpos]==200&&picky[ypos]==y))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		for(int y=130;y<=330;y+=5)//same4
			if((pickx[xpos]==y&&picky[ypos]==120)||(pickx[xpos]==y&&picky[ypos]==170))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		for(int y=70;y<=100;y+=5)//same5
			if((pickx[xpos]==y&&picky[ypos]==195)||(pickx[xpos]==y&&picky[ypos]==100))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		for(int y=200;y<=330;y+=5)//same6
			if((pickx[xpos]==y&&picky[ypos]==80)||(pickx[xpos]==y&&picky[ypos]==210))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		for(int y=210;y<=250;y+=5)//same7
			if((pickx[xpos]==200&&picky[ypos]==y)||(pickx[xpos]==150&&picky[ypos]==y))
			{
				 xpos=r.nextInt(62);
			     ypos=r.nextInt(45);
			}
		}
		g.setColor(Color.PINK);//food color
		g.fillRect(pickx[xpos], picky[ypos], 5, 5);//food location
		g.setColor(Color.WHITE);//refresh snke lenght box
		g.fillRect(180, 260, 30, 20);
		g.setColor(Color.WHITE);//refresh snke score
		g.fillRect(85, 260, 30, 20);
		g.setColor(Color.RED);
		g.setFont(new Font("Serif",Font.BOLD,20));//string lenght
		g.drawString("Lenght : "+Integer.toString(snakelenght), 10, 275);
		g.setColor(Color.WHITE);
		g.setColor(Color.RED);//string score
		g.setFont(new Font("Serif",Font.BOLD,20));
		g.drawString("Score: "+Integer.toString(score), 120, 275);
		g.dispose();
	
	}


	private void gameover(Graphics g) {
		right=false;
		left=false;
		up=false;
		down=false;
		t.stop();
		g.setFont(new Font("Serif",Font.BOLD,50));
		g.drawString("Game Over", 60,120);
		g.setFont(new Font("Serif",Font.BOLD,40));
		g.drawString("Space To Restart", 20, 180);
		score=0;
		if(first==0)
		{cl2.loop(0);
		first++;
		}
		else
		{
			cl2.loop(1);}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//t.start();
		if(right)
		{
			for(int i=snakelenght-1;i>=0;i--)
			{
				snakex[i+1]=snakex[i];
				snakey[i+1]=snakey[i];
				if(snakex[i]==325)
				{
					snakex[i]=5;
				}
			}
			snakex[0]=snakex[0]+5;
			repaint();
			}
		if(left)
		{
			for(int i=snakelenght-1;i>=0;i--)
			{
				snakex[i+1]=snakex[i];
				snakey[i+1]=snakey[i];
				if(snakex[i]<15)
				{
					snakex[i]=330;
				}
			}
			snakex[0]=snakex[0]-5;
			repaint();
		}
		if(up)
		{
			for(int i=snakelenght-1;i>=0;i--)
			{
				snakey[i+1]=snakey[i];
				snakex[i+1]=snakex[i];				
				if(snakey[i]<15)
				{
					snakey[i]=250;
				}
			}
				snakey[0]=snakey[0]-5;
			repaint();
		}
		if(down)
		{
			for(int i=snakelenght-1;i>=0;i--)
			{
				snakey[i+1]=snakey[i];
				snakex[i+1]=snakex[i];
				if(snakey[i]>240)
				{
					snakey[i]=5;
				}
			}
			snakey[0]=snakey[0]+5;
			repaint();
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			move++;
			if(!left)
				right=true;
			else{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			move++;
			if(!right)
				left=true;
			else{
				right=true;
				left=false;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			move++;
			if(!down)
				up=true;
			else{
				up=false;
				down=true;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			move++;
			if(!up)
				down=true;
			else{
				down=false;
				up=true;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			if(!escape)
			{
				escape=true;
			}
			else
			{
				t.start();
				escape=false;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			t.start();
			snakelenght=3;
			score=0;
			move=0;
			repaint();
			
			
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {

		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
