package Member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Books.Books;
import Books.BooksADM;
import Main.Main_Frame;

public class Member_Gui extends JFrame implements ActionListener, MouseListener {
	// 상단부
	private JPanel northP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton NoticeB = new JButton("Main");
	private JButton rentBookB = new JButton("대여 하기");
	private JButton mypageB = new JButton("My Page");
	// 센터 메인
	private JPanel startP = new JPanel();
	private JPanel mainP = new JPanel();
	private JPanel mainP_north = new JPanel();
	private JPanel mainP_west = new JPanel();
	private JPanel mainP_east = new JPanel();
	private JLabel mainP_n_L = new JLabel("신간 리스트");
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String key5;
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	private JButton bt5;
	int cnt = 1;

	// 하단부
	private JPanel southP = new JPanel(new BorderLayout());
	private JLabel titlelb = new JLabel("회원 메뉴");
	private JButton backB = new JButton("로그아웃");

	// 센터부(mypagemenu)
	private JButton rentListB = new JButton("대여목록");
	private JButton retrunB = new JButton("반납하기");
	private JPanel centerP_rlist = new JPanel();
	// 센터부(rentlist)

	private JPanel centerP_rlist1 = new JPanel();
	private JPanel centerP_rlist2 = new JPanel();

	private String[] rcolName = new String[] { "책번호", "책이름", "고객ID", "대여일자" };
	private DefaultTableModel rModel = new DefaultTableModel(rcolName, 0);
	private JTable rentList = new JTable(rModel);
	private JScrollPane rscroll = new JScrollPane(rentList);
	private Object[] rrecord = new Object[4];

	// 센터부 반납하기
	String returnBookno;
	String rentBookno;
	// 센터부(테이블)

	private String[] colName = { "도서번호", "도서명", "저자명", "대여가능여부" };
	private DefaultTableModel model = new DefaultTableModel(colName, 0);
	private JTable rentT = new JTable(model);
	private JScrollPane scroll = new JScrollPane(rentT);
	private Object[] record = new Object[4];
	private JPanel centerP_list = new JPanel();
	private JPanel centerP_list1 = new JPanel();
	private JPanel centerP_list2 = new JPanel();
	private JButton listB = new JButton("대여");

	// override
	private Member nowUser = null;
	private Main_Frame mf;
	private BooksADM badm;

