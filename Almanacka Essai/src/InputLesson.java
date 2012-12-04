import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Date;
import java.util.List;
import java.util.ListIterator;
//import java.util.Locale;

public class InputLesson
{
	private String _lessonId;
	private Boolean _isLocked;
	private Boolean _isInputLocked;
	private Boolean _isAutomaticLocked;
	private String _placeWrapId;
//	private Date _begDate;
//	private Date _endDate;
	private List<Choice> _intensities;
    private List<Choice> _monitors;
    private List<Choice> _hosts;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public InputLesson(String lessonIdToSet, boolean isInputLockedToSet, String idPlaceToSet/*, String beginDateToSet, String endDateToSet*/ ) throws ParseException
	{
		_lessonId = lessonIdToSet;
		_isInputLocked = isInputLockedToSet;
		_placeWrapId = idPlaceToSet;
	//	_begDate = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE).parse(beginDateToSet);
	//	_endDate = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE).parse(endDateToSet);
	}
	
	/* Getters and Setters */
	public String getLessonId()
	{
		return _lessonId;
	}

	public void setLessonId(String lessonId)
	{
		this._lessonId = lessonId;
	}

	public Boolean getIsLocked()
	{
		return _isLocked;
	}

	public void setIsLocked(Boolean isLocked)
	{
		this._isLocked = isLocked;
	}

	public Boolean getIsInputLocked()
	{
		return _isInputLocked;
	}

	public void setIsInputLocked(Boolean isInputLocked)
	{
		this._isInputLocked = isInputLocked;
	}

	public Boolean getIsAutomaticLocked()
	{
		return _isAutomaticLocked;
	}

	public void setIsAutomaticLocked(Boolean isAutomaticLocked)
	{
		this._isAutomaticLocked = isAutomaticLocked;
	}

	public String getPlaceWrapId()
	{
		return _placeWrapId;
	}

	public void setPlaceWrapId(String placeWrapId)
	{
		this._placeWrapId = placeWrapId;
	}

/*	public Date getBegDate() 
	{
		return _begDate;
	}

	public void setBegDate ( Date begDate) 
	{
		this._begDate = begDate;
	}

	public Date getEndDate()
	{
		return _endDate;
	}
	
	public void setEndDate( Date endDate)
	{
		this._endDate = endDate;
	}
*/
	public List<Choice> getIntensities()
	{
		return _intensities;
	}
	
    public List<Choice> getMonitors()
    {
    	return _monitors;
    }
    
    public List<Choice> getHosts()
    {
    	return _hosts;
    }

	/*
     * returns false if the lesson is invalid.
     */
    protected Boolean preprocessInput(InputPlanning planning)
    {
        if( !_isLocked )
        {
            _isAutomaticLocked = _intensities.size() == 1 && _monitors.size() == 1 && _hosts.size() == 1;
        }
        return true;
    }
	
	public List<InputLesson> DataFromDB() throws ParseException, SQLException, ClassNotFoundException
	{
		List<InputLesson> list = new ArrayList<InputLesson>();
		connect();
		getConnect();
		statement = connection.createStatement();
		result = statement.executeQuery("SELECT * FROM almanacka.lesson ;");
		while(result.next())
		{
			String lessonIdFromDB = result.getString("lessonId");
			Boolean isLocked = result.getBoolean("block");
			String idPlaceFromDB = result.getString("idPlace");
		//	Date beginDateFromDB = result.getDate("beginDate");
		//	Date endDateFromDB = result.getDate("endDate");
			
		//	SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		//	String endDateString = new String(date.format(endDateFromDB));
		//	String beginDateString = new String(date.format(beginDateFromDB));
			
			InputLesson b = new InputLesson(lessonIdFromDB, isLocked, idPlaceFromDB/*, beginDateString, endDateString*/);
			list.add(b);
	//		System.out.println("Données : " + lessonIdFromDB+ " " + isLocked+ " " + idPlaceFromDB);
		//	System.out.println(b);
		}
		ReadInfos(list);
		return list;
	}
	
	public void ReadInfos(List<InputLesson> list)
	{	
		ListIterator<InputLesson> li = list.listIterator();
	//	Iterator<InputLesson> li = list.iterator();
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}

	public String toString()
	{
		return "InputLesson [ lessonId = " + _lessonId + ", idPlace = " + _placeWrapId + ", isLocked " + _isLocked + " ]";
	}
	
	public void PrintInfos()
	{
		System.out.println("Le id du cours " + _lessonId);
		System.out.println("le block " + _isInputLocked);
		System.out.println("la place " + _placeWrapId);
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