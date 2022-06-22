package Member;

public class Rent {
	int bookNo = 0;
	String bookName = null;
	String memberID = null;
	String rent_date =null;
	
	public int getbookNo()	{
		return bookNo;
	}
	public String getbName()	{
		return bookName;
	}
	public String getmID()	{
		return memberID;
	}
	public String getDate()	{
		return rent_date;
	}
	public void setbNo(int bookNo)	{
		this.bookNo=bookNo;
	}
	public void setbName(String bookName)	{
		this.bookName = bookName;
	}
	public void setmID(String memberID)	{
		this.memberID= memberID;
	}
	public void setDate(String rentdate)	{
		this.rent_date=rentdate;
	}
}
