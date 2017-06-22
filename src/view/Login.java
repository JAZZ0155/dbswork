package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private StudentView stuView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("学生选课系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("登录");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(113, 10, 60, 35);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("学号：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(78, 69, 54, 21);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(143, 69, 77, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(99, 124, 93, 23);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(stuView==null){
			stuView=new StudentView(this);
		}
		stuView.init(Integer.parseInt(textField.getText()));
		stuView.setVisible(true);
		this.setVisible(false);
	}
}
