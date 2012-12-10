package com.almanacka.planning;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import com.mysql.ConnectionToDB;
import com.mysql.CreatePlanning;


public class Main 
{
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("bonjour");
		
		InputLesson test = new InputLesson("1", false, "Paris"/*, "2012-05-01", "2012-05-20"*/);
		test.toString();
		
		System.out.println("");
		System.out.println("PARTIE INPUTPLANNING");
		System.out.println("");
		
		Connection a = ConnectionToDB.connect();
		if (a != null)
		{
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