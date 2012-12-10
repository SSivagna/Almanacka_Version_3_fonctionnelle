package com.almanacka.planning;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.mysql.ConnectionToDB;
import com.mysql.CreatePlanning;

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

		if (a != null)
		{
			Statement statement = a.createStatement();
			int rSet = statement.executeUpdate("INSERT INTO almanacka.lesson (lessonId, block ,placeId, begDate ,endDate) values ('1','false', '0', '"+date+"', '"+date2+"' );");
			System.out.println("Valeur du int rSet :" + rSet);
			
			
			System.out.println("avant appel CP");
			CreatePlanning.createPlanning(a);
			System.out.println("après appel CP");
		}
		else
		{
			System.out.println("Erreur lors de la connexion depuis le MAIN");
		}
	}
}
//INSERT INTO `almanacka`.`lesson` (`lessonId`, `idPlace`, `idPersonMonitor`, `begDate`, `during`, `idIntensity`, `endDate`) VALUES ('7', '0', '1', '2012-11-01 18:00:00', '1', '2', '2013-01-30 19:00:00');

