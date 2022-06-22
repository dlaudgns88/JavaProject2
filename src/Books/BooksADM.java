package Books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BooksADM {
	private Books_DAO bdao = new Books_DAO(); //<<<<< bado의 주소값이 달라지는것 같다  x
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
		// System.out.println("badm에 접속했어");
		System.out.println(bdao+"업데이트리스트");
		bdao.updaterentList(rbNo); //
//		System.out.println("badm에 접속했어");

	}

	public void updaterentList2(int returnBNo) {
		// TODO Auto-generated method stub
		bdao.updaterentList2(returnBNo); //
	}

	public int rentcheck(int rbNo) {
		ArrayList<Books> rentcheck = bdao.rentList(); //
		System.out.println(bdao+"렌트체크");
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
			+ " // " + newB.getName() + "가 새로 입고됬습니다.");
			System.out.println(newB.getName());
			newB_no = String.valueOf(newB.getNo());
			labelMap.put(newB_no, newLabel);
//			System.out.println("라벨등록성공");
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
			JButton newButton = new JButton("대여");
			System.out.println(newB.getName());
			newB_no = String.valueOf(newB.getNo());
			buttonMap.put(newB_no, newButton);
//			System.out.println("버튼등록성공");
		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
