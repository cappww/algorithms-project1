import edu.gwu.algtest.*;
import java.lang.*;
import java.util.*;
import edu.gwu.util.*;

public class TreeEnumeration implements Enumeration {
  boolean isKeyEnum;
  LinkedList<TreeNode> nodeList;
  int index;

  public static void main(String[] args) {
    MyBinarySearchTree tree = new MyBinarySearchTree();
    tree.insert(7, "Seven");
    tree.insert(5, "Five");
    tree.insert(9, "Nine");
    tree.insert(3, "Three");
    tree.insert(6, "Six");
    tree.insert(8, "Eight");
    tree.insert(10, "Ten");
    tree.insert(2, "Two");
    tree.insert(4, "Four");

    TreeEnumeration e = new TreeEnumeration(tree.root);

    while (e.hasMoreElements()){
      System.out.println(e.nextElement());
    }
  }

  public TreeEnumeration(TreeNode root){
    index = 0;
    isKeyEnum = false;
    nodeList = new LinkedList<TreeNode>();
    Stack<TreeNode> nodeStack = new Stack<TreeNode>();
    TreeNode node = root;

    while(node != null || nodeStack.size()> 0){
      while(node != null){
        nodeStack.push(node);
        node = node.left;
      }
      node = nodeStack.pop();
      nodeList.add(node);
      node = node.right;
    }
  }

  public boolean hasMoreElements(){
    if(index < nodeList.size()){
      return true;
    } else {
      return false;
    }
  }

  public Object nextElement(){
    TreeNode node = nodeList.get(index);
    index++;
    if(isKeyEnum){
      return node.key;
    } else {
      return node.value;
    }

  }
}
