package com;
import java.sql.*;


public class Item {
	
	
	public Connection connect() {
		
		Connection con = null;
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/test1","root","");
				
				//For testing 
				
				System.out.println("Successfully connected");
				
			} 
		
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return con;
		
		
	
}
	
	public String insertItem(String code,String name,String price, String desc) {
		
		String output = "";
		
		try {
				Connection con = connect();
				
				if(con == null) 
				{
					return "Error while connecting to the database";
				
				}
				
				// create prepared statement
				String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" + " values (?,?,?,?,?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2,code);
				preparedStmt.setString(3,name);
				preparedStmt.setDouble(4, Double.parseDouble(price));
				preparedStmt.setString(5, desc);
				
				//execute statement
				
				preparedStmt.execute();
				con.close();
				
				
				output = "Inserted Successfully";
		
				
				}catch(Exception e) {
		    	
					output = "Error while inserting";
					
					System.err.println(e.getMessage());
		    	
				}
		
				return output;
		
		}
	
		public String readItems()
		{
				String output = "";
				
				try {
						Connection con =  connect();
						
						if(con == null) 
						{
							return "Error while connecting to the database for reading";
							
						}
						else
						{
							String query = "select * from items";
							
							Statement stmt = con.createStatement();
							
							ResultSet rs = stmt.executeQuery(query);
							
							// iterate through the rows in the result set
							
							while(rs.next()) {
								
								String itemID = Integer.toString(rs.getInt("itemID"));
								String itemCode = rs.getString("itemCode");
								String itemName = rs.getString("itemName");
								String itemPrice = Double.toString(rs.getDouble("itemPrice"));
								String Description = rs.getString("itemDesc");
								
								
								//Add a row into the html table
								
								output += "<tr><td>" +itemCode +  "</td>";
								output += "<td>" + itemName + "</td>";
								output += "<td>" + itemPrice + "</td>";
								output += "<td>" + Description + "</td>";
								
								// buttons
								
								output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
									   + "<td><form method='post' action='items.jsp'>"
									   + "<input name='btnRemove' type='submit' value='Remove'>"
									   + "<input name='itemID' type='hidden' value='" + itemID + "'>"
									   + "</form></td></tr>";
								
								}
								
								con.close();
							
							
							
							
						}
						
				}catch(Exception e) 
				{
					output = "Error while reading the items";
					System.err.println(e.getMessage());
					
				}
				
				
				return output;
		
		}
	
		
	
	
}
	
