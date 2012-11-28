import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputLesson
{
	private String lessonId;
	private Boolean isLocked;
	private Boolean isInputLocked;
	private Boolean isAutomaticLocked;
	private String placeWrapId;
	private Date begDate;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	/* Getters and Setters */
	public String getLessonId()
	{
		return lessonId;
	}

	public void setLessonId(String lessonId)
	{
		this.lessonId = lessonId;
	}

	public Boolean getIsLocked()
	{
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked)
	{
		this.isLocked = isLocked;
	}

	public Boolean getIsInputLocked()
	{
		return isInputLocked;
	}

	public void setIsInputLocked(Boolean isInputLocked)
	{
		this.isInputLocked = isInputLocked;
	}

	public Boolean getIsAutomaticLocked()
	{
		return isAutomaticLocked;
	}

	public void setIsAutomaticLocked(Boolean isAutomaticLocked)
	{
		this.isAutomaticLocked = isAutomaticLocked;
	}

	public String getPlaceWrapId()
	{
		return placeWrapId;
	}

	public void setPlaceWrapId(String placeWrapId)
	{
		this.placeWrapId = placeWrapId;
	}

	public Date getBegDate() 
	{
		return begDate;
	}

	public void setBegDate ( Date begDate) 
	{
		this.begDate = begDate;
	}
	
	
	/*public InputLesson( ) throws SQLException, ClassNotFoundException
	{
		lessonId = result.getString("idLesson");
		isInputLocked = result.getBoolean("block");
		placeWrapId = result.getString("idPlace");
		begDate = result.getDate("beginDate");
		isLocked = false;
		isAutomaticLocked = false;
	}*/
	
	public InputLesson(String lessonIdToSet, boolean isInputLockedToSet, String idPlaceToSet, String beginDateToSet ) throws ParseException
	{
		lessonId = lessonIdToSet;
		isInputLocked = isInputLockedToSet;
		placeWrapId = idPlaceToSet;
		begDate = new SimpleDateFormat("yyyy.MM.dd",Locale.FRANCE).parse(beginDateToSet);
	}
	
	public void ReadInfos()
	{
		System.out.println("DONNEES : " + lessonId + "," + placeWrapId+ "," + begDate+ ".");
	}
}