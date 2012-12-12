package com.mysql;
import java.sql.Connection;


public class DeconnectionToDB 
{
	public static void disconnect(Connection a)
	{
		String url = "jdbc:mysql://localhost:3306/almanacka";
		
		try
		{
			Class.forName(url);
			a.close(); 
		}
		catch (Exception e)
		{
			e.getMessage();
			System.out.println("Déconnexion impossible");
		}
	}
}