	public Member_Gui() {
		this.setBounds(100, 200, 500, 500);
		this.setTitle("도서대여 시스템");
		// 상단부
		northP.setPreferredSize(new Dimension(500, 50));
		northP.add(NoticeB);
		northP.add(rentBookB);
		northP.add(mypageB);
		this.add(northP, "North");
		startP.setPreferredSize(new Dimension(500, 300));
		this.add(startP, "Center");

		// 하단부
		southP.setPreferredSize(new Dimension(500, 50));
		southP.add(titlelb, "Center");
		titlelb.setHorizontalAlignment(JLabel.CENTER);
		southP.add(backB, "West");
		this.add(southP, "South");

		// 액션버튼 구성
		mypageB.addActionListener(this);
		backB.addActionListener(this);
		rentBookB.addActionListener(this);
		listB.addActionListener(this);
		rentListB.addActionListener(this);
		retrunB.addActionListener(this);
		NoticeB.addActionListener(this);

		rentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rentList.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = rentList.getSelectedRow();
				int column = rentList.getSelectedColumn();
				returnBookno = String.valueOf(rentList.getValueAt(row, column));
			}
		});
		rentT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		rentT.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = rentT.getSelectedRow();
				int column = rentT.getSelectedColumn();
				rentBookno = String.valueOf(rentT.getValueAt(row, column));
				System.out.println(rentBookno);
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public void addLabel() {

		for (Entry<String, JLabel> entrySet : badm.getLabelMap().entrySet()) {
			mainP_west.add(entrySet.getValue());
		}

	}

	public void addButton() {

		for (Entry<String, JButton> entrySet : badm.getButtonMap().entrySet()) {
			System.out.println(entrySet.getKey());
			addButtonKey(entrySet.getKey(), cnt, entrySet.getValue()); //
			System.out.println("추가됬어?");
			mainP_east.add(entrySet.getValue());
			System.out.println("추가됬어");
			cnt =cnt+1;
		}
		cnt=0;
	}

	public void addButtonKey(String k, int btcnt, JButton a)	{
		try {
			switch (btcnt) {
			case 1:
				this.key1=k;
				this.bt1=a;
				bt1.addActionListener(this);
				System.out.println(key1);
				System.out.println("성공");
				
				break;
			case 2:
				this.key2=k;
				this.bt2=a;
				bt2.addActionListener(this);
				System.out.println(key2);
				System.out.println("성공");
				
				break;
			case 3:
				this.key3=k;
				this.bt3=a;
				bt3.addActionListener(this);
				System.out.println(key3);
				System.out.println("성공");
				
				break;
			case 4:
				this.key4=k;
				this.bt4=a;
				bt4.addActionListener(this);
				System.out.println(key4);
				System.out.println("성공");
				
				break;
			case 5:
				this.key5=k;
				this.bt5=a;
				bt5.addActionListener(this);
				System.out.println(key5);
				System.out.println("성공");
				
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		
		}
		
	}

	public void mainlist() {
		addLabel();
		addButton();
	}

	public void deadcode() {
		// TODO : 메인출력화면
//		int cnt = 0;
////		Map<String, JLabel> hashLabel = badm.getLabelMap();
//		for (Entry<String, JLabel> entrySet : badm.getLabelMap().entrySet()) {
//			// JButton dd= new JButton("대여"); //주소값을 관리해줄 무언가가 필요하다
//			mainP_west.add(entrySet.getValue()); // <<<<<<<
//			System.out.println(cnt+"겟라벨");
//			cnt++;
//
//		}
//		mainP.add(mainP_west, "Center");
////		Map<String, JButton> hashButton = badm.getButtonMap();
//		for (Entry<String, JButton> entrySet : badm.getButtonMap().entrySet()) {
//			System.out.println(cnt);
//			
//			if (cnt == 1) {
//				this.key1 = entrySet.getKey();
//				this.bt1 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
//				System.out.println(cnt+"겟버튼1");
//				System.out.println("한번등록");
//			} else if (cnt == 2) {
//				this.key2 = entrySet.getKey();
//				this.bt2 = entrySet.getValue();
//				System.out.println(cnt+"겟버튼2");
//				mainP_east.add(entrySet.getValue());
//				System.out.println("두번등록");
//
//			} else if (cnt == 3) {
//				System.out.println(cnt);
//				this.key3 = entrySet.getKey();
//				this.bt3 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
//				System.out.println("세번등록");
//			} else if (cnt == 4) {
//				System.out.println(cnt);
//				this.key4 = entrySet.getKey();
//				this.bt4 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
//			} else if (cnt == 5) {
//				this.key5 = entrySet.getKey();
//				this.bt5 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
//
//			}else	{
//				cnt = 0 ; 
//				break;
////			}
//		}
//		if (bt1 != null) {
//			bt1.addActionListener(this);
//			System.out.println("첫번째버튼 등록됬어");
//		} else if (bt2 != null) {
//			bt2.addActionListener(this);
//			System.out.println("두번째버튼 등록됬어");
//		} else if (bt3 != null) {
//			bt3.addActionListener(this);
//			System.out.println("세번째버튼 등록됬어");
//		} else if (bt4 != null) {
//			bt4.addActionListener(this);
//		} else if (bt5 != null) {
//			bt5.addActionListener(this);
//
//		}
//		mainP.add(mainP_east, "East");
//	}

//			switch (cnt) {
//			case 1:
//				this.key1 = entrySet.getKey();
//				this.bt1 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
//				
////					System.out.println(key1);
////					System.out.println("1번저장성공");
//				break;
//			case 2:
//				this.key2 = entrySet.getKey();
//				this.bt2 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println(key2);
////					System.out.println("2번저장성공");
//				break;
//			case 3:
//				this.key3 = entrySet.getKey();
//				this.bt3 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println(key3);
////					System.out.println("3번저장성공");
//				break;
//			case 4:
//				this.key4 = entrySet.getKey();
//				this.bt4 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println("4번저장성공");
////					System.out.println(key4);
//				break;
//			case 5:
//				this.key5 = entrySet.getKey();
//				this.bt5 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println(key5);
////					System.out.println("5번저장성공");
//				break;
//			default:
//				break;
//			}
//		}
//		if (bt1 != null) {
//			bt1.addActionListener(this);
//		} else if (bt2 != null) {
//			bt2.addActionListener(this);
//
//		} else if (bt3 != null) {
//			bt3.addActionListener(this);
//		} else if (bt4 != null) {
//			bt4.addActionListener(this);
//		} else if (bt5 != null) {
//			bt5.addActionListener(this);
//
//		}
//		mainP.add(mainP_east, "East");
//	}
	}

	public void mainPanel() {
		mainP.setLayout(new BorderLayout());
		mainP.setPreferredSize(new Dimension(500, 300));
		mainP_north.setPreferredSize(new Dimension(500, 50));
		mainP_west.setPreferredSize(new Dimension(350, 250));
		mainP_west.setLayout(new GridLayout(5, 1, 0, 20));
		mainP_east.setPreferredSize(new Dimension(150, 250));
		mainP_east.setLayout(new GridLayout(5, 1, 0, 20));
		mainP_north.add(mainP_n_L);
		mainP.add(mainP_east, "East");
		mainP.add(mainP_north, "North");
		mainP.add(mainP_west, "Center");
		mainlist();

	}

	public void returnBookPanel() {
		custom_rentlistPanel();
//		System.out.println(returnBookno);
//		int returnBNo = Integer.valueOf(returnBookno);
//		nowUser.retunbook(returnBNo, nowUser);

		custom_rentlist();

	}

	public void custom_rentlistPanel() {
		centerP_rlist.setLayout(new BorderLayout());
		centerP_rlist.setPreferredSize(new Dimension(500, 350));
		centerP_rlist1.setPreferredSize(new Dimension(350, 350));
		centerP_rlist2.setPreferredSize(new Dimension(150, 350));

		centerP_rlist2.add(rentListB);
		centerP_rlist2.add(retrunB);
//		centerP_rlist2.add(delmemberB);

		centerP_rlist.add(centerP_rlist1, "Center");
		centerP_rlist.add(centerP_rlist2, "East");

		custom_rentlist();

	}

	public void custom_rentlist() {
		DefaultTableModel tableModel = (DefaultTableModel) rentList.getModel();
		tableModel.setRowCount(0);

		rscroll.setPreferredSize(new Dimension(300, 350));
		ArrayList<Rent> rList = nowUser.rentlist();

		Rent tempG = null;

		for (int i = 0; i < rList.size(); i++) {
			tempG = rList.get(i);
			if (rList.get(i).getmID().equals(nowUser.getID())) {
				rrecord[0] = tempG.getbookNo();
				rrecord[1] = tempG.getbName();
				rrecord[2] = tempG.getmID();
				rrecord[3] = tempG.getDate();
				rModel.addRow(rrecord);
			}
		}
		centerP_rlist1.add(rscroll);
	}

	public void mymenuPanel() {
		centerP_rlist.setLayout(new BorderLayout());
		centerP_rlist.setPreferredSize(new Dimension(500, 350));
		centerP_rlist1.setPreferredSize(new Dimension(350, 350));
		centerP_rlist2.setPreferredSize(new Dimension(150, 350));

		centerP_rlist2.add(rentListB);
		centerP_rlist2.add(retrunB);
		centerP_rlist.add(centerP_rlist1, "Center");
		centerP_rlist.add(centerP_rlist2, "East");
	}

	public void rentMenuPanel() {
		centerP_list.setLayout(new BorderLayout());
		centerP_list.setPreferredSize(new Dimension(500, 350));
		centerP_list1.setPreferredSize(new Dimension(350, 350));
		centerP_list2.setPreferredSize(new Dimension(150, 350));
		centerP_list2.add(listB);
		centerP_list.add(centerP_list1, "Center");
		centerP_list.add(centerP_list2, "East");

		rentList();

	}

	private void rentList() {
		DefaultTableModel tableModel = (DefaultTableModel) rentT.getModel();
		tableModel.setRowCount(0);

		scroll.setPreferredSize(new Dimension(300, 350));

		ArrayList<Books> rentList = badm.rentList();
		Books tempG = null;
		for (int i = 0; i < rentList.size(); i++) {
			tempG = rentList.get(i);
			record[0] = tempG.getNo();
			record[1] = tempG.getName();
			record[2] = tempG.getAuthor();
			record[3] = tempG.getRent();
			model.addRow(record);

		}

		centerP_list1.add(scroll);
	}

	public void override(Member m, BooksADM badm, Main_Frame mf) {
		this.nowUser = m;
		this.badm = badm;
		this.mf = mf;

		changeFrame();
	}

	public void changeFrame() {

		this.setVisible(true);
	}

	public void reloadFrame() {
		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(backB)) {
			this.setVisible(false);
			mf.changeFrame();
//			System.out.println("걸려?");

		} else if (obj.equals(mypageB)) {
			this.remove(startP);
			this.remove(mainP);
			this.remove(centerP_list);
			this.add(centerP_rlist, "Center");
			custom_rentlistPanel();
			reloadFrame();
		} else if (obj.equals(rentBookB)) {
			this.remove(startP);
			this.remove(mainP);
			this.remove(centerP_rlist);

			this.add(centerP_list, "Center");
			rentMenuPanel();
			reloadFrame();

		} else if (obj.equals(NoticeB)) {
			this.remove(startP);
			this.remove(mainP);
			this.remove(centerP_list);
			this.remove(centerP_rlist);
			this.add(mainP, "Center");
			mainPanel();
			reloadFrame();
		}
		if (obj.equals(retrunB)) {
			System.out.println(returnBookno);
			int returnBNo = Integer.valueOf(returnBookno);
			nowUser.retunbook(returnBNo, nowUser);
			badm.updaterentList2(returnBNo);
			this.remove(startP);
			this.remove(mainP);
			this.remove(centerP_list);
			this.add(centerP_rlist, "Center");
			returnBookPanel();
			reloadFrame();
		}
		if (obj.equals(listB)) {
			System.out.println(rentBookno);
			int rbNo = Integer.valueOf(rentBookno);
			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				this.remove(mainP);
				this.remove(centerP_rlist);
				this.add(centerP_list, "Center");
				rentMenuPanel();
				reloadFrame();
			} else {
				JOptionPane.showMessageDialog(this, "이미 대여된 책입니다.");
			}

		}
		if (obj.equals(rentListB)) {
			System.out.println(nowUser.getID());
			System.out.println(nowUser.getNo());
			nowUser.rentlist();
		}
		if (obj.equals(bt1)) {
			int rbNo = Integer.valueOf(key1);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "대여 성공");
			} else {
				JOptionPane.showMessageDialog(this, "이미 대여된 책입니다.");
			}

		}
		if (obj.equals(bt2)) {
			int rbNo = Integer.valueOf(key2);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "대여 성공");
			} else {
				JOptionPane.showMessageDialog(this, "이미 대여된 책입니다.");
			}

		}
		if (obj.equals(bt3)) {
			int rbNo = Integer.valueOf(key3);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "대여 성공");
			} else {
				JOptionPane.showMessageDialog(this, "이미 대여된 책입니다.");
			}

		}
		if (obj.equals(bt4)) {
			int rbNo = Integer.valueOf(key4);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "대여 성공");
			} else {
				JOptionPane.showMessageDialog(this, "이미 대여된 책입니다.");
			}

		}
		if (obj.equals(bt5)) {
			int rbNo = Integer.valueOf(key5);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "대여 성공");
			} else {
				JOptionPane.showMessageDialog(this, "이미 대여된 책입니다.");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
