import java.util.HashMap;
import java.util.Map;

public class TestClass {
	public static void main(String[] args) {
	}
	
}


class LRUCache {
	
	public static class LRUNode {
		public int key;
		public int val;
		public LRUNode prev;
		public LRUNode next;
		
		public LRUNode(int k, int v) {
			key = k;
			val = v;
		}
	}
	
	
	
	public int get(int key) {
		if(!map.containsKey(key)) {
			return -1;
		}
		
		LRUNode node = map.get(key);
		deleteNode(node);
		setToFront(node);
		
		if(map.size() == capacity) {
			// delete the last node
			map.remove(end.key);
			deleteNode(end);
		}
		
		return node.val;
	}
	
	private void setToFront(LRUCache.LRUNode node) {
		node.next = head;
		if(head != null) {
			head.prev = node;
		}
		head = node;
		
		if(end == null) {
			end = node;
		}
		
	}

	private void deleteNode(LRUCache.LRUNode node) {
		if(node != head) { // not head
			node.prev.next = node.next;
		}
		else { // head
			if(node.next != null) {
				node.next.prev = null;
			}
			head = node.next;
		}
		if(node != end) { // not tail
			node.next.prev = node.prev;
		}
		else { //tail
			if(node.prev != null) {
				node.prev.next = null;
			}
			end = node.prev;
		}
	}

	public void put(int key, int value) {
		LRUNode newNode = new LRUNode(key, value);
		if(!map.containsKey(key)) {
			// check for the capacity
			if(map.size() == capacity) { // capaciy full
				map.remove(end.key);
				deleteNode(end);
			}
			map.put(key, newNode);
			setToFront(newNode);
		}
		else {
			LRUNode node = map.get(key);
			deleteNode(node);
			node.val = value;
			setToFront(node);
		}
	}
	
	// data members
	Map<Integer, LRUNode> map = new HashMap<>();
	LRUNode head;
	LRUNode end;
	int capacity;
}
