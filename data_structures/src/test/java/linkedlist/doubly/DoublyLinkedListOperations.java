package linkedlist.doubly;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoublyLinkedListOperations {

    @Test
    public void testSinglyLinkedListInsertAtHead() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly();
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(0);

        doublyLinkedList.insertAtHead(1);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(1);

        doublyLinkedList.insertAtHead(5);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(5, 1);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(2);

        doublyLinkedList.insertAtHead(7);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(7, 5, 1);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(3);
    }

    @Test
    public void testSinglyLinkedListInsertAtEnd() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();

        doublyLinkedList.insertAtEnd(1);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(1);

        doublyLinkedList.insertAtEnd(5);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 5);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(2);

        doublyLinkedList.insertAtEnd(7);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(3);
    }

    @Test
    public void testSinglyLinkedListInsertAfter() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.insertAtEnd(1);
        doublyLinkedList.insertAtEnd(3);
        doublyLinkedList.insertAtEnd(5);
        doublyLinkedList.insertAtEnd(7);

        doublyLinkedList.insertAfter(2, 0);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 3, 5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(4);

        doublyLinkedList.insertAfter(2, 1);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 2, 3, 5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(5);

        doublyLinkedList.insertAfter(8, 7);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 2, 3, 5, 7, 8);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(6);

        doublyLinkedList.insertAfter(6, 5);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 2, 3, 5, 6, 7, 8);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(7);
    }

    @Test
    public void testSinglyLinkedListSearchNode() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.insertAtEnd(1);
        doublyLinkedList.insertAtEnd(3);
        doublyLinkedList.insertAtEnd(5);
        doublyLinkedList.insertAtEnd(7);


        Assertions.assertThat(doublyLinkedList.searchNode(2)).isFalse();
        Assertions.assertThat(doublyLinkedList.searchNode(1)).isTrue();
        Assertions.assertThat(doublyLinkedList.searchNode(3)).isTrue();
        Assertions.assertThat(doublyLinkedList.searchNode(5)).isTrue();
        Assertions.assertThat(doublyLinkedList.searchNode(7)).isTrue();
    }

    @Test
    public void testSinglyLinkedListDeleteByValue() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.insertAtEnd(1);
        doublyLinkedList.insertAtEnd(3);
        doublyLinkedList.insertAtEnd(5);
        doublyLinkedList.insertAtEnd(7);

        doublyLinkedList.deleteByValue(3);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(3);

        doublyLinkedList.deleteByValue(4);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(3);

        doublyLinkedList.deleteByValue(1);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(2);

        doublyLinkedList.deleteByValue(7);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(5);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(1);

        doublyLinkedList.deleteByValue(5);
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly();
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(0);
    }

    @Test
    public void testSinglyLinkedListDeleteAtHead() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.insertAtEnd(1);
        doublyLinkedList.insertAtEnd(3);
        doublyLinkedList.insertAtEnd(5);
        doublyLinkedList.insertAtEnd(7);

        doublyLinkedList.deleteAtHead();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(3, 5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(3);

        doublyLinkedList.deleteAtHead();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(5, 7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(2);

        doublyLinkedList.deleteAtHead();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(7);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(1);

        doublyLinkedList.deleteAtHead();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly();
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(0);

        doublyLinkedList.deleteAtHead();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly();
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(0);
    }

    @Test
    public void testSinglyLinkedListDeleteAtTail() {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.insertAtEnd(1);
        doublyLinkedList.insertAtEnd(3);
        doublyLinkedList.insertAtEnd(5);
        doublyLinkedList.insertAtEnd(7);

        doublyLinkedList.deleteAtTail();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 3, 5);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(3);

        doublyLinkedList.deleteAtTail();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1, 3);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(2);

        doublyLinkedList.deleteAtTail();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly(1);
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(1);

        doublyLinkedList.deleteAtTail();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly();
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(0);

        doublyLinkedList.deleteAtTail();
        Assertions.assertThat(doublyLinkedList.toList()).containsExactly();
        Assertions.assertThat(doublyLinkedList.size()).isEqualTo(0);
    }
}
