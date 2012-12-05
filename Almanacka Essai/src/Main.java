import java.sql.SQLException;
import java.text.ParseException;

public class Main 
{
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("bonjour");
		
		InputLesson test = new InputLesson("1", false, "Paris"/*, "2012-05-01", "2012-05-20"*/);
	//	test.toString(); 
		test.PrintInfos();
		
		test.DataFromDB();
		
		System.out.println("");
		System.out.println("PARTIE INPUTPLANNING");
		System.out.println("");
		InputPlanning IP = new InputPlanning();
		IP.createPlanning();
		IP.PrintInfos();
	}
}