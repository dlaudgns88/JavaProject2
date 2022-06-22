package Books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BooksADM {
	private Books_DAO bdao = new Books_DAO(); //<<<<< bado�� �ּҰ��� �޶����°� ����  x
	Books checkB;
	Books newB;
	String newB_no;
	Map<String, JLabel> labelMap; // <<<<<<<<<
	Map<String, JButton> buttonMap; // <<<<<<<<<<

	public Map<String, JButton> getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(Map<String, JButton> buttonMap) {
		this.buttonMap = buttonMap;
	}

	public Map<String, JLabel> getLabelMap() {
		return labelMap;
	}

	public void setLabelMap(Map<String, JLabel> labelMap) {
		this.labelMap = labelMap;
	}

	public void add(String inputName, String inputAuthor) {
		newB = new Books();
		newB.setName(inputName);
		newB.setAuthor(inputAuthor);
		bdao.add(newB);
		addLabel();
		addButton();
	}

	public ArrayList<Books> list() {
		ArrayList<Books> bList = bdao.list();

		return bList;

	}

	public void del(int inputNo) {
		bdao.del(inputNo);
	}

	public Books search(String inputName) {
		Books searchBook = new Books();
		searchBook.setName(inputName);
		if (bdao.search(searchBook) != null) {
			return searchBook;

		}
		return null;
	}

	public ArrayList<Books> searchList(String inputName) {
		System.out.println(inputName);
		ArrayList<Books> searchList = bdao.searchList(inputName);
		return searchList;
	}

	public ArrayList<Books> rentList() {
		ArrayList<Books> rentList = bdao.rentList();
		return rentList;

	}

	public void updaterentList(int rbNo) {
		// System.out.println("badm�� �����߾�");
		System.out.println(bdao+"������Ʈ����Ʈ");
		bdao.updaterentList(rbNo); //
//		System.out.println("badm�� �����߾�");

	}

	public void updaterentList2(int returnBNo) {
		// TODO Auto-generated method stub
		bdao.updaterentList2(returnBNo); //
	}

	public int rentcheck(int rbNo) {
		ArrayList<Books> rentcheck = bdao.rentList(); //
		System.out.println(bdao+"��Ʈüũ");
		for (int i = 0; i < rentcheck.size(); i++) {
			if (rentcheck.get(i).getNo() == rbNo) {
				int check = i;
				if (rentcheck.get(check).getRent().equals("N")) {
					return i;
				}
			}
		}
		return -1;

	}

	public void addLabel() {
		// TODO Auto-generated method stub
		if (labelMap == null) {
			labelMap = new HashMap<>();
		}
		try {
			JLabel newLabel = new JLabel(">>>  " + newB.getDate()
			+ " // " + newB.getName() + "�� ���� �԰����ϴ�.");
			System.out.println(newB.getName());
			newB_no = String.valueOf(newB.getNo());
			labelMap.put(newB_no, newLabel);
//			System.out.println("�󺧵�ϼ���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void addButton() {
		if (buttonMap == null) {
			buttonMap = new HashMap<>();
		}
		try {
			JButton newButton = new JButton("�뿩");
			System.out.println(newB.getName());
			newB_no = String.valueOf(newB.getNo());
			buttonMap.put(newB_no, newButton);
//			System.out.println("��ư��ϼ���");
		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
