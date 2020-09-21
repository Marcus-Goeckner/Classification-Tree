package program.traversals;

import program.structures.Node;
import program.structures.Tree;
import program.traversals.commands.TraversalCommand;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An implementation of the Iterative BreadthFirstSearch algorithm.
 *
 */
public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {

    /**
     * Constructs a new BreadthFirst Traversal for the given tree.
     *
     * @param tree The tree to traverse
     */
    public BreadthFirstTraversal(Tree<E> tree) {
        super(tree);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Node<E>> traverse() {
        Queue<Node<E>> q1 = new LinkedList<>();
        if (!tree.isEmpty()) {
            Queue<Node<E>> q2 = new LinkedList<>();
            q2.offer(tree.root());
            while (!q2.isEmpty()) {
                Node<E> p = q2.poll();
                q1.add(p);
                for (Node<E> c : tree.children(p)) {
                    q2.offer(c);
                }
            }
        }
        return q1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Node<E>> traverseFrom(Node start) {
       if (start == null) {
           throw new IllegalArgumentException("Start node cannot be null.");
       }
        Queue<Node<E>> q1 = new LinkedList<>();
        if (!tree.isEmpty()) {
            Queue<Node<E>> q2 = new LinkedList<>();
            q2.offer(start);
            while (!q2.isEmpty()) {
                Node<E> p = q2.poll();
                q1.add(p);
                for (Node<E> c : tree.children(p)) {
                    q2.offer(c);
                }
            }
        }
        return q1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommand(TraversalCommand cmd) {
        this.command = cmd;
    }
}
