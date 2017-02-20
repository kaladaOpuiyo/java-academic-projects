package cmsc_350_project_2;

import java.util.Stack;

/**
 *
 * @author Kalada Opuiyo
 */
public class PostfixToInfix {

    private String[] tokenizedExpression;
    private final Stack<Node> operandStack;
    private final Stack<String> operatorStack;
    private Node tree;
    private int address;

    public PostfixToInfix() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public String convertToInFix(String expression) {

        tokenizedExpression = expression.split("|");

        for (String element : tokenizedExpression) {

            if (element.matches("[a-zA-Z0-9]+") && !element.matches("\\s")) {
                operandStack.push(new OperandNode(element));
            }

            if (element.matches("[+\\-*/]") && !element.matches("\\s")) {
                operatorStack.push(element);
            }

            if (!operatorStack.isEmpty()) {
                tree = new OperatorNode(operatorStack.pop(), operandStack.pop(), operandStack.pop(), address++);
                operandStack.push(tree);

            }

            if (!element.matches("[+\\-*/]") && !element.matches("[a-zA-Z0-9]+") && !element.matches("\\s")) {

                if (element.isEmpty()) {
                    return "Enter Expression";
                }
                throw new RuntimeException(element);
            }

        }

        return tree.inOrderWalk() + "\n";
    }

}
