package impl;

import java.util.Comparator;

import common.InvalidIndexException;
import common.ListNode;
import interfaces.IListManipulator;
import interfaces.IOperator;
import interfaces.ITransformation;

/**
 * This class represents the recursive implementation of the IListManipulator interface.
 *
 */
public class RecursiveListManipulator implements IListManipulator {

    @Override
    public int size(ListNode head) {
        if (head == null) {
            return 0;
        }
        if (head.next == null) {
            return 1;
        } else {
            return size(head.next) + 1;
        }

    }

    @Override
    public boolean contains(ListNode head, Object element) {
        if (head == null) {
            return false;
        }
        if (head.element != element && head.next != null) {
            contains(head.next, element);
        } else if (head.element == element) {
            return true;
        }
        return false;
    }

    @Override
    public int count(ListNode head, Object element) {
        if (head == null) {
            return 0;
        }
        if (head.element.equals(element)) {
            return count(head.next, element) + 1;
        } else {
            return count(head.next, element);
        }
    }

    @Override
    public String convertToString(ListNode head) {
        String list = "";
        if (head == null) {
            return list;
        }
        if (head.next == null) {
            return head.element.toString();
        } else {
            return head.element.toString() + "," + convertToString(head.next);
        }
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (head == null || n < 0) {
            throw new InvalidIndexException();
        } else if (head.next == null && n > 0) {
            throw new InvalidIndexException();
        }
        if (n == 0) {
            return head.element;
        } else {
            return getFromFront(head.next, n -1);
        }

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
    }

    @Override
    public ListNode deepCopy(ListNode head) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean containsDuplicates(ListNode head) {
        // TODO Auto-generated method stub
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
