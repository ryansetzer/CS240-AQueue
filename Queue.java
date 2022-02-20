/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2019 - Ville Karavirta and Cliff Shaffer
 */

public interface 5Queue<E> { // Queue class ADT
  // Reinitialize queue
  public void clear();

  // Put element on rear
  public boolean enqueue(E it);

  // Remove and return element from front
  public E dequeue();

  // Return front element
  public E frontValue();

  // Return queue size
  public int length();
  
  //Tell if the queue is empty or not
  public boolean isEmpty();
  
  public void printAll();
}

