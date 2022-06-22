package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Books.BooksADM;
import Books.Books_Gui;
import Member.MemberADM;
import Member.Member_Gui;
import Signup.Signup;
import Signup.admlogin;

public class Main_Frame extends JFrame implements ActionListener {
	// 상단부
	private JPanel northP = new JPanel(new BorderLayout());
	private JPanel northP2 = new JPanel();
	private JLabel titlelb = new JLabel("Book Rental System");
	private JButton admLogin = new JButton("관리자 로그인");
	// 센터부(로그인시스템)
	private JPanel centerP = new JPanel(new BorderLayout());
	private JPanel loginSystem = new JPanel(new GridLayout(5, 1, 5, 0));
	private JLabel idlb = new JLabel("ID입력");
	private JTextField idT = new JTextField(8);
	private JLabel pwlb = new JLabel("PW입력");
	private JPasswordField pwT = new JPasswordField(10);
	private JPanel blank = new JPanel();
	private JPanel blank2 = new JPanel();
	private JPanel blank3 = new JPanel();
	// 측면부(우측부1)
	private JPanel eastP = new JPanel(new BorderLayout());
	private JPanel eastP1 = new JPanel(new GridLayout(2, 1));
	private JButton loginB = new JButton("로그인");
	private JButton signupB = new JButton("회원가입");
	private JPanel blank4 = new JPanel();
	private JPanel blank5 = new JPanel();
	private JPanel blank6 = new JPanel();
	// 측면부(우측하단부)
	private JPanel esP = new JPanel(new BorderLayout());
	private JButton searchIDPW = new JButton("ID/PW 찾기");

	// 측면부(좌측부)
	private JPanel westP = new JPanel();

	// 하단부
	private JPanel southP = new JPanel(new BorderLayout());
	private JPanel southP2 = new JPanel();
	private JLabel teamName = new JLabel("팀명 : LH");
	private JLabel name = new JLabel("개발자 : 이명훈");
	// 각 Gui 객체를 생성
	private Books_Gui bGui = new Books_Gui();
	private Member_Gui mGui = new Member_Gui();
	private Signup newmember = new Signup();
	private BooksADM badm = new BooksADM();
	private MemberADM madm = new MemberADM();
	private admlogin adm = new admlogin();
	// 관리자로그인
	// private String admID = "adm";
	// private String admPW = "1234";b
	public Main_Frame() {

		this.setBounds(100, 200, 500, 300);

		// 메인타이틀
		this.setTitle("도서대여 시스템"); // 좌 여백
		// 상단부 구성
		northP.add(titlelb, "West");
//		northP2.add(admLogin);
		northP.add(northP2, "East");
		// setPreferredSize()
		this.add(northP, "North");
		// 센터부 구성

		loginSystem.add(idlb);
		loginSystem.add(idT);
		loginSystem.add(pwlb);
		loginSystem.add(pwT);

		centerP.add(loginSystem, "Center");
		centerP.add(blank, "West");
		blank.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		centerP.add(blank2, "East");
		blank.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		this.add(centerP, "Center"); // 하단부 여백

		// 우측부 구성 1
		eastP1.add(blank6);
		blank6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		eastP1.add(loginB);
		// eastP1.add(admLogin);
		eastP.add(eastP1, "Center");
		eastP.add(blank4, "West");
		blank.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		eastP.add(blank5, "East");
		blank.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
		this.add(eastP, "East");
		eastP.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));
		// 우측 하단부 구성

		eastP.add(esP, "South");
		// 좌측부 구성
		westP.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));
		this.add(westP, "West");

		// 하단부구성
		southP.add(teamName, "West"); // 좌우 여백주자
		southP.add(name, "East");
		this.add(southP, "South");
		southP2.add(signupB);
		southP2.add(admLogin);

		southP.add(southP2, "North");
		// outhP2.setBorder(BorderFactory.createEmptyBorder(0 , 50 , 0 , 0));

		// 로그인 버튼 액션 구성
		loginB.addActionListener(this);
		admLogin.addActionListener(this);
		signupB.addActionListener(this);
		searchIDPW.addActionListener(this);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		System.out.println("생성자 호출");

	}
	public void closeFrame()	{
		this.setVisible(false);
	}

	public void changeFrame() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(admLogin)) {
			System.out.println("관리자 로그인 중..");
			adm.changeFrame(badm, this,bGui);
			
			
//			this.setVisible(false);
		} else if (obj.equals(loginB)) {
			System.out.println("로그인 중...");

			String inputID = idT.getText();
			String inputPW = pwT.getText();
			// System.out.println(inputID);

			if (madm.loginProcess(inputID, inputPW) != null) {
				JOptionPane.showMessageDialog(this, "환영합니다.");
				mGui.override(madm.getLoginUser(), badm, this);
				// System.out.println(madm.getLoginUser().getID());
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}

		} else if (obj.equals(signupB)) {
			System.out.println("회원가입 중...");
			newmember.changeFrame(this, madm);
			this.setVisible(false);
		}
		// System.out.println();

	}

}
