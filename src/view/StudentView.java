package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentView extends JFrame implements ActionListener{

	private JFrame father;
	private int Sno;
	private JPanel contentPane;
	private CardLayout cl;
	private StuInf jibenxinxi;
	private XuanKe xuanke;
	private TuiKe tuike;
	private Grade chengji;
	private JPanel boxPanel;
	private JButton ji;
	private JButton xuan;
	private JButton tui;
	private JButton exit;
	private JButton grade;
	
	public void init(int sno){
		Sno = sno;
		this.setTitle("学生学号："+sno);
		jibenxinxi.init(this.Sno);
	}

	/**
	 * Create the frame.
	 */
	public StudentView(JFrame father) {
		this.father=father;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 237, 64);
		contentPane.add(panel);
		
		boxPanel = new JPanel();
		boxPanel.setBounds(10, 84, 237, 211);
		contentPane.add(boxPanel);
		cl=new CardLayout();
		boxPanel.setLayout(cl);
		
		jibenxinxi=new StuInf();
		boxPanel.add(jibenxinxi,"ji");
		xuanke=new XuanKe();
		boxPanel.add(xuanke,"xuan");
		tuike=new TuiKe();
		boxPanel.add(tuike,"tui");
		chengji=new Grade();
		boxPanel.add(chengji,"cheng");
		
		ji = new JButton("基本信息");
		ji.setFont(new Font("宋体", Font.PLAIN, 12));
		ji.addActionListener(this);
		ji.setActionCommand("ji");
		panel.add(ji);
		
		xuan = new JButton("选课");
		xuan.setFont(new Font("宋体", Font.PLAIN, 12));
		xuan.addActionListener(this);
		xuan.setActionCommand("xuan");
		panel.add(xuan);
		
		tui = new JButton("退课");
		tui.setFont(new Font("宋体", Font.PLAIN, 12));
		tui.addActionListener(this);
		tui.setActionCommand("tui");
		panel.add(tui);
		
		exit = new JButton("退出");
		exit.setFont(new Font("宋体", Font.PLAIN, 12));
		exit.addActionListener(this);
		
		grade = new JButton("成绩");
		grade.addActionListener(this);
		grade.setFont(new Font("SimSun", Font.PLAIN, 12));
		grade.setActionCommand("grade");
		panel.add(grade);
		exit.setActionCommand("exit");
		panel.add(exit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "ji":
			jibenxinxi.init(this.Sno);
			cl.show(boxPanel, "ji");
			break;
		case "xuan":
			xuanke.init(this.Sno);
			cl.show(boxPanel, "xuan");
			break;
		case "tui":
			tuike.init(this.Sno);
			cl.show(boxPanel, "tui");
			break;
		case "exit":
			this.setVisible(false);
			this.father.setVisible(true);
			break;
		case "grade":
			chengji.init(this.Sno);
			cl.show(boxPanel, "cheng");
			break;
		}
	}
}
