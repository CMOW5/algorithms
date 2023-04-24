package datastructures.linkedlist.doubly;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<T> {

    private Node headNode;
    private Node tailNode;
    private int size;

    public boolean isEmpty() {
        return headNode == null && tailNode == null;
    }

    public void insertAtHead(T data) {
        if (isEmpty()) {
            headNode = new Node(data, null, null);
            tailNode = headNode;
        } else {
            Node newHeadNode = new Node(data, null, headNode);
            headNode.prevNode = newHeadNode;
            headNode = newHeadNode;
        }
        size++;
    }

    public void insertAtEnd(T data) {
        if (isEmpty()) {
            insertAtHead(data);
            return;
        }

        Node newLastNode = new Node(data, tailNode, null);
        tailNode.setNextNode(newLastNode);
        tailNode = newLastNode;
        size++;
    }

    //inserts data after the given prev data node
    public void insertAfter(T data, T previous) {
        Node currentNode = headNode;

        //traverse the list until node having data equal to previous is found
        while (currentNode != null && !(currentNode.getData().equals(previous))) {
            currentNode = currentNode.getNextNode();
        }

        if (currentNode != null) {
            Node newNode = new Node(data, currentNode, currentNode.nextNode);
            currentNode.nextNode = newNode;
            if (newNode.nextNode != null) {
                newNode.nextNode.prevNode = newNode;
            }
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

        if (headNode != null) {
            headNode.prevNode = null;
        } else {
            tailNode = null;
        }

        size--;
    }

    //Deletes data from the tail of list
    public void deleteAtTail() {
        //if list is empty then simply return
        if (isEmpty()) {
            return;
        }

        //make the nextNode of the headNode equal to new headNode
        tailNode = tailNode.prevNode;

        if (tailNode != null) {
            tailNode.nextNode = null;
        } else {
            headNode = null;
        }

        size--;
    }

    public void deleteByValue(T data) {
        if (isEmpty()) {
            return;
        }

        Node currentNode = headNode;

        // if data is in the head, then just update it
        if (currentNode.data.equals(data)) {
            deleteAtHead();
            return;
        }

        // search in the list until the data is found
        while (currentNode != null) {
            if (data.equals(currentNode.data)) {
                currentNode.prevNode.nextNode = currentNode.nextNode;

                if (currentNode.nextNode != null) {
                    currentNode.nextNode.prevNode = currentNode.prevNode;
                }
                size--;
                return;
            }
            currentNode = currentNode.nextNode;
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
        private Node prevNode;
        private Node nextNode;

        public Node(T data, Node prevNode, Node nextNode) {
            this.data = data;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }
    }
}
