package com.almanacka.planning;

import java.sql.SQLException;
import java.text.ParseException;
import com.mysql.GetPlanning;


public class Main 
{
	public static void main(String[] args) throws  ClassNotFoundException, SQLException, ParseException
	{ 	
		GetPlanning.getPlanning();
	}
}