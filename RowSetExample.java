import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class RowSetExample 
{

	private static final String DATABASE_URL="jdbc:derby:C:\\Users\\yehudit\\Documents\\מדעי המחשב - האוניברסיטה הפתוחה\\סדנה בתכנות מתקדם בשפת ג'אווה 20503\\JDBC Lecture\\students";
	private static final String USERNAME="kerido";
	private static final String PASSWORD="kerido";
	
	public static void main (String[]args)
	{
		try (JdbcRowSet rowSet=RowSetProvider.newFactory().createJdbcRowSet())
		{
			rowSet.setUrl(DATABASE_URL);
			rowSet.setUsername(USERNAME);
			rowSet.setPassword(PASSWORD);
			rowSet.setCommand("SELECT * FROM students");
			rowSet.execute(); //connection, statement & resultSet is encapsulated.
			ResultSetMetaData metaData=rowSet.getMetaData();
			int numberOfColumns=metaData.getColumnCount();
			System.out.println("Students table of Students Database:\n");
			
			// display rowSet header
	         for (int i = 1; i <= numberOfColumns; i++)
	            System.out.printf("%-8s\t", metaData.getColumnName(i));
	         System.out.println();
	         
	         // display each row
	         while (rowSet.next()) 
	         {
	            for (int i = 1; i <= numberOfColumns; i++)
	               System.out.printf("%8s\t", rowSet.getObject(i));
	            System.out.println();
	         }
		} //at the end of the block all the resources are closed by the jdbcRowSet object.
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);;
		}
	}
}
