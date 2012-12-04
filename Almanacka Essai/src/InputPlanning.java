import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public List<InputLesson> getLessons()
	{
		return _lessons;
	}
	
	public Boolean monitorCanTeach(String monitorId, String intensityId)
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
		
	public void AddInputLesson( InputLesson lesson )
	{
		_lessons.add(lesson);
	}
	   
	public void addMonitorCanTeach( String monitorId, String intensityId )
	{
		_monitorIntensities.add(monitorId+"|"+intensityId);
	}
	
	public List<InputPlanning> createPlanning()
	{
		List<InputLesson> list = new ArrayList<InputLesson>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		connect();
		getConnect();

		statement = connection.createStatement();
		result = statement.executeQuery("SELECT * FROM almanacka.lesson;");
		while(result.next())
		{
			String lessonIdFromDB = result.getString("lessonId");
			Boolean isLocked = result.getBoolean("block");
			String idPlaceFromDB = result.getString("idPlace");
			
			InputLesson b = new InputLesson(lessonIdFromDB, isLocked, idPlaceFromDB);
			
	!		_lessons.add(b);
		}
		
		result = statement.executeQuery("SELECT * FROM almanacka.usermonitorintensity;");
		while(result.next())
		{
			String monitorIdFromDB = result.getString("idMonitor");
			String intensityIdFromDB = result.getString("idIntensity");
			
			_monitorIntensities.add(intensityIdFromDB);
			_monitorIntensities.add(monitorIdFromDB);
		}
		ReadInfos(list);
		return List<InputPlanning> ;
	}
	
	public void ReadInfos(List<InputLesson> list)
	{	
		ListIterator<InputLesson> li = list.listIterator();
		
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}
	
	@Override
	public String toString() 
	{
		return "InputPlanning [_monitorIntensities=" + _monitorIntensities + "]";
	}

	public void ReadInfosHashset(HashSet<String> list)
	{	
		Iterator<String> li = list.iterator();
		
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}
	
	public void PrintInfos()
	{
	/*	System.out.println("Le id du cours " + _lessonId);
		System.out.println("le block " + _isInputLocked);
		System.out.println("la place " + _placeWrapId);*/
	//	System.out.println("la date " + _begDate );
	//	System.out.println("la date " + _endDate );
	}
	
	public boolean connect() throws ClassNotFoundException
	{
		String url = "jdbc:mysql://localhost:3306/almanacka";
		String user = "root";
		String password = "root";		
		boolean status;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
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
	
	/*public static boolean disconnect()
	{
		String url = "jdbc:mysql://localhost:3306/almanacka";
		boolean finalStatus;
		
		try
		{
			Class.forName(url);
			connection.close();
			finalStatus = true;
		}
		catch (Exception e)
		{
			finalStatus = false;
			e.getMessage();
			System.out.println("Déconnexion imposible");
		}
		return finalStatus;
	}*/
	
	public Connection getConnect()
	{
		return connection;
	}
}