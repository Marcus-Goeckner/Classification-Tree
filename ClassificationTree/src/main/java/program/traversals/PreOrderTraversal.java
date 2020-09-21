package program.traversals;


import program.structures.Node;
import program.structures.Tree;

import java.util.List;

/**
 * An implementation of a DepthFirst PreOrder Traversal for a tree.
 *
 * @param <E> The type of data stored in the tree to be traversed.
 */
public class PreOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new PreOrder traversal for the provided tree.
     *
     * @param tree Tree to be traversed.
     */
    public PreOrderTraversal(Tree<E> tree) {
        super(tree);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subtree(Node<E> p, List<Node<E>> snapshot) {
        if (snapshot == null || p == null) {
            throw new IllegalArgumentException();
        }
        snapshot.add(p);
        for (Node<E> c : tree.children(p)) {
            subtree(c, snapshot);
        }
    }
}
