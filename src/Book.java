
class Book {
	
	public Book(long id, String title)
	{
		this.id = id;
		this.title = title;
	}
	
	public long id;
	public String title;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Id: %s, Title: %s", this.id, this.title);
	}
}
