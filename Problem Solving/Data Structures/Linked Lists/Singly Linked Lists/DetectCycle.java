/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as: 
    class Node {
        int data;
        Node next;
    }
*/

boolean hasCycle(Node head) {
    
    if(head!=null && head.next!=null) {
        Node s = head;
        Node f = head.next;
        while(f != null && f.next != null) {
            if(s==f) return true;
            s = s.next;
            f = (f.next).next;
        }
        return false;
    }
    else return false;
}
