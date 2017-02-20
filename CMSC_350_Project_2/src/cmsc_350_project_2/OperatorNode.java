/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc_350_project_2;

/**
 *
 * @author Kalada Opuiyo, Code was referenced from module 2, section II-B
 */
public class OperatorNode implements Node {

    private final Node left ;
     private final Node right;
    private final String operator;
    private final int newAddress;

    public OperatorNode(String operator,
            Node right, Node left, int address) {

        this.operator = operator;
        this.left = left;
        this.right = right;
        this.newAddress = address;
        
        AddressFile.registerAddress(left.getAddressOrValue(), right.getAddressOrValue(), operator, getAddressOrValue());
       
   }

    @Override
    public String inOrderWalk() {
        String leftValue = left.inOrderWalk();
        String rightValue = right.inOrderWalk();

        return "(" + leftValue + " " + operator + " "
                + rightValue + ")";
    }

    @Override
    public final String getAddressOrValue() {

        return "R" + newAddress;
    }

}
