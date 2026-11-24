package DSAModule.Module2;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Nov16 {

    // Double Linked List
    static class Node {
        int val;
        Node next;
        Node previous;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        // Creating double LL
        Node head = new Node(1);
        Node current = head;

        for (int i = 2; i <= 8; i++) {
            Node newNode = new Node(i);

            current.next = newNode;
            newNode.previous = current;

            current = newNode;
        }

        Node tail = current;


        printDoubleLLFromHead(head);
        printDoubleLLFromTail(tail);

        // Add new node to Dll
        Node newNode = new Node(100);
        Node node = addingNewNodeToDllToIndexK(head, newNode, 4);
        printDoubleLLFromHead(node);

        // Delete a given node at Index
        Node node1 = deleteNodeAtK(head, 4);
        printDoubleLLFromHead(node1);

        // Cycle detection
        boolean isCircular = isLLCycled(head);
        System.out.println(isCircular);
    }

    private Node getIntersectionNodeOpt(Node A, Node B) {

        Node currentA = A;
        Node currentB = B;

        while (currentA != currentB) {

            currentA = (currentA == null) ? B : currentA.next;
            currentB = (currentB == null) ? A : currentB.next;
        }

        return currentA;
    }

    private Node getIntersectionNode(Node A, Node B) {

        if (A == null || B == null) {
            return null;
        }

        Node current = B;

        while (current.next != null) {
            current = current.next;
        }

        current.next = A;

        Node fast = A;
        Node slow = A;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            current.next = null;
            return null;
        }

        slow = A;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        current.next = null;

        return slow;
    }

    private static boolean isLLCycled(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    private static Node deleteNodeAtK(Node head, int k) {
        // 1 2 3 4 100 5 6 7 8
        // 1 2 3 4 5 6 7 8
        if (head == null || k < 0) {
            return head;
        }

        if (k == 0) {
            Node nextNode = head.next;
            head.next = null;
            return nextNode;
        }

        Node current = head;
        int i = 0;

        while (i < k && current != null) {
            current = current.next;
            i++;
        }

        if (current == null) {
            return head;
        }

        Node previousNode = current.previous;
        Node nextNode = current.next;

        previousNode.next = nextNode;

        if (nextNode != null) {
            nextNode.previous = previousNode;
        }

        // to pick for garbage collector
        current.next = null;
        current.previous = null;


        return head;
    }

    // 1 2 3 4 100 5 6 7 8
    private static Node addingNewNodeToDllToIndexK(Node head, Node newNode, int k) {

        if (k < 0) {
            return head;
        }

        if (k == 0) {
            newNode.next = head;
            head.previous = newNode;
            return newNode;
        }

        Node current = head;
        int i = 0;

        while (i < k - 1 && current != null) {
            current = current.next;
            i++;
        }

        if (current == null) {
            return head;
        }

        Node nextNode = current.next;

        current.next = newNode;
        newNode.previous = current;
        newNode.next = nextNode;
        nextNode.previous = newNode;

        return head;
    }

    private static void printDoubleLLFromTail(Node tail) {
        Node current = tail;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.previous;
        }
        System.out.println();
    }

    private static void printDoubleLLFromHead(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }


}

// LRU Cache
// Least Recently Used Item is removed

class LRUCache {

    static class ListNode {

        String key;
        LocalDateTime value;
        ListNode previous;
        ListNode next;

        public ListNode(String key, LocalDateTime value) {
            this.key = key;
            this.value = value;
        }

    }

    ListNode head, tail;
    HashMap<String, ListNode> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new ListNode("dummyHead", LocalDateTime.MIN);
        tail = new ListNode("dummyTail", LocalDateTime.MIN);

        head.next = tail;
        tail.previous = head;
    }

    public LocalDateTime get(String key) {
        if (!cache.containsKey(key)) {
            return LocalDateTime.MIN;
        }

        ListNode node = cache.get(key);
        removeNode(node);

        node.value = LocalDateTime.now();

        placeAtTail(node);
        return node.value;
    }

    public void put(String key) {
        if (capacity <= 0) {
            return;
        }

        if (cache.containsKey(key)) {
            ListNode node = cache.get(key);

            removeNode(node);
            node.value = LocalDateTime.now();
            placeAtTail(node);

            return;
        }

        if (cache.size() == capacity) {
            ListNode topNode = head.next;

            removeNode(topNode);
            cache.remove(topNode.key);
        }

        ListNode node = new ListNode(key, LocalDateTime.now());

        placeAtTail(node);
        cache.put(key, node);
    }

    // Helpers
    private void placeAtTail(ListNode node) {
        ListNode previousNode = tail.previous;
        previousNode.next = node;
        node.previous = previousNode;

        node.next = tail;
        tail.previous = node;
    }

    private void removeNode(ListNode node) {
        ListNode previousNode = node.previous;
        ListNode nextNode = node.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        node.next = null;
        node.previous = null;
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put("A");
        cache.put("B");
        cache.put("C");

        cache.get("A");

        cache.put("D");


    }


}


















