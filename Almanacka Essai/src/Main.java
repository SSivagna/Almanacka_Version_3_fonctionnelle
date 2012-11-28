import java.text.ParseException;

public class Main 
{
	public static void main(String[] args) throws ParseException
	{
		System.out.println("bonjour");
		
		InputLesson test = new InputLesson("1", false, "Paris", "2012.05.01");

		test.ReadInfos();
	}
}
