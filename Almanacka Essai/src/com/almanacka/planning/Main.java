package com.almanacka.planning;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import com.mysql.ConnectionToDB;
import com.mysql.CreatePlanning;
import com.mysql.InsertOutputLessonInDB;

public class Main 
{
	public static void main(String[] args) throws  ClassNotFoundException, SQLException, ParseException
	{ 
		Connection a = ConnectionToDB.connect();

		if (a != null)
		{	
			System.out.println("PARTIE INPUTPLANNING" + " " );
			System.out.println(" ");
			System.out.println("  avant appel createInputPlanning");
			
			InputPlanning IP1 = CreatePlanning.createPlanning(a);
		
			System.out.println(" ");
			System.out.println("  après appel createInputPlanning");
			System.out.println(" ");
			System.out.println("PARTIE OUTPUTPLANNING" + " avant appel createOutputPlanning ");
			System.out.println(" ");
			
			OutputLesson.createOutputPlanning(IP1, 0);

			
			System.out.println(" ");
			System.out.println("après appel createOutputplanning");
			System.out.println("  ");

			System.out.println("PARTIE INSERTION BASE DE DONNEES");
			System.out.println(" ");
			System.out.println("  avant appel insertOutputlesson");
			InsertOutputLessonInDB.InsertOutputLesson(a, IP1, 0);
			System.out.println(" ");
			System.out.println("après appel insertOutputlesson");
		}
	}
}