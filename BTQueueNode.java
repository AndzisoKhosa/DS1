// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BTQueueNode
{
   BinaryTreeNode node;
   BTQueueNode next;
   /**
     * Constructs a new BTQueueNode with the specified binary tree node and next node reference.
     * @param n The binary tree node.
     * @param nxt The next node in the queue.
     */
   public BTQueueNode ( BinaryTreeNode n, BTQueueNode nxt )
   {
      node = n;
      next = nxt;
   }
}
