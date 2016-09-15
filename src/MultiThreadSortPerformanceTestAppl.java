import java.util.Map.Entry;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class MultiThreadSortPerformanceTestAppl {
	private static final int N_ELEMENTS = 1000000;
	private static final int N_THREADS = 100;
	private static final int MAX_NUMBER = 1000;
	private static int[] array;
	
	public static void main(String[] args) {
		array = createArray(N_ELEMENTS);
		HashMap<Integer, Long> times = new HashMap<>();
		for (int i = 1; i < N_THREADS; i++) {
			long timeStart = System.currentTimeMillis();
			SortControl sort = new SortControl(array, i);
			sort.sort();
			times.put(i, System.currentTimeMillis() - timeStart);
		}
		printFromMin(times);
	}
	
	private static void printFromMin(HashMap<Integer, Long> times) {
		ArrayList<Entry<Integer,Long>> list = new ArrayList<>(times.entrySet());
		list.sort(new Comparator<Entry<Integer,Long>>(){
			@Override
			public int compare(Entry<Integer, Long> e1, Entry<Integer, Long> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		for(Entry<Integer,Long> entry: list){
			System.out.println("time = " + entry.getValue() + "\t nThreads = " + entry.getKey());
		}
	}

	private static int[] createArray(int nElements) {
		int[] res = new int[nElements];
		Random gen = new Random();
		for(int i = 0; i < nElements; i++){
			res[i] = gen.nextInt(MAX_NUMBER);
		}
		return res;
	}
}
