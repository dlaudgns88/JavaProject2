package Member;

import java.util.ArrayList;

public class MemberADM {
	Member_DAO mdao = new Member_DAO();
	Member loginuser;
	Member newmember;

	public void add(String inputID, String inputPW, String inputTel, String inputName) {
		Member newMember = new Member();
		newMember.setID(inputID);
		newMember.setPw(inputPW);
		newMember.setTel(inputTel);
		newMember.setName(inputName);
		mdao.add(newMember);

	}

	public Member loginProcess(String inputID, String inputPW) {
		loginuser = new Member();
		loginuser.setID(inputID);
		loginuser.setPw(inputPW);
		loginuser=mdao.loginProceess(loginuser);
		if (loginuser != null) {
			return loginuser;
		}
		return null;

	}
	public Member getLoginUser()	{
		mdao.selectone(loginuser);
		return loginuser;
	}

	public int check(String inputID) {
		System.out.println(inputID);
		ArrayList<Member> mlist = mdao.memberList();
		for(int i=0; i<mlist.size(); i++)	{
			String member = mlist.get(i).getID();
			if(member.equals(inputID))	{
				System.out.println("중복이야");
				return i;
			}
		}
		return -1;
	}

}
