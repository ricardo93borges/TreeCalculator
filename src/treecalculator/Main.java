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
        String exp = "( ( 5 + 12 ) + ( ( 10 - 8 ) + 2 ) )";
        //exp = "( ( ( ( 58 + 33 ) - ( ( 108 - 79 ) + 2 ) ) * ( 9 ^ 3 ) ) + ( ( 5 + 12 ) + ( ( 10 - 8 ) + 2 ) ) )";
        Calculator calculator = new Calculator("output.txt", "expressoes.txt");
        //calculator.validateExpression();
        ArrayList<BinaryTreeOfString> trees = calculator.createTree(exp);
        /*for (int i = 0; i < trees.size(); i++) {
            System.out.println("Tree " + i);
            BinaryTreeOfString tree = trees.get(i);
            LinkedListOfString list = tree.positionsPos();
            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }
        }*/
        
        LinkedListOfString list = trees.get(0).positionsPos();
        double result = calculator.calculateTree(list);
        System.out.println("Result="+result);

        /*
        BinaryTreeOfString t = new BinaryTreeOfString();
        t.addRoot("+");
        
        BinaryTreeOfString t2 = new BinaryTreeOfString();
        t2.addRoot("+");
        t2.addLeft("5", t2.getRoot());
        t2.addRight("12", t2.getRoot());
        
        BinaryTreeOfString t3 = new BinaryTreeOfString();
        t3.addRoot("+");
        t3.addLeft("2", t3.getRoot());
        
        BinaryTreeOfString t4 = new BinaryTreeOfString();
        t4.addRoot("-");
        t4.addLeft("10", t4.getRoot());
        t4.addRight("8", t4.getRoot());
        
        t3.addSubtreeRight(t4, t3.getRoot());
        t.addSubtreeRight(t3, t.getRoot());
        t.addSubtreeLeft(t2, t.getRoot());
        
        
        
        LinkedListOfString list = t.positionsPos();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        */
    }

}
