package linkedlist.singly;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList<T> {

    private Node headNode;
    private int size;

    public boolean isEmpty() {
        return headNode == null;
    }

    public void insertAtHead(T data) {
        headNode = new Node(data, headNode);
        size++;
    }

    public void insertAtEnd(T data) {
        if (isEmpty()) {
            insertAtHead(data);
            return;
        }

        Node node = headNode;

        while (node.getNextNode() != null) {
            node = node.getNextNode();
        }

        node.setNextNode(new Node(data, null));
        size++;
    }

    //inserts data after the given prev data node
    public void insertAfter(T data, T previous) {
        Node node = headNode;

        //traverse the list until node having data equal to previous is found
        while (node != null && !(node.getData().equals(previous))) {
            node = node.getNextNode();
        }

        if (node != null) {
            node.setNextNode(new Node(data, node.getNextNode()));
            size++;
        }
    }

    // It returns true or false to show if a certain value does or does not exist in the list.
    boolean searchNode(T data) {
        Node node = headNode;

        //traverse the list until node having data equal to previous is found
        while (node != null && !(node.getData().equals(data))) {
            node = node.getNextNode();
        }

        return node != null;
    }

    //Deletes data from the head of list
    public void deleteAtHead() {
        //if list is empty then simply return
        if (isEmpty()) {
            return;
        }

        //make the nextNode of the headNode equal to new headNode
        headNode = headNode.nextNode;
        size--;
    }

    public void deleteByValue(T data) {
        if (isEmpty()) {
            return;
        }

        Node node = headNode;

        // if data is in the head, then just update it
        if (headNode.data.equals(data)) {
            deleteAtHead();
            return;
        }

        // search in the list until the data is found
        while (node.nextNode != null) {
            if (node.nextNode.data.equals(data)) {
                node.nextNode = node.nextNode.nextNode;
                size--;
                return;
            }
            node = node.nextNode;
        }
    }


    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node node = headNode;

        while (node != null) {
            list.add(node.getData());
            node = node.getNextNode();
        }

        return list;
    }

    public class Node {
        private T data;
        private Node nextNode;

        public Node(T data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public T getData() {
            return data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
