package programcreek.algolist;

public class MyStack<T> {
	private T[] data_;
	private int size_;
	private int head_;
	
	@SuppressWarnings("unchecked")
	MyStack(int size) {
		this.size_ = size;
		data_ = (T[]) new Object[size];
		head_ = -1;
	}
	
	public T pop() {
		if(head_ == -1) {
			return null;
		}
		return data_[head_--];
	}

	public boolean push(T data) {
		if(head_ == size_-1) {
			return false;
		}
		
		data_[++head_] = data;
		return true;
	}
}
