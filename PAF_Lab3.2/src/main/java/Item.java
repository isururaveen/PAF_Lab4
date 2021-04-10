import java.sql.Connection;

import java.sql.*;

public class Item 
{
	//Connect to the db
	public Connection connect() {
		Connection con = null;
		
		try {
			
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://http://localhost:8081/paf_lab","root","");
		
		//for testing
		System.out.println("Successfully Connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	//Insert data
	public String insertItem(String code, String name, String price, String desc) {
			
		String output="";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting";
				
			}
			
			//create prepared statement
			String query  = " insert into item (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
									+ "values (?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Insert Successfully";
		}
		catch(Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Read data
	public String readItems() {
		String output="";
		
		try {
			Connection con = connect();
			if(con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			//Prepare the html table to be displayed
			output = "<table border='1'>"
					+"<tr><th>Item Code</th><th>Item Name</th><th> Item Price</th> <th> Item Description </th>"
					+"<th> Update </th> <th>Remove </th> </tr>";
			
			String query = "SELECT * FROM Item";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while (rs.next()) 
			{
				String ItemID = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String itemDesc = rs.getString("itemDesc");
				
				//Add into the html table
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
				
				//Buttons
				output += "<td><input name ='btnUpdate' type='button' value='Update'</td>"
						+"<td><form method = 'post' action='items.jsp'>"
						+"<input name = 'btnRemove' type='submit' value ='remove'>"
						+"<input name = 'itemID' type = 'hidden' value ='" + ItemID + "'>"
						+"</form></td></tr>";
				
			}
			
			con.close();
			
			//complete the html table
			output += "<table>";
			
		}catch(Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	//Delete Data
	public String deleteItem(String itemID) {
		String output="";
		
		try 
		{
			Connection con = connect();
			
			if(con == null) 
			{
				return "Error while connecting to the database for deleting.";
			}
			
			//Create a prepared statement
			String query = "DELETE FROM item WHERE itemID = ?";
			
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted Successfully";
		}
		catch(Exception e)
		{
			output = "Error while deleting the item";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
