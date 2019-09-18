package datastructures;

public class ListNodeWithRandom {
	@Override
	public String toString() {
		return "ListNodeWithRandom [data=" + data + ", next=" + next.data + ", random=" + random.data + "]";
	}

	public ListNodeWithRandom(int data) {
		this.data = data;
		this.next = null;
		this.random = null;
	}
	public int data;
	public ListNodeWithRandom next;
	public ListNodeWithRandom random;
	
	public static void printList(ListNodeWithRandom head) {
		while(head.next != null) {
			System.out.print("[" + head.data + "," + head.random.data  + "]" + "-->");
			head = head.next;
		}
		System.out.print("[" + head.data + "," + head.random.data  + "]\n");
	}
}
