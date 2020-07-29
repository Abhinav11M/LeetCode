package programcreek.algolist;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode node = root;
		for(int i = 0; i < word.length(); ++i) {
			char ch = word.charAt(i);
			if(node.getChildren().containsKey(ch)) {
				node = node.getChildren().get(ch);
			}
			else {
				TrieNode newNode = new TrieNode(i == word.length()-1);
				node.getChildren().put(ch, newNode);
				node = newNode;
			}
		}
	}

	public boolean search(String word) {
		TrieNode t = searchNode(word);
		if(t != null && t.isEndOfWord()) {
			return true;
		}
		
		return false;
	}
	
	public boolean startsWith(String prefix) {
		TrieNode preNode = searchNode(prefix);
		return preNode != null;
	}

	private TrieNode searchNode(String word) {
		
		TrieNode t = root;
		
		for(int i = 0; i < word.length(); ++i) {
			char ch = word.charAt(i);
			if(t.getChildren().containsKey(ch)) {
				t = t.getChildren().get(ch);
			}
			else {
				return null;
			}
		}
		
		return t;
	}
}
