package com.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		ResultSet rSet = statement.executeQuery("SELECT L.lessonId, L.idPlace, L.begDate, L.block, L.endDate, L.idIntensity,  H.idPersonHost, M.idPersonMonitor  " 
												+"FROM almanacka.lesson L, almanacka.lessonpersonhost H, almanacka.lessonpersonmonitor M "
												+"WHERE L.lessonId = H.lessonId AND L.lessonId = M.lessonId ORDER BY (L.lessonId);");
		
		ArrayList<InputLesson> lessons = new ArrayList<InputLesson>();
		HashSet<String> monitorIntensities = new HashSet<String>();
		
		int lessonIdFromDB;
		String idPlaceFromDB;
		Date begDate;
		Byte isLocked;
		Date endDate;
		String idIntensity;
		String idMonitor;
		List<String> hosts = new ArrayList<String>();
	
		Boolean hasMoreLine = rSet.next();
		while( hasMoreLine )
		{
			lessonIdFromDB = Integer.parseInt(rSet.getString("lessonId"));
			idPlaceFromDB = rSet.getString("idPlace");
			begDate = rSet.getDate("begDate");
			isLocked = rSet.getByte("block");
			endDate = rSet.getDate("endDate");
			idIntensity=rSet.getString("idIntensity");
			idMonitor=rSet.getString("idPersonMonitor");
			
			hosts.clear();
			hosts.add(rSet.getString("idPersonHost"));
			
			hasMoreLine = rSet.next();
			while( hasMoreLine && Integer.parseInt(rSet.getString("lessonId")) == lessonIdFromDB  )
			{
				hosts.add(rSet.getString("idPersonHost"));
				hasMoreLine = rSet.next();
			}
			
			try
			{
				InputLesson b = new InputLesson(String.valueOf(lessonIdFromDB), isLocked, idPlaceFromDB, new java.util.Date( begDate.getTime() ), new java.util.Date( endDate.getTime() ), idIntensity, idMonitor, hosts );
				lessons.add(b);
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de la création du planning ");
			}
		}
		
		rSet = statement.executeQuery("SELECT idMonitor, idIntensity FROM almanacka.usermonitorintensity;");
		while(rSet.next())
		{
			String monitorIdFromDB = rSet.getString("idMonitor");
			String intensityIdFromDB = rSet.getString("idIntensity");			
			monitorIntensities.add(intensityIdFromDB + '|' + monitorIdFromDB);
		}
		
		if ( statement != null )
		{
			statement.close();
		}
		
		return new InputPlanning(lessons, monitorIntensities);
	}
}