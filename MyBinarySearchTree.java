//java -jar ../algtest.jar a1.props
import edu.gwu.algtest.*;
import java.lang.*;
import java.util.*;
import edu.gwu.util.*;

public class MyBinarySearchTree implements TreeSearchAlgorithm {
  TreeNode root;

  public static void main(String[] args) {
    MyBinarySearchTree tree = new MyBinarySearchTree();
    tree.insert(7, "Seven");
   tree.insert(5, "Five");
/*    tree.insert(11, "Eleven");
    tree.insert(3, "Three");
    tree.insert(6, "Six");
    tree.insert(9, "Nine");
    tree.insert(12, "Twelve");
    tree.insert(2, "Two");
    tree.insert(4, "Four");
    tree.insert(8, "Eight");
    tree.insert(10, "Ten");
*/
    printTreeInOrder(tree.getRoot());

    tree.delete(7);
    tree.delete(5);

    printTreeInOrder(tree.getRoot());

  }

  public static void printTreeInOrder(TreeNode root){
    Stack<TreeNode> nodeStack = new Stack<TreeNode>();
    TreeNode node = root;

    while(node != null || nodeStack.size()> 0){
      while(node != null){
        nodeStack.push(node);
        node = node.left;
      }
      node = nodeStack.pop();
      System.out.print(node.key + ", ");
      node = node.right;
    }
    System.out.println();
  }

  //Interface TreeSearchAlgorithm
  public TreeNode getRoot(){
    return root;
  }

  public TreeNode getNode(Comparable key){
    TreeNode node = this.getRoot();

    //1. While the node's children are not null
    while(node.left != null || node.right != null){
      //1.1 If the key matches with the node, return the pair
      if(key.equals(node.key)) return node;
      //1.2 If key is less than node.key, set node to its left child.
      else if(key.compareTo(node.key) == -1 ) node = node.left;
      //1.3 If key is greater than node.key, set node to its right child.
      else if(key.compareTo(node.key) == 1) node = node.right;
    }
    //2. If the desired node is a leaf, this line will return it.
    if(key.equals(node.key)) return node;

    //If there is no node with that key, returns null.
    return null;
  }

  //Interface OrderedSearchAlgorithm
  public Object insert(Comparable key, Object value) {
    TreeNode newNode = new TreeNode(key, value);
    //1. If root is null, set newNode as root and then return root.
    if(root == null){
      root = newNode;
      return null;
    }

    //2. Continue a while-loop until the newNode becomes a child
    TreeNode node = this.getRoot();
    while(true){

      //2.1 If newNode.key is equivalent to node.key
      if(newNode.key.equals(node.key)){
        //  2.1.1 Replace the old node with the new one and return the old value.
        Object oldValue = node.value;
        node.value = newNode.value;
        return oldValue;
      }
      //2.2 If newNode.key is less than node.key
      if(newNode.key.compareTo(node.key) == -1){
        //  2.2.1 If left child is null, set newNode as left child of node
        if(node.left == null){
          node.left = newNode;
          newNode.parent = node;
          return null;
        }
        //  2.2.2 Else Set node to its left child, continue to next iteration
        else {
          node = node.left;
        }
      }
      //2.3 Same process as step 3.1 except it is for right child
      if(newNode.key.compareTo(node.key) == 1){
        if(node.right == null){
          node.right = newNode;
          newNode.parent = node;
          return null;
        } else {
          node = node.right;
        }
      }
    }
  }

  public ComparableKeyValuePair search(Comparable key){
    TreeNode node = this.getRoot();

    //1. While the node's children are not null
    while(node.left != null || node.right != null){
      //1.1 If the key matches with the node, return the pair
      if(key.equals(node.key)) return new ComparableKeyValuePair(node.key, node.value);
      //1.2 If key is less than node.key, set node to its left child.
      //(int)key < (int)node.key
      else if(key.compareTo(node.key) == -1) node = node.left;
      //1.3 If key is greater than node.key, set node to its right child.
      //(int)key > (int)node.key
      else if(key.compareTo(node.key) == 1) node = node.right;
    }
    //2. If the desired node is a leaf, this line will return it.
    if(key.equals(node.key)) return new ComparableKeyValuePair(node.key, node.value);

    //If there is no node with that key, returns null.
    return null;
  }

  public Object delete(Comparable key){
    TreeNode node = getNode(key);
    if(node == null) return null;

    //Case 1: node is leaf
    if(node.left == null && node.right == null){
      if(node.equals(root)){
        root = null;
        return null;
      }
      if(node.parent.key.compareTo(node.key) == 1){
        node.parent.left = null;
      } else {
        node.parent.right = null;
      }
    }
    //Case 2: node has two children
    else if (node.left != null && node.right != null){
      TreeNode successor = getNode(successor(key));
      Comparable succKey = (Comparable)successor.key;
      Comparable succValue = (Comparable)successor.value;
      delete(successor(key));
      node.key = succKey;
      node.value = succValue;
    }
    //Case 3: node has one child
    else {
      TreeNode child;
      if(node.left != null) child = node.left;
      else child = node.right;

      if(!node.equals(root)){
        if(node.equals(node.parent.left)) node.parent.left = child;
        else node.parent.right = child;
      }
      else root = child;
    }
    return node.value;
  }

  public Comparable successor(Comparable key){
    TreeEnumeration e = (TreeEnumeration)getKeys();
    while (e.hasMoreElements()){
      if(key.equals(e.nextElement())){
        return (Comparable)e.nextElement();
      }
    }
    return null;
  }

  public Comparable predecessor(Comparable key){
    TreeEnumeration e = (TreeEnumeration)getKeys();
    Object prev = null;
    Object curr;
    while (e.hasMoreElements()){
      curr = e.nextElement();
      if(key.equals(curr)){
        return (Comparable)prev;
      }
      prev = curr;
    }
    return null;
  }

  public ComparableKeyValuePair minimum(){
    return null;
  }

  public ComparableKeyValuePair maximum(){
    return null;
  }

  //Interface SearchAlgorithm
  public void initialize(int maxSize){

  }

  public int getCurrentSize(){
    return 0;
  }

  public Enumeration getKeys(){

    TreeEnumeration e = new TreeEnumeration(this.getRoot());
    e.isKeyEnum = true;
    return e;
  }

  public Enumeration getValues(){
    TreeEnumeration e = new TreeEnumeration(this.getRoot());
    e.isKeyEnum = false;
    return e;

  }

  //Interface Algorithm
  public String getName(){
    return "BinarySearch - CappW";
  }

  public void setPropertyExtractor(int algID, PropertyExtractor prop){

  }
}
