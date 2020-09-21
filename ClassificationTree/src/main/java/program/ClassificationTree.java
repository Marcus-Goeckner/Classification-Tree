package program;

import program.structures.Node;
import program.structures.impl.LinkedBinaryTree;
import program.traversals.BreadthFirstTraversal;
import program.traversals.InOrderTraversal;
import program.traversals.commands.EnumeratedSaveCommand;
import program.traversals.commands.EnumerationCommand;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 */
public class ClassificationTree {

    private LinkedBinaryTree<Datum> tree;

    /**
     * Constructs a new Animal tree class which manages an underlying animal
     * tree
     */
    public ClassificationTree(String fileName) throws IOException {
        tree = new LinkedBinaryTree<>();
        load(fileName);
    }

    /**
     * Main method which controls the identification and tree management loop.
     */
    public void identify() throws FileNotFoundException {
        LinkedBinaryTree.BinaryTreeNode<Datum> currentNode = (LinkedBinaryTree.BinaryTreeNode<Datum>) tree.root();
        identify(currentNode);
    }

    public void identify(Node<Datum> nextNode) throws FileNotFoundException {
        LinkedBinaryTree.BinaryTreeNode<Datum> currentNode = (LinkedBinaryTree.BinaryTreeNode<Datum>) nextNode;
        System.out.println("Is this animal " + currentNode.getElement().getPrompt() + "? (Y/N) > ");
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.next().toUpperCase();

        if (answer.equals("N")) {
            if (currentNode.getRight() == null) {
                System.out.println("I don't know about an animal with these characteristics that isn't " + currentNode.getElement().getPrompt() + ".");
                System.out.println("What is the new animal? >");
                String newAnimal = in.next();
                System.out.println(("What characteristic does a " + newAnimal + " have that " + currentNode.getElement().getPrompt() + " does not? >"));
                String newCharacteristic = in.next();
                tree.addRight(currentNode, new Datum(currentNode.getElement().getPrompt()));
                tree.addLeft(currentNode, new Datum("a " + newAnimal));
                currentNode.setElement(new Datum(newCharacteristic));
                System.out.println("Do you have another animal to identify? (Y/N) > ");
                answer = in.next().toUpperCase();

                if (answer.equals("Y")) {
                    identify();
                } else {
                    System.out.println("Enter a file name to save the tree to > ");
                    String fileName;
                    fileName = in.next();
                    save(fileName);
                    System.exit(0);
                }
            } else {
                currentNode = currentNode.getRight();
                identify(currentNode);
            }
        } else if (answer.equals("Y")) {
            if (currentNode.getLeft() == null) {
                if (answer.equals("Y")) {
                    System.out.println("Found your animal!");
                    System.out.println("Do you have another animal to identify? (Y/N) > ");
                    answer = in.next().toUpperCase();

                    if (answer.equals("Y")) {
                        identify();
                    } else {
                        System.out.println("Enter a file name to save the tree to > ");
                        String fileName;
                        fileName = in.next();
                        save(fileName);
                        System.exit(0);
                    }
                }
            } else {
                currentNode = currentNode.getLeft();
                identify(currentNode);
            }
        }
    }

    /**
     * Saves a tree to a file.
     */
    public void save(String fileName) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            EnumeratedSaveCommand saveCommand = new EnumeratedSaveCommand(writer);
            EnumerationCommand enumerationCommand = new EnumerationCommand();
            InOrderTraversal iot = new InOrderTraversal(tree);
            Iterable<Node<Datum>> nodeVisits = iot.traverse();
            for (Node n : nodeVisits) {
                enumerationCommand.execute(tree, n);
            }
            BreadthFirstTraversal bft = new BreadthFirstTraversal(tree);
            Iterable<Node<Datum>> nodesSaves = bft.traverse();
            for (Node n : nodesSaves) {
                saveCommand.execute(tree, n);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

    }

    /**
     * Loads a tree from the given file, if an exception occurs during file
     * operations, a hardcoded basic tree will be loaded instead.
     */
    public void load(String fileName) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            Queue<Datum> datums = new LinkedList<>();

            while((line = reader.readLine()) != null) {
                String[] arr = line.split(":");
                int datumNum = Integer.parseInt(arr[1]);
                String prompt = arr[3];
                Datum datum = new Datum(prompt, datumNum);
                datum.setSide(arr[2]);
                datums.offer(datum);
            }
            while (!datums.isEmpty()) {
                LinkedBinaryTree.BinaryTreeNode p = null;
                if (tree.isEmpty()) {
                    p = (LinkedBinaryTree.BinaryTreeNode) tree.setRoot(datums.poll());
                } else {
                    p = (LinkedBinaryTree.BinaryTreeNode) tree.root();
                    insertDatum(datums.poll(), p);
                }
            }
        } catch(IOException e) {
            System.out.println("Could not find specified file. Loading hardcoded basic tree instead.");
            BufferedReader reader = new BufferedReader(new FileReader("test75.txt"));
            String line;
            Queue<Datum> datums = new LinkedList<>();

            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(":");
                int datumNum = Integer.parseInt(arr[1]);
                String prompt = arr[3];
                Datum datum = new Datum(prompt, datumNum);
                datum.setSide(arr[2]);
                datums.offer(datum);

            }
            while (!datums.isEmpty()) {
                LinkedBinaryTree.BinaryTreeNode p = null;
                if (tree.isEmpty()) {
                    p = (LinkedBinaryTree.BinaryTreeNode) tree.setRoot(datums.poll());
                } else {
                    p = (LinkedBinaryTree.BinaryTreeNode) tree.root();
                    insertDatum(datums.poll(), p);
                }
            }
        }
    }


    public void insertDatum(Datum datum, LinkedBinaryTree.BinaryTreeNode<Datum> p) {
        if (datum.getNumber() < p.getElement().getNumber()) {
            if (p.getLeft() != null) {
                insertDatum(datum, p.getLeft());
            } else {
                tree.addLeft(p, datum);
            }
        } else if (datum.getNumber() > p.getElement().getNumber()) {
            if (p.getRight() != null) {
                insertDatum(datum, p.getRight());
            } else {
                tree.addRight(p, datum);
            }
        }
    }
}