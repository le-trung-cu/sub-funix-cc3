package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeSearchAVL<T> {
	
	public static class TreeNode<T> {
		T val;
		String key;
		TreeNode<T> left;
		TreeNode<T> right;
		int blanceFactor = 0;
		
		public TreeNode(String key, T value) {
			this.val = value;
			this.key = key;
		}
	}
	
	public int add(TreeNode<T> root, String key, T value) {
		
		int compare = root.key.compareTo(key);
		if (compare == 0) {
			// key has ready exists
			return -1;
		}
		if(compare > 0) {
			add(root.left, key, value);
		}else if(compare < 0) {
			add(root.right, key, value);
		}
		return 1;
	}
	
	public T search(TreeNode<T> root, String key) {
		if (root == null) {
			return null;
		}
		int compare = root.key.compareTo(key);
		if(compare == 0) {
			return root.val;
		}
		
		if (compare > 0) {
			return search(root.left, key);
		}
		return search(root.right, key);
	}
	
	public List<T> getPostOrder(TreeNode<T> root) {
		
		var result = new ArrayList<T>();
		
		if(root == null) {
			return result;
		}
		var stack = new Stack<TreeNode<T>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			var current = stack.pop();
			stack.push(current.left);
			stack.push(current.right);
			
			result.add(current.val);
		}
		return result;
	}
	
	
}












