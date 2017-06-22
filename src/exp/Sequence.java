package exp;

public class Sequence {
	public static String createTable="create table sequence"
					+ "(seq_name varchar(20) primary key,"
					+ "current_val int not null,"
					+ "increment_val int default '1' not null)";
	public static String insert="insert into sequence"
			+ " values ('seq1', 1, 1);";
	//只能在mysql client里面输入
	public static String function="delimiter //"
			+ "create funtion a(v_seq_name varchar(20))"
			+ " returns integer"
			+ " begin"
			+ " declare value integer;"
			+ " set value =0;"
			+ " select current_val into value from sequence where seq_name=v_seq_name;"
			+ " return value;"
			+ " end;"
			+ "//";
	
}
