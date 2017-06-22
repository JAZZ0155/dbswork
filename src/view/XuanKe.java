package view;

import javax.swing.JPanel;

import dbswork.Course;
import dbswork.DbsUtil;
import dbswork.StuCou;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XuanKe extends JPanel implements ActionListener{
	
	JComboBox<String> comboBox;
	private int sno;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	/**
	 * Create the panel.
	 */
	public XuanKe() {
		setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(58, 96, 112, 28);
		add(comboBox);
		
		JButton button = new JButton("确定");
		button.addActionListener(this);
		button.setBounds(69, 134, 93, 23);
		add(button);
		
		JLabel label = new JLabel("选课");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(94, 43, 39, 28);
		add(label);
	}
	
	public void init(int sno){
		this.sno=sno;
		comboBox.removeAll();
		String sql="select Cname from c"
				+ " where Cno not in"
				+ "(select Cno from sc"
				+ " where Sno="+sno+")";
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			while(rs.next()){
				comboBox.addItem(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int cno=Course.selectCnoByCname(comboBox.getSelectedItem().toString());
		StuCou sc=new StuCou();
		sc.setSno(this.sno);
		sc.setCno(cno);
		try {
			StuCou.insertSc(sc);
			comboBox.removeItem(comboBox.getSelectedItem());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}
	
}
