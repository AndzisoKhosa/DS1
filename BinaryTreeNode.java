// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

/**
 * BinaryTreeNode represents a node in a binary tree.
 * It stores a data element and references to its left and right child nodes.
 */
public class BinaryTreeNode
{
   String data;
   BinaryTreeNode left;
   BinaryTreeNode right;
   
   /**
     * Constructs a new BinaryTreeNode with the specified data and child nodes.
     * @param d The data element of the node.
     * @param l The left child node.
     * @param r The right child node.
     */
   public BinaryTreeNode(String d, BinaryTreeNode l, BinaryTreeNode r )
   {
      data = d;
      left = l;
      right = r;
   }
   // Additional methods for getting left and right child nodes, and toString method
   BinaryTreeNode getLeft () { return left; }
   BinaryTreeNode getRight () { return right; }
   public String toString(){return ""+data;}
}
