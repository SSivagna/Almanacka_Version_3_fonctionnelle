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
import java.util.List;

import com.almanacka.planning.InputLesson;
import com.almanacka.planning.InputPlanning;

public class CreatePlanning
{
	static public InputPlanning createPlanning(Connection connection) throws SQLException, ParseException
	{
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery("SELECT L.lessonId, L.idPlace, L.begDate, L.block, L.endDate, L.idIntensity, L.idPersonMonitor, H.idPersonHost  " +
												"FROM almanacka.lesson L, almanacka.lessonpersonhost H " +
												"WHERE L.lessonId=H.lessonId ORDER BY (L.lessonId) ASC ;");
		
		ArrayList<InputLesson> lessons = new ArrayList<InputLesson>();
		HashSet<String> monitorIntensities = new HashSet<String>();
		
		String idPlaceFromDB = null;
		Date begDate = null;
		Boolean isLocked = false;
		Date endDate = null;
		String idIntensity = null;
		String idMonitor = null;
		
		while(rSet.next())
		{
			List<String>hosts = new ArrayList<String>();
			
			String lessonIdFromDB = rSet.getString("lessonId");
			
			while(lessonIdFromDB == rSet.getString("lessonId"))
			{
				idPlaceFromDB = rSet.getString("idPlace");
				begDate = rSet.getDate("begDate");
				Time begDateTime= rSet.getTime("begDate"); // collection of the lessons beginning hours.
				isLocked = rSet.getBoolean("block");
				endDate = rSet.getDate("endDate");
				Time endDateTime = rSet.getTime("endDate"); // collection of the lessons ending hours.
			
				idIntensity=rSet.getString("idIntensity");
				idMonitor=rSet.getString("idPersonMonitor");
				hosts.add(rSet.getString("idPersonHost"));
			}
			
			/*
			ResultSet rSet1 = statement.executeQuery("SELECT idIntensity FROM almanacka.lesson WHERE lessonId=lessonId");			
			List<String> ity= new ArrayList<String>();
			while(rSet1.next())
			{
				ity.add(rSet1.getString("idIntensity"));
			}
						
			ResultSet rSet= statement.executeQuery("SELECT idPersonMonitor FROM almanacka.lesson WHERE lessonId=lessonId");
			List<String> monit= new ArrayList<String>();
			while(rSet1.next())
			{
				monit.add(rSet1.getString("idPersonMonitor"));
			}
						
			ResultSet rSet1 = statement.executeQuery("SELECT idPersonHost FROM almanacka.lessonpersonhost WHERE lessonId=lessonId");			
			List<String> host= new ArrayList<String>();
			while(rSet1.next())
			{
				host.add(rSet1.getString("idPersonHost"));
			}
			*/	
	
			try
			{
				InputLesson b = new InputLesson(lessonIdFromDB, isLocked, idPlaceFromDB, new java.util.Date( begDate.getTime() ), new java.util.Date( endDate.getTime() ), idIntensity, idMonitor, hosts );
				lessons.add(b);
			}
			
		/*	try
			{
				InputLesson b = new InputLesson(lessonIdFromDB, isLocked, idPlaceFromDB, new java.util.Date( begDate.getTime() ), new java.util.Date( endDate.getTime()), _idItensity, _idMonitor );
				lessons.add(b);
			}*/
			catch (Exception e)
			{
				System.out.println("erreur lors de la création ");
			}
			
			//System.out.println("Données depuis CP : " + lessonIdFromDB +" , " + begDate + " , " + endDate + " , "+ begDateTime + " , " + endDateTime +".");
		}
		
		rSet = statement.executeQuery("SELECT idMonitor, idIntensity FROM almanacka.usermonitorintensity;");
		
		while(rSet.next())
		{
			String monitorIdFromDB = rSet.getString("idMonitor");
			String intensityIdFromDB = rSet.getString("idIntensity");			
			monitorIntensities.add(intensityIdFromDB + '|' + monitorIdFromDB);
		}
		return new InputPlanning(lessons, monitorIntensities);
	}
}