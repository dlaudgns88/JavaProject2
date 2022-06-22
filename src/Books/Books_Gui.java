package Books;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Main.Main_Frame;
import Signup.admlogin;

public class Books_Gui extends JFrame implements ActionListener, MouseListener {
	// 상단부
//	private JPanel northP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton addBookB = new JButton("도서 등록");
	private JButton delBookB = new JButton("도서 삭제");
	private JButton searchBookB = new JButton("도서 검색");
	private JButton listBookB = new JButton("전체 보기");

	// 인터페이스 업그레이드
//	private JPanel centerP = new JPanel();	
	private JPanel centerP = new JPanel(); //
	private JPanel centerP_west = new JPanel(); //
	private JPanel centerP_east = new JPanel(); //
	// 센터부(책 등록)
	private JPanel centerP_add = new JPanel();
	private JPanel centerP_add_west = new JPanel();
	private JPanel centerP_add_w_w = new JPanel();
	private JPanel centerP_add_w_e = new JPanel();
	private JPanel centerP_add_east = new JPanel();
	private JButton addB = new JButton("등록");
	private JLabel bookname = new JLabel("책이름 입력");
	private JLabel bookauthor = new JLabel("저자입력");
	private JTextField newBookNameT = new JTextField(8);
	private JTextField newBookAuthorT = new JTextField(8);
	// 센터부 (삭제)
	private JPanel centerP_del = new JPanel();
	private JPanel centerP_del_west = new JPanel();
	private JPanel centerP_del_east = new JPanel();
	private JPanel centerP_del_south = new JPanel();
	private JButton delB = new JButton("삭제");
	private JPanel del_blank = new JPanel();
	private JPanel del_blank1 = new JPanel();
	private String click;
	//센터부 (검색)
	private JPanel centerP_search = new JPanel();
	private JPanel centerP_search_west = new JPanel();
	private JPanel centerP_search_west2 = new JPanel();
	private JPanel centerP_search_east = new JPanel();
	private JLabel searchBookL = new JLabel("검색할 책 제목 입력하세요");
	private JTextField searchBookT = new JTextField(10);
	private JButton searchBookB1 = new JButton("검색");
	
	// 센터부(책 리스트)
	private String[] colName = new String[] { "No", "책이름", "저자", "등록일자" };
	private DefaultTableModel Model = new DefaultTableModel(colName, 0);
	private JTable bookList = new JTable(Model);
	private JScrollPane scroll = new JScrollPane(bookList);
	private Object[] record = new Object[4];

	// 하단부
	private JPanel southP = new JPanel(new BorderLayout());
	private JLabel titlelb = new JLabel("도서 관리");
	private JButton backB = new JButton("로그아웃");

	private BooksADM badm = null;
	private Main_Frame mf = null;

	public Books_Gui() {
		this.setBounds(100, 200, 500, 500);
		this.setTitle("도서대여 시스템");
		mainpanel();
		// 상단부

		// 하단부
		southP.add(titlelb, "Center");
		titlelb.setHorizontalAlignment(JLabel.CENTER);
		southP.add(backB, "West");
		this.add(southP, "South");

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// 액션버튼 구성
		backB.addActionListener(this);
		addBookB.addActionListener(this);
		listBookB.addActionListener(this);
		addB.addActionListener(this);
		delBookB.addActionListener(this);
		delB.addActionListener(this);
		searchBookB.addActionListener(this);
		searchBookB1.addActionListener(this);
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookList.addMouseListener(this);
	}

	private void searchList(String inputName) {
		DefaultTableModel tableModel = (DefaultTableModel) bookList.getModel();
		tableModel.setRowCount(0);
		System.out.println(inputName);
		ArrayList<Books> searchList = badm.searchList(inputName);
		Books tempG = null;
		for (int i = 0; i < searchList.size(); i++) {
			tempG = searchList.get(i);
			record[0] = tempG.getNo();
			record[1] = tempG.getName();
			record[2] = tempG.getAuthor();
			record[3] = tempG.getDate();
			Model.addRow(record);
		}
		scroll.setPreferredSize(new Dimension(300, 350));
		centerP_search_west.add(scroll, "Center");
	}
	public void searchMenuPanel_2(String inputName) {
		centerP_search.setLayout(new BorderLayout());
		centerP_search.setPreferredSize(new Dimension(500, 350));
		centerP_search_west2.setPreferredSize(new Dimension(350, 350));
		centerP_search_east.setPreferredSize(new Dimension(150, 350));
		centerP_search_east.add(listBookB);
		centerP_search_east.add(addBookB);
		centerP_search_east.add(delBookB);
		centerP_search_east.add(searchBookB);
		centerP_search.add(centerP_search_west, "Center");
		centerP_search.add(centerP_search_east, "East");
		System.out.println(inputName);
		searchList(inputName);
	}

