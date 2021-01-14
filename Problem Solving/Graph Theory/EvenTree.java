import java.util.Scanner;

public class Solution {
  public static void main(String[] a) {
    Scanner s = new Scanner(System.in);
    int vertices = s.nextInt();
    int edges = s.nextInt();
    Nodes[] nodes = new Nodes[vertices];
    while(vertices-- > 0) nodes[vertices] = new Node();
    while(edges-- > 0) nodes[s.nextInt() - 1].setParent(nodes[s.nextInt() - 1]);
    int disconnects = -1;
    for(Node n : nodes) {
      if(n.descendantCount%2 == 0) disconnects++;
    }
    System.out.println(disconnects);
  }
}

class Node {
  private Node parent;
  int descendantCount = 0;
  
  void setParent(Node n) {
    parent = n;
    Node ancestor = parent;
    while(ancestor != null) {
      ancestor.descendantCount++;
      ancestor = ancestor.getParent();
    }
  }
  
  Node getParent() {
    return parent;
  }
}
