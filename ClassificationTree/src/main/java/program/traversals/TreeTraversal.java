package program.traversals;

import program.traversals.commands.TraversalCommand;
import program.structures.Node;

/**
 * An interface for tree traversal algorithms.
 *
 * @param <E> The type of data stored in the tree to be traversed.
 */
public interface TreeTraversal<E> {

    /**
     * Method which initiates the traversal of a tree from the root node. This
     * method returns the an iterable container of nodes representing a
     * resulting traveral of the tree.
     *
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    Iterable<Node<E>> traverse();

    /**
     * Method which initiates the traversal of a tree from the root node. This
     * method returns the an iterable container of nodes representing a
     * resulting traversal of the tree.
     *
     * @param node Root of the subtree to start the traversal at.
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    Iterable<Node<E>> traverseFrom(Node<E> node);

    /**
     * Sets the executable command to the provided value.
     *
     * @param cmd The new executable command
     */
    void setCommand(TraversalCommand cmd);
}
