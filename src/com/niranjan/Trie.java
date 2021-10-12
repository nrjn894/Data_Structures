//This is Trie Data Structure which is used in Auto Completion in Search Engines.

package com.niranjan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
	//Trie Node which have a map and a boolean variable
	class Node{
		Map<Character,Node> map;
		boolean last;
		public Node(Map<Character, Node> map, boolean last) {
			super();
			this.map = map;
			this.last = last;
		}
	}
	
	//Root of Trie
	Node root = new Node(new HashMap<Character,Node>(),false);
	
	
	//Insert method working fine
	public void insert(String word) {
		int len = word.length();
		Node currentNode = root;
		int i = 0;
		while(i < len && currentNode.map.containsKey(word.charAt(i))) {
			currentNode = currentNode.map.get(word.charAt(i));
			i++;
		}
		while(i<len) {
			Node tempNode = new Node(new HashMap<Character,Node>(),false);
			currentNode.map.put(word.charAt(i), tempNode);
			currentNode = tempNode;
			i++;
		}
		currentNode.last = true;
	}
	
	//This is delete method to delete the word, it has some problem....now Fixed ..looks like working 
	public boolean delete(String word) {
		return delete(word,0,word.length(),root);
	}
	
	private boolean delete(String word , int i, int len, Node node) {
		if(i == len) {
			if(node.map.size() == 0) {
				return true;
			}else {
				node.last = false;
				return false;
			}
			
		}
		boolean deletionPossible = false;
		if(node.map.containsKey(word.charAt(i))) {
			deletionPossible =  delete(word,i+1,len,node.map.get(word.charAt(i)));
		}
		
		if(deletionPossible && !node.last) {
			node.map.remove(word.charAt(i));
		}
		return deletionPossible;
	}
	
	
	//get method working fine
	public List<String> get(String prefix){
		List<String> list = new ArrayList<String>();
		int len = prefix.length();
		int i = 0;
		Node currentNode = root;
		while(i < len && currentNode.map.containsKey(prefix.charAt(i))) {
			currentNode = currentNode.map.get(prefix.charAt(i));
			i++;
		}
		if( i == len) {
			get(list, currentNode, prefix);
		}
		return list;
	}
	
	private void get(List<String> list ,Node node,String word) {
		if(node.last) {
			list.add(word);
		}
		for (Map.Entry<Character, Node> entry : node.map.entrySet()) {
		    Character c = entry.getKey();
		    Node nextNode = entry.getValue();
		    get(list, nextNode, word + c);
		}
	}
	

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("Niranjan Singh");
		trie.insert("Mathematics");
		trie.insert("I like coding");
		trie.insert("I like coffee");
		trie.insert("I like reading");
		trie.insert("abcdef");
		trie.insert("abmnt");
		trie.insert("abc");
		trie.insert("gbhjy");
		trie.delete("abc");
		trie.get("Ilike").stream().forEach(p-> System.out.println(p));
	}

}
