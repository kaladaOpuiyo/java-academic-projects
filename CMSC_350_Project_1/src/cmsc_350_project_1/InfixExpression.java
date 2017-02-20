package cmsc_350_project_1;

import java.util.Stack;

/**
 *
 * @author Kalada Opuiyo
 */
public class InfixExpression {

    String[] tokenizedExpression;
    Stack<String> operandStack;
    Stack<String> operatorStack;

    boolean execution = true;

    public InfixExpression() {

        operandStack = new Stack<>();
        operatorStack = new Stack<>();

    }

    public String evaluateExpression(String expression) {

        try {
            tokenizedExpression = expression.split("(?<=[-+*/()])|(?=[-+*/()]|\\s{2,})");
            for (String element : tokenizedExpression) {

                if (element.matches("[0-9][0-9]*")) {

                    operandStack.push(element);

                } else if (element.matches("[(]")) {

                    operatorStack.push(element);

                } else if (element.matches("[)]")) {

                    while (!operatorStack.peek().matches("[(]")
                            && operandStack.size() > 1) {

                        preformCalculation();

                    }
                } else if (element.matches("[+\\-*/]")) {

                    while (!operatorStack.empty() && !operatorStack.peek().matches("[(]")
                            && checkPrecedence(operatorStack.peek(), element)
                            && operandStack.size() > 1) {

                        preformCalculation();

                    }
                    operatorStack.push(element);
                }
            }
            while (!operatorStack.empty() && operandStack.size() > 1) {

                if (operatorStack.peek().matches("[(]")) {
                    operatorStack.pop();
                }
                preformCalculation();

            }

        } catch (ArithmeticException ae) {

            execution = false;
            return "Cannot Divide by Zero";
        }

        if (execution == true && !operandStack.empty()) {
            return String.valueOf(Integer.parseInt(operandStack.pop()));
        } else {
            return "Empty or Invalid Expression";
        }
    }

    private void preformCalculation() {

        String operator = operatorStack.pop();
        int operand1 = Integer.parseInt(operandStack.pop());
        int operand2 = Integer.parseInt(operandStack.pop());

        Integer calculatedResult = operator.matches("[+]") ? operand1 + operand2
                : operator.matches("[-]") ? operand2 - operand1
                : operator.matches("[*]") ? operand1 * operand2
                : operand2 / operand1;

        operandStack.push(String.valueOf(calculatedResult));
    }

    private boolean checkPrecedence(String peek, String element) {

        int topOperator;
        int currentOperator;

        topOperator = peek.matches("[+]") || peek.matches("[-]") ? 0 : 1;
        currentOperator = element.matches("[+]") || element.matches("[-]") ? 0 : 1;
        
        return topOperator >= currentOperator;
    }

}
