package model;
import java.util.ArrayList;
/**
 * @author Jose Daniel Fuentes -jd.fuentes
 */
public class NewickTree {
    ArrayList<Node> nodeList = new ArrayList<>();
    private Node root;

    public NewickTree() {

    }

    public NewickTree readNewickFormat(String newick) {
        this.root = readSubtree(newick.substring(0, newick.length() - 1));
        return this;
    }
    public String[] split(String s) {

        ArrayList<Integer> splitIndices = new ArrayList<>();

        int rightParenCount = 0;
        int leftParenCount = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    leftParenCount++;
                    break;
                case ')':
                    rightParenCount++;
                    break;
                case ',':
                    if (leftParenCount == rightParenCount) splitIndices.add(i);
                    break;
            }
        }

        int numSplits = splitIndices.size() + 1;
        String[] splits = new String[numSplits];

        if (numSplits == 1) {
            splits[0] = s;
        } else {

            splits[0] = s.substring(0, splitIndices.get(0));

            for (int i = 1; i < splitIndices.size(); i++) {
                splits[i] = s.substring(splitIndices.get(i - 1) + 1, splitIndices.get(i));
            }

            splits[numSplits - 1] = s.substring(splitIndices.get(splitIndices.size() - 1) + 1);
        }

        return splits;
    }

    public Node readSubtree(String s) {

        int leftParen = s.indexOf('(');
        int rightParen = s.lastIndexOf(')');

        if (leftParen != -1 && rightParen != -1) {

            String name = s.substring(rightParen + 1);
            String[] childrenString = split(s.substring(leftParen + 1, rightParen));

            Node node = new Node(name);
            node.children = new ArrayList<>();
            for (String sub : childrenString) {
                Node child = readSubtree(sub);
                node.children.add(child);
                child.parent = node;
            }

            nodeList.add(node);
            return node;
        }
        else if (leftParen == rightParen) {

            Node node = new Node(s);
            nodeList.add(node);
            return node;

        }
        else{
            throw new RuntimeException("unbalanced ()'s");
        }
    }

    public String toString() {
        return root.toString() + ";";
    }

}