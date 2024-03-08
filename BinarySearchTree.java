// Hussein's Binary Search Tree
// 27 March 2017
// Hussein Suleman

/**
 * BinarySearchTree represents a binary search tree data structure.
 * It extends the BinaryTree class and provides methods for insertion, deletion, and searching.
 */
public class BinarySearchTree extends BinaryTree
{
   /**
     * Inserts a new data element into the binary search tree.
     * @param d The data element to insert.
     */
   public void insert( String d )
   {
      if (root == null)
         root = new BinaryTreeNode(d, null, null);
      else
         insert (d, root);
   }
   /**
     * Inserts a new data element into the binary search tree starting from the specified node.
     * @param d The data element to insert.
     * @param node The starting node for insertion.
     */
   public void insert( String d, BinaryTreeNode node )
   {
      if (d.compareTo(node.data) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode(d, null, null);
         else
            insert(d, node.left);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode(d, null, null);
         else
            insert (d, node.right);
      }
   }
   /**
    * Searches for a data element in the binary search tree based on starting keywords.
    * This method returns the node containing the data element if found, otherwise null.
    * @param d The starting keywords to search for.
    * @return The node containing the data element if found, otherwise null.
    */
   public BinaryTreeNode find( String d )
   {
      if (root == null)
         return null;
      else
         return find(d, root);
   }
   public BinaryTreeNode find( String d, BinaryTreeNode node )
   {
      if (node.data.startsWith(d))
         return node;
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
   }
   
   // Additional methods for deletion, finding minimum node, and removing minimum node
   public void delete ( String d )
   {
      root = delete (d, root);
   }   
   public BinaryTreeNode delete ( String d, BinaryTreeNode node )
   {
      if (node == null) return null;
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else if (node.left != null && node.right != null )
      {
         node.data = findMin (node.right).data;
         node.right = removeMin (node.right);
      }
      else
         if (node.left != null)
            node = node.left;
         else
            node = node.right;
      return node;
   }
   
   public BinaryTreeNode findMin ( BinaryTreeNode node )
   {
      if (node != null)
         while (node.left != null)
            node = node.left;
      return node;
   }

   public BinaryTreeNode removeMin ( BinaryTreeNode node )
   {
      if (node == null)
         return null;
      else if (node.left != null)
      {
         node.left = removeMin (node.left);
         return node;
      }
      else
         return node.right;
   }
   
}
