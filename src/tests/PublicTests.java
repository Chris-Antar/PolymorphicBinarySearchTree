package tests;
import java.util.NoSuchElementException;
import javax.net.ssl.SSLContext;
import static org.junit.Assert.*;
import org.junit.Test;
import searchTree.EmptyTree;
import searchTree.SearchTreeMap;
import searchTree.Tree;

public class PublicTests{
	
	@Test
	public void testEasyBSTSearch() {
		Tree<Integer,String> t = EmptyTree.getInstance();
		assertTrue(t.search(0) == null);
		t = t.insert(5, "THIS IS 5");
		assertEquals("THIS IS 5", t.search(5));
		t = t.insert(8, "THIS IS 8");
		assertEquals("THIS IS 8", t.search(8));
	}
	
	@Test
	public void testEmptySearchTreeMap() {
		SearchTreeMap<String, Integer> s = new SearchTreeMap<String, Integer>();
		assertEquals(0, s.size());
		try {
			assertEquals(null, s.getMin());
			fail("Should have thrown NoSuchElementException");
		} catch (NoSuchElementException e) {
			assert true; // as intended
		}
		try {
			assertEquals(null, s.getMax());
			fail("Should have thrown NoSuchElementException");
		} catch (NoSuchElementException e) {
			assert true; // as intended
		}
		assertEquals(null, s.get("a"));
	}
	
	@Test
	public void testBasicSearchTreeMapStuff() {
		SearchTreeMap<Integer,String> s = new SearchTreeMap<Integer,String>();
		assertEquals(0, s.size());
		s.put(2, "Two");
		s.put(1, "One");
		s.put(3, "Three");
		s.put(1, "OneSecondTime");
		assertEquals(3, s.size());
		assertEquals("OneSecondTime", s.get(1));
		assertEquals("Two", s.get(2));
		assertEquals("Three", s.get(3));
		assertEquals(null, s.get(8));
		
		
	}
	@Test
	public void testBasicSearchTreeMapStuff2() {
		SearchTreeMap<Integer,String> s = new SearchTreeMap<Integer,String>();
		assertEquals(0, s.size());
		s.put(2, "Two");
		s.put(1, "One");
		s.put(3, "Three");
		s.put(1, "OneSecondTime");
		s.put(5, "Five");
		s.put(8, "Eight");
		s.put(24, "twentyfour");
		assertEquals(6, s.size());
		assertEquals("OneSecondTime", s.get(1));
		assertEquals("Two", s.get(2));
		assertEquals("Three", s.get(3));
		
		/*
		s.remove(24);
		assertEquals("Three", s.get(3));
		assertEquals("Eight", s.get(8));
		System.out.println(s.keyList());
		s.remove(8);
		assertEquals("Five", s.get(5));
		System.out.println(s.keyList());
		s.remove(5);
		assertEquals("Three", s.get(3));
		System.out.println(s.keyList());
		s.remove(3);
		assertEquals("Two", s.get(2));
		System.out.println(s.keyList());
		assertEquals("OneSecondTime", s.get(1));
		s.remove(2);
		assertEquals("OneSecondTime", s.get(1));
		System.out.println(s.keyList());
		s.remove(1);
		System.out.println(s.keyList());
		s.remove(24);
		assertEquals(0,s.size());
		*/
		
	}
	
}