package program.structures.impl;

import program.structures.BinaryTree;
import program.structures.Node;
import program.structures.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LinkedBinaryTree class to be used for classification tree
 *
 * @author Marcus Goeckner
 * @param <E> Type of data to be contained in the tree
 */
public class LinkedBinaryTree<E extends Comparable> implements Tree<E>, BinaryTree<E> {

    protected BinaryTreeNode<E> root;
    private int size = 0;

    /**
     * Nested BinaryTreeNode class to be used for the nodes in LinkedBinaryTree.
     *
     * @author Marcus Goeckner
     * @param <E> The type of the element to be contained in the node.
     */
    public static class BinaryTreeNode<E extends Comparable> implements Node<E> {

        public E element;
        private BinaryTreeNode<E> parent;
        private BinaryTreeNode<E> right;
        private BinaryTreeNode<E> left;

        public BinaryTreeNode(E e, BinaryTreeNode<E> above, BinaryTreeNode<E> leftChild, BinaryTreeNode<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        /**
         * @return The element contained in this node.
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * @return The parent node of this class. Can be null.
         */
        @Override
        public Node<E> getParent() {
            return parent;
        }

        /**
         * @return The left child of this node.
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * @return The right child of this node.
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the new value of this node to the provided one. Throws an
         * IllegalArgumentException if the provided value is null.
         *
         * @param element New value to be contained in this node.
         */
        @Override
        public void setElement(E element) throws IllegalArgumentException {
            if (element == null) {
                throw new IllegalArgumentException();
            } else {
                this.element = element;
            }
        }

        /**
         * Sets the parent of this node.
         *
         * @param parentNode New parent of this node.
         */
        public void setParent(BinaryTreeNode<E> parentNode) {
            parent = parentNode;
        }

        /**
         * Sets the left child of this node.
         *
         * @param leftChild New left child of this node.
         */
        public void setLeft(BinaryTreeNode<E> leftChild) {
            left = leftChild;
        }

        /**
         * Sets the right child of this node.
         *
         * @param rightChild New right child of this node.
         */
        public void setRight(BinaryTreeNode<E> rightChild) {
            right = rightChild;
        }
    }

