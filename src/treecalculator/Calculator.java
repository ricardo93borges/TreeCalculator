/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treecalculator;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Ricardo
 */
public class Calculator {

    int stackSize = 0;
    HashSet<String> operators = new HashSet<String>();
    String outputFile;
    String inputFile;

    public Calculator(String outputFile, String inputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("^");
    }

    /**
     *
     * @param expression
     * @return
     */
    public boolean validate(String expression) {
        int opening = 0;
        int closing = 0;

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                opening++;
            } else if (c == ')') {
                closing++;
            }
        }
        return opening == closing;
    }

    /**
     *
     * @param str
     * @return boolean
     */
    public boolean isOperator(String str) {
        return operators.contains(str);
    }

    /**
     *
     * @param str
     * @return boolean
     */
    public boolean isOperand(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param op2
     * @param operator
     * @param op1
     * @return String
     */
    public String calculate(String op2, String operator, String op1) {
        double result = 0;
        switch (operator) {
            case "+":
                result = Double.parseDouble(op1) + Double.parseDouble(op2);
                break;
            case "-":
                result = Double.parseDouble(op1) - Double.parseDouble(op2);
                break;
            case "*":
                result = Double.parseDouble(op1) * Double.parseDouble(op2);
                break;
            case "/":
                result = Double.parseDouble(op1) / Double.parseDouble(op2);
                break;
            case "^":
                result = Math.pow(Double.parseDouble(op1), Double.parseDouble(op2));
                break;
        }
        return String.valueOf(result);
    }

    public void validateExpression() {
        FileHandler reader = new FileHandler();

        String result = "";
        ArrayList<String> expressions = reader.read(this.inputFile);

        for (int i = 0; i < expressions.size(); i++) {
            String expression = expressions.get(i);
            //Validade
            if (validate(expression)) {
                this.createTree(expression);
                System.out.println("OK");
            } else {
                System.out.println("FAIL");
                result = "Expressão inválida";
            }

            FileHandler fh = new FileHandler();
            try {
                fh.write(outputFile, expression);
                fh.write(outputFile, "Resultado: " + result);
                fh.write(outputFile, "Tamanho máximo da pilha:  " + this.stackSize);
            } catch (FileNotFoundException e) {
                System.out.println("FileHandler error: " + e.getMessage());
            } catch (UnsupportedEncodingException e) {
                System.out.println("FileHandler error: " + e.getMessage());
            }
        }
    }

    /**
     * TODO unir as arvores resultantes usando o ultimo elemento o raiz
     *
     * @param expression
     */
    public ArrayList<BinaryTreeOfString> createTree(String expression) {
        //Remove spaces
        expression = expression.replaceAll("\\s", "");

        LinkedListOfString operands = new LinkedListOfString();
        LinkedListOfString operators = new LinkedListOfString();
        LinkedListOfString roots = new LinkedListOfString();
        ArrayList<BinaryTreeOfString> subtrees = new ArrayList<BinaryTreeOfString>();

        for (int i = 0; i < expression.length(); i++) {
            String token = String.valueOf(expression.charAt(i));
            String tokenPlusOne = null;
            String tokenPlusTwo = null;
            String tokenMinusOne = null;
            if (i + 1 <= expression.length() - 1) {
                tokenPlusOne = String.valueOf(expression.charAt(i + 1));
            }
            if (i + 2 <= expression.length() - 1) {
                tokenPlusTwo = String.valueOf(expression.charAt(i + 2));
            }
            if (i - 1 >= 0) {
                tokenMinusOne = String.valueOf(expression.charAt(i - 1));
            }

            System.out.println("token: " + token);
            if (isOperand(token)) {
                if (isOperand(tokenPlusOne)) {
                    int c = 1;
                    String next = String.valueOf(expression.charAt(i + c));
                    boolean hasNext = (i + c < expression.length());
                    while (hasNext && isOperand(next)) {
                        token += next;
                        c++;
                        next = String.valueOf(expression.charAt(i + c));
                    }
                    if (i + c < expression.length()) {
                        i += c-1;
                    }
                }
                operands.add(token);
                System.out.println("token+: " + token);
                System.out.println("operands.size():" + operands.size());

            } else if (isOperator(token)) {
                if (isOperand(tokenMinusOne) && tokenPlusOne.equals("(")) {
                    System.out.println("Case 4");
                    //Case 4
                    BinaryTreeOfString t = new BinaryTreeOfString();
                    t.addRoot(token);
                    t.addLeft(tokenMinusOne, t.getRoot());

                    String n1 = String.valueOf(expression.charAt(i + 2));
                    String op = String.valueOf(expression.charAt(i + 3));
                    String n2 = String.valueOf(expression.charAt(i + 4));

                    BinaryTreeOfString t2 = new BinaryTreeOfString();
                    t2.addRoot(op);
                    t2.addLeft(n1, t.getRoot());
                    t2.addRight(n2, t.getRoot());

                    t.addSubtreeRight(t2, t.getRoot());
                    if (i + 5 < expression.length()) {
                        i += 5;
                    }
                    subtrees.add(t);

                } else if (tokenPlusOne.equals("(")) {
                    //Case 2
                    System.out.println("Root! " + token);
                    BinaryTreeOfString t = new BinaryTreeOfString();
                    t.addRoot(token);
                    subtrees.add(t);
                    roots.add(token);
                } else {
                    operators.add(token);
                    System.out.println("operators.size():" + operators.size());
                }
            } else if (token.equals(")")) {
                if (isOperator(tokenPlusOne) && isOperand(tokenPlusTwo)) {
                    //Case 3
                    System.out.println("Case 3");
                    BinaryTreeOfString t = new BinaryTreeOfString();
                    t.addRoot(operators.get(0));
                    t.addLeft(operands.get(0), t.getRoot());
                    t.addRight(operands.get(1), t.getRoot());

                    BinaryTreeOfString t2 = new BinaryTreeOfString();
                    t2.addRoot(tokenPlusOne);
                    t2.addLeft(tokenPlusTwo, t2.getRoot());

                    t2.addSubtreeRight(t, t2.getRoot());

                    subtrees.add(t2);

                    if (i + 2 <= expression.length() - 1) {
                        i += 2;
                    }
                } else if (operands.size() == 2 && operators.size() == 1) {
                    //Case 1
                    System.out.println("Case 1");
                    BinaryTreeOfString t = new BinaryTreeOfString();
                    t.addRoot(operators.get(0));
                    t.addLeft(operands.get(0), t.getRoot());
                    t.addRight(operands.get(1), t.getRoot());
                    subtrees.add(t);
                }
                operands.clear();
                operators.clear();
            }

        }

        System.out.println("count roots: " + roots.size());
        System.out.println("count trees: " + subtrees.size());
        return subtrees;
    }

}
