package program.traversals;

import program.structures.Node;
import program.structures.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of TreeTraversal algorithms using a Template Design
 * Pattern.
 *
 * @param <E> The type of data stored in the tree.
 */
public abstract class DepthFirstTraversal<E> extends AbstractTraversal<E> {

    /**
     * Constructs a new TreeTraversal template for the given tree.
     *
     * @param tree Tree to traverse with this template.
     * @throws IllegalArgumentException If provided tree is null
     */
    public DepthFirstTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
    }

    

    /**
     * Method which initiates the traversal of a tree from the provided start
     * node. This method returns an iterable container of nodes representing a
     * resulting traversal of the subtree.
     *
     * @param start The node at which to start the traversal
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    protected Iterable<Node<E>> subTreeTraverse(Node<E> start)  {
       if (start == null) {
           throw new IllegalArgumentException();
       }
       return traverseFrom(start);
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Node<E>> traverse() {
        List<Node<E>> snapshot = new ArrayList<>();
        if (!tree.isEmpty()) {
            subtree(tree.root(), snapshot);
        }
        return snapshot;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
       if (node == null) {
           throw new IllegalArgumentException();
       }
        List<Node<E>> snapshot = new ArrayList<>();
        if (!tree.isEmpty()) {
            subtree(node, snapshot);
        }
        return snapshot;
    }
    
    /**
     * The recursive method called to extract the subtree that is next in the
     * traversal.
     *
     * @param p Node whose subtree is needed.
     * @param snapshot List of nodes comprising the traversal
     * @throws IllegalArgumentException If p is null or not in the tree or the provided list is null.
     */
    protected abstract void subtree(Node<E> p, final List<Node<E>> snapshot) throws IllegalArgumentException;
}
