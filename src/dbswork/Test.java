package dbswork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exp.Sequence;
import exp.exp2;
import exp.exp3;
import exp.exp4;

public class Test {
	
    public static void main(String[] args) throws SQLException{
    	long startTime = System.currentTimeMillis();
    	DbsUtil.getCon().setAutoCommit(false);
//    	DbsUtil.exec(exp4.sql2);
//    	Student.cleanTable();
//    	DbsUtil.execute("truncate table s");
//    	Student.InsManyStu();
    	Student.NewIns();
//    	StuCou.addForeignKey();
//    	StuCou.dropForeignKey();
//    	StuCou.cleanTable();
//    	for(int i=5;i<20;i++){
//    		StuCou.insertMany(i);
//    	}
//    	String sql="select count(*) from s"
//    			+ " where s.Sdept='艺术'";
//    	ResultSet rs=DbsUtil.executeQuery(sql);
//    	if(rs.next()){
//    		System.out.println(rs.getString(1));
//    	}
//    	String sql="insert into s(Sname,Ssex,Sage,Sdept) values(?,?,?,?)";
//    	PreparedStatement ps=DbsUtil.getCon().prepareStatement(sql);
//    	for(int i=0;i<1000;i++){
//			ps.setString(1, "a");
//			ps.setBoolean(2, true);
//			ps.setInt(3, 1);
//			ps.setString(4, "cc");
//			ps.addBatch();
//		}
//		ps.executeBatch();
//		ps.close();
    	DbsUtil.getCon().commit();
        DbsUtil.disconnect();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}