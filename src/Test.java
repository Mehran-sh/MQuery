import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;

import mquery.*;

public class Test {

	public Flow<Book> getQuery()
	{
		ArrayList<Book> d = new ArrayList<>();
		d.add(new Book(6, "Book 6"));
		d.add(new Book(4, "Book 4"));
		d.add(new Book(5, "Book 5"));
		d.add(new Book(2, "Book 2"));
		d.add(new Book(3, "Book 3"));
		d.add(new Book(1, "Book 1"));
		return Flow.query(d);
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

}
