package com.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

import com.almanacka.planning.InputLesson;
import com.almanacka.planning.InputPlanning;

public class CreatePlanning
{
	static public InputPlanning createPlanning(Connection connection) throws SQLException, ParseException
	{
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery("SELECT lessonId, idPlace, begDate, block, endDate FROM almanacka.lesson;");

		ArrayList<InputLesson> lessons = new ArrayList<InputLesson>();
		HashSet<String> monitorIntensities = new HashSet<String>();
		
		while(rSet.next())
		{
			String lessonIdFromDB = rSet.getString("lessonId");
			String idPlaceFromDB = rSet.getString("idPlace");
			Date begDate = rSet.getDate("begDate");
			Time begDateTime= rSet.getTime("begDate"); // collection of the lessons beginning hours.
			Boolean isLocked = rSet.getBoolean("block");
			Date endDate = rSet.getDate("endDate");
			Time endDateTime = rSet.getTime("endDate"); // collection of the lessons ending hours.
			
		/*	try
			{*/
				InputLesson b = new InputLesson(lessonIdFromDB, isLocked, idPlaceFromDB, new java.util.Date( begDate.getTime() ), new java.util.Date( endDate.getTime() ) );
				lessons.add(b);
	/*		}
			catch (Exception e)
			{
				System.out.println("erreur lors de la création ");
			}*/
			
			
			System.out.println("Données depuis CP : " + lessonIdFromDB +" , " + begDate + " , " + endDate + " , "+ begDateTime + " , " + endDateTime +".");
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
}