    public BinaryTreeNode<E> createNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> left,
                                        BinaryTreeNode<E> right) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot create a node with null elements");
        }
        return new BinaryTreeNode<>(element, parent, left, right);
    }

    /**
     * Returns the left child of the provided node.
     *
     * @param p The parent node of whom the left child is desired.
     * @return The left child of the provided node, can be null if no such child
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the right child of the provided node.
     *
     * @param p The parent node of whom the right child is desired.
     * @return The right child of the provided node, can be null if no such
     * child exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Returns the sibling node of the provided node, if such a sibling exists.
     * That is, if the right node is provided the left node will be returned
     * from the same parent.
     *
     * @param p The node of whom a sibling is requested.
     * @return The sibling of the provided node, or null if no such sibling
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        Node<E> parent = parent(node);
        if (parent == null) {
            return null;
        } else if (node == left(parent)) {
            return right(parent);
        } else {
            return left(parent);
        }
    }

    /**
     * Adds the provided element as a new node to the left side of the provided
     * node.
     *
     * @param p       The node to which the element is to be added as the left child.
     * @param element Element to be added
     * @return The newly created left child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     *                                  provided element is null, or if the provided node already has a left
     *                                  child.
     */
    @Override
    public Node<E> addLeft(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        BinaryTreeNode<E> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Adds the provided element as a new node to the right side of the provided
     * node.
     *
     * @param p       The node to which the element is to be added as the right child.
     * @param element Element to be added
     * @return The newly created right child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     *                                  provided element is null, or if the provided node already has a right
     *                                  child.
     */
    @Override
    public Node<E> addRight(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        BinaryTreeNode<E> child = createNode(element, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * @return The root node of this tree or null if the Tree is empty.
     */
    @Override
    public Node<E> root() {
        BinaryTreeNode<E> r = root;
        return r;
    }

    /**
     * Sets the tree's root node to the provided item, by creating a new node
     * (unless the given item is the same as the current root's item). Note that
     * this must also reset the size of the tree to the correct value if the
     * current node is replaced.
     *
     * @param item New item for the root node.
     * @return The new root node.
     */
    @Override
    public Node<E> setRoot(E item) {
        if (item == null) {
            root = null;
            size = 0;
            return null;
        } else {
            BinaryTreeNode<E> newRoot = createNode(item, null, null, null);
            root = newRoot;
            size = 1;
            return newRoot;
        }

    }

    /**
     * Returns the parent node of the node provided, or null if the node is also
     * the root of the tree.
     *
     * @param p Node whose parent is being requested.
     * @return The parent of the provided node, or null if the provided node is
     * the root.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public Node<E> parent(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns an iterable collection of the children attached to the provided
     * node.
     *
     * @param p The node whose children are requested.
     * @return An iterable collection of the children attached to the provided
     * node.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException {
        validate(p);
        List<Node<E>> children = new ArrayList<>(2);
        if (left(p) != null) {
            children.add(left(p));
        }
        if (right(p) != null) {
            children.add(right(p));
        }
        return children;

    }

    /**
     * Returns the number of children currently attached to the provided node.
     *
     * @param p Node whose number of children is requested.
     * @return The number of children attached to the provided node.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public int numChildren(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    /**
     * Tests whether the node is an internal node or not. That is whether the
     * node has children.
     *
     * @param p The node to test.
     * @return True if the node is an internal node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public boolean isInternal(Node<E> p) throws IllegalArgumentException {
        validate(p);
        return numChildren(p) > 0;
    }

    /**
     * Tests whether the node is an external node of the tree. That is whether
     * the node has no children and thus is a leaf of the tree.
     *
     * @param p The node to test.
     * @return True if the node is a leaf node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public boolean isExternal(Node<E> p) throws IllegalArgumentException {
        validate(p);
        return numChildren(p) == 0;
    }

    /**
     * Tests whether this node is the root node of the tree. That is that the
     * provided node has children but not parent.
     *
     * @param p Node to test.
     * @return True if the node is the root of the tree.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public boolean isRoot(Node<E> p) throws IllegalArgumentException {
        return p == root();
    }

    /**
     * Inserts the item into the tree under the provided node. If the provided
     * node is null the item becomes the new root of the tree, beware.
     *
     * @param item Item to be inserted into the tree.
     * @param p    The parent node of the tree, if null the item becomes the new
     *             root so be aware.
     * @return True if the item was able to be inserted, false otherwise (for
     * example the item was null)
     * @throws IllegalArgumentException if the provided parent node is invalid,
     *                                  or the provided value is null.
     */
    @Override
    public Node<E> insert(E item, Node<E> p) throws IllegalArgumentException {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        BinaryTreeNode<E> node =  validate(p);
        Node child = null;
        if (node.getRight() != null && node.getLeft() != null) {
            throw new IllegalArgumentException("Node has two children");
        }
        if (item == null || item == p.getElement()) {
            throw new IllegalArgumentException("Item invalid.");
        } else if (node.getLeft() == null && node.getRight() == null) {
            child = addLeft(node, item);
        } else if (node.getLeft() != null && node.getRight() == null) {
            child = addRight(node, item);
        }
        size++;
        return child;
    }

    /**
     * Removes the given item from the provided parent node.
     *
     * @param item Item to be removed from the list of children of the provided
     *             node.
     * @param p    Parent node.
     * @return true if the item was removed, false otherwise.
     * @throws IllegalArgumentException If the provided parent node is not valid
     *                                  or the value is null.
     */
    @Override
    public boolean remove(E item, Node<E> p) throws IllegalArgumentException {
        if (item == null || p == null) {
            return false;
        }
        BinaryTreeNode node = (BinaryTreeNode)p;
        try {
            if (item.compareTo(node.getElement()) < 0) {
                if (node.getLeft().getElement() == item) {
                    if (node.getLeft().getLeft() == null || node.getLeft().getRight() == null) {
                        node.setLeft(node.getLeft().getRight());
                        size--;
                        return true;
                    }
                }
                return remove(item, node.getLeft());
            }
            if (item.compareTo(node.getElement()) > 0) {
                if (node.getRight().getElement() == item) {
                    if (node.getRight().getRight() == null || node.getRight().getLeft() == null) {
                        node.setRight(node.getRight().getLeft());
                        size--;
                        return true;
                    }
                }
                return remove(item, node.getRight());
            }
            if (item.compareTo(node.getElement()) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    if (node == root()) {
                        setRoot(null);
                    }
                    return true;
                } else if (node.getLeft() != null && node.getRight() != null) {
                    BinaryTreeNode temp = node;
                    E min = (E) minValue(temp.getRight()).getElement();
                    node.setElement(min);
                    removeMin(node.getRight());
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Function to be used to remove the min value of items in a tree with root p.
     *
     * @param p Parent node.
     */
    public void removeMin(Node<E> p) {
        BinaryTreeNode<E> node = validate(p);
        if (node.getLeft() == null) {
            setRoot(null);
        } else {
            removeMin(node.getLeft());
        }
    }

    /**
     * Finds the minimum value in a tree.
     *
     * @param p Parent node.
     * @return Minimum value in a tree with root p.
     */
    public Node<E> minValue(Node<E> p) {
        BinaryTreeNode node = validate(p);
        if (node.getLeft() == null) {
            return node;
        } else {
            return minValue(node.getLeft());
        }
    }

    /**
     * @return The number of nodes currently in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if the tree contains no nodes (that is the root = null),
     * false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return root() == null;
    }

    /**
     * Updates the value of the node to the provided value. Throws an
     * IllegalArgumentException if the value is null, the node is null, or the
     * node is not in this Tree.
     *
     * @param node    Node whose value is to be updated.
     * @param element New value for the node.
     * @throws IllegalArgumentException If the provided node is invalid, or the
     *                                  element value is null.
     */
    @Override
    public E set(Node<E> node, E element) throws IllegalArgumentException {
       BinaryTreeNode n = validate(node);
       if (element == null) {
           throw new IllegalArgumentException("Element cannot be null");
       } else {
           node.setElement(element);
           return element;
       }
    }

    /**
     * Validates that the provided node is not null, is of a subtype of Node
     * supported by the implementing tree class, and is currently in this tree.
     * If these conditions are not met then an IllegalArgumentException is
     * thrown.
     *
     * @param p The node to be validated.
     * @return A node of the expected type specific to the implementing tree.
     * @throws IllegalArgumentException Thrown if the provided node is null, not
     *                                  in the current tree, or is not of a type supported by the current tree.
     */
    @Override
    public BinaryTreeNode<E> validate(Node<E> p) throws IllegalArgumentException {
        if ((!(p instanceof BinaryTreeNode))) {
            throw new IllegalArgumentException();
        }
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
        if (node.getParent() == null && !isRoot(node)) {
            throw new IllegalArgumentException("Provided node is not in the tree.");
        }
        return node;
    }

    /**
     * Calculates the depth of the given node in the current tree.
     *
     * @param node Node whose depth is to be calculated
     * @return Depth of the node in the tree.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public int depth(Node<E> node) throws IllegalArgumentException {
        BinaryTreeNode<E> n = validate(node);
        if (isRoot(n)) {
            return 0;
        } else {
            return 1 + depth(parent(n));
        }
    }

    /**
     * Recusively calculates the size of a subtree rooted at the provided node.
     *
     * @param node Node whose subtree size is to be calculated
     * @return Size of the subtree (excluding the root)
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public int subTreeSize(Node<E> node) throws IllegalArgumentException {
        BinaryTreeNode n = validate(node);
        return (subTreeSize(n.getLeft()) + 1 + subTreeSize(n.getRight()));
    }

    /**
     * Checks if the provided node is the last child of it's parent node. Note
     * that the root node always returns true.
     *
     * @param node Node to check.
     * @return True if the node is the last child of it's parent node or is the
     * root, false otherwise.
     * @throws IllegalArgumentException If the provided node is not valid.
     */
    @Override
    public boolean isLastChild(Node<E> node) throws IllegalArgumentException {
        BinaryTreeNode n = validate(node);
        if (n == root) {
            return true;
        } else {
            return n.getRight() == null && n.getLeft() == null;
        }
    }
}
