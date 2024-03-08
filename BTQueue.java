// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BTQueue
{   
   BTQueueNode head;
   BTQueueNode tail;
      
   public BTQueue ()
   {
      head = null;
      tail = null;
   }
   /**
     * Dequeues the next binary tree node from the queue.
     * @return The next binary tree node in the queue, or null if the queue is empty.
     */
   public BinaryTreeNode getNext ()
   {
      if (head == null)
         return null;
      BTQueueNode qnode = head;
      head = head.next;
      if ( head == null )
         tail = null;
      return qnode.node;
   }
   /**
     * Enqueues a binary tree node into the queue.
     * @param node The binary tree node to enqueue.
     */
   public void enQueue ( BinaryTreeNode node )
   {
      if (tail == null)
      {   
         tail = new BTQueueNode (node, null);
         head = tail;
      }   
      else
      {
         tail.next = new BTQueueNode (node, null);
         tail = tail.next;
      }   
   }   
}
