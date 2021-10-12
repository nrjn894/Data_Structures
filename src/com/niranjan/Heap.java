package com.niranjan;

public class Heap {
	private int[] arrHeap;
	private int count;
	private int capacity;
	
	//Heap Constructor
	public Heap(int capacity) {
		this.capacity = capacity;
		arrHeap = new int[capacity];
		this.count = 0;
	}
	
	//Heap resize 
	public boolean resizeHeap(int capacity) {
		if(this.count > capacity) {
			System.out.println("more elements than capacity");
			return false;
		}
		if (this.capacity == capacity) {
			System.out.println("No change in capacity");
			return false;
		}
		this.capacity = capacity;
		int[]oldArrHeap = arrHeap;
		arrHeap = new int[capacity];
		System.arraycopy(oldArrHeap, 0, arrHeap, 0, this.count);
		oldArrHeap = null;
		return true;
	}
	
	//Getting the index of parent of an element
	private int getParent(int x) {
		if(x == 0) {
			return -1;
		}
		return (x-1)/2;
	}
	
	//Getting the index of left child of an element
	private int getLeftChild(int x) {
		int lchild = 2*x+1;
		if(lchild >= this.count) {
			return -1;
		}
		return lchild;
	}
	
	//Getting the index of right child of an element
	private int getRightChild(int x) {
		int rchild = 2*x+2;
		if(rchild >= this.count) {
			return -1;
		}
		return rchild;
	}
	
	//Heapify the heap from the given element to the bottom
	private void percolateDown(int x) {
		int max = x;
		int lchild = getLeftChild(x);
		int rchild = getRightChild(x);
		if(lchild != -1 && arrHeap[lchild] > arrHeap[max]) {
			max = lchild;
		}
		if(rchild != -1 && arrHeap[rchild] > arrHeap[max]) {
			max = rchild;
		}
		if(max != x) {
			int temp = arrHeap[max];
			arrHeap[max] = arrHeap[x];
			arrHeap[x] = temp;
			percolateDown(max);
		}
	}
	
	//Heapify the heap from the given element to the root upwards
	private void percolateUp(int x) {
		int parent = getParent(x);
		if (parent != -1 && arrHeap[parent] < arrHeap[x]) {
			int temp = arrHeap[parent];
			arrHeap[parent] = arrHeap[x];
			arrHeap[x] = temp;
			percolateUp(parent);
		}
	}
	
	public int delete() {
		if(this.count == 0) {
			return Integer.MIN_VALUE;
		}
		int temp = arrHeap[0];
		arrHeap[0] = arrHeap[this.count -1];
		this.count --;
		percolateDown(0);
		return temp;
	}
	
	public boolean insert(int x) {
		if (this.count == this.capacity) {
			System.out.println("Resize Heap");
			return false;
		}
		this.count ++;
		arrHeap[this.count-1] = x;
		percolateUp(this.count-1);
		return true;
	}
	
	public static void main(String[]args) {
		Heap heap = new Heap(10);
		heap.insert(15);
		heap.insert(25);
		heap.insert(56);
		heap.insert(46);
		heap.insert(45);
		heap.insert(16);
		heap.insert(67);
		heap.insert(84);
		heap.insert(19);
		heap.insert(19);
		heap.resizeHeap(11);
		heap.insert(19);
		//System.out.println(heap.insert(10));
		//System.out.println(heap.insert(11));
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
		System.out.println(heap.delete());
	}

}
