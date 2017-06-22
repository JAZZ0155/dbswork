package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbswork.DbsUtil;
import dbswork.Student;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AdminView extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField grade;
	private JButton find;
	private JButton pingfen;
	private JLabel no;
	private JLabel name;
	private JLabel sex;
	private JLabel age;
	private JLabel dept;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminView() {
		setResizable(false);
		setTitle("管理员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		textField.setBounds(148, 22, 110, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("搜索学号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(22, 10, 127, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("学号：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(43, 116, 48, 28);
		contentPane.add(lblNewLabel_1);
		
		no = new JLabel("..");
		no.setFont(new Font("宋体", Font.PLAIN, 16));
		no.setBounds(101, 116, 72, 28);
		contentPane.add(no);
		
		name = new JLabel("..");
		name.setFont(new Font("宋体", Font.PLAIN, 16));
		name.setBounds(101, 154, 72, 28);
		contentPane.add(name);
		
		JLabel label_1 = new JLabel("姓名：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(43, 154, 48, 28);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("性别：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(43, 192, 48, 28);
		contentPane.add(label_2);
		
		sex = new JLabel("..");
		sex.setFont(new Font("宋体", Font.PLAIN, 16));
		sex.setBounds(101, 192, 72, 28);
		contentPane.add(sex);
		
		age = new JLabel("..");
		age.setFont(new Font("宋体", Font.PLAIN, 16));
		age.setBounds(101, 230, 72, 28);
		contentPane.add(age);
		
		JLabel label_5 = new JLabel("年龄");
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));
		label_5.setBounds(43, 230, 48, 28);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("系别：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 16));
		label_6.setBounds(43, 268, 48, 28);
		contentPane.add(label_6);
		
		dept = new JLabel("..");
		dept.setFont(new Font("宋体", Font.PLAIN, 16));
		dept.setBounds(101, 268, 72, 28);
		contentPane.add(dept);
		
		JButton btnNewButton = new JButton("退出");
		btnNewButton.setBounds(450, 24, 72, 23);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox.setBounds(264, 173, 95, 32);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("选择课程");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(326, 111, 72, 39);
		contentPane.add(lblNewLabel_3);
		
		grade = new JTextField();
		grade.setFont(new Font("宋体", Font.PLAIN, 16));
		grade.setBounds(386, 173, 95, 32);
		contentPane.add(grade);
		grade.setColumns(10);
		
		pingfen = new JButton("评分");
		pingfen.addActionListener(this);
		pingfen.setActionCommand("pingfen");
		pingfen.setBounds(326, 234, 72, 23);
		contentPane.add(pingfen);
		
		find = new JButton("搜索");
		find.setActionCommand("find");
		find.addActionListener(this);
		find.setFont(new Font("宋体", Font.PLAIN, 14));
		find.setBounds(101, 60, 72, 23);
		contentPane.add(find);
	}
	
	public void find(){
		Student s=new Student();
		int sno=Integer.parseInt(textField.getText());
		s.setSno(sno);
		Student.selectStudent(s);
		no.setText(String.valueOf(s.getSno()));
		name.setText(s.getSname());
		sex.setText(s.getSsex()?"男":"女");
		age.setText(String.valueOf(s.getSage()));
		dept.setText(s.getSdept());
		
		comboBox.removeAll();
		String sql="select Cname from c,sc"
				+ " where c.Cno=sc.Cno and sc.Sno="+sno;
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			while(rs.next()){
				comboBox.addItem(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pingfen(){
		String sql="update sc set grade="+grade.getText()
		+ " where Sno="+Integer.parseInt(no.getText())
		+ " and Cno=(select Cno from c where Cname='"+comboBox.getSelectedItem().toString()+"')";
		try {
			DbsUtil.execute(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "find":
			find();
			break;
		case "pingfen":
			pingfen();
			break;
		}
	}
}
