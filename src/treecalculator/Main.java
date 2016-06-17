/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treecalculator;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator("output.txt", "expressoes.txt");
        calculator.validateExpression();
    }

}
