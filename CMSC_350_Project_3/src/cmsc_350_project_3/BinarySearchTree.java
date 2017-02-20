package cmsc_350_project_3;

public class BinarySearchTree<T> {

    public BinarySearchTree root;
    private BinarySearchTree left;
    private BinarySearchTree right;
    private String element;
    private String[] dataArray;
    private String displayResult = "";

    public BinarySearchTree() {
    }

    public BinarySearchTree(String element) {

        this.element = element;
    }

    public void initializeTree(String data) {

        dataArray = data.trim().split("\\s+");

        root = new BinarySearchTree(dataArray[0]);
    }

    private BinarySearchTree insertNewValue(BinarySearchTree<T> node, String data, String type) {

        if (node == null) {
            return new BinarySearchTree(data);
        }

        switch (type) {

            case "Integer":

                if (Integer.valueOf(data) < Integer.valueOf(node.element)) {

                    node.left = insertNewValue(node.left, data, type);
                } else {
                    node.right = insertNewValue(node.right, data, type);
                }

                break;

            case "Fraction":

                if ((new Fraction(node.element)).compareTo(data) > 0) {

                    node.right = insertNewValue(node.right, data, type);
                } else {
                    node.left = insertNewValue(node.left, data, type);
                }

                break;
        }

        return node;
    }

    public String inOrderSort(BinarySearchTree node) {

        if (node != null) {

            inOrderSort(node.left);
            displayResult += node.element + " ";
            inOrderSort(node.right);
        }

        return displayResult;
    }

    public String desOrderSort(BinarySearchTree node) {

        if (node != null) {

            desOrderSort(node.right);
            displayResult += node.element + " ";
            desOrderSort(node.left);
        }

        return displayResult;
    }

    public void insert(BinarySearchTree node, String type) {

        for (String data : dataArray) {

            if (!data.equals(dataArray[0])) {

                insertNewValue(node, data, type);
            }

        }

    }

    
    
}

