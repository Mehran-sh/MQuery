import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;

import mquery.*;

public class Test {
	
	public ArrayList<Book> getData()
	{
		ArrayList<Book> d = new ArrayList<>();
		d.add(new Book(6, "Book 6"));
		d.add(new Book(4, "Book 4"));
		d.add(new Book(5, "Book 5"));
		d.add(new Book(2, "Book 2"));
		d.add(new Book(3, "Book 3"));
		d.add(new Book(1, "Book 1"));
		return d;
	}
	
	public Flow<Book> getQuery()
	{
		return Flow.query(getData());
	}
	
	@org.junit.Test
	public void whereTest() {
		Flow<Book> f = getQuery();
		Book result = f.where(v -> v.id == 2).firstOrDefault();
		Assert.assertNotNull(result);
		Assert.assertEquals(result.id, 2);
	}
	
	@org.junit.Test
	public void orderByTest()
	{
		Flow<Book> f = getQuery();
		f = f.orderBy(b -> b.id);
		ArrayList<Book> result = f.all();
		assertEquals(result.get(0).id, 1);
		assertEquals(result.get(5).id, 6);
	}

	@org.junit.Test
	public void maxTest()
	{
		Flow<Book> f = getQuery();
		double max = f.max(b -> b.id);
		assertEquals(max, 6.0, 0.1);
	}
	
	@org.junit.Test
	public void minTest()
	{
		Flow<Book> f = getQuery();
		double min = f.min(b -> b.id);
		assertEquals(min, 1.0, 0.1);
	}

	@org.junit.Test
	public void selectTest()
	{
		Flow<Book> f = getQuery();
		ArrayList<Author> result = f
				.where(b -> b.id % 2 == 0)
				.select(b -> new Author(){{id = 1L; name = "mehran"; bookIds.add(b.id);}})
				.all();
		
		assertEquals(result.size(), 3);
		assertEquals(result.get(0).name, "mehran");
		assertEquals(result.get(0).bookIds.get(0), 6, 0.1);
	}
}
