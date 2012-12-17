package com.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.almanacka.planning.InputPlanning;
import com.almanacka.planning.OutputLesson;
import com.almanacka.planning.OutputPlanning;

public class InsertOutputLessonInDB
{
	static public void InsertOutputLesson(Connection connection, InputPlanning input, int p)
	{
		OutputPlanning a = OutputLesson.CreateOutputPlanning(input, p);
		for(int i =0; i <a.getLessons().size(); i++ )
		{
			try
			{
				a.getLessons().get(i);
				
				String sql = "INSERT INTO almanacka.lessonfortest (lessonId, block ,idPlace) VALUES (?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, a.getLessons().get(i).getLessonId());
				String chaine = a.getLessons().get(i).getLessonId();
				System.out.println("Voici : " + chaine);
				preparedStatement.setBoolean(2, false);
				preparedStatement.setString(3, a.getLessons().get(i).getPlaceWrapId());
				preparedStatement.executeUpdate();
			//	java.sql.Date date = new Date(a.getLessons().get(i).getBegDate());
				//java.util.Date date = a.getLessons().get(i).getBegDate();
				//java.sql.Date dateSQL = new Date(date);
				//	preparedStatement.setDate(4, dateSQL);
				//	preparedStatement.setDate(5, a.getLessons().get(i).getEndDate());
				
			//	int rSet = statement.executeUpdate( "INSERT INTO almanacka.lessonForTest (lessonId, block ,idPlace, begDate, endDate) VALUES ('"+a.getLessons().get(i).getLessonId()+"', 'false', '"+a.getLessons().get(i).getPlaceWrapId()+"', '"+ a.getLessons().get(i).getBegDate()+"', '"+a.getLessons().get(i).getEndDate()+"');" );
			//	System.out.println("Valeur du int rSet :" + rSet);
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de l'insertion de l'OuputLesson dans la base de données !!! ");
			}
			finally
			{
				
			}
		}
	}
}