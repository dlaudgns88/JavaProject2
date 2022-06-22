package Signup;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Books.BooksADM;
import Books.Books_Gui;
import Main.Main_Frame;

public class admlogin extends JFrame implements ActionListener {
	private JPanel main = new JPanel();
	private JPanel north = new JPanel();
	private JPanel west = new JPanel();
	private JPanel east = new JPanel();
	private JPanel south = new JPanel();
	private JLabel loginL = new JLabel("관리자번호 입력");
	private JPasswordField pass= new JPasswordField(4);
	private JButton check = new JButton("로그인");
	private String admpass = "1111";
	
	Main_Frame mf ;
	BooksADM badm;
	Books_Gui bGui;
	
	public admlogin()	{
		this.setSize(300,200);
		this.setLocation(200, 200);
		//this.setBounds(200, 200, 300, 200);
		north.setPreferredSize(new Dimension(300,20));
		west.setPreferredSize(new Dimension(50,125));
		east.setPreferredSize(new Dimension(50,125));
		this.add(north,"North");
		this.add(west,"West");
		this.add(east,"East");
		south.add(check);
		main.setLayout(new GridLayout(3,1,0,20));
		main.add(loginL);
		main.add(pass);
		main.add(south);
		this.add(main,"Center");
		
		loginL.setHorizontalAlignment(JLabel.CENTER);
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		check.addActionListener(this);
		
		
			
		
	}

	public void changeFrame(BooksADM badm, Main_Frame main_Frame, Books_Gui bGui) {
		this.badm = badm;
		this.mf=main_Frame;
		this.bGui=bGui;
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		//pass.getSelectedText();
		if(obj.equals(check))	{
			System.out.println(pass.getText());
			if(admpass.equals(pass.getText())) {
				JOptionPane.showMessageDialog(this, "로그인 성공");
				bGui.override(badm, mf);
				this.setVisible(false);
				mf.closeFrame();
			}else	{
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}
		}
	}
}
