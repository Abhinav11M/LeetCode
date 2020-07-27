package programcreek.algolist;

public class QFromArray<T> {
	private T[] data;
	private int head;
	private int tail;
	private int size;

	@SuppressWarnings("unchecked")
	public QFromArray(int size) {
		data = (T[]) new Object[size];
		head = -1;
		tail = -1;
		size = 0;
	}
	
	public boolean push(T val) {
		if(size == data.length) {
			return false;
		}
		
		if(head == -1) { // Entry of first element
			data[++head] = val;
			++tail;
		}
		else {
			tail = (tail+1) % data.length;
			data[tail] = val; // insert from tail
		}
		++size;
		
		return true;
	}
	
	public T pop() {
		if(size == 0) {
			return null;
		}
		
		if(head == tail) {
			T retVal = data[head];
			head = tail = -1;
			--size;
			return retVal;
		}
		
		T retVal = data[head]; // Remove from head
		head = (head+1) % data.length;
		--size;
		
		return retVal;
	}
	
	public T peek() {
		if(size == 0) {
			return null;
		}

		return data[head];
	}
	
	public int size() {
		return size;
	}
}
