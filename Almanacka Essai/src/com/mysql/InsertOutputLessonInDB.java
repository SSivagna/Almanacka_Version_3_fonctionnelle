package com.mysql;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.almanacka.planning.InputPlanning;
import com.almanacka.planning.OutputLesson;
import com.almanacka.planning.OutputPlanning;

public class InsertOutputLessonInDB
{
	static public void InsertOutputLesson(Connection connection, InputPlanning input, int p) throws SQLException
	{
		OutputPlanning a = OutputLesson.CreateOutputPlanning(input, p);
		for(int i =0; i <a.getLessons().size(); i++ )
		{
			try
			{
				a.getLessons().get(i);
		
				String sql = "INSERT INTO almanacka.lessonfortest (lessonId, block) VALUES (?, ?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				String chaine = a.getLessons().get(i).getLessonId();
			//	System.out.println("chaîne 1 : " + chaine);
			//	String chaine2 = a.getLessons().get(i).getPlaceWrapId();
				
				
				
				preparedStatement.setString(1, chaine);
				preparedStatement.setBoolean(2, false);
			//	preparedStatement.setString(3, chaine2);
				
			//	java.sql.Date sqlBegDate = new Date(a.getLessons().get(i).getBegDate().getTime());
			//	java.sql.Date sqlEndDate = new Date(a.getLessons().get(i).getEndDate().getTime());
				
			//	preparedStatement.setDate(4, sqlBegDate );
			//	preparedStatement.setDate(5, sqlEndDate);
			//	System.out.println("sqlBegDate : " + sqlBegDate);
			//	System.out.println("sqlEndDate : " + sqlEndDate);
				
				int rSet = preparedStatement.executeUpdate();
			
				System.out.println("Valeur du int rSet :" + rSet);
			}
			catch (Exception e)
			{
				System.out.println("Erreur lors de l'insertion de l'OuputLesson dans la base de données !!! ");
			}
	/*		finally
			{
				if( preparedStatement != null)
				{
					preparedStatement.close();
				}
			}*/
		}
	}
}