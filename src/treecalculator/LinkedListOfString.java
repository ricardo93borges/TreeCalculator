/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treecalculator;

/**
 *
 * @author Ricardo
 */
public class LinkedListOfString {

    private static class Node<String> {

        private String element;
        private Node<String> next;

        public Node(String element, Node<String> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<String> head = null;
    private Node<String> tail = null;
    private int count = 0;

    public LinkedListOfString() {
        head = null;
        tail = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public void add(String element) {
        Node n = new Node(element, null);
        if (count == 0) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        count++;
    }

    public String get(int index) {
        if (index < 0 || index >= count) {
            return "Erro";
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.element.toString();
    }

    public void add(int index, String element) {
        if (index < 0 || index > count) {
            System.out.println("erro");
            return;
        }
        Node n = new Node(element, null);
        if (index == 0) {
            if (count == 0) {
                tail = n;
                head = n;
            } else {
                n.next = head;
                head = n;
            }
        } else if (index == count) {
            tail.next = n;
            tail = n;
        } else {
            Node aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            n.next = aux.next;
            aux.next = n;
        }
        count++;
    }

    public String set(int index, String element) {
        if (index < 0 || index > count) {
            System.out.println("erro");
            return "Erro";
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        String e = aux.element.toString();
        aux.element = element;
        return e;
    }

    boolean remove(String element) {
        if (count == 0) {
            return false;
        }
        if (element == head.element) {
            head = head.next;
            if (count == 1) {
                tail = null;
            }
            count--;
            return true;
        }

        Node aux = head.next;
        Node ant = head;
        while (aux != null) {
            if (element == aux.element) {
                if (aux == tail) {
                    tail = ant;
                    tail.next = null;
                } else {
                    ant.next = aux.next;
                }
                count--;
                return true;
            } else {
                aux = aux.next;
                ant = ant.next;
            }
        }
        return false;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }
}
