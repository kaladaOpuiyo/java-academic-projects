/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc_350_project_2;

/**
 * Code was referenced from module 2, section II-B.
 *
 * @author Kalada Opuiyo
 */
public class OperandNode<T> implements Node {

    private final T value;

    public OperandNode(T value) {
        this.value = value;
    }

    @Override
    public String inOrderWalk() {
        return String.valueOf(value);
    }

    @Override
    public String getAddressOrValue() {
        return inOrderWalk();

    }

}
