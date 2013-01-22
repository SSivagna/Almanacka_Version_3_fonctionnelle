package com.mysql;

import java.sql.Connection;
import java.sql.Date;
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
		
	//	DeleteTableLesson.DeleteTableInDB(connection);
		
		for(int i = 0; i < a.getLessons().size(); i++ )
		{
		//	String sql = "INSERT INTO almanacka.lesson (lessonId, idPlace, begDate, block, endDate, idIntensity ) VALUES (?, ?, ?, ?, ?, ?)";
			String sql = "INSERT INTO almanacka.lessonfortest (lessonId, idPlace, begDate, block, endDate, idIntensity ) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			try
			{
				OutputLesson lesson = a.getLessons().get(i);
			
	     	/*	java.sql.Date sqlBegDate = new Date(lesson.getBegDate().getTime());
				java.sql.Date sqlEndDate = new Date(lesson.getEndDate().getTime());*/
				
				// essai depuis wiki java
				  java.util.Date date = new java.util.Date(lesson.getBegDate().getTime());
			      long t = date.getTime();
			      java.sql.Date sqlDate = new java.sql.Date(t);
			      java.sql.Time sqlTime = new java.sql.Time(t);
			      java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
				   
			      System.out.println(sqlTimestamp);
				
			      
			      java.util.Date date2 = new java.util.Date(lesson.getEndDate().getTime());
			      long t2 = date2.getTime();
			      java.sql.Date sqlDate2 = new java.sql.Date(t2);
			      java.sql.Time sqlTime2 = new java.sql.Time(t2);
			      java.sql.Timestamp sqlTimestamp2 = new java.sql.Timestamp(t2);
				   
			      System.out.println(sqlTimestamp2);
				
				
				//Insertion des outputLessons
				
			    preparedStatement.setInt(1,Integer.parseInt(lesson.getLessonId()));
				preparedStatement.setInt(2, Integer.parseInt(lesson.getPlaceWrapId()));
			//	preparedStatement.setDate(3, /*sqlBegDate*/ sqlTimestamp );
				preparedStatement.setTimestamp(3, sqlTimestamp);
				preparedStatement.setBoolean(4, false);
			//	preparedStatement.setDate(5, /*sqlEndDate*/ sqlTimestamp2 );
				preparedStatement.setTimestamp(5, sqlTimestamp2);
				preparedStatement.setInt(6, Integer.parseInt(lesson.getIntensityId()));	
			System.out.println(	preparedStatement.toString());
				
				int rSet =  preparedStatement.executeUpdate();
				System.out.println("Valeur du int rSet :" + rSet);
		//		preparedStatement.close();
		//		System.out.println("   ");	
		
								
				//Insertion des hosts
				
		//		String sql2 = "INSERT INTO almanacka.lessonpersonhost (lessonId, idPersonHost) VALUES ( ? , ? )";
				String sql2 = "INSERT INTO almanacka.lessonpersonhostfortest (lessonId, idPersonHost) VALUES ( ? , ? )";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setInt(1, Integer.parseInt(lesson.getLessonId()));
				preparedStatement2.setInt(2, Integer.parseInt(lesson.getHostd()));
		/*		int rSet2 = */ preparedStatement2.executeUpdate(); 
		//		preparedStatement2.toString();
				
		//		System.out.println("Valeur du int rSet 2 :" + rSet2);
		//		preparedStatement2.close();
		//		System.out.println("   ");	
				
				
				//Insertion des hosts
	
		//		String sql3 = "INSERT INTO almanacka.lessonpersonmonitor (lessonId, idPersonMonitor) VALUES ( ? , ? )";
		String sql3 = "INSERT INTO almanacka.lessonpersonmonitorfortest (lessonId, idPersonMonitor) VALUES ( ? , ? )";
		
		PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
				preparedStatement3.setInt(1, Integer.parseInt(lesson.getLessonId()));
				preparedStatement3.setInt(2, Integer.parseInt(lesson.getMonitorId()));
		/*		int rset3 = */ preparedStatement3.executeUpdate();
				
		//		System.out.println("Valeur du int rSet 3 :" + rset3);
		//		preparedStatement3.close();	
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de l'insertion de l'OuputLesson dans la base de données !!! ");
				System.out.println(" ");
			}
		}
	}
}