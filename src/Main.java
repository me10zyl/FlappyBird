import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.swing.JApplet;

public class Main extends JApplet implements Runnable, KeyListener, MouseListener
{
	public static int SCREEN_WIDTH = 800;// ������2�ı�������Ϊ pipe.x-=2
	public static int SCREEN_HIGHT = 500;// ������2�ı�������Ϊ pipe.x-=2
	private Image subScreen = null;
	private Graphics drawSubScreen = null;// ˫�ػ���-�λ���
	private int v = 0; // �����ٶ�
	private int x = 90;// С��X
	private int y = 0; // �������
	private int d = 30;// С��ֱ��
	private ArrayList<Pipe2> pipes; // �������Ա�
	private int xGap = 140; // ���Ӽ��
	public static boolean isAlive = true; // С���Ƿ����
	public static int score = 0; //����

	@Override
	public void init()
	{
		pipes = new ArrayList<Pipe2>();
		pipes.add(new Pipe2(SCREEN_WIDTH, 0));
		subScreen = createImage(SCREEN_WIDTH, SCREEN_HIGHT);
		drawSubScreen = subScreen.getGraphics();
		setSize(SCREEN_WIDTH, SCREEN_HIGHT);
		setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
	}
	@Override
	public void start()
	{
		// TODO Auto-generated method stub
		super.start();
		new Thread(this).start();
	}
	@Override
	public void stop()
	{
		// TODO Auto-generated method stub
		super.stop();
	}
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		super.destroy();
	}
	@Override
	public void update(Graphics g)
	{
	}
	@Override
	public void paint(Graphics g)
	{
		// TODO Auto-generated method stub
		drawSubScreen.setColor(Color.WHITE);
		drawSubScreen.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HIGHT);
		drawSubScreen.setColor(Color.BLACK);
		drawSubScreen.fillOval(x, y, d, d);
		for (Pipe2 pipe : pipes)
		{
			pipe.draw(drawSubScreen);
		}
		drawScore(drawSubScreen);
		if (!isAlive)
		{
			drawLose(drawSubScreen);
		}
		g.drawImage(subScreen, 0, 0, this);
	}
	private void drawScore(Graphics g) // �����
	{
		String str = "�֔���" + score;
		g.setFont(new Font(null, Font.BOLD, 12));
		g.setColor(Color.GREEN);
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth(str);
		int stringAscent = fm.getAscent();
		int stringDescent = fm.getDescent();
		int x = this.getWidth() - stringWidth;
		int y = (stringAscent - stringDescent);
		g.drawString(str, x, y);
	}
	private void drawLose(Graphics g) // �����
	{
		String str = "�����~!\r\n�c����Ļ�^�m����";
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 50));
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth(str);
		int stringAscent = fm.getAscent();
		int stringDescent = fm.getDescent();
		int x = this.getWidth() / 2 - stringWidth / 2;
		int y = this.getHeight() / 2 + (stringAscent - stringDescent) / 2;
		g.drawString(str, x, y);
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				Thread.sleep(30);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v += 1;
			if (y + v + d >= SCREEN_HIGHT)
			{
				System.out.println("�㱻ˤ����");
				if (isAlive)
				{
					new Thread(new PlayWav("sounds/sfx_die.wav")).start();
				}
				isAlive = false;
			} else
			{
				y += v;
			}
			if (isAlive)
			{
				for (int i = 0; i < pipes.size(); i++)
				{
					Pipe2 pipe = pipes.get(i);
					pipe.onBirdEnter(x, y, d);
					if (x == pipe.getX())
					{
						new Thread(new PlayWav("sounds/sfx_point.wav")).start();
						score++;
					}
					pipe.setX(pipe.getX() - 2);
				}
				Pipe2 finalPipe = pipes.get(pipes.size() - 1);
				if (finalPipe.getX() + xGap + Pipe2.WIDTH == SCREEN_WIDTH)
				{
					System.out.println("�ý�����Ļ��");
					pipes.add(new Pipe2(SCREEN_WIDTH, 0));
				}
				Pipe2 firstPipe = pipes.get(0);
				if (firstPipe.getX() + Pipe2.WIDTH == 0)
				{
					System.out.println("��ɾ������");
					pipes.remove(0);
				}
			}
			repaint();
		}
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (isAlive)
			{
				new Thread(new PlayWav("sounds/sfx_wing.wav")).start();
				v = -10;
				System.out.println("jump");
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		System.out.println(e.getX() + "," + e.getY());
		if (!isAlive)
		{
			v = 0; // �����ٶ�
			x = 90;// С��X
			y = 0; // �������
			score = 0;
			pipes = new ArrayList<Pipe2>();
			pipes.add(new Pipe2(SCREEN_WIDTH, 0));
			isAlive = true;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		if (isAlive)
		{
			new Thread(new PlayWav("sounds/sfx_wing.wav")).start();
			v = -10;
			System.out.println("jump");
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
}
