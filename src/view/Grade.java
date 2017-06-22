package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import dbswork.DbsUtil;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class Grade extends JPanel {

	private int sno;
	private JTextArea jta;
	private JScrollPane scroll;
	/**
	 * Create the panel.
	 */
	public Grade() {
		setLayout(new BorderLayout());
		
		jta = new JTextArea();
		jta.setEditable(false);
//		jta.setBounds(10, 10, 217, 191);
		scroll = new JScrollPane(jta);
		scroll.setVisible(true);
		
		scroll.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		
		add(scroll);

	}
	
	public void init(int sno){
		this.sno=sno;
		String sql="select Cname,Grade from c,sc"
				+ " where c.Cno=sc.cno and sc.Sno="+sno;
		try {
			ResultSet rs=DbsUtil.executeQuery(sql);
			while(rs.next()){
				jta.append(rs.getString(1)+"  "+rs.getInt(2)+"\n");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
