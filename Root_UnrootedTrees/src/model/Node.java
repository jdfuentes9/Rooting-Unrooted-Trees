package model;

import java.util.ArrayList;

public class Node {

        private static int nodeId = 0;
        private String name;
        private double weight;
        boolean realName = false;
        ArrayList<Node> children;
        Node parent;


        /**
         * @param name name in "actualName:weight" format, weight defaults to zero if colon absent
         */
        Node(String name) {

            int colonIndex = name.indexOf(':');
            String actualNameText;
            if (colonIndex == -1) {
                actualNameText = name;
                weight = 0;
            } else {
                actualNameText = name.substring(0, colonIndex);
                System.out.println(name);
                weight = Double.parseDouble(name.substring(colonIndex+1));
            }

            if (actualNameText.equals("")) {
                this.realName = false;
                this.name = Double.toString(nodeId);
                nodeId++;
            } else {
                this.realName = true;
                this.name = actualNameText;
            }
        }

        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node other = (Node) o;
            return this.name.equals(other.name);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (children != null && children.size() > 0) {
                sb.append("(");
                for (int i = 0; i < children.size() - 1; i++) {
                    sb.append(children.get(i).toString());
                    sb.append(":");
                    sb.append(children.get(i).weight);
                    sb.append(",");
                }
                sb.append(children.get(children.size() - 1).toString());
                sb.append(":");
                sb.append(this.weight);
                sb.append(")");
            }
            if (name != null) sb.append(this.getName());
            return sb.toString();
        }

        String getName() {
            if (realName)
                return name;
            else
                return "";
        }
}

