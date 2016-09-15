import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MultiThreadSortTest {
	private static final int N_ELEMENTS = 10000;
	private static final int N_THREADS = 50;
	private static final int MAX_NUMBER = 1000;
	private static final int N_RUNS = 1000;
	private static int[] array;

	@Before
	public void setUp() throws Exception {
		array = new int[N_ELEMENTS];
		Random gen = new Random();
		for(int i = 0; i < N_ELEMENTS; i++){
			array[i] = gen.nextInt(MAX_NUMBER);
		}
	}

	@Test
	public void testSort() {
		for (int j = 0; j < N_RUNS; j++) {
			SortControl sort = new SortControl(array, N_THREADS);
			sort.sort();
			int[] arraySorted = sort.getArray();
			//print(arraySorted);
			for (int i = 0; i < arraySorted.length - 1; i++) {
				assertTrue(arraySorted[i] <= arraySorted[i + 1]);
			} 
		}
		
	}

//	private void print(int[] array) {
//		for(int num : array){
//			System.out.print(num + "\t");
//		}
//	}

}
