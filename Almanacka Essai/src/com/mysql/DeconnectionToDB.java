package com.mysql;
import java.sql.Connection;


public class DeconnectionToDB 
{
	public static boolean disconnect(Connection a)
	{
		String url = "jdbc:mysql://localhost:3306/almanacka";
		boolean finalStatus;
		
		try
		{
			Class.forName(url);
			a.close(); 
			finalStatus = true;
		}
		catch (Exception e)
		{
			finalStatus = false;
			e.getMessage();
			System.out.println("Déconnexion impossible");
		}
		return finalStatus;
	}
}