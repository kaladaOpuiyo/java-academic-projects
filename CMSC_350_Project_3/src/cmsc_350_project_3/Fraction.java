/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc_350_project_3;

/**
 *
 * @author Kalada Opuiyo
 */
public class Fraction implements Comparable {

    public String fraction;

    public Fraction(String fraction) {
        this.fraction = fraction;
    }

    @Override
    public String toString() {
        return fraction;
    }

    @Override
    public int compareTo(Object o) {

        String frt = o.toString();

        String values[] = frt.split("/");

        Float f = Float.valueOf(values[0]) / Float.valueOf(values[1]);

        values = this.fraction.split("/");
        Float result = Float.valueOf(values[0]) / Float.valueOf(values[1]);

        return f.compareTo(result);

    }

}
