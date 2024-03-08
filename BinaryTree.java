// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

/**
 * BinaryTree represents a binary tree data structure.
 * It provides methods for getting the height, size, and traversing the tree in pre-order, post-order, in-order, and level-order.
 */
public class BinaryTree
{
   BinaryTreeNode root;
   
   public BinaryTree ()
   {
      root = null;
   }
   
   /**
     * Returns the height of the binary tree.
     * @return The height of the binary tree.
     */
   public int getHeight ()
   {
      return getHeight (root);
   }   
   public int getHeight ( BinaryTreeNode node )
   {
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   // Additional methods for getting size, traversing tree, and level-order traversal
   public int getSize ()
   {
      return getSize (root);
   }   
   public int getSize ( BinaryTreeNode node )
   {
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   public String getLevelOrder ()
   {
      if (root == null)
         return "";
      BTQueue q = new BTQueue ();
      q.enQueue (root);
      BinaryTreeNode node;
      String end = "";
      while ((node = q.getNext ()) != null)
      {
         String result = node.data+"\n";
         end+=result;
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
      return end;
   }

   public void visit ( BinaryTreeNode node )
   {
      System.out.println (node.data);
   }
   
   public void preOrder ()
   {
      preOrder (root);
   }
   public void preOrder ( BinaryTreeNode node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }

   public void postOrder ()
   {
      postOrder (root);
   }
   public void postOrder ( BinaryTreeNode node )
   {
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }   
   }

   public void inOrder ()
   {
      inOrder (root);
   }
   public void inOrder ( BinaryTreeNode node )
   {
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }   
   }

   public void levelOrder ()
   {
      if (root == null)
         return;
      BTQueue q = new BTQueue ();
      q.enQueue (root);
      BinaryTreeNode node;
      while ((node = q.getNext ()) != null)
      {
         visit (node);
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
   }
}
