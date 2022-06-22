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
	// ��ܺ�
	private JPanel northP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton NoticeB = new JButton("Main");
	private JButton rentBookB = new JButton("�뿩 �ϱ�");
	private JButton mypageB = new JButton("My Page");
	// ���� ����
	private JPanel startP = new JPanel();
	private JPanel mainP = new JPanel();
	private JPanel mainP_north = new JPanel();
	private JPanel mainP_west = new JPanel();
	private JPanel mainP_east = new JPanel();
	private JLabel mainP_n_L = new JLabel("�Ű� ����Ʈ");
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

	// �ϴܺ�
	private JPanel southP = new JPanel(new BorderLayout());
	private JLabel titlelb = new JLabel("ȸ�� �޴�");
	private JButton backB = new JButton("�α׾ƿ�");

	// ���ͺ�(mypagemenu)
	private JButton rentListB = new JButton("�뿩���");
	private JButton retrunB = new JButton("�ݳ��ϱ�");
	private JPanel centerP_rlist = new JPanel();
	// ���ͺ�(rentlist)

	private JPanel centerP_rlist1 = new JPanel();
	private JPanel centerP_rlist2 = new JPanel();

	private String[] rcolName = new String[] { "å��ȣ", "å�̸�", "��ID", "�뿩����" };
	private DefaultTableModel rModel = new DefaultTableModel(rcolName, 0);
	private JTable rentList = new JTable(rModel);
	private JScrollPane rscroll = new JScrollPane(rentList);
	private Object[] rrecord = new Object[4];

	// ���ͺ� �ݳ��ϱ�
	String returnBookno;
	String rentBookno;
	// ���ͺ�(���̺�)

	private String[] colName = { "������ȣ", "������", "���ڸ�", "�뿩���ɿ���" };
	private DefaultTableModel model = new DefaultTableModel(colName, 0);
	private JTable rentT = new JTable(model);
	private JScrollPane scroll = new JScrollPane(rentT);
	private Object[] record = new Object[4];
	private JPanel centerP_list = new JPanel();
	private JPanel centerP_list1 = new JPanel();
	private JPanel centerP_list2 = new JPanel();
	private JButton listB = new JButton("�뿩");

	// override
	private Member nowUser = null;
	private Main_Frame mf;
	private BooksADM badm;

	public Member_Gui() {
		this.setBounds(100, 200, 500, 500);
		this.setTitle("�����뿩 �ý���");
		// ��ܺ�
		northP.setPreferredSize(new Dimension(500, 50));
		northP.add(NoticeB);
		northP.add(rentBookB);
		northP.add(mypageB);
		this.add(northP, "North");
		startP.setPreferredSize(new Dimension(500, 300));
		this.add(startP, "Center");

		// �ϴܺ�
		southP.setPreferredSize(new Dimension(500, 50));
		southP.add(titlelb, "Center");
		titlelb.setHorizontalAlignment(JLabel.CENTER);
		southP.add(backB, "West");
		this.add(southP, "South");

		// �׼ǹ�ư ����
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
			System.out.println("�߰����?");
			mainP_east.add(entrySet.getValue());
			System.out.println("�߰����");
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
				System.out.println("����");
				
				break;
			case 2:
				this.key2=k;
				this.bt2=a;
				bt2.addActionListener(this);
				System.out.println(key2);
				System.out.println("����");
				
				break;
			case 3:
				this.key3=k;
				this.bt3=a;
				bt3.addActionListener(this);
				System.out.println(key3);
				System.out.println("����");
				
				break;
			case 4:
				this.key4=k;
				this.bt4=a;
				bt4.addActionListener(this);
				System.out.println(key4);
				System.out.println("����");
				
				break;
			case 5:
				this.key5=k;
				this.bt5=a;
				bt5.addActionListener(this);
				System.out.println(key5);
				System.out.println("����");
				
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
		// TODO : �������ȭ��
//		int cnt = 0;
////		Map<String, JLabel> hashLabel = badm.getLabelMap();
//		for (Entry<String, JLabel> entrySet : badm.getLabelMap().entrySet()) {
//			// JButton dd= new JButton("�뿩"); //�ּҰ��� �������� ���𰡰� �ʿ��ϴ�
//			mainP_west.add(entrySet.getValue()); // <<<<<<<
//			System.out.println(cnt+"�ٶ�");
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
//				System.out.println(cnt+"�ٹ�ư1");
//				System.out.println("�ѹ����");
//			} else if (cnt == 2) {
//				this.key2 = entrySet.getKey();
//				this.bt2 = entrySet.getValue();
//				System.out.println(cnt+"�ٹ�ư2");
//				mainP_east.add(entrySet.getValue());
//				System.out.println("�ι����");
//
//			} else if (cnt == 3) {
//				System.out.println(cnt);
//				this.key3 = entrySet.getKey();
//				this.bt3 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
//				System.out.println("�������");
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
//			System.out.println("ù��°��ư ��ω��");
//		} else if (bt2 != null) {
//			bt2.addActionListener(this);
//			System.out.println("�ι�°��ư ��ω��");
//		} else if (bt3 != null) {
//			bt3.addActionListener(this);
//			System.out.println("����°��ư ��ω��");
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
////					System.out.println("1�����强��");
//				break;
//			case 2:
//				this.key2 = entrySet.getKey();
//				this.bt2 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println(key2);
////					System.out.println("2�����强��");
//				break;
//			case 3:
//				this.key3 = entrySet.getKey();
//				this.bt3 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println(key3);
////					System.out.println("3�����强��");
//				break;
//			case 4:
//				this.key4 = entrySet.getKey();
//				this.bt4 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println("4�����强��");
////					System.out.println(key4);
//				break;
//			case 5:
//				this.key5 = entrySet.getKey();
//				this.bt5 = entrySet.getValue();
//				mainP_east.add(entrySet.getValue());
////					System.out.println(key5);
////					System.out.println("5�����强��");
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
//			System.out.println("�ɷ�?");

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
				JOptionPane.showMessageDialog(this, "�̹� �뿩�� å�Դϴ�.");
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
				JOptionPane.showMessageDialog(this, "�뿩 ����");
			} else {
				JOptionPane.showMessageDialog(this, "�̹� �뿩�� å�Դϴ�.");
			}

		}
		if (obj.equals(bt2)) {
			int rbNo = Integer.valueOf(key2);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "�뿩 ����");
			} else {
				JOptionPane.showMessageDialog(this, "�̹� �뿩�� å�Դϴ�.");
			}

		}
		if (obj.equals(bt3)) {
			int rbNo = Integer.valueOf(key3);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "�뿩 ����");
			} else {
				JOptionPane.showMessageDialog(this, "�̹� �뿩�� å�Դϴ�.");
			}

		}
		if (obj.equals(bt4)) {
			int rbNo = Integer.valueOf(key4);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "�뿩 ����");
			} else {
				JOptionPane.showMessageDialog(this, "�̹� �뿩�� å�Դϴ�.");
			}

		}
		if (obj.equals(bt5)) {
			int rbNo = Integer.valueOf(key5);

			if (badm.rentcheck(rbNo) == -1) {
				nowUser.rent(nowUser, rbNo);
				badm.updaterentList(rbNo);
				JOptionPane.showMessageDialog(this, "�뿩 ����");
			} else {
				JOptionPane.showMessageDialog(this, "�̹� �뿩�� å�Դϴ�.");
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
