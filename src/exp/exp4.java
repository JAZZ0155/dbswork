package exp;

public class exp4 {
	public static String sql1="create view man(Sno,Sname,Cno,Grade)"
			+ " as select s.Sno,Sname,Cno,Grade from s,sc"
			+ " where s.Ssex=1 and s.Sno=sc.Sno";
	public static String sql2="select Sno,Sname from man"
			+ " group by Sno having avg(Grade)>80";
}
