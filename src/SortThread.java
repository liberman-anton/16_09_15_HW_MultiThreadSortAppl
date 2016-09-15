import java.util.Arrays;

public class SortThread extends Thread {
	static private int[] array;
	private int from;
	private int to;
	
	public SortThread(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}

	public static void setArray(int[] array) {
		SortThread.array = array;
	}

	@Override
	public void run(){
		Arrays.sort(array, from, to);
	}
}
