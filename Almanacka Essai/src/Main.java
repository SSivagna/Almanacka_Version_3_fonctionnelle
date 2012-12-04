import java.sql.SQLException;
import java.text.ParseException;
//import java.util.List;

public class Main 
{
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("bonjour");
		
		InputLesson test = new InputLesson("1", false, "Paris"/*, "2012-05-01", "2012-05-20"*/);
		test.PrintInfos();
		test.toString();
	//	InputPlanning IP = new InputPlanning();
	/*	List<InputLesson> a = */
		test.DataFromDB();
	}
}
