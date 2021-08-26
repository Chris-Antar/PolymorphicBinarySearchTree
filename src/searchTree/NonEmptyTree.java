package searchTree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K, V> left, right;
	private K key;
	private V value;
	//Standard constructor for NonEmptyTree
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	/*This method searches for a key in the tree and returns the associated
	 * value. If the key is not present it will return null through the 
	 * EmptyTree search method
	 */
	public V search(K key) {
		int comparison = key.compareTo(this.key);
		if (comparison == 0) {
			return value;
		}
		if (comparison < 0) {
			return left.search(key);
		} else {
			return right.search(key);
		}
	}
	/*This method inserts a key and value into the tree. If the key is already
	 * in the tree it will instead just update its value
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {
		int comparison = key.compareTo(this.key);
		if (comparison == 0) {
			this.value = value;
			return this;
		} else if (comparison < 0) {
			left = left.insert(key, value);
		} else {
			right = right.insert(key, value);
		}
		return this;
	}

	public Tree<K, V> delete(K key) {
		int comparison = key.compareTo(this.key);
		if (comparison == 0) {
			//First checks the left child to see if there is a smaller value
			try {
				/*This code initially sets the current object's key to the 
				 * smallest value on the left side, and then traverses down
				 * the children of the current object until it reaches the 
				 * key that was initially set to be deleted. If that object
				 * has no children it will set it to "SINGLETON", if it has
				 * children it repeats the same process.
				 */
				K leftMax = left.max();
				this.key = leftMax;
				V tempValue = left.search(leftMax);
				left = left.delete(leftMax);
				//V tempValue = left.search(leftMax);
				value = tempValue;
				

			} catch (TreeIsEmptyException e) {
				/*If the NonEmptyTree object does not have a left child, it will
				 * then check the right side to get the next smallest value
				 */
				try {
					//This code does the same as block above but for right side
					K rightMin = right.min();
					this.key = right.min();
					V tempvalue = right.search(rightMin);
					right = right.delete(rightMin);
					value = tempvalue;
					
				} catch (TreeIsEmptyException b) {
					/*If the NonEmptyTree has no children, it will return the 
					 * instance of the empty tree.
					*/
					return EmptyTree.getInstance();
				}
			}
		}
		if (comparison < 0) {
			left = left.delete(key);
		} else if(comparison > 0) {
			right = right.delete(key);
		}
		return this;

	}
	/*This method returns the maximum value(key) in the tree.
	 */
	public K max() {
		try {
			return this.right.max();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}
	//This method returns the minimum value(key) in the tree
	public K min() {
		try {
			return this.left.min();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}
	//This method returns the size of the tree
	public int size() {
		int counter = 1;
		counter += this.left.size();
		counter += this.right.size();
		return counter;
	}
	/*This method traverses the tree and adds every key to a collection "c"
	 * It does this traversal by visiting the smallest values first.
	 */
	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);
	}
	/*This method returns a subtree containing every key in between "fromKey"
	 * and "toKey". 
	 * 
	 */
	public Tree<K, V> subTree(K fromKey, K toKey) {
		int comparison1 = fromKey.compareTo(key); // fromKey is bigger if > 0,
													// less than if < 0
		int comparison2 = toKey.compareTo(key); // toKey is bigger if > 0, less
		if (comparison1 > 0) {					// than if < 0
			return left.subTree(fromKey, toKey);
		} 
		else if (comparison2 < 0) {
			return right.subTree(fromKey, toKey);
		} else {
			return new NonEmptyTree<K, V>(key, value,
					left.subTree(fromKey, toKey),
					right.subTree(fromKey, toKey));
		}

	}
}