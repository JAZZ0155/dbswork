package exp;

public class exp3 {
	public static String sql1="update sc set grade=grade*1.1 where Cno=1";
	public static String sql2="delete from sc"
			+ "	where Cno in"
			+ "(select Cno from c"
			+ " where Cname='中国酒文化')";
	//Can't write; duplicate key in table
	public static String sql3="delete from s where Sno=18";
}
