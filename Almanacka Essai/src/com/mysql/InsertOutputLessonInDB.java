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
		
		DeleteTableLesson.DeleteTableInDB(connection);
		
		for(int i = 0; i < a.getLessons().size(); i++ )
		{
			String sql = "INSERT INTO almanacka.lesson (lessonId, idPlace, begDate, block, endDate, idIntensity ) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			try
			{
				OutputLesson lesson = a.getLessons().get(i);
			
	     		java.sql.Date sqlBegDate = new Date(lesson.getBegDate().getTime());
				java.sql.Date sqlEndDate = new Date(lesson.getEndDate().getTime());
				
				//Insertion des outputLessons
				
			    preparedStatement.setInt(1,Integer.parseInt(lesson.getLessonId()));
				preparedStatement.setInt(2, Integer.parseInt(lesson.getPlaceWrapId()));
				preparedStatement.setDate(3, sqlBegDate );
				preparedStatement.setBoolean(4, false);
				preparedStatement.setDate(5, sqlEndDate);
				preparedStatement.setInt(6, Integer.parseInt(lesson.getIntensityId()));	
				preparedStatement.toString();
				
		/*		int rSet = */ preparedStatement.executeUpdate();
		//		System.out.println("Valeur du int rSet :" + rSet);
				preparedStatement.close();
		//		System.out.println("   ");	
		
								
				//Insertion des hosts
				
				String sql2 = "INSERT INTO almanacka.lessonpersonhost (lessonId, idPersonHost) VALUES ( ? , ? )";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setInt(1, Integer.parseInt(lesson.getLessonId()));
				preparedStatement2.setInt(2, Integer.parseInt(lesson.getHostd()));
		/*		int rSet2 = */ preparedStatement2.executeUpdate(); 
		//		preparedStatement2.toString();
				
		//		System.out.println("Valeur du int rSet 2 :" + rSet2);
				preparedStatement2.close();
		//		System.out.println("   ");	
				
				
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