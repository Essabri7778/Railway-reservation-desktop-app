package application.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import application.outils.DbConnection;

public class CostumerModel {

		private int id;
		private String CIN;
		private String name;
		private String email;
		private String tele;
		private String password;
		private DbConnection connexionbase = new DbConnection();
		private static int index=1;
		
		public CostumerModel() {
		}
		public CostumerModel(String CIN,String name,String email,String tele,String password) {
			this.CIN = CIN;
			this.name = name;
			this.email = email;
			this.tele = tele;
			this.password = password;
			
		}
		//***************************** ADD CUSTUMERS *************************************************
		 public int CreateCostumer(String CIN,String name,String email,String tele,String password) {
			 	PreparedStatement preparedStatement = null;
				String sql =null;
				int isAffected=0;
				String sqlQuery1 = "select max(id_costumer) from costumers";
		        ResultSet rs=null;
		        PreparedStatement stmt1 = null;
				try {
					Connection connection = connexionbase.getConnection();
					stmt1 = connection.prepareStatement(sqlQuery1);
		        	rs=stmt1.executeQuery();
		        	rs.next();
		        	index=rs.getInt(1);
					sql = "insert into costumers(id_costumer,name_costumer,CIN,email,tele,passworde) values(?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1,index+1);
					preparedStatement.setString(2,CIN);
					preparedStatement.setString(3,name);
					preparedStatement.setString(4,email);
					preparedStatement.setString(5,tele);
					preparedStatement.setString(6,password);
					//CONTROLLEUR
					/*if(ValidName(name)) {
						preparedStatement.setString(3,name);
					/*} 
					/*if( ValidEmail(email)) {
						preparedStatement.setString(4,email);
					}*/
					/*if(ValidNumberPhone(tele)) {
						preparedStatement.setString(5,tele);
					}
					if(ValidPassword(password)) {
						preparedStatement.setString(6,password);
					}*/
					isAffected = preparedStatement.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isAffected;
		 }
		 public void AddCustumer(String CIN,String name,String email,String tele,String password) {
			 
		 }
		 //**************************************** DELETE CUSTUMERS ************************************************************
		 public boolean DeleteCostumer(String cin) {
			PreparedStatement preparedStatement = null;
			String sql =null;
			boolean isDeleted=false;
			 try {
	                Connection con = connexionbase.getConnection();				
					sql = "delete from costumers where cin =?";
					preparedStatement = con.prepareStatement(sql);
					preparedStatement.setString(1,cin);
					isDeleted = preparedStatement.execute();
				 
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 return isDeleted;
		 }
		 //********************************************* UPDATE CUSTUMERS ********************************************************************
		 public int UpdateCostumer(String name,String CIN,String email,String tele,String password) {
			 PreparedStatement preparedStatement = null;
			 String sql =null;
			 int isUppdated=0;
			 try {
				 Connection con = connexionbase.getConnection();
				 sql = "update costumers set name_costumer=?,CIN=?,email=?,tele=?,passworde=? where cin=?";
				 preparedStatement = con.prepareStatement(sql);
					    preparedStatement.setString(1,name);
					    preparedStatement.setString(2,CIN);
					    preparedStatement.setString(3,email);
					    preparedStatement.setString(4,tele);
					    preparedStatement.setString(5,password);
					    preparedStatement.setString(6,CIN);
					    isUppdated = preparedStatement.executeUpdate();
					    
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 return isUppdated;
		 }
		 //*********************************************** SEARCH CUSTUMERS **************************************************
		public void SearchCostumer(String input) {
			try {
				PreparedStatement preparedStatement = null;
				Connection con = connexionbase.getConnection();
				String sql="select name_costumers from costumers where name like ?";
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1,"%"+input+"%");
				ResultSet result = preparedStatement.executeQuery();
				while(result.next()) {
					System.out.println(result.getString("name_costumer"));
			    }
		   }catch (Exception e) {
				e.printStackTrace();
			}
		}
		//****************************** GET ALL CUSTUMERS **********************************************************
		public ResultSet AllCostumers() {
			ResultSet result =null;
			try {
				PreparedStatement preparedStatement = null;
				Connection con = connexionbase.getConnection();
			    String sql="select * from costumers order by name_costumer";
			    preparedStatement = con.prepareStatement(sql);

			    result = preparedStatement.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		//************************************************ GET ONE CUSTUMERS ******************************************************************
		public ResultSet getCustumer(String cin) {
			ResultSet result =null;
			try {
				PreparedStatement preparedStatement = null;
				Connection con = connexionbase.getConnection();
			    String sql="select * from costumers where cin = ?";
			    preparedStatement = con.prepareStatement(sql);
			    result = preparedStatement.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
			
		}
		//************************************************** GETTERS *******************************************************
		
		public int getId() {
			return id;
		}
		
		public String getCIN() {
			return CIN;
		}
		
		public String getName() {
			return name;
		}
		
		public String getEmail() {
			return email;
		}
		
		public String getTele() {
			return tele;
		}
		
		public String getPassword() {
			return password;
		}
		
		
		
		
		
	}



