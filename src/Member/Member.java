package Member;

import java.util.ArrayList;

//	ȸ����ȣ ,ȸ��id, ��й�ȣ, ����ó, �������

public class Member {
	int no = 0;
	String id = null;
	String pw = null;
	String name = null;
	String tel = null;
	String date = null;

	Rent_DAO rdao = new Rent_DAO();

	public int getNo() {
		return no;
	}

	public String getID() {
		return id;
	}

	public String getPW() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public String getDate() {
		return date;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setID(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void IDget(String inputID) {
		System.out.println(inputID);

	}

	public void rent(Member nowUser, int inputNo) {

//		this.id = nowUser.getID();
//		System.out.println(id); // ���̵𰪹ۿ� ����..ȸ����ȣ�� ����....�׷��� �̰� memberDAO�� �־��ְ� ���ϰ����� nowuser�� ��ü��
//								// �����ϸ�...����������?
//		System.out.println(inputNo);
//		this.no = nowUser.getNo();
//		System.out.println(no);
		rdao.rent(no, inputNo);
	}

	public ArrayList<Rent> rentlist() {
//		radm.rentlist(nowUser,inputNo);
		ArrayList<Rent> rList = rdao.rentlist();
		
		return rList;
		
	}
	public void retunbook(int returnBNo, Member nowUser)	{
		rdao.returnbook(returnBNo,nowUser.getNo());
		
	}

}
