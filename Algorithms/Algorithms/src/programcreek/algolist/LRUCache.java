package programcreek.algolist;

import java.util.HashMap;

public class LRUCache {

	public static class LRUNode {
		public int key;
		public int val;
		public LRUNode next;
		public LRUNode prev;
		
		public LRUNode(int k, int v) {
			key = k;
			val = v;
		}
	}

	public LRUCache(int capacity) {
        this.capacity = capacity;
		map = new HashMap<>();
    }
    
    public int get(int key) {
    	if(!map.containsKey(key)) {
    		return -1;
    	}

    	LRUNode node = map.get(key);
    	int retVal = node.val;
    	// Bring node to front
    	deleteNode(node);
    	setToHead(node);
    	
    	return retVal;
    }

	public void put(int key, int value) {
		if(map.containsKey(key)) {
			LRUNode node = map.get(key);
			node.val = value;
			deleteNode(node);
			setToHead(node);
		}
		else {
			LRUNode newNode = new LRUNode(key, value);
			if(map.size() == capacity) {
				// remove last
				map.remove(end.key);
				deleteNode(end);
			}
			map.put(key, newNode);
			setToHead(newNode);
		}
    }
    
    // Sets the node at the head
    private void setToHead(LRUNode node) {
    	node.next = head;

    	if(head != null) {
    		head.prev = node;
    	}
		head = node;
		
		if(end == null) {
			end = node;
		}
	}

    // Unlinks the node
	private void deleteNode(LRUNode node) {
		if(node.prev != null) { // not head
			node.prev.next = node.next;
		}
		else { // head
			if(node.next != null) {
				node.next.prev = null;
			}
			head = node.next;
		}
		if(node.next != null) { // not end
			node.next.prev = node.prev;
		}
		else { // end
			if(node.prev != null) {
				node.prev.next = null;
			}
			end = node.prev;
		}
	}
    
	private int capacity;
	private HashMap<Integer, LRUNode> map;
	private LRUNode head = null;
	private LRUNode end = null;
}
