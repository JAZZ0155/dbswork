package exp;

public class exp1 {
	public static String createtable="create table aaa"
							+ "(a1 char(9) primary key,"
							+ "a2 char(10) unique,"
							+ "a3 int"
							+ ");";
	public static String droptable="drop table aaa cascade";
	public static String altertable="alter table aaa "
							+ "add b1 char(5)";
	public static String createindex="create index ind on aaa(a3);";
	public static String dropindex="drop index ind on aaa;";
	public static String insert="insert into aaa values(1,0,0,0)";
}
