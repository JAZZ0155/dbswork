package exp;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbswork.DbsUtil;

public class exp2 {
	public static String sql1="select Sno,Sname from s,sc"
			+ " where s.Sno=sc.Sno and sc.Cno=1";
	public static String sql2="select Sno,Sname from s,c,sc"
			+ " where s.Sno=sc.Sno and sc.Cno=c.Cno and c.Cname='数据结构'";
	public static String sql3="select Sno,Sname from s"
			+ " where s.Sno not in"
			+ "(select Sno from sc"
			+ " where Cno=1)";
	public static String sql4="select Sno from s"
			+ " where not exists"
			+ "(select * from c"
			+ " where not exists"
			+ "(select * from sc"
			+ " where sc.Cno=c.Cno and sc.Sno=s.Sno))";
	public static String sql5="select Sno,AVG(Grade) as avg from sc"
			+ " where Sno in"
			+ "(select Sno from s"
			+ " where not exists"
			+ "(select * from sc"
			+ " where s.Sno=sc.Sno and sc.Grade<60 and sc.Cno!=1))"
			+ " group by Sno order by avg desc";
	public static String sql6="select s.Sno,Sname from s,sc"
			+ " where sc.Cno=5 and s.Sno=sc.Sno"
			+ " order by Grade desc limit 1,1";
	public static String sql7="select Sno,Sname from s"
			+ " where Sno in"
			+ "(select Sno from sc"
			+ " where Cno in"
			+ "(select Cno from c"
			+ " where Ccredit=3)"
			+ " and Grade>=80"
			+ " group by Sno"
			+ " having count(*)>=3)";
	public static String sql8_createView="create view scc(Sno,CouNum)"
			+ " as select Sno,count(*) from sc group by sno;";
	public static String sql8="select Sno from scc"
			+ "	where CouNum in"
			+ "(select CouNum from scc"
			+ " group by CouNum"
			+ " having count(*)=1)";
}
