package program.traversals;

import program.structures.Node;
import program.structures.Tree;
import program.structures.impl.LinkedBinaryTree;

import java.util.List;

/**
 * A recursive implementation of the inorder tree traversal algorithm.
 *
 * @param <E> The type of data in the tree to be traversed
 */
public class InOrderTraversal<E extends Comparable> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new InOrder tree traversal for the given tree.
     *
     * @param tree Tree to be traversed.
     */
    public InOrderTraversal(Tree<E> tree) {
        super(tree);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subtree(Node<E> p, List<Node<E>> snapshot) {
        if (p == null || snapshot == null) {
            throw new IllegalArgumentException();
        }
        LinkedBinaryTree.BinaryTreeNode<E> node = (LinkedBinaryTree.BinaryTreeNode<E>) tree.validate(p);
        if (node.getLeft() != null) {
            subtree(node.getLeft(), snapshot);
        }
        snapshot.add(node);
        if (node.getRight() != null) {
            subtree(node.getRight(), snapshot);
        }
    }
}
