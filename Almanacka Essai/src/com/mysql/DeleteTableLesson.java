package com.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTableLesson 
{
	static public void DeleteTableInDB(Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		int rSet =	statement.executeUpdate("TRUNCATE TABLE FROM almanacka.gggh");
		System.out.println("Valeur du int rSet :" + rSet);
	}
}