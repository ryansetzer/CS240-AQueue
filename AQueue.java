/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2019 - Ville Karavirta and Cliff Shaffer
 */

class 55AQueue<E> implements Queue<E> {

	// Keep this non-private for testing purposes!
	E[] queueArray; // Array holding queue elements

	private static final int defaultSize = 10;
	private int front; // Index of front element
	private int size; // Number of elements stored.

	// Constructors
	@SuppressWarnings("unchecked") // Generic array allocation
	AQueue(int capacity) {
		queueArray = (E[]) new Object[capacity];
		front = 0;
		size = 0;
	}

	AQueue() {
		this(defaultSize);
	}

	// Reinitialize
	public void clear() {
		for (int i = 0; i < size; i++) {
			queueArray[i] = null;
		}
		front = 0;
		size = 0;
	}

	// Put "it" in queue
	public boolean enqueue(E it) {
		if (queueArray.length == size) {
			E[] newQueue = (E[]) new Object[queueArray.length * 2];
			for (int i = 0; i < queueArray.length; i++) {
				newQueue[front + i] = queueArray[(front + i) % queueArray.length];
			}
			queueArray = newQueue;
		}
		queueArray[(front + size) % queueArray.length] = it;
		size++;
		return true;
	}

	// Remove and return front value
	public E dequeue() {
		if (size != 0) {
			E result = queueArray[front];
			queueArray[front] = null;
			front = (front + 1) % queueArray.length;
			size--;
			return result;
		} else {
			return null;
		}
	}

	// Return front value
	public E frontValue() {
		return queueArray[front];
	}

	// Return queue size
	public int length() {
		return size;
	}

	// Check if the queue is empty
	public boolean isEmpty() {
		return size == 0;
	}

	public void printAll() {
		System.out.println("front - " + front);
		for (int i = 0; i < queueArray.length; i++) {
			System.out.println("[" + i + "] - " + queueArray[i]);
		}
	}
}
