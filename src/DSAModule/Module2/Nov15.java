package DSAModule.Module2;

import java.util.Arrays;

public class Nov15 {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        // creating nodes
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        // linking nodes
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
        // 1 = head
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Nov15 clazz = new Nov15();


        // Divide list in 2 halves
        //clazz.divideList(node1);
        System.out.println();

        // Find Middle element
        clazz.findMiddleElementInLL(node1);
        System.out.println();

        // Sort Linked List -> converted into Array
        int[] ints = clazz.mergeSortLinkedList(node1);
        System.out.println(Arrays.toString(ints));

        // Sort Linked List -> directly on LL
        ListNode listNode = clazz.sortLL(node1);
        printLinkedList(listNode);

        // Merge two LL
        ListNode listNode1 = clazz.mergeTwoLists(node1, node4);
        printLinkedList(listNode1);

        // Check palindrome in LL
        boolean palindromeLL = clazz.isPalindromeLL(node1);
        System.out.println(palindromeLL);

        // Find longest Palindrome
        String len = findLongestPalindrome("abbabb");
        System.out.println(len);

        // Find longest Palindrome length in LL
        int i = clazz.longestPalindromeLengthLL(node1);
        System.out.println(i);


    }

    private int longestPalindromeLengthLL(ListNode head) {

        if (head == null) {
            return 0;
        }

        int maxLength = 1;

        ListNode current = head;
        ListNode prev = null;

        while (current != null) {

            ListNode nextNode = current.next;

            int length = 2 * countMatchingNodes(prev, current);
            maxLength = Math.max(maxLength, length);

            length = 1 + 2 * countMatchingNodes(prev, nextNode);
            maxLength = Math.max(maxLength, length);

            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return maxLength;
    }

    private static int countMatchingNodes(ListNode left, ListNode right) {
        int count = 0;
        while (left != null && right != null) {
            if (left.val == right.val) {
                count++;
            }
            else {
                break;
            }
            left = left.next;
            right = right.next;
        }
        return count;
    }

    private static String findLongestPalindrome(String str) {
        char[] charArray = str.toCharArray();
        int maxLength = 0;
        int startIndex = 0;

        for (int i = 0; i < charArray.length; i++) {
            int[] odd = expand(charArray, i, i);
            int[] even = expand(charArray, i, i + 1);

            if(odd[2] > maxLength) {
                maxLength = odd[2];
                startIndex = odd[0] + 1;
            }

            if (even[2] > maxLength) {
                maxLength = even[2];
                startIndex = even[0] + 1;
            }
        }
        return str.substring(startIndex, startIndex + maxLength);
    }

    private static int[] expand(char[] charArray, int left, int right) {
        while (left >= 0 && right < charArray.length
                && charArray[left] == charArray[right]) {
            left--;
            right++;
        }
        int length = right - left - 1;
        return new int[]{left, right, length};
    }

    private boolean isPalindromeLL(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        // divide in halves
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half
        ListNode previousNode = null;
        ListNode currentNode = slow;

        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }


        // compare
        ListNode left = head;
        ListNode right = previousNode;

        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        tail.next = (left != null) ? left : right;

        return dummy.next;
    }

    private ListNode sortLL(ListNode head) {
        // find middle
        ListNode current = head;
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // sort both halves
        ListNode left = sortLL(head);
        ListNode right = sortLL(slow);

        // merge sorted halves
        return mergeSortedHalvesInLL(left, right);
    }

    private ListNode mergeSortedHalvesInLL(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = (left != null) ? left : right;

        return dummy.next;
    }

    private int[] mergeSortLinkedList(ListNode head) {
        int size = getSizeOfLL(head);
        int[] copyArray = copyArrayLL(size, head);
        sortArray(copyArray, 0, size - 1);
        return copyArray;
    }

    private void sortArray(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        sortArray(arr, left, mid);
        sortArray(arr, mid + 1, right);

        mergeSortedArraysLL(arr, left, mid, right);
    }

    private void mergeSortedArraysLL(int[] A, int left, int mid, int right) {
        int[] ans = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (A[i] <= A[j]) {
                ans[k] = A[i];
                i++;
            } else {
                ans[k] = A[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            ans[k++] = A[i++];
        }

        while (j <= right) {
            ans[k++] = A[j++];
        }

        for (int ele = 0; ele < ans.length; ele++) {
            A[left + ele] = ans[ele];
        }
    }

    private int[] copyArrayLL(int size, ListNode head) {
        int[] arr = new int[size];
        ListNode current = head;
        int i = 0;

        while (current != null) {
            arr[i] = current.val;
            current = current.next;
            i++;
        }
        return arr;
    }

    private int getSizeOfLL(ListNode head) {
        int size = 0;
        ListNode current = head;

        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }

    private void findMiddleElementInLL(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println(slow.val);
    }

    private void divideList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        printLinkedList(head);
        printLinkedList(slow);
    }

    private static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
