import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InputPlanning
{
	private List<InputLesson> _lessons;
	private HashSet<String> _monitorIntensities;
	
	public InputPlanning()
	{
		_lessons = new ArrayList<InputLesson>();
		_monitorIntensities = new HashSet<String>();
	}
	
	public InputPlanning( List<InputLesson> lessons, HashSet<String> monitorIntensities )
	{
		_lessons = lessons;
		_monitorIntensities = monitorIntensities;
	}
	
	public List<InputLesson> getLessons()
	{
		return _lessons;
	}
	
	public Boolean doesMonitorCanTeach(String monitorId, String intensityId)
	{
		return _monitorIntensities.contains( monitorId+"|"+intensityId );
	}
		
	public int computeCardinality()
	{		
		int cardinality = 1;
		for(InputLesson IL : _lessons)
		{
			IL.preprocessInput( this );
			cardinality = cardinality * IL.getIntensities().size() * IL.getMonitors().size() * IL.getHosts().size();
		}
		return cardinality;
    }

	/*
	 * Create a planning
	 */
	static public InputPlanning createPlanning(Connection connection) throws SQLException, ParseException
	{
		Statement statement = connection.createStatement();;
		ResultSet rSet = statement.executeQuery("SELECT * FROM almanacka.lesson;");
		

		ArrayList<InputLesson> lessons = new ArrayList<InputLesson>();
		HashSet<String> monitorIntensities = new HashSet<String>();
		
		while(rSet.next())
		{
			String lessonIdFromDB = rSet.getString("lessonId");
			Boolean isLocked = rSet.getBoolean("block");
			String idPlaceFromDB = rSet.getString("idPlace");
			Date begDate = rSet.getDate("BegDate");
			Date endDate = rSet.getDate("EndDate");
			InputLesson b = new InputLesson(lessonIdFromDB, isLocked, idPlaceFromDB, new java.util.Date( begDate.getTime() ), new java.util.Date( endDate.getTime() ) );
			
			lessons.add(b);
		}
		
		rSet = statement.executeQuery("SELECT * FROM almanacka.usermonitorintensity;");
		while(rSet.next())
		{
			String monitorIdFromDB = rSet.getString("idMonitor");
			String intensityIdFromDB = rSet.getString("idIntensity");			
			monitorIntensities.add(intensityIdFromDB + '|' + monitorIdFromDB);
		}
		return new InputPlanning(lessons, monitorIntensities);
	}
	
	/*
	 * Display one element from _monitorIntensities
	 */
	@Override
	public String toString() 
	{
		return "InputPlanning [_monitorIntensities=" + _monitorIntensities + "]";
	}
	
	
	/*
	 * Display every elements from _monitorIntensities
	 */
	public void PrintInfosHashset(HashSet<String> hashset)
	{	
		Iterator<String> li = hashset.iterator();
		
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}

	
	/*
	 *  Display all informations about an InputLesson
	*/
		public void PrintInfos(List<InputLesson> list)
	{	
		ListIterator<InputLesson> li = list.listIterator();
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}
	
		
	/*
	 * Display all informations about _lessons
	 */
	public void PrintInfosList(List<InputLesson> list)
	{
		ListIterator<InputLesson> li = list.listIterator();
		while (li.hasNext())
		{
			System.out.println(li.next().toString());//li.next().PrintInfos();
		}
	}
	
	
	/*
	 * Display all elements from _ monitorIntensities and _lessons
	 */
	public void PrintInfos()
	{
		PrintInfosList(_lessons);
		System.out.println("");
		PrintInfosHashset(_monitorIntensities);
	}
	
	/*
	 * Connection to the DataBase Almanacka
	 */
	public boolean connect() throws ClassNotFoundException
	{
		String url = "jdbc:mysql://localhost:3306/almanacka";
		String user = "root";
		String password = "root";	
		boolean status;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion à la base de données REUSSIE");
			status = true;
		}
		catch (SQLException e)
		{
			status = false;
			e.getMessage();
			System.out.println("Erreur de connexion à la base de données");
		}
		return status;
	}
	
	public Connection getConnect()
	{
		Connection connection;
		return connection;
	}
}