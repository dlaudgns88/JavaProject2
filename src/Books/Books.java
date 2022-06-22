package Books;
	//도서번호, 도서명, 저자, 등록일자
public class Books {
	private int bookNo = 0;
	private String bookName = null;
	private String bookAuthor = null;
	private String date = null;
	private String rent = null;
	
	public int getNo()	{
		return bookNo;
	}
	public String getName()	{
		return bookName;
	}
	public String getAuthor()	{
		return bookAuthor;
	}
	public String getDate()	{
		return date;
	}
	
	public void setNo(int no)	{
		this.bookNo = no;
	}
	
	public void setName(String name)	{
		this.bookName = name;
	}
	
	public void setAuthor(String author)	{
		this.bookAuthor = author;
	}
	
	public void setDate(String date)	{
		this.date = date;
	}
	public String getRent()	{
		return rent;
	}
	public void setRent(String rent)	{
		this.rent=rent;
	}
}
