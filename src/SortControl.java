import java.util.LinkedList;

public class SortControl {	
	private int[] array;
	private int nElements;
	private int nThreads;
	
	public SortControl(int[] array, int nThreads){
		this.array = array;
		this.nElements = array.length;
		this.nThreads = nThreads;
	}
	
	public int[] getArray() {
		return array;
	}

	public void sort() {
		LinkedList<SortThread> listOfThread = startThreads(nThreads);
		joins(listOfThread);
		merge(nThreads);
	}

	private void merge(int nThreads) {
		int[] res = new int[nElements];
		int nElemInThread = nElements / nThreads;
		for(int i = 1; i < nThreads - 1; i++){
			int lastIndexA = i * nElemInThread;
			int lastIndexB = (i + 1) * nElemInThread;
			res = mergeOneThread(lastIndexA,lastIndexB);
			updateArray(res,lastIndexB);
		}
		res = mergeOneThread(nElemInThread * (nThreads - 1), nElements);
		updateArray(res,nElements);
	}

	private void updateArray(int[] res, int lastIndexB) {
		for(int i = 0; i < lastIndexB; i++){
			array[i] = res[i];
		}
	}

	private int[] mergeOneThread(int lastIndexA, int lastIndexB) {
		int[] res = new int[nElements];
		int indexA = 0;
		int indexB = lastIndexA;
		int j =0;
		while(indexA < lastIndexA && indexB < lastIndexB){
			if(array[indexA] > array[indexB])
				res[j++] = array[indexB++];
			else 
				res[j++] = array[indexA++];
		}
		while(indexA < lastIndexA){
			res[j++] = array[indexA++];
		}
		while(indexB < lastIndexB){
			res[j++] = array[indexB++];
		}
		return res;
	}

	private void joins(LinkedList<SortThread> listOfThread) {
		for(SortThread thread : listOfThread){
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private LinkedList<SortThread> startThreads(int nThreads) {
		LinkedList<SortThread> res = new LinkedList<>();
		int nElemInThread = nElements / nThreads;
		SortThread.setArray(array);
		int from = 0;
		int to = nElemInThread;
		SortThread thread;
		for(int i = 0; i < nThreads; i++){
			thread = new SortThread(from,to);
			thread.start();
			res.add(thread);
			from = to;
			to += nElemInThread;
			if(i == nThreads - 2)
				to += nElements - nThreads * nElemInThread;
		}
		return res;
	}
}
