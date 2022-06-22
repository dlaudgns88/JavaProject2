package Signup;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;

import Main.Main_Frame;
import Member.MemberADM;

public class Signup extends JFrame implements ActionListener {
	// ����
	private JPanel northP = new JPanel();
	private JPanel centerP = new JPanel(new BorderLayout());
	private JPanel centerP_w = new JPanel();
	private JPanel centerP_e = new JPanel();
	private JPanel signupP = new JPanel(new GridLayout(8, 1,0,20));
	private JPanel signupP1 = new JPanel(new GridLayout(8, 1,0,20));
	private JPanel eastP = new JPanel(new BorderLayout());
	private JPanel eastP_w = new JPanel();
	private JPanel eastP_e = new JPanel();
	//private JLabel newID = new JLabel();
	private JTextField newIDT = new JTextField(8);
	//private JLabel newPW = new JLabel("��й�ȣ�� �Է��ϼ���");
	private JPasswordField newPWT = new JPasswordField(10);
	//private JLabel newTel = new JLabel("��ȭ��ȣ�� �Է��ϼ���");
	private JTextField newNameT = new JTextField(8);
	private JTextField newTelT = new JTextField(13);
	private JButton checkID = new JButton("check");
	private JButton add = new JButton("���");
	//������
	private JPanel westP = new JPanel(new GridLayout(8, 1,20,0));
	private JLabel newIDL = new JLabel("ID");
	private JLabel newPWL = new JLabel("PW");
	private JLabel newNameL = new JLabel("Name");
	private JLabel newTelL = new JLabel("TEL");

	// �ϴܺ�
	private JPanel southP = new JPanel(new BorderLayout());
	private JLabel titlelb = new JLabel("ȸ�� ����");
	private JButton backB = new JButton("�����ܰ�");
	
	Main_Frame mf ;
	MemberADM madm;

	public Signup() {
		this.setBounds(100, 200, 500, 500);
		this.setTitle("�����뿩 �ý���");
		northP.setPreferredSize(new Dimension(500,100));
		this.add(northP,"North");
		
		//������
		westP.add(newIDL);
		westP.add(newPWL);
		westP.add(newNameL);
		westP.add(newTelL);
		this.add(westP,"West");
		westP.setPreferredSize(new Dimension(100,450));
		newIDL.setHorizontalAlignment(JLabel.CENTER);
		newPWL.setHorizontalAlignment(JLabel.CENTER);
		newTelL.setHorizontalAlignment(JLabel.CENTER);
		newNameL.setHorizontalAlignment(JLabel.CENTER);
		
		// ���ͺ�
		signupP.add(newIDT);
		signupP.add(newPWT);
		signupP.add(newNameT);
		signupP.add(newTelT);
		signupP.add(add);
		
		// TODO : üũ���̵�
		signupP1.add(checkID);
		centerP.add(signupP,"Center");
		centerP.add(centerP_w,"West");
		centerP.add(centerP_e,"East");
		signupP.setPreferredSize(new Dimension(250,450));
//		centerP_w.setPreferredSize(new Dimension(50,450));
		centerP_e.setPreferredSize(new Dimension(50,450));
//		centerP.add(signupP1,"East");
		centerP.add(eastP,"East");
		eastP.add(eastP_e,"East");
		eastP.add(eastP_w,"West");
		eastP.add(signupP1,"Center");
		signupP1.setPreferredSize(new Dimension(100,450));
		eastP.setPreferredSize(new Dimension(150,450));
		eastP_w.setPreferredSize(new Dimension(25,450));
		eastP_e.setPreferredSize(new Dimension(25,450));
		this.add(centerP, "Center");

		// �ϴܺ�
		southP.add(titlelb, "Center");
		titlelb.setHorizontalAlignment(JLabel.CENTER);
		southP.add(backB, "West");
		this.add(southP, "South");

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//�׼ǹ�ư ����
		backB.addActionListener(this);
		add.addActionListener(this);
		checkID.addActionListener(this);
	}

	public void changeFrame(Main_Frame main_Frame, MemberADM madm) {
		this.mf=main_Frame;
		this.madm=madm;
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj.equals(backB))	{
			this.setVisible(false);
			mf.changeFrame();
		}
		if(obj.equals(add))	{
			String inputID = newIDT.getText();
			String inputPW = newPWT.getText();
			String inputTel	= newTelT.getText();
			String inputName = newNameT.getText();
			madm.add(inputID,inputPW,inputTel,inputName);
			this.setVisible(false);
			mf.changeFrame();
		}
		if(obj.equals(checkID))	{
			String inputID = newIDT.getText();
			if(madm.check(inputID)==-1)	{
				JOptionPane.showMessageDialog(this, "���԰�����");
			}else	{
				JOptionPane.showMessageDialog(this, "�̹� ��ϵ� ���̵��");
			}
		}
	}

}