	public void searchmenuPanel()	{
		centerP_search.setLayout(new BorderLayout());
		centerP_search.setPreferredSize(new Dimension(500, 350));
		centerP_search_west.setPreferredSize(new Dimension(350, 350));
		centerP_search_east.setPreferredSize(new Dimension(150, 350));
		centerP_search_east.add(listBookB);
		centerP_search_east.add(addBookB);
		centerP_search_east.add(delBookB);
		centerP_search_east.add(searchBookB);
		centerP_search_west.add(searchBookL);
		centerP_search_west.add(searchBookT);
		centerP_search_west.add(searchBookB1);
		
		centerP_search.add(centerP_search_west, "Center");
		centerP_search.add(centerP_search_east, "East");
	}
	public void dellist() {
		// TODO 책삭제시 리스트 출력
		DefaultTableModel tableModel = (DefaultTableModel) bookList.getModel();
		tableModel.setRowCount(0);

		ArrayList<Books> delList = badm.list();
		Books tempG = null;

		for (int i = 0; i < delList.size(); i++) {
			tempG = delList.get(i);
			record[0] = tempG.getNo();
			record[1] = tempG.getName();
			record[2] = tempG.getAuthor();
			record[3] = tempG.getDate();
			Model.addRow(record);

		}
		scroll.setPreferredSize(new Dimension(300, 350));
		centerP_del_west.add(scroll);
	}

	public void delPanel() {
		// TODO 책삭제 패널구성
		centerP_del.setLayout(new BorderLayout());
		centerP_del.setPreferredSize(new Dimension(500, 350));
		centerP_del_west.setPreferredSize(new Dimension(350, 300));
		centerP_del_east.setPreferredSize(new Dimension(150, 300));
		centerP_del_south.setPreferredSize(new Dimension(350, 50));
		del_blank.setPreferredSize(new Dimension(120, 50));
		del_blank1.setPreferredSize(new Dimension(280, 50));
		centerP_del_south.setLayout(new BorderLayout());
		centerP_del_east.add(listBookB);
		centerP_del_east.add(addBookB);
		centerP_del_east.add(delBookB);
		centerP_del_east.add(searchBookB);
		centerP_del_south.add(del_blank,"West");
		centerP_del_south.add(del_blank1,"East");
//		delB.setPreferredSize(new Dimension(100, 50));
		centerP_del_south.add(delB,"Center");
		centerP_del.add(centerP_del_west, "Center");
		centerP_del.add(centerP_del_east, "East");
		centerP_del.add(centerP_del_south,"South");
		dellist();
	}

	public void add() {
		// TODO 책추가
		centerP_add_w_w.setPreferredSize(new Dimension(150, 300));
		centerP_add_w_e.setPreferredSize(new Dimension(200, 300));
		centerP_add_w_w.setLayout(new GridLayout(7, 1, 50, 30));
		centerP_add_w_e.setLayout(new GridLayout(7, 1, 50, 30));
		centerP_add_w_w.add(bookname);
		bookname.setHorizontalAlignment(JLabel.CENTER);
		centerP_add_w_w.add(bookauthor);
		bookauthor.setHorizontalAlignment(JLabel.CENTER);
		centerP_add_west.add(centerP_add_w_w, "Center");

		centerP_add_w_e.add(newBookNameT);
		centerP_add_w_e.add(newBookAuthorT);
		centerP_add_w_e.add(addB);
		centerP_add_west.add(centerP_add_w_e, "East");
	}

