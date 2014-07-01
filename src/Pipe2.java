import java.awt.Graphics;
import java.util.Random;

public class Pipe2
{
	private int spacing = 150;
	public static int WIDTH = Sphere.WIDTH;
	private Sphere sphere1;
	private Sphere sphere2;
	private int x;
	private int y;

	public Pipe2(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		Random ran = new Random();
		sphere1 = new Sphere(x, y, ran.nextInt(Main.SCREEN_HIGHT - spacing));
		sphere2 = new Sphere(x, y + spacing + sphere1.getHeight(), Main.SCREEN_HIGHT);
	}
	public void draw(Graphics g)
	{
		g.fillRect(sphere1.getX(), sphere1.getY(), Sphere.WIDTH, sphere1.getHeight());
		g.fillRect(sphere2.getX(), sphere2.getY(), Sphere.WIDTH, sphere2.getHeight());
	}
	public int getSpacing()
	{
		return spacing;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setSpacing(int spacing)
	{
		this.spacing = spacing;
	}
	public void setX(int x)// 一定要更新sp1,sp2的X坐标
	{
		this.x = x;
		sphere1.setX(x);
		sphere2.setX(x);
	}
	public void setY(int y)// 上部sp1的更新
	{
		this.y = y;
		sphere1.setY(y);
	}
	public void onBirdEnter(int x, int y, int len)
	{
		sphere1.onBirdEnter(x, y, len);
		sphere2.onBirdEnter(x, y, len);
	}
}
