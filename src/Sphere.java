public class Sphere
{
	public static int WIDTH = 100;
	private int x;
	private int y;
	private int height = 293;

	public Sphere(int x, int y, int height)
	{
		super();
		this.x = x;
		this.y = y;
		this.height = height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public int getHeight()
	{
		return height;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void onBirdEnter(int x, int y, int len) // Åö×²Æ÷
	{
		String str = "Åö×²µ½ÁË";
		if (pointEnterRect(x, y))
		{
			Main.isAlive = false;
			new Thread(new PlayWav("sounds/sfx_hit.wav")).start();
			System.out.println(str);
		}
		if (pointEnterRect(x + len, y))
		{
			Main.isAlive = false;
			new Thread(new PlayWav("sounds/sfx_hit.wav")).start();
			System.out.println(str);
		}
		if (pointEnterRect(x + len, y + len))
		{
			Main.isAlive = false;
			new Thread(new PlayWav("sounds/sfx_hit.wav")).start();
			System.out.println(str);
		}
		if (pointEnterRect(x, y + len))
		{
			Main.isAlive = false;
			new Thread(new PlayWav("sounds/sfx_hit.wav")).start();
			System.out.println(str);
		}
	}
	boolean pointEnterRect(int x, int y)
	{
		return this.x <= x && x <= this.x + Sphere.WIDTH && this.y <= y && y <= this.y + height;
	}
}
