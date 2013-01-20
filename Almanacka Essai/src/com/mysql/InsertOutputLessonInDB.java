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
		
		for(int i = 0; i < a.getLessons().size(); i++ )
		{
			String sql = "INSERT INTO almanacka.lessonfortest (lessonId, idPlace, begDate, block, endDate, idIntensity , idPersonMonitor) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			try
			{
				OutputLesson lesson = a.getLessons().get(i);
			
	     		java.sql.Date sqlBegDate = new Date(lesson.getBegDate().getTime());
				java.sql.Date sqlEndDate = new Date(lesson.getEndDate().getTime());
				
			    preparedStatement.setInt(1,Integer.parseInt(lesson.getLessonId()));
				preparedStatement.setInt(2, Integer.parseInt(lesson.getPlaceWrapId()));
				preparedStatement.setDate(3, sqlBegDate );
				preparedStatement.setBoolean(4, false);
				preparedStatement.setDate(5, sqlEndDate);
				preparedStatement.setInt(6, Integer.parseInt(lesson.getMonitorId()));
				preparedStatement.setInt(7, Integer.parseInt(lesson.getIntensityId()));
				
				System.out.println("ds insertintoDB");
				System.out.println(preparedStatement.toString());
				System.out.println(" ");
							
				int rSet = preparedStatement.executeUpdate();
				System.out.println("Valeur du int rSet :" + rSet);
				System.out.println("   ");
		
				System.out.println("Insertion des hosts");
				System.out.println("      ");
				String sql2 = "INSERT INTO almanacka.lessonpersonhostfortest (lessonId, idPersonHost) VALUES ( ? , ? )";
				PreparedStatement preparedSstatement2 = connection.prepareStatement(sql2);
				preparedSstatement2.setInt(1, Integer.parseInt(lesson.getLessonId()));
				preparedSstatement2.setString(2, lesson.getHostd());
				int rSet2 = preparedSstatement2.executeUpdate(); 
				System.out.println(preparedSstatement2.toString());
				System.out.println("Valeur du int rSet 2 :" + rSet2);
				System.out.println("   ");
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de l'insertion de l'OuputLesson dans la base de données !!! ");
				System.out.println(" ");
			}
			finally
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
			}
		}
	}
}