package program.traversals.commands;

import program.structures.Node;
import program.structures.Tree;

public abstract class TraversalCommand<E> {

    public abstract void execute(Tree<E> tree, Node<E> node);
}
