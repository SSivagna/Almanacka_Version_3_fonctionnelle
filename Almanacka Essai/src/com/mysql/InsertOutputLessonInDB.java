package com.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.almanacka.planning.InputPlanning;
import com.almanacka.planning.OutputLesson;
import com.almanacka.planning.OutputPlanning;

public class InsertOutputLessonInDB
{
	static public void InsertOutputLesson(Connection connection, InputPlanning input, int p) throws SQLException
	{
		OutputPlanning a = OutputLesson.createOutputPlanning(input, p);
		
		DeleteTableLesson.DeleteTableInDB(connection);
		
		for(int i = 0; i < a.getLessons().size(); i++ )
		{
			String sql = "INSERT INTO almanacka.lesson (lessonId, idPlace, begDate, block, endDate, idIntensity ) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			try
			{
				OutputLesson lesson = a.getLessons().get(i);
			
				java.util.Date date = new java.util.Date(lesson.getBegDate().getTime());
				long t = date.getTime();
				new java.sql.Date(t);
				new java.sql.Time(t);
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
  
				java.util.Date date2 = new java.util.Date(lesson.getEndDate().getTime());
				long t2 = date2.getTime();
				new java.sql.Date(t2);
				new java.sql.Time(t2);
				java.sql.Timestamp sqlTimestamp2 = new java.sql.Timestamp(t2);
			
				//Insertion des outputLessons
				
			    preparedStatement.setInt(1,Integer.parseInt(lesson.getLessonId()));
				preparedStatement.setInt(2, Integer.parseInt(lesson.getPlaceWrapId()));
				preparedStatement.setTimestamp(3, sqlTimestamp);
				preparedStatement.setBoolean(4, false);
				preparedStatement.setTimestamp(5, sqlTimestamp2);
				preparedStatement.setInt(6, Integer.parseInt(lesson.getIntensityId()));	
				
		/*		int rSet = */ preparedStatement.executeUpdate();
		//		System.out.println("Valeur du int rSet :" + rSet);
								
				//Insertion des hosts
				
				String sql2 = "INSERT INTO almanacka.lessonpersonhost (lessonId, idPersonHost) VALUES ( ? , ? )";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setInt(1, Integer.parseInt(lesson.getLessonId()));
				preparedStatement2.setInt(2, Integer.parseInt(lesson.getHostd()));
		/*		int rSet2 = */ preparedStatement2.executeUpdate();
				
		//		System.out.println("Valeur du int rSet 2 :" + rSet2);
		//		preparedStatement2.close();
				
				
				//Insertion des hosts
	
				String sql3 = "INSERT INTO almanacka.lessonpersonmonitor (lessonId, idPersonMonitor) VALUES ( ? , ? )";
				PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
				preparedStatement3.setInt(1, Integer.parseInt(lesson.getLessonId()));
				preparedStatement3.setInt(2, Integer.parseInt(lesson.getMonitorId()));
		/*		int rset3 = */ preparedStatement3.executeUpdate();
				
		//		System.out.println("Valeur du int rSet 3 :" + rset3);
				preparedStatement3.close();	
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de l'insertion de l'OuputLesson dans la base de données !!! ");
				System.out.println(" ");
			}
		}
	}
}