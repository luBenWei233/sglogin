package org.sg.lf.sglogin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import org.sg.lf.sglogin.util.DataBaseConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BaseDao {
	
	private static ComboPooledDataSource cpds;
	//jdbc
	private static LinkedList<Connection> pool;
	
    protected static void getSource(){
		try{
			String ssl="";
			if(DataBaseConfig.getSsl()==false) {
				ssl="&useSSL=false";
			}
			cpds.setDriverClass( "com.mysql.jdbc.Driver" );  
			cpds.setJdbcUrl("jdbc:mysql://"+DataBaseConfig.getUrl()
			+":"+DataBaseConfig.getPort()
			+"/"+DataBaseConfig.getDatabase()
			+"?characterEncoding=UTF-8&testConnectionOnCheckout=true&autoReconnect=true&tcpRcvBuf=10240000&serverTimezone=UTC"+ssl);
			cpds.setUser(DataBaseConfig.getUsername());
			cpds.setPassword(DataBaseConfig.getPassword());
			// 下面的设置是可选的，c3p0可以在默认条件下工作，也可以设置其他条件
			cpds.setMinPoolSize(DataBaseConfig.getPoolMinCount());   
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(DataBaseConfig.getPoolMaxCount());
		 }
	catch (Exception e)
	    { e.printStackTrace(); }
    }
    
    private static Connection getOneConnection() {
		Connection con=null;
		try{
			//MySql的数据连接
			String ssl="";
			if(DataBaseConfig.getSsl()==false) {
				ssl="&useSSL=false";
			}
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+DataBaseConfig.getUrl()
			+":"+DataBaseConfig.getPort()
			+"/"+DataBaseConfig.getDatabase()
			+"?characterEncoding=UTF-8&testConnectionOnCheckout=true&autoReconnect=true&tcpRcvBuf=10240000&serverTimezone=UTC"+ssl,
			DataBaseConfig.getUsername(), 
			DataBaseConfig.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
    
    protected static Connection getConnection(){
        Connection conn = null;
        try {
        	if("mysql".equals(DataBaseConfig.getDataName())) {
        		if("jdbc".equals(DataBaseConfig.getPoolType())) {
        			if(pool==null){
        	            pool = new LinkedList<>();
        	            for(int i=0;i<DataBaseConfig.getPoolMinCount();i++){
        	                pool.add(getOneConnection());
        	            }
        	        }
        	        if(pool.size()<=0){
        	            return getOneConnection();
        	        }
        	        conn=pool.remove();
        	        if(conn==null) {
        	        	pool.clear();
        	            for(int i=0;i<DataBaseConfig.getPoolMinCount();i++){
        	                pool.add(getOneConnection());
        	            }
        	            return pool.remove();
        	        }
        	        return conn;
        		}else {
        			if(cpds==null) {
        				cpds = new ComboPooledDataSource();
        				getSource();
        			}
        			conn = cpds.getConnection();
                    if(conn==null) {
                    	getSource();
                    	conn = cpds.getConnection();
                    }
        		}
        	}else {
        		//sqlite3
				Class.forName("org.sqlite.JDBC");
				return DriverManager.getConnection("jdbc:sqlite:plugins/SGLogin/sglogin.sqlite3");
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    protected void closeAll(Connection con,Statement s,ResultSet r)
    {
	try
	    {
		if("mysql".equals(DataBaseConfig.getDatabase())) {
			if("jdbc".equals(DataBaseConfig.getPoolType())) {
				if (con != null) pool.add(con);
			    if (s != null) s.close();
			    if (r != null) r.close();
			}else {
				if (con != null) con.close();
			    if (s != null) s.close();
			    if (r != null) r.close();
			}
		}else {
			if (con != null) con.close();
		    if (s != null) s.close();
		    if (r != null) r.close();
		}
		
	    }catch (Exception e)
	    { e.printStackTrace();}
    }
}
