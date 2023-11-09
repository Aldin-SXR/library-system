package ba.edu.ibu.library;

import static org.junit.jupiter.api.Assertions.*;

import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApplicationTests {

	@Test
	void contextLoads() {
		String[] actual = {"Hello", "JUnit"};
		String[] expected = {"Hello", "JUnit"};
		assertArrayEquals(actual, expected);

		assertTrue(2 > 1);
		assertFalse(2 > 3);

		User u = null;
		assertNull(u);

		u = new User();
		assertNotNull(u);

		int[] array = {1, 2, 3};
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
			int i = array[3];
		});
		assertEquals("Index 3 out of bounds for length 3", e.getMessage());
	}

}
