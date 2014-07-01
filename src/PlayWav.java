import java.applet.Applet;
import java.applet.AudioClip;
/**
 * “Ù¿÷≤•∑≈∆˜
 * @author ZyL
 *
 */
public class PlayWav extends Applet implements Runnable
{

	AudioClip audio;
	String str = null;
	public PlayWav(String str)
	{
		this.str = str;
	}
	@Override
	public void run()
	{
		try
		{
			audio = Applet.newAudioClip(this.getClass().getResource(str));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		audio.play();
	}

}