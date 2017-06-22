package dbswork;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
	
	private int Cno;
	private String Cname;
	private int Cpno;
	private int Ccredit;	//都是1学分吧
	
	public static final String[] COURSES={"高数","英语","体育",
			"微观经济学","数据库原理","管理学原理","计算机基础及应用","普通逻辑学",
			"社会学概论","健康教育","经济学原理","社会心理学","西方社会学理论","中国酒文化",
			"人力资源管理","社会救助学","社会统计学","宗教社会学","组织行为学"};
	
	public int getCno() {
		return Cno;
	}

	public void setCno(int cno) {
		Cno = cno;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public int getCpno() {
		return Cpno;
	}

	public void setCpno(int cpno) {
		Cpno = cpno;
	}

	public int getCcredit() {
		return Ccredit;
	}

	public void setCcredit(int ccredit) {
		Ccredit = ccredit;
	}

	//创建表
	public static void createTable() throws SQLException{
		String sql="create table c"
				+ "(Cno int primary key,"
				+ "Cname char(10) unique,"
				+ "Cpno int,"
				+ "Ccredit int"
				+ ");";
		DbsUtil.execute(sql);
	}
	
	//删除表
	public static void dropTable() throws SQLException{
		String sql="drop table c";
		DbsUtil.execute(sql);
	}
	
	public static void cleanTable() throws SQLException{
		String sql="delete from c";
		DbsUtil.execute(sql);
	}
	
	//删除行
	public static void deleleCourse(Course c) throws SQLException{
		String sql="delete from c"
				+ "where Cno="+c.Cno;
		DbsUtil.execute(sql);
	}
	
	//添加行
	//Cno,Cname,Cpno,Ccredit
	//设置主键
	public static void insertCourse(Course c) throws SQLException{
		String sql="insert into c values("+c.Cno+",'"
				+c.Cname+"',"+c.Cpno+","+c.Ccredit+")";
		DbsUtil.execute(sql);
	}
	
	//Cno,Cname,Cpno,Ccredit
	//不设置主键，自增长
	public static void insertCourse(Course c,boolean index) throws SQLException{
		String sql;
		if(index){
			sql="insert into c values("+c.Cno+",'"
					+c.Cname+"',"+c.Cpno+","+c.Ccredit+");";
		}else{
			sql="insert into c(Cname,Cpno,Ccredit) values('"+
					c.Cname+"',"+c.Cpno+","+c.Ccredit+")";
		}
		DbsUtil.execute(sql);
	}
	
	//Cno,Cname,Cpno,Ccredit
//	public static void insertCourse(Course c,boolean thr) throws SQLException{
//		String sql="insert into c values("+c.Cno+",'"
//				+c.Cname+"',"+c.Cpno+","+c.Ccredit+");";
//		DbsUtil.execute(sql,thr);
//	}
	
	//更新行
	//Cno,Cname,Cpno,Ccredit
	public static void updateCourse(Course c) throws SQLException{
		String sql="update c "
				+ "set Cname='"+c.Cname+"',Cpno="+c.Cpno+",Ccredit="+c.Ccredit
				+ " where Cno="+c.Cno;
		DbsUtil.execute(sql);
	}
	
	//查询行
	//Cno,Cname,Cpno,Ccredit
	public static void selectCourse(Course c){
		String sql="select * from c "
				+ "where Cno="+c.Cno;
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			if(rs.next()){
				c.setCno(rs.getInt("Cno"));
				c.setCname(rs.getString("Cname"));
				c.setCpno(rs.getInt("Cpno"));
				c.setCcredit(rs.getInt("Ccredit"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int selectCnoByCname(String name){
		String sql="select Cno from c"
				+ " where Cname='"+name+"'";
		int cno = 0;
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			if(rs.next()){
				cno=rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return cno;
	}
	
	public static void insManyCou() throws SQLException{
		Course cou=new Course();
		cou.setCcredit(1);
		for(int i=0;i<COURSES.length;i++){
			cou.setCname(COURSES[i]);
			Course.insertCourse(cou,false);
		}
	}
	
	//Cno,Cname,Cpno,Ccredit
	public static void randomStu(Course c){
		c.setCno((int)(1+Math.random()*10000));
		c.setCname(COURSES[(int)(Math.random()*COURSES.length)]);
		c.setCpno(0);
		c.setCcredit((int)(1+Math.random()*5));
	}
	
	public void show(){
		System.out.println(this.Cno+","+this.Cname+","+this.Cpno+","+this.Ccredit);
	}
}
