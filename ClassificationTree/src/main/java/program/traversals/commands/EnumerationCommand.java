package program.traversals.commands;

import program.Datum;
import program.structures.Node;
import program.structures.Tree;

/**
 * A Traversal Command which traverses a tree of Datum objects and sets their number to the number in which they were visited.
 */
public class EnumerationCommand extends TraversalCommand<Datum> {

    private int current;

    /**
     * Constructs a new Enumeration Command and sets the initial value of current to 0;
     */
    public EnumerationCommand() {
        current = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Tree<Datum> tree, Node<Datum> node) {
        node.getElement().setNumber(current);
        current += 1;
    }

}
