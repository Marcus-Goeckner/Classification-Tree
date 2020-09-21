package program.traversals;

import program.structures.Node;
import program.structures.Tree;

import java.util.List;

/**
 * A recursive implementation of the PostOrder depth first traversal of a tree.
 *
 * @param <E> The type of data stored in the tree to be traversed.
 */
public class PostOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new post order traversal for the provided tree
     *
     * @param tree The tree to traverse
     */
    public PostOrderTraversal(Tree<E> tree) {
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
        for (Node<E> c : tree.children(p)) {
            subtree(c, snapshot);
        }
        snapshot.add(p);
    }
}
