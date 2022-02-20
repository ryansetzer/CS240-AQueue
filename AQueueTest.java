import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Array-Queue Lab.
 * 
 * @author Nathan Sprague
 * @version 9/23/2020
 *
 */
class AQueueTest {

  @Test
  public void testDequeueFromEmpty() {
    AQueue<String> queue = new AQueue<>();
    assertEquals(null, queue.dequeue());
    assertEquals(0, queue.length());
  }

  @Test
  public void testEnqueDequeueNoWrap() {
    AQueue<String> queue = new AQueue<>(100);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.dequeue());
    assertEquals("B", queue.dequeue());
    assertEquals("C", queue.dequeue());
  }

  @Test
  public void testEnqueDequeueWithWrap() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.dequeue());
    assertEquals("B", queue.dequeue());
    assertEquals("C", queue.dequeue());

    queue.enqueue("D");
    queue.enqueue("E");
    queue.enqueue("F");

    assertEquals("D", queue.dequeue());
    assertEquals("E", queue.dequeue());
    assertEquals("F", queue.dequeue());

    assertEquals(3, ((Object[]) queue.queueArray).length);
  }

  @Test
  public void testFrontValueAfterEnqueue() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    assertEquals("A", queue.frontValue());
    queue.enqueue("B");
    assertEquals("A", queue.frontValue());
    queue.enqueue("C");
    assertEquals("A", queue.frontValue());
  }

  @Test
  public void testFrontValueAfterDequeue() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.frontValue());
    queue.dequeue();
    assertEquals("B", queue.frontValue());
    queue.dequeue();
    assertEquals("C", queue.frontValue());
  }
  
  @Test
  public void testDequeNullsRemovedItemInArray() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    assertEquals("A", ((Object[])queue.queueArray)[0]); // NOT BLACK BOX TESTING!
    assertEquals("B", ((Object[])queue.queueArray)[1]); 

    queue.dequeue();
    assertEquals(null, ((Object[])queue.queueArray)[0]);
    assertEquals("B", ((Object[])queue.queueArray)[1]); 
    
    queue.dequeue();
    assertEquals(null, ((Object[])queue.queueArray)[1]); 
  }
  

  @Test
  public void testClear() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    queue.clear();
    assertEquals(0, queue.length());
    assertTrue(queue.isEmpty());
  }
  
  
  @Test
  public void testClearNullsArray() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.clear();
    assertEquals(null, ((Object[])queue.queueArray)[0]);
  }
  
  

  @Test
  public void testLengthAfterEnqueue() {
    AQueue<String> queue = new AQueue<>(100);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals(3, queue.length());
    assertEquals(100, ((Object[]) queue.queueArray).length);
  }

  @Test
  public void testLengthAfterEnqueueAndDequeueNoWrap() {
    AQueue<String> queue = new AQueue<>(100);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.dequeue();
    queue.dequeue();

    assertEquals(1, queue.length());
    assertEquals(100, ((Object[]) queue.queueArray).length);
  }

  @Test
  public void testLengthAfterEnqueueAndDequeueWithWrap() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.dequeue();
    queue.dequeue();
    queue.enqueue("D");
    queue.enqueue("E");

    assertEquals(3, queue.length());
    assertEquals(3, ((Object[]) queue.queueArray).length);
  }

  @Test
  public void testIsEmptyNewlyInitializedQueue() {
    AQueue<String> queue = new AQueue<>(3);
    assertTrue(queue.isEmpty());
  }

  @Test
  public void testIsEmptyQueueWithOneItem() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    assertFalse(queue.isEmpty());
  }


  @Test
  public void testIsEmptyEnqueueAndDequeueWithWrapNotEmpty() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.dequeue();
    queue.dequeue();
    queue.enqueue("D");
    queue.enqueue("E");
    assertFalse(queue.isEmpty());
  }

  @Test
  public void testIsEmptyEnqueueAndDequeueWithWrapEmpty() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.dequeue();
    queue.dequeue();
    queue.enqueue("D");
    queue.enqueue("E");
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    assertTrue(queue.isEmpty());
  }

  // TESTS THAT REQUIRE ARRAY RESIZING

  @Test
  public void testLengthAfterGrowingArrayNoDequeues() {
    AQueue<String> queue = new AQueue<>(2);
    queue.enqueue("A");
    queue.enqueue("B");
    assertEquals(2, queue.length());
    assertEquals(2, ((Object[]) queue.queueArray).length);
    queue.enqueue("C");
    queue.enqueue("D");
    assertEquals(4, queue.length());
    assertEquals(4, ((Object[]) queue.queueArray).length);
  }

  @Test
  public void testLengthAfterGrowingArrayWithDequeues() {
    AQueue<String> queue = new AQueue<>(2);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.dequeue();
    queue.enqueue("C");
    assertEquals(2, queue.length());
    assertEquals(2, ((Object[]) queue.queueArray).length);
    queue.enqueue("C");
    queue.enqueue("D");
    assertEquals(4, queue.length());
    assertEquals(4, ((Object[]) queue.queueArray).length);
  }

  @Test
  public void tesCorrectDequeueAfterGrowingArray() {
    AQueue<String> queue = new AQueue<>(2);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.dequeue();
    queue.enqueue("C");
    assertEquals(2, queue.length());
    assertEquals(2, ((Object[]) queue.queueArray).length);
    queue.enqueue("D");
    queue.enqueue("E");
    assertEquals(4, queue.length());
    assertEquals(4, ((Object[]) queue.queueArray).length);
    queue.printAll();

    assertEquals("B", queue.dequeue());
    queue.printAll();
    assertEquals("C", queue.dequeue());
    assertEquals("D", queue.dequeue());
    queue.printAll();
    assertEquals("E", queue.dequeue());

  }

}
