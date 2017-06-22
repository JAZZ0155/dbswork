package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import dbswork.DbsUtil;
import dbswork.Student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StuInf extends JPanel implements ActionListener{
	
	private int sno;
	private JTextField name;
	private JTextField sex;
	private JTextField age;
	private JTextField dept;

	/**
	 * Create the panel.
	 */
	public StuInf() {
		setLayout(null);
		
		JLabel label = new JLabel("基本信息");
		label.setBounds(99, 10, 64, 19);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		add(label);
		
		JLabel label_1 = new JLabel("姓名：");
		label_1.setBounds(70, 39, 54, 15);
		add(label_1);
		
		JLabel lblNewLabel = new JLabel("性别：");
		lblNewLabel.setBounds(70, 64, 54, 15);
		add(lblNewLabel);
		
		JLabel label_2 = new JLabel("年龄：");
		label_2.setBounds(70, 89, 54, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel("院系：");
		label_3.setBounds(70, 114, 54, 15);
		add(label_3);
		
		name = new JTextField();
		name.setBounds(109, 39, 93, 21);
		add(name);
		name.setColumns(10);
		
		sex = new JTextField();
		sex.setBounds(109, 63, 93, 21);
		add(sex);
		sex.setColumns(10);
		
		age = new JTextField();
		age.setBounds(109, 89, 93, 21);
		add(age);
		age.setColumns(10);
		
		dept = new JTextField();
		dept.setBounds(109, 114, 93, 21);
		add(dept);
		dept.setColumns(10);
		
		JButton button = new JButton("确定");
		button.addActionListener(this);
		button.setBounds(80, 148, 93, 23);
		add(button);
	}
	
	public void init(int sno){
		this.sno=sno;
		Student s=new Student();
		s.setSno(sno);
		Student.selectStudent(s);
		name.setText(s.getSname());
		sex.setText(s.getSsex()?"男":"女");
		age.setText(String.valueOf(s.getSage()));
		dept.setText(s.getSdept());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Student s=new Student();
		s.setSno(this.sno);
		s.setSname(name.getText());
		s.setSage(Integer.parseInt(age.getText()));
		s.setSdept(dept.getText());
		s.setSsex(sex.getText().equals("男")?true:false);
		try {
			Student.updateStudent(s);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
