import edu.gwu.algtest.*;
import java.lang.*;
import java.util.*;
import edu.gwu.util.*;

public class MyRLTree implements TreeSearchAlgorithm {
  private TreeNode root;

  public static void main(String[] args) {

  }

  //Interface TreeSearchAlgorithm
  public TreeNode getRoot(){
    return root;
  }

  //Interface OrderedSearchAlgorithm
  public Object insert(Comparable key, Object value) {
    TreeNode newNode = new TreeNode(key, value);
    //1. If root is null, set newNode as root and then return root.
    if(root == null){
      root = newNode;
      return root.value;
    }

    TreeNode node = this.getRoot();
    //2. If newNode.key is equivalent to node.key
    if(newNode.key.equals(node.key)){
      //  2.1 print "there is already a node with this key" and return node.
      System.out.println("There is already a node with this key");
      return node.value;
    }

    //3. Continue a while-loop until the newNode becomes a child
    while(true){
      //3.1 If newNode.key is less than node.key
      //(int)newNode.key < (int)node.key
      if(newNode.key.compareTo(node.key) == -1){
        //  3.1.1 If left child is null, set newNode as left child of node
        if(node.left == null){
          node.left = newNode;
          newNode.parent = node;
          return newNode.value;
        }
        //  3.1.2 Else Set node to its left child, continue to next iteration
        else {
          node = node.left;
        }
      }
      //3.2 Same process as step 3.1 except it is for right child
      //(int)newNode.key > (int)node.key
      if(newNode.key.compareTo(node.key) == 1){
        if(node.right == null){
          node.right = newNode;
          newNode.parent = node;
          return newNode.value;
        } else {
          node = node.right;
        }
      }
    }
    //return null;
  }

  public void rotateTree(){

  }

  public ComparableKeyValuePair search(Comparable key){
    return null;
  }

  public Object delete(Comparable key){
    //Do not need to implement.
    return null;
  }

  public Comparable successor(Comparable key){
    //Do not need to implment.
    return null;
  }

  public Comparable predecessor(Comparable key){
    //do not need to implment.
    return null;
  }

  public ComparableKeyValuePair minimum(){
    return null;
  }

  public ComparableKeyValuePair maximum(){
    return null;
  }

  //Interface SearchAlgorithm
  public Enumeration getKeys(){
    Enumeration e = new Enumeration();
    return null;
  }

  public Enumeration getValues(){
    return null;
  }

  public void initialize(int maxSize){

  }

  public int getCurrentSize(){
    return 0;
  }

  //Interface Algorithm
  public String getName(){
    return "MyRLTree - CappW";
  }

  public void setPropertyExtractor(int algID, PropertyExtractor prop){

  }
}
