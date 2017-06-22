package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import dbswork.Course;
import dbswork.DbsUtil;
import dbswork.StuCou;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TuiKe extends JPanel implements ActionListener{
	
	private int sno;
	JComboBox<String> comboBox;
	/**
	 * Create the panel.
	 */
	public TuiKe() {
		setLayout(null);
		comboBox = new JComboBox();
		comboBox.setBounds(71, 92, 90, 28);
		add(comboBox);

		JButton button = new JButton("确定");
		button.addActionListener(this);
		button.setBounds(68, 141, 93, 23);
		add(button);
		
		JLabel label = new JLabel("退课");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(98, 39, 37, 33);
		add(label);

	}
	
	public void init(int sno){
		this.sno=sno;
		String sql="select Cname from c,sc"
				+ " where sc.Sno="+sno+" and sc.Cno=c.Cno";
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
		String Cname=comboBox.getSelectedItem().toString();
		int cno=Course.selectCnoByCname(Cname);
		StuCou sc=new StuCou();
		sc.setSno(this.sno);
		sc.setCno(cno);
		try {
			StuCou.deleteSc(sc);
			comboBox.removeItem(comboBox.getSelectedItem());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}

}
