package program.traversals;

import program.structures.Tree;
import program.traversals.commands.TraversalCommand;

/**
 * The abstract tree traversal super class.
 *
 */
public abstract class AbstractTraversal<E> implements TreeTraversal<E> {

    protected Tree<E> tree;
    protected TraversalCommand command;

    /**
     * Constructs an AbstractTraversal for the given tree.
     *
     * @param tree Tree to traverse
     * @throws IllegalArgumentException if the provided tree is null.
     */
    public AbstractTraversal(Tree<E> tree) throws IllegalArgumentException {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        this.tree = tree;
        this.command = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommand(TraversalCommand command) {
        this.command = command;
    }
}
