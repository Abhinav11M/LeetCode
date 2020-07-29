package programcreek.algolist;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

@ToString
public class TrieNode {
	private Map<Character, TrieNode> children;
	private boolean endOfWord;
	
	public TrieNode() {
		children = new HashMap<>();
		endOfWord = false;
	}

	public TrieNode(boolean endOfWord) {
		children = new HashMap<>();
		this.endOfWord = endOfWord;
	}
	
	public Map<Character, TrieNode> getChildren() {
		return children;
	}
	
	public boolean isEndOfWord() {
		return endOfWord;
	}
}
