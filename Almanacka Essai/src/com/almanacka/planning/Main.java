package com.almanacka.planning;

import java.sql.Connection;
/*import java.sql.ResultSet;
import java.sql.Statement;*/
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.mysql.ConnectionToDB;
import com.mysql.CreatePlanning;
//import com.mysql.jdbc.PreparedStatement;

public class Main 
{
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("bonjour");
		String alpha = "2012-05-01 18:00";
		String beta = "2012-05-20 19:00";
		
		Date date = new SimpleDateFormat("YYYY-MM-dd HH:mm", Locale.ENGLISH).parse(alpha);
		Date date2 = new SimpleDateFormat("YYYY-MM-dd HH:mm", Locale.ENGLISH).parse(beta);
		InputLesson test = new InputLesson("1", false, "0", date, date2);
		test.toString();
		
		System.out.println("");
		System.out.println("PARTIE INPUTPLANNING");
		System.out.println("");
		
		Connection a = ConnectionToDB.connect();

		if (a != null)//"INSERT INTO pet(name,sex,dob) VALUES("+value1+", 'm','1998-11-11')");
		{
			/*Statement statement = /*ConnectionToDB.connect().createStatement(); a.createStatement();
			try
			{
				int rSet = statement.executeUpdate( "INSERT INTO almanacka.lesson (lessonId, block ,idPlace, begDate, endDate) VALUES ('1', 'false', '0',  "+'" + date + "'+", + "'"+ date2 +"'");" );
				int rSet = statement.executeUpdate( "INSERT INTO almanacka.lesson (begDate, endDate, lessonId ,idPlace) VALUES ("+ date +" , " + date2 + ", '0', '5')");
				System.out.println("Valeur du int rSet :" + rSet);
			}
			catch (Exception e)
			{
				System.out.println("erreur lors de l'insertion ");
			}*/
			
	/*		PreparedStatement st = null;
			st = a.prepareStatement(Retrieve_st);
			st.setString(1, 0);
			
			ResultSet r = */
			
			System.out.println("avant appel CP");
			InputPlanning IP1 =CreatePlanning.createPlanning(a);
			System.out.println("après appel CP");
			
			System.out.println("avant appel Optimize");
			OutputLesson OL = new OutputLesson(test);
			OL.optimize(IP1);
			System.out.println("après appel Optimize");
		}
		else
		{
			System.out.println("Erreur lors de la connexion depuis le MAIN");
		}
	}
}