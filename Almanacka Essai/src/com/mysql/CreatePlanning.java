package com.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Time;
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
		ResultSet rSet = statement.executeQuery("SELECT L.lessonId, L.idPlace, L.begDate, L.block, L.endDate, L.idIntensity, L.idPersonMonitor, H.idPersonHost  " 
												+"FROM almanacka.lesson L, almanacka.lessonpersonhost H "
												+"WHERE L.lessonId=H.lessonId ORDER BY (L.lessonId) ASC ;");
		
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

		Boolean hasMoreLine = rSet.next();		// renvoie false s'il n'y a pas de lignes à la suite	
		while( hasMoreLine )
		{
			lessonIdFromDB = Integer.parseInt(rSet.getString("lessonId"));
			idPlaceFromDB = rSet.getString("idPlace");
			begDate = rSet.getDate("begDate");
			isLocked = rSet.getByte("block");		// ceci récupère la valeur du tinyInt
			endDate = rSet.getDate("endDate");
			idIntensity=rSet.getString("idIntensity");
			idMonitor=rSet.getString("idPersonMonitor");
			
			// on remet à zéro la liste 
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
				System.out.println(b.toString());
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de la création ");
			}
			
		/*	System.out.println("Données depuis CP : " + lessonIdFromDB +" , " + begDate + " , " + endDate + " "+idIntensity + " " + idMonitor/*+ " , "+ begDateTime + " , " + endDateTime +".");
			
			ListIterator<InputLesson> li = lessons.listIterator();
			while(li.hasNext())
			{
				System.out.println(li.next().toString());
			}
			System.out.println("Données depuis le CP liste : "); */
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