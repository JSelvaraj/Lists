package impl;

import java.util.Comparator;
import java.util.List;

import common.InvalidIndexException;
import common.ListNode;
import interfaces.IListManipulator;
import interfaces.IOperator;
import interfaces.ITransformation;

/**
 * This class represents the iterative implementation of the IListManipulator interface.
 *
 */
public class IterativeListManipulator implements IListManipulator {

    @Override
    public int size(ListNode head) {
        int size = 0;
        if (head == null) {
            return size;
        }
        ListNode currentNode = head.next;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    @Override
    public boolean contains(ListNode head, Object element) {
        if (head.element == element) {
            return true;
        } else {
            ListNode currentNode = head.next;
            while (currentNode != null) {
                if (currentNode.element == element) {
                    return true;
                } else {
                    currentNode = currentNode.next;
                }
            }
        }

        return false;
    }

    @Override
    public int count(ListNode head, Object element) {
        int count = 0;
        while (head != null) {
            if (head.element == element) {
                count++;
            }
            head = head.next;
        }
        return count;
    }

    @Override
    public String convertToString(ListNode head) {
        String list = "";
        if (head != null) {
            list = list + head.element.toString();
        }
        ListNode currentNode = head.next;
        while (currentNode != null) {
            list = list + ", " + currentNode.element.toString();
            currentNode = currentNode.next;
        }
        return list;
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (n < 0) {
            throw new InvalidIndexException();
        }
        if (n == 0) {
            return head.element;
        }
        ListNode currentNode = head.next;
        n--;
        while (n > 0) {
            currentNode = currentNode.next;
            n--;
            if (currentNode == null) {
                throw new InvalidIndexException();
            }
        }
        return currentNode.element;
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
        int position = (size(head) - 1) - n;
        if (n < 0 || position < 0) {
            throw new InvalidIndexException();
        }
        return getFromFront(head, position);
    }

    @Override
    public boolean deepEquals(ListNode head1, ListNode head2) {
        ListNode currentNode1, currentNode2;
        if (head1.element != head2.element) {
            return false;
        }
        currentNode1 = head1.next;
        currentNode2 = head2.next;
        while (currentNode1 != null || currentNode2 != null ) {
            if (currentNode1.element != currentNode2.element) {
                return false;
            }
            currentNode1 = currentNode1.next;
            currentNode2 = currentNode2.next;
        }
        return true;
    }

    @Override
    public ListNode deepCopy(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode currentNode = head.next;
        ListNode newNode;
        ListNode copy = new ListNode(head.element);
        while (currentNode != null) {
            newNode = new ListNode(currentNode.element);
            copy.next = newNode;
            currentNode = currentNode.next;
        }
        return copy;
    }

    /**
     * There are n(n-1)/2 comparisons.)
     * @param head the head of the list
     * @return
     */
    @Override
    public boolean containsDuplicates(ListNode head) {
        ListNode currentNode = head.next;
        if (contains(currentNode, head.element)){
            return true;
        }
        ListNode next = head.next.next;
        for (int i = 0; i < size(head); i++) {
            if (contains(next, currentNode.element)) {
                return true;
            }
            currentNode = currentNode.next;
            next = next.next;
        }
        return false;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode flatten(ListNode head) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isCircular(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsCycles(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ListNode sort(ListNode head, Comparator comparator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode map(ListNode head, ITransformation transformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object reduce(ListNode head, IOperator operator, Object initial) {
        // TODO Auto-generated method stub
        return null;
    }

}
