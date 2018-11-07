package impl;

import java.io.UncheckedIOException;
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
        size++;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    @Override
    public boolean contains(ListNode head, Object element) {
        if (head != null && head.element == element) {
            return true;
        } else {
            ListNode currentNode;
            if (head != null) {
                currentNode = head.next;
                while (currentNode != null) {
                    if (currentNode.element == element) {
                        return true;
                    } else {
                        currentNode = currentNode.next;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public int count(ListNode head, Object element) {
        int count = 0;
        if (head != null && head.element.equals(element)) {
            count++;
        } else if (head == null) {
            return count;
        }
        if (head.next != null) {
            ListNode currentNode = head.next;
            while (currentNode != null) {
                if (currentNode.element.equals(element)) {
                    count++;
                }
                currentNode = currentNode.next;
            }
        }
        return count;
    }

    @Override
    public String convertToString(ListNode head) {
        String list = "";
        if (head != null) {
            list = list + head.element.toString();
            if (head.next != null) {
                ListNode currentNode = head.next;
                while (currentNode != null) {
                    list = list + "," + currentNode.element.toString();
                    currentNode = currentNode.next;
                }
            }
        }
        return list;

    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (n < 0 || head == null) {
            throw new InvalidIndexException();
        }
        if (n == 0) {
            return head.element;
        }
        ListNode currentNode = null;
        if (head.next != null) {
            currentNode = head.next;
            n--;
            while (n > 0) {
                currentNode = currentNode.next;
                n--;
                if (currentNode == null) {
                    throw new InvalidIndexException();
                }
            }
        }
        if (currentNode == null) {
            throw new InvalidIndexException();
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
        if (head1 == null  || head2 == null) {
            return head1 == head2;
        }

        ListNode currentNode1, currentNode2;
        if (head1.element != head2.element) {
            return false;
        }
        if (head1.next != null && head2.next != null) {
            currentNode1 = head1.next;
            currentNode2 = head2.next;
            while (currentNode1 != null || currentNode2 != null) {
                if (currentNode1.element != currentNode2.element) {
                    return false;
                }
                currentNode1 = currentNode1.next;
                currentNode2 = currentNode2.next;
            }
        }
        return true;
    }

    @Override
    public ListNode deepCopy(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode copy = new ListNode(head.element);
        ListNode currentNode;
        if (head.next != null) {
            currentNode = head.next;
            ListNode newNode;
            while (currentNode != null) {
                newNode = new ListNode(currentNode.element);
                append(copy, newNode);
                currentNode = currentNode.next;
            }
        }

        return copy;
    }

    /**
     * There are n(n-1)/2 comparisons.
     * @param head the head of the list
     * @return
     */
    @Override
    public boolean containsDuplicates(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next != null) {
            ListNode currentNode = head.next;
            if (contains(currentNode, head.element)) {
                return true;
            }
            if (head.next.next != null) {
                ListNode next = head.next.next;
                for (int i = 0; i < size(head); i++) {
                    if (contains(next, currentNode.element)) {
                        return true;
                    }
                    currentNode = currentNode.next;
                    next = next.next;
                }
            }
        }
        return false;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        if (head1 == null) {
            head1 = head2;
            return head1;
        } else if (head1.next == null) {
            head1.next = head2;
            return head1;
        }
        ListNode currentNode = head1.next;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = head2;
        return head1;
    }

    /**
     * First the head of the inner linked list at the head of the outer linked list is made into the head of new linked
     * list. A node containing a tail of the new linked list is created. Then the outer linked list containing the heads
     * of the other linked lists is iterated through, and each head is put onto the tail of the new list, the tail is
     * updated as these new lists are added.
     * @param head the head of a list, each element of which is itself the head of a list
     * @return the linked list formed by all the inner linked lists.
     */

    @Override
    public ListNode flatten(ListNode head) {
        ListNode newList = null;
        ListNode currentNode = head;
        ListNode tailNode = null;
        if (currentNode != null) {
            newList = (ListNode) currentNode.element;
            tailNode = newList;
            while (tailNode.next != null) {
                tailNode = tailNode.next;
            }
            if (currentNode.next != null) {
                currentNode = currentNode.next;
                while (currentNode != null) {
                    tailNode.next = (ListNode) currentNode.element;
                    while (tailNode.next != null) {
                        tailNode = tailNode.next;
                    }
                    currentNode = currentNode.next;
                }

            }
        }
        return newList;
    }


    /**
     * Two nodes are made, one node (currentNode) that iterates through the list, another that follows it. The currentNode keeps count
     * of how many nodes it has traversed. Each time it changes, a follower node catches up to it, counting how many
     * node it has traversed. If the follower node's and the currentNode's counts are different in means the currentNode
     * looped at some point.
     * @param head the head of the list
     * @return true if the list is circular (the last node points to the head of the list as its next node), false if it isn't.
     */
    @Override
    public boolean isCircular(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next != null) {
            ListNode currentNode = head.next;
            int currentCount = 1;
            int followerCount = 1;
            while (currentNode != null) {

                if (currentNode == head) {
                    return true;
                }
                for (ListNode follower = head.next; follower != currentNode; follower = follower.next) {
                    followerCount++;
                }
                if (currentCount != followerCount) {
                    return false;
                }
                currentNode = currentNode.next;
                currentCount++;
                followerCount = 1;
            }
        }
        return false;
    }

    @Override
    public boolean containsCycles(ListNode head) {

        if (head == null) {
            return false;
        }
        ListNode fastNode = head;
        ListNode slowNode = head;

        while (true) {
            if (fastNode.next == null) {
                return false;
            } else {
                fastNode = fastNode.next;
            }
            if (fastNode.next == null) {
                return false;
            } else {
                fastNode = fastNode.next;
                slowNode = slowNode.next;
            }
            if (fastNode == slowNode) {
                return true;
            }
        }
    }

    /**
     * Implements bubble sort. This is the variant that ends the loop early if no elements have been swapped on a
     * particular iteration.
     * @param head the head of the list
     * @param comparator the ordering to be used
     * @return the sorted list
     */

    @Override
    public ListNode sort(ListNode head, Comparator comparator) {
        if (head == null) {
            return null;
        }
        if (head.next != null) {
            ListNode currentNode = head;
            ListNode nextNode = head.next;
            boolean swapped = true;
            while (swapped) {
                swapped = false;
                nextNode = head.next;
                currentNode = head;
                for (int i = 0; i < size(head) - 1; i++) {
                    if (comparator.compare(currentNode.element, nextNode.element) > 0) {
                        Object temp = nextNode.element;
                        nextNode.element = currentNode.element;
                        currentNode.element = temp;
                        swapped = true;
                    }
                    currentNode = currentNode.next;
                    nextNode = nextNode.next;
                }
            }
        }
        return head;
    }

    @Override
    public ListNode map(ListNode head, ITransformation transformation) {
        if (head == null) {
            return null;
        }
        ListNode newList = deepCopy(head);
        newList.element = transformation.transform(newList.element);
        if (newList.next != null) {
            ListNode currentNode = newList.next;
            for (int i = 1; i < size(newList); i++) {
                currentNode.element = transformation.transform(currentNode.element);
                currentNode = currentNode.next;
            }
        }
        return newList;
    }

    @Override
    public Object reduce(ListNode head, IOperator operator, Object initial) {
        if (head == null) {
            return initial;
        }
        ListNode currentNode = head;
        Object sum = operator.operate(currentNode.element, initial);
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            sum = operator.operate(sum, currentNode.element);
        }
        return sum;
    }

}
