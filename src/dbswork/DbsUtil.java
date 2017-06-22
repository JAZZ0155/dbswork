package dbswork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbsUtil {
	
	private static Connection con=null;
	private static Statement st=null;
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/another?useSSL=true";
	private static String USER = "root";
	private static String PASSWORD = "root";
	
    public static Connection getCon() {
		return con;
	}
    
//    public static Statement getSt() {
//		return st;
//	}

	static{
    	try {
			Class.forName(DRIVER);
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void connect() throws Exception{
        con = DriverManager.getConnection(URL,USER,PASSWORD);
        st=con.createStatement();
    }
	
	public static void disconnect() throws SQLException{
		con.close();
	}
	
	public static void execute(String sql) throws SQLException{
		try{
			con.createStatement().execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
//			throw new SQLException(sql);
		}
	}
	
	public static void exec(String sql){
		ResultSet rs;
		try {
			rs = DbsUtil.executeQuery(sql);
			while(rs.next()){
	    		System.out.println(rs.getInt(1)/*+" "+rs.getString(2)*/);
	    	}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void execute(String sql,boolean thr) throws SQLException{
//		if(thr){
//			st.execute(sql);
//		}else{
//			DbsUtil.execute(sql);
//		}
//	}
	
	public static ResultSet executeQuery(String sql) throws SQLException{
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}
    
    public static String randomName(){
    	String name = "";
    	for(int i=0;i<10;i++){
    		int j=(int) (Math.random()*52);
    		if(j>25) j+=6;
    		char c=(char) (65+j);
    		name+=c;
    	}
    	return name;
    }
}
