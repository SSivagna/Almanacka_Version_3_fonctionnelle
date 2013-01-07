package com.almanacka.planning;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
/*import java.sql.ResultSet;
import java.sql.Statement;*/
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.mysql.ConnectionToDB;
import com.mysql.CreatePlanning;
import com.mysql.DeconnectionToDB;
//import com.mysql.DeconnectionToDB;
//import com.mysql.jdbc.PreparedStatement;
import com.mysql.InsertOutputLessonInDB;

public class Main 
{
	
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException
	{ 
		Connection a = ConnectionToDB.connect();

		if (a != null)
		{			
			System.out.println("PARTIE INPUTPLANNING");
			System.out.println("avant appel createInputPlanning");
			InputPlanning IP1 = CreatePlanning.createPlanning(a);
			IP1.PrintInfos();
			//System.out.println(IP1.getLessons().size());
			System.out.println("après appel createInputPlanning");
			
			
			System.out.println("   ");
			
			/*
			System.out.println("PARTIE OUTPUTPLANNING");
			System.out.println("avant appel createOutputPlanning");
			//	OutputLesson OL = new OutputLesson(test);
			OutputLesson.createOutputPlanning(IP1, 0);
			// il faut que l'on ait des inputlesson ds ce IP1 dont monitorintensites et etc qui ne sont pas null!!!
			System.out.println("après appel createOutputplanning");
			
			/*System.out.println("avant appel insertOutputlesson");
			InsertOutputLessonInDB.InsertOutputLesson(a, IP1, 0);
			System.out.println("après appel insertOutputlesson");*/
		}
	}
}