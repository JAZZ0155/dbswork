package exp;

public class exp5 {
	public static String sql1="select Sno,count(*),avg(Grade) from sc"
			+ " where Grade is not null group by Sno";
	public static String sql2="grant all privileges on s,sc,c to public";
}
