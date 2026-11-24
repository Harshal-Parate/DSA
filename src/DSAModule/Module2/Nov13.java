package DSAModule.Module2;

import java.util.ArrayList;

public class Nov13 {

    static class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {

        // creating nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(3);
        Node node5 = new Node(3);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        // linking nodes
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
        // 1 = head
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;


        // Operations in linked list

        // 1. Access the Kth element
        accessKthElement(node1, 3);

        // 2. Search for value
        boolean isPresent = searchForValueX(node1, 4);
        System.out.println(isPresent);

        // 3. Insert Node at positon k
        Node newNode1 = new Node(100);
        Node nodeA = insertNodeAtPositionK(node1, newNode1, 3);
        printLinkedList(nodeA);
        System.out.println();

        // 4. Insert value at positon k
        Node nodeB = insertValueAtPositionK(node1, 60, 3);
        printLinkedList(nodeB);
        System.out.println();

        //5. Deletion at K th position
        deletionAtKithPosition(node1, 4);
        Node nodeC = deletionAtKithPosition(node1, 3);
        printLinkedList(nodeC);
        System.out.println();

        // 6. Delete all occurrences of the element
        Node nodeD = deleteNodeWithValue(node1, 3);
        printLinkedList(nodeD);
        System.out.println();

        // 7. Delete first occurrence of the element
        Node nodeE = deleteNodeWithValueAtFirst(node1, 2);
        printLinkedList(nodeE);
        System.out.println();

        // 8. Reverse linked list Brute Force
        Node nodeF = reverseLinkedListBruteForce(node1);
        printLinkedList(nodeF);
        System.out.println();

        // 8. Reverse linked list Brute Force
        Node nodeG = reverseLinkedListOpt(node1);
        printLinkedList(nodeG);
        System.out.println();

        // 9. Inserting dummies in side of each elements
        Node nodeH = insertingDummies(node1);
        printLinkedList(nodeH);
        System.out.println();

        // 10. Copy list with random pointer
        Node nodeI = copyListWithRandomPointer(node1);
        printLinkedList(nodeI);
        System.out.println();

    }

    private static Node insertingDummies(Node head) {
        Node current = head;

        while (current != null) {
            Node newNode = new Node(current.val);

            Node nextNode = current.next;
            current.next = newNode;
            newNode.next = nextNode;

            current = current.next.next;
        }
        return head;
    }

    private static Node copyListWithRandomPointer(Node head) {
        if (head == null) {
            return null;
        }

        // copy dummies inserted
        Node current = head;

        while (current != null) {
            Node newNode = new Node(current.val);

            newNode.next = current.next;
            current.next = newNode;

            current = newNode.next;
        }

        // Step 2: Assign random pointers to copied nodes
        current = head;

        while (current != null) {
            Node copyNode = current.next;
            copyNode.random = (current.random == null) ? null : current.random.next;
            current = copyNode.next;
        }

        Node temp = head;
        Node copyHead = temp.next;
        Node copyTail = copyHead;

        while (temp != null) {
            Node copyNode = temp.next;
            // origin list
            temp.next = copyNode.next.next;
            // new list
            copyTail.next = copyHead.next;
            // move forward
            copyTail = copyTail.next;
            temp = temp.next;
        }
        return copyHead;
    }

    private static Node createDeepCopyOfList(Node head) {
        Node current = head;
        Node dummy = new Node(-1);
        Node tail = dummy;

        while (current != null) {
            Node newNode = new Node(current.val);

            tail.next = newNode;
            tail = newNode;

            current = current.next;
        }
        return dummy.next;
    }

    private static Node reverseLinkedListOpt(Node head) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;

            previous = current;
            current = nextNode;
        }
        return previous;
    }

    private static Node reverseLinkedListBruteForce(Node head) {
        Node current = head;
        ArrayList<Integer> vals = new ArrayList<>();

        while (current != null) {
            vals.add(current.val);
            current = current.next;
        }


        Node dummy = new Node(-1);
        Node tempHead = dummy;

        for (int i = vals.size() - 1; i >= 0; i--) {
            tempHead.next = new Node(vals.get(i));
            tempHead = tempHead.next;
        }
        return dummy.next;
    }

    private static Node deleteNodeWithValueAtFirst(Node head, int k) {
        if (head == null) {
            return null;
        }

        if (head.val == k) {
            return head.next;
        }

        Node current = head;

        while (current != null && current.next != null) {
            if (current.next.val == k) {
                current.next = current.next.next;
                break;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    private static Node deleteNodeWithValue(Node head, int k) {
        if (head == null) {
            return null;
        }

        if (head.val == k) {
            return head.next;
        }

        Node current = head;

        while (current != null && current.next != null) {
            if (current.next.val == k) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;

    }

    private static Node deletionAtKithPosition(Node head, int k) {
        if (head == null || k < 0) {
            return head;
        }

        if (k == 0) {
            return head.next;
        }

        Node current = head;
        int i = 0;

        while (i < k - 1 && current != null) {
            current = current.next;
            i++;
        }

        if (current == null || current.next == null) {
            return head;
        }

        Node nextt = current.next.next;
        current.next = nextt;
        return head;
    }

    private static Node insertValueAtPositionK(Node head, int value, int k) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
        // 1 -> 2 -> 3 -> 100 -> 4 -> 5 -> 6 -> 7 -> null

        if (k < 0) {
            return head;
        }

        if (k == 0) {
            Node newNode = new Node(value);
            newNode.next = head;
            return newNode;
        }

        Node current = head;
        int i = 0;

        while (i < k - 1 && current != null) {
            current = current.next;
            i++;
        }

        // k is greater than length of list
        if (current == null) {
            return head;
        }

        Node nextt = current.next; // 4
        Node newNode = new Node(value); // creating new node
        current.next = newNode; // attaching 3 with 60
        newNode.next = nextt; // attaching 60 with 4


        return head;
    }

    private static Node insertNodeAtPositionK(Node head, Node newNode, int k) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
        // 1 -> 2 -> 3 -> 100 -> 4 -> 5 -> 6 -> 7 -> null

        if (k < 0) {
            return head;
        }

        // 1
        // 100 -> 1
        if (k == 0) {
            newNode.next = head;
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

        newNode.next = current.next;
        current.next = newNode;

        return head;
    }

    private static boolean searchForValueX(Node head, int k) {
        Node current = head;

        while (current != null) {
            if (current.val == k) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private static void accessKthElement(Node head, int k) {
        Node current = head;
        int count = 0;

        while (count < k && current != null) {
            count++;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Invalid position");
            return;
        }

        System.out.println(current.val);
    }

    private static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
