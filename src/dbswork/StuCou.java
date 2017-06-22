package dbswork;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StuCou {
	
	private int Sno;
	private int Cno;
	private int Grade;
	
	public int getSno() {
		return Sno;
	}

	public void setSno(int sno) {
		Sno = sno;
	}

	public int getCno() {
		return Cno;
	}

	public void setCno(int cno) {
		Cno = cno;
	}

	public int getGrade() {
		return Grade;
	}

	public void setGrade(int grade) {
		Grade = grade;
	}

	public static void createTable() throws SQLException{
		String sql="create table sc"
				+ "(Sno int,"
				+ "Cno int,"
				+ "Grade int"
				+ "primary key(Sno,Cno),"
				+ "foreign key(Sno) references s(Sno),"
				+ "foreign key(Cno) references c(Cno)"
				+ ");";
		DbsUtil.execute(sql);
	}

	//Sno,Cno,Grade
	public static void dropTable() throws SQLException{
		String sql="drop table sc";
		DbsUtil.execute(sql);
	}
	
	public static void cleanTable() throws SQLException{
		String sql="delete from sc";
		DbsUtil.execute(sql);
	}
	
	public static void addForeignKey() throws SQLException{
		String sql1="alter table sc"
				+ " add constraint for_sno foreign key(Sno) references s(Sno) on delete cascade on update cascade";
		String sql2="alter table sc"
				+ " add constraint for_cno foreign key(Cno) references c(Cno) on delete cascade on update cascade";
		DbsUtil.execute(sql1);
		DbsUtil.execute(sql2);
	}
	
	public static void dropForeignKey() throws SQLException{
		String sql1="alter table sc"
				+ " drop foreign key for_sno";
				
		String sql2="alter table sc"
				+ " drop foreign key for_cno";
		DbsUtil.execute(sql1);
		DbsUtil.execute(sql2);
	}
	
	//Sno,Cno,Grade
	public static void deleteSc(StuCou sc) throws SQLException{
		String sql="delete from sc"
				+ " where Sno="+sc.Sno+" and Cno="+sc.Cno;
		DbsUtil.execute(sql);
	}
	
	//Sno,Cno,Grade
	public static void insertSc(StuCou sc) throws SQLException{
		String sql="insert into sc values("+sc.Sno+","+sc.Cno+","+sc.Grade+")";
		DbsUtil.execute(sql);
	}
	
	public static int randomGrade(){
		return (int)(60+Math.random()*41);
	}
	
	public static void insertMany(int sno) throws SQLException{
		StuCou sc=new StuCou();
		sc.setSno(sno);
		for(int i=0;i<Course.COURSES.length;i++){
			sc.setCno(i+1);
			sc.setGrade(StuCou.randomGrade());
			StuCou.insertSc(sc);
		}
	}
	
	//Sno,Cno,Grade
	public static void updateSc(StuCou sc) throws SQLException{
		String sql="update sc"
				+ " set grade="+sc.Grade
				+ " where Sno="+sc.Sno+" and Cno="+sc.Cno;
		DbsUtil.execute(sql);
	}
	
	public static void selectSc(StuCou sc){
		String sql="select * from sc"
				+ "where Sno="+sc.Sno+" and Cno="+sc.Cno;
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			if(rs.next()){
				sc.Sno=rs.getInt("Sno");
				sc.Cno=rs.getInt("Cno");
				sc.Grade=rs.getInt("Grade");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
