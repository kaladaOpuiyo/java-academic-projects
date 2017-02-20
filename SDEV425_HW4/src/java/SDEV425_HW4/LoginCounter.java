/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

public  class LoginCounter {

        public static int countLoginAttemnpt = 0;

        public LoginCounter() {
        }

        public LoginCounter(int countLoginAttemnpt) {

            this.countLoginAttemnpt = countLoginAttemnpt;

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public static int getCountLoginAttemnpt() {
            return countLoginAttemnpt;
        }

        public  void setCountLoginAttemnpt(int countLoginAttemnpt) {
            this.countLoginAttemnpt = countLoginAttemnpt;
        }
    }