	public void addPanel() { //
		// TODO 책추가 패널
		centerP_add.setLayout(new BorderLayout());
		centerP_add.setPreferredSize(new Dimension(500, 350));
		centerP_add_west.setPreferredSize(new Dimension(350, 350));
		centerP_add_west.setLayout(new BorderLayout());
		centerP_add_east.setPreferredSize(new Dimension(150, 350));
		centerP_add_east.add(listBookB);
		centerP_add_east.add(addBookB);
		centerP_add_east.add(delBookB);
		centerP_add_east.add(searchBookB);
		centerP_add.add(centerP_add_east, "East");
		centerP_add.add(centerP_add_west, "Center");
		add();
	}

	public void showlistallPanel() { ///
		// TODO 책리스트 패널
		mainpanel();
		showlist();
	}

	public void showlist() { ///
		// TODO 책리스트
		DefaultTableModel tableModel = (DefaultTableModel) bookList.getModel();
		tableModel.setRowCount(0);

		ArrayList<Books> bList = badm.list();
		Books tempG = null;

		for (int i = 0; i < bList.size(); i++) {
			tempG = bList.get(i);
			record[0] = tempG.getNo();
			record[1] = tempG.getName();
			record[2] = tempG.getAuthor();
			record[3] = tempG.getDate();
			Model.addRow(record);

		}
		scroll.setPreferredSize(new Dimension(300, 350));
		centerP_west.add(scroll);
	}

	public void mainpanel() { ///
		// TODO 메인페널
		centerP.setLayout(new BorderLayout());
		centerP.setPreferredSize(new Dimension(500, 350));
		centerP_west.setPreferredSize(new Dimension(350, 350));
		centerP_east.setPreferredSize(new Dimension(150, 350));

		centerP_east.add(listBookB);
		centerP_east.add(addBookB);
		centerP_east.add(delBookB);
		centerP_east.add(searchBookB);
		centerP.add(centerP_west, "Center");
		centerP.add(centerP_east, "East");
		this.add(centerP, "Center");
		// changeFrame();
	}

	public void changeFrame() {
		this.setVisible(true);
	}

	public void reloadFrame() {

		this.revalidate();
		this.repaint();
	}

	public void override(BooksADM badm, Main_Frame mf) {
		// TODO Auto-generated method stub
		this.badm = badm;
		System.out.println(badm);
		this.mf = mf;
		changeFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj.equals(backB)) {

			this.setVisible(false);
			mf.changeFrame();
		} else if (obj.equals(listBookB)) {
			this.remove(centerP_del);
			this.remove(centerP_add);
			this.remove(centerP_search);
			this.add(centerP);
			showlistallPanel();
			reloadFrame();

		} else if (obj.equals(addBookB)) {
			this.remove(centerP);
			this.remove(centerP_del);
			this.remove(centerP_search);
			this.add(centerP_add);
			addPanel();
			reloadFrame();

		} else if (obj.equals(delBookB)) {
			this.remove(centerP);
			this.remove(centerP_add);
			this.remove(centerP_search);
			this.add(centerP_del);
			delPanel();
			reloadFrame();
		} else if (obj.equals(searchBookB)) {
			this.remove(centerP);
			this.remove(centerP_add);
			this.remove(centerP_del);
			this.add(centerP_search);
			searchmenuPanel();
			reloadFrame();
		}

		if (obj.equals(addB)) {
			String inputName = newBookNameT.getText();
			String inputAuthor = newBookAuthorT.getText();
			badm.add(inputName, inputAuthor);
		}
		if (obj.equals(delB)) {
			int clickNo =  Integer.valueOf(click);
			badm.del(clickNo);
			this.remove(centerP_del);
			this.add(centerP_del);
			delPanel();
			reloadFrame();
			
		}
		if (obj.equals(searchBookB1)) {
			String inputName = searchBookT.getText();
			System.out.println(inputName);
			if (badm.search(inputName) != null) {
				this.remove(centerP_add);
				
				this.remove(centerP_del);
				this.remove(centerP_search);
				this.add(centerP_search, "Center");
				searchMenuPanel_2(inputName);
				System.out.println(inputName);
				reloadFrame();
			} else {
				JOptionPane.showMessageDialog(this, "검색결과가 없어 ㅠㅠ");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int row = bookList.getSelectedRow();
		int column = bookList.getSelectedColumn();
		 System.out.println(bookList.getValueAt(row, column));
		 this.click = String.valueOf(bookList.getValueAt(row, column));
		 System.out.println(click);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
