package dbswork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
	
	private int Sno;
	private String Sname;
	private boolean Ssex;	//1男，0女
	private int Sage;
	private String Sdept;
	
	//10个系
	public static final String[] DEPTS=
		{"计算机","数学","艺术","航天","自动化","机械","电子","外语","经济","文学"};

	public int getSno() {
		return Sno;
	}

	public void setSno(int sno) {
		Sno = sno;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public boolean getSsex() {
		return Ssex;
	}

	public void setSsex(boolean ssex) {
		Ssex = ssex;
	}

	public int getSage() {
		return Sage;
	}

	public void setSage(int sage) {
		Sage = sage;
	}

	public String getSdept() {
		return Sdept;
	}

	public void setSdept(String sdept) {
		Sdept = sdept;
	}
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	//Sno,Sname,Ssex,Sage,Sdept
	public Student(String Sname) {
		this.Sname=Sname;
	}
	
	//创建表
	public static void createTable() throws SQLException{
		String sql="create table s"
				+ "(Sno int primary key,"
				+ "Sname char(10),"
				+ "Ssex bit,"
				+ "Sage int,"
				+ "Sdept char(10)"
				+ ");";
		DbsUtil.execute(sql);
	}
	
	//删除表
	public static void dropTable() throws SQLException{
		String sql="drop table s";
		DbsUtil.execute(sql);
	}
	
	public static void cleanTable() throws SQLException{
		String sql="delete from s";
		DbsUtil.execute(sql);
	}
	
	//删除行
	public static void deleleStudent(Student s) throws SQLException{
		String sql="delete from s "
				+ "where Sno="+s.Sno;
		DbsUtil.execute(sql);
	}

	//添加行
	//Sno,Sname,Ssex,Sage,Sdept
	//设置主键
	public static void insertStudent(Student s) throws SQLException{
		String sql="insert into s values("+s.Sno+",'"+s.Sname+"',"+
				(s.Ssex?"1":"0")+","+s.Sage+",'"+s.Sdept+"')";
		DbsUtil.execute(sql);
	}
	
	//Sno,Sname,Ssex,Sage,Sdept
	//不设置主键
	public static void insertStudent(Student s,boolean index) throws SQLException{
		String sql;
		if(index){
			sql="insert into s values("+s.Sno+",'"+s.Sname+"',"+
					(s.Ssex?"1":"0")+","+s.Sage+",'"+s.Sdept+"')";
		}else{
			sql="insert into s(Sname,Ssex,Sage,Sdept) values('"+
					s.Sname+"',"+(s.Ssex?"1":"0")+","+s.Sage+",'"+s.Sdept+"')";
		}
		DbsUtil.execute(sql);
	}
	
//	public static void insertStudent(Student s,boolean thr) throws SQLException{
//		String sql="insert into s values("+s.Sno+",'"+s.Sname+"',"+
//				(s.Ssex?"1":"0")+","+s.Sage+",'"+s.Sdept+"')";
//		DbsUtil.execute(sql,thr);
//	}
	
	//更新行
	public static void updateStudent(Student s) throws SQLException{
		String sql="update s "
				+ "set Sname='"+s.Sname+"',Ssex="+(s.Ssex?"1":"0")+",Sage="+s.Sage+",Sdept='"+s.Sdept+"'"
				+ " where Sno="+s.Sno+";";
		DbsUtil.execute(sql);
	}
	
	//查询行
	//Sno,Sname,Ssex,Sage,Sdept
	public static void selectStudent(Student s){
		String sql="select * from s "
				+ "where Sno="+s.Sno;
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			if(rs.next()){
				s.setSname(rs.getString("Sname"));
				s.setSsex(rs.getBoolean("Ssex"));
				s.setSage(rs.getInt("Sage"));
				s.setSdept(rs.getString("Sdept"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//增加多行
	//Sno,Sname,Ssex,Sage,Sdept
	public static void InsManyStu() throws SQLException{
		Student stu=new Student();
		for(int i=0;i<10000;i++){
			Student.randomStu(stu);
			Student.insertStudent(stu,false);
		}
	}
	
	public static void NewIns(){
		Student stu=new Student();
		String sql="insert into s(Sname,Ssex,Sage,Sdept) values(?,?,?,?)";
		try {
			PreparedStatement ps=DbsUtil.getCon().prepareStatement(sql);
			for(int i=0;i<1000000;i++){
				Student.randomStu(stu);
				ps.setString(1, stu.Sname);
				ps.setBoolean(2, stu.Ssex);
				ps.setInt(3, stu.Sage);
				ps.setString(4, stu.Sdept);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void randomStu(Student s){
//		s.setSno((int)(1+Math.random()*10000));
		s.setSname(DbsUtil.randomName());
		s.setSsex((int)(0.5+Math.random())<1);
		s.setSage(17+(int)(Math.random()*10));
		s.setSdept(DEPTS[(int)(Math.random()*DEPTS.length)]);
	}
	
	public void show(){
		System.out.println(this.Sno+","+this.Sname+","+(this.Ssex?"男":"女")+","+this.Sage+","+this.Sdept);
	}
	
}
