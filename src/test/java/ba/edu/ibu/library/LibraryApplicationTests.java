package ba.edu.ibu.library;

import static org.junit.jupiter.api.Assertions.*;

import ba.edu.ibu.library.core.model.Book;
import ba.edu.ibu.library.core.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LibraryApplicationTests {

	@Test
	@Order(1)
	void contextLoads() {
		// assertNotEquals(5, 2 + 2);
// 		String[] actual = {"Hello", "JUnit"};
//		String[] expected = {"Hello", "JUnit"};
//		assertArrayEquals(expected, actual);
//
//		assertTrue(2 > 1);
//		assertFalse(2 > 3);
//
//		User u = null;
//		assertNull(u);
//
//		u = new User();
//		assertNotNull(u);
//
//		int[] array = {1, 2, 3};
//		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
//			int i = array[3];
//		});
//		assertEquals("Index 3 out of bounds for length 3", e.getMessage());

		Assertions.assertThat(2 + 2).isEqualTo(4);

		Book b = new Book();
		b.setTitle("Book 1");

		Book b2 = new Book();
		b2.setTitle("Book 1");

		Assertions.assertThat(b).usingRecursiveComparison().isEqualTo(b2);
	}

	@Test
	@Order(3)
	void test2() {
		assertEquals("hi", "hi");
	}

	@Test
	@Order(2)
	void test3() {
		assertEquals("hi", "hi");
	}

}
