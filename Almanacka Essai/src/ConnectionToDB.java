import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionToDB 
{
	public static boolean connect(Connection connection) throws ClassNotFoundException
	{
		String url = "jdbc:mysql://localhost:3306/almanacka";
		String user = "root";
		String password = "root";		
		boolean status;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion à la base de données REUSSIE");
			status = true;
		}
		catch (SQLException e)
		{
			status = false;
			e.getMessage();
			System.out.println("Erreur de connexion à la base de données");
		}
		return status;
	}
}
