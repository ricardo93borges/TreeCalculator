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
public class BinaryTreeOfString {
 
     private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        private String element;

        public Node(String element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    // Atributos
    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz

    // Metodos
    public BinaryTreeOfString() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public String getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void setRoot(String element) {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root.element = element;
    }
    
    public Integer getParent(Integer element) {
        // Implementar
        return null;
    }

    public int count() {
        return count(root);
    }
    
    private int count(Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + count(n.left) + count(n.right);
        }
    }

    public int height() {
        Queue<Node> fila = new Queue<>();
        Node aux = null;
        Node fim = null;
        if (root != null) {
            fila.enqueue(root);
            while(!fila.isEmpty()) {
                aux = fila.dequeue();
                if (aux.left != null)
                    fila.enqueue(aux.left);
                if (aux.right != null)
                    fila.enqueue(aux.right);
                fim = aux;
            }
        }                  
        int cont=0;        
        while (fim != root) {
            cont++;
            fim = fim.father;
        }        
        return cont;     
    }
    
    public boolean addRoot(String element) {
        //Implementar
        if (root != null){
            return false;
        }
        
        Node node = new Node(element);
        root = node;
        count++;
        return true;
    }

    public boolean addLeft(String element, String father) {
        Node n = searchNodeRef(father, root);
        if (n == null)
            return false;
        if (n.left != null) 
            return false;
        Node left = new Node(element);
        n.left = left;
        left.father = n;
        count++;
        return true;
        
    }
    
    public boolean addRight(String element, String father) {
        Node n = searchNodeRef(father, root);
        if (n == null)
            return false;
        if (n.right != null) 
            return false;
        Node right = new Node(element);
        n.right = right;
        right.father = n;
        count++;
        return true;
    }    

    public boolean removeBranch(String element) {
        Node n = this.searchNodeRef(element, root);
        
        if (n == null) {
            return false;
        }
        
        if (n == root) {
            root = null;
            count = 0;
            return true;
        }
        
        Node father = n.father;
    
        if (father.left == n) {
            father.left = null;
        } else {
            father.right = null;
        }
        n.father = null;
        count = count - this.count(n);
        return true;
    }
    
    public boolean addSubtreeLeft(BinaryTreeOfString subtree, String father){
        Node n = this.searchNodeRef(father, root);
        if(n == null) return false;

        if(this.hasLeft(n.element)) return false;
        
        if(subtree.root == null) return false;
        
        n.left = subtree.root;
        n.left.father = n;
        n.left.left = subtree.root.left;
        n.left.right = subtree.root.right;
        subtree.root.father = n;

        count = count + this.count(n);
        
        return true;
    }
    
    public boolean addSubtreeRight(BinaryTreeOfString subtree, String father){
        Node n = this.searchNodeRef(father, root);
        if(n == null) return false;

        if(this.hasRight(n.element)) return false;
        
        if(subtree.root == null) return false;
        
        n.right = subtree.root;
        n.right.father = n;
        n.right.left = subtree.root.left;
        n.right.right = subtree.root.right;

        count = count + this.count(n);
        
        return true;
    }

    public Integer set(Integer old, Integer element) {
        //Implementar
        return null;
    }
    
    public boolean isExternal(int element) {
        // Implementar
        return false;
    }

    public boolean isInternal(int element) {
        // Implementar
        return false;
    }
    
    public boolean hasLeft(String element) {
        Node n = this.searchNodeRef(element, root);
        if (n != null)
            if ( n.left != null)
                return true;
        return false;
    }
    
    public boolean hasRight(String element) {
        Node n = this.searchNodeRef(element, root);
        if (n != null)
            if ( n.right != null)
                return true;
        return false;
    }    
            
    public String getLeft(String element) {
        Node n = this.searchNodeRef(element, root);
        if (n != null)
            if ( n.left != null)
                return n.left.element;
        return null;
    }

    public String getRight(String element) {
        Node n = this.searchNodeRef(element, root);
        if (n != null)
            if ( n.right != null)
                return n.right.element;
        return null;
    }

    public LinkedListOfString positionsPre() {
        LinkedListOfString res = new LinkedListOfString();
        positionsPreAux(root, res);
        return res;
    }
    private void positionsPreAux(Node n, LinkedListOfString res) {
        if(n == null)
            return;
        res.add(n.element);
        if(n.left != null)
            positionsPreAux(n.left, res);
        if(n.right != null)
            positionsPreAux(n.right, res);
    }

    public LinkedListOfString positionsPos() {
        LinkedListOfString res = new LinkedListOfString();
        positionsPosAux(root, res);
        return res;
    }
    
    private void positionsPosAux(Node n, LinkedListOfString res) {
        if(n == null)
            return;
        if(n.left != null)
            positionsPosAux(n.left, res);
        if(n.right != null)
            positionsPosAux(n.right, res);
        res.add(n.element);
    }

    public LinkedListOfString positionsCentral() {
        LinkedListOfString res = new LinkedListOfString();
        positionsCentralAux(root, res);
        return res;
    }
    private void positionsCentralAux(Node n, LinkedListOfString res) {
        if(n == null)
            return;
        if(n.left != null)
            positionsCentralAux(n.left, res);
        res.add(n.element);
        if(n.right != null)
            positionsCentralAux(n.right, res);

    }

    public LinkedListOfString positionsWidth() {
        LinkedListOfString li = new LinkedListOfString();
        Queue<Node> fila = new Queue<>();
        Node aux = null;
        if (root != null) {
            fila.enqueue(root);
            while(!fila.isEmpty()) {
                aux = fila.dequeue();
                if (aux.left != null)
                    fila.enqueue(aux.left);
                if (aux.right != null)
                    fila.enqueue(aux.right);
                li.add(aux.element);
            }
        }        
        return li;
    }

    public int level (String element) {
        Node n = this.searchNodeRef(element, root);
        if (n==null)
            return -1;
        int cont=0;        
        while (n!= root) {
            cont++;
            n = n.father;
        }        
        return cont;
    }
            
    public String strPositionsPre() {
        return strPositionsPre(root);
    }
    private String strPositionsPre(Node n) {
        String res = "";
        // Implementar
        return res;
    }

    public String strPositionsPos() {
        return strPositionsPos(root);
    }
    private String strPositionsPos(Node n) {
        String res = "";
        // Implementar
        return res;
    }

    public String strPositionsCentral() {
        return strPositionsCentral(root);
    }
    private String strPositionsCentral(Node n) {
        String res = "";
        // Implementar
        return res;
    }

    public boolean contains(String element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    private Node searchNodeRef(String element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.element.equals(element)) {
                res = target;
            } else {
                res = searchNodeRef(element, target.left);
                if (res == null) {
                    res = searchNodeRef(element, target.right);
                }
            }
        }
        return res;
    }


}
