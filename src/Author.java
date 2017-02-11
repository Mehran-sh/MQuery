import java.util.ArrayList;

class Author {
	public Long id;
	public String name;
	public ArrayList<Long> bookIds;
	
	public Author(){
		bookIds = new ArrayList<>();
	}
	
	public Author(Long id, String name, ArrayList<Long> bookIds)
	{
		this.id = id;
		this.name = name;
		this.bookIds = bookIds;
	}
}
