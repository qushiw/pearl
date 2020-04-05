package com.web.demo.dataStructure;

/**
 * @author jrqushiwen
 * @date 2020/3/30 9:46
 */
public class ReverseListByGroup {

    public static ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(0);
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead.next;
            newHead.next = head;
            head = next;
        }
        return newHead.next;

    }

    public static ListNode reverseGroupFromEnd(ListNode head, int k) {
        head =  reverseList(head);
        head =  reverseGroup(head, k);
        head =  reverseList(head);
        return head;
    }

    public static ListNode reverseGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++){
            temp = temp.next;
        }
        if (temp == null) {
            return head;
        }
        ListNode t2 = temp.next;
        temp.next = null;
        ListNode newHead = reverseList(head);
        ListNode newTemp = reverseGroup(t2, k);
        head.next = newTemp;
        return newHead;
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
////        ListNode node6 = new ListNode(6);
////        ListNode node7 = new ListNode(7);
////        ListNode node8 = new ListNode(8);
        node1.next = node2;
//        node2.pre = node1;
        node2.next = node3;
//        node3.pre = node2;
        node3.next = node4;
//        node4.pre = node3;
        node4.next = node5;
//        node5.pre = node4;

        ListNode listNode = reverseGroupFromEnd(node1, 3);
        System.out.println(listNode);
////        node5.next = node6;
////        node6.next = node7;
////        node7.next = node8;
//        ListNode newList = reverseGroup(node1,3);
//        System.err.println(newList);



    }


}
