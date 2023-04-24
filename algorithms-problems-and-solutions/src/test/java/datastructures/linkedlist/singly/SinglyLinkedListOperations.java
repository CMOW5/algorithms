package datastructures.linkedlist.singly;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListOperations {

    @Test
    public void testSinglyLinkedListInsertAtHead() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly();
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(0);

        singlyLinkedList.insertAtHead(1);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(1);

        singlyLinkedList.insertAtHead(5);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(5, 1);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(2);

        singlyLinkedList.insertAtHead(7);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(7, 5, 1);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(3);
    }

    @Test
    public void testSinglyLinkedListInsertAtEnd() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        singlyLinkedList.insertAtEnd(1);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(1);

        singlyLinkedList.insertAtEnd(5);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 5);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(2);

        singlyLinkedList.insertAtEnd(7);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 5, 7);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(3);
    }

    @Test
    public void testSinglyLinkedListInsertAfter() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.insertAtEnd(1);
        singlyLinkedList.insertAtEnd(3);
        singlyLinkedList.insertAtEnd(5);
        singlyLinkedList.insertAtEnd(7);

        singlyLinkedList.insertAfter(2, 0);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 3, 5, 7);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(4);

        singlyLinkedList.insertAfter(2, 1);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 2, 3, 5, 7);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(5);

        singlyLinkedList.insertAfter(8, 7);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 2, 3, 5, 7, 8);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(6);

        singlyLinkedList.insertAfter(6, 5);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 2, 3, 5, 6, 7, 8);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(7);
    }

    @Test
    public void testSinglyLinkedListSearchNode() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.insertAtEnd(1);
        singlyLinkedList.insertAtEnd(3);
        singlyLinkedList.insertAtEnd(5);
        singlyLinkedList.insertAtEnd(7);


        Assertions.assertThat(singlyLinkedList.searchNode(2)).isFalse();
        Assertions.assertThat(singlyLinkedList.searchNode(1)).isTrue();
        Assertions.assertThat(singlyLinkedList.searchNode(3)).isTrue();
        Assertions.assertThat(singlyLinkedList.searchNode(5)).isTrue();
        Assertions.assertThat(singlyLinkedList.searchNode(7)).isTrue();
    }

    @Test
    public void testSinglyLinkedListDeleteByValue() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.insertAtEnd(1);
        singlyLinkedList.insertAtEnd(3);
        singlyLinkedList.insertAtEnd(5);
        singlyLinkedList.insertAtEnd(7);

        singlyLinkedList.deleteByValue(3);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 5, 7);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(3);

        singlyLinkedList.deleteByValue(4);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(1, 5, 7);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(3);

        singlyLinkedList.deleteByValue(1);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(5, 7);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(2);

        singlyLinkedList.deleteByValue(7);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly(5);
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(1);

        singlyLinkedList.deleteByValue(5);
        Assertions.assertThat(singlyLinkedList.toList()).containsExactly();
        Assertions.assertThat(singlyLinkedList.size()).isEqualTo(0);
    }

}
