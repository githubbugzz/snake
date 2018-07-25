import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {
    //��������һ��һ�ڹ��ɣ�������node,ͷ��β����node
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private Node n = new Node(10,10,Dir.L);
    public Snake(Node nodes){
        head = nodes;
        tail = nodes;
        size = 1;
    }
    public Snake(){
        head=n;
        tail=n;
        size=1;
    }
    private void addToTail(){//�ӵ�β����
        Node node = null;
        switch (tail.dir){
            case L:
                node = new Node(tail.row,tail.col+1,tail.dir);
                break;
            case D:
                node = new Node(tail.row-1,tail.col,tail.dir);
                break;
            case R:
                node = new Node(tail.row,tail.col-1,tail.dir);
                break;
            case U:
                node = new Node(tail.row+1,tail.col,tail.dir);
                break;
        }
        tail.next = node;//���½���node��ԭ��������������
        tail = node; //��tail���൱��һ���α꣩����β�ڵ�
        size++;
    }
    private void addToHead() {
        Node  node = null;
        switch (head.dir){
            case L:
                node = new Node(head.row,head.col-1,head.dir);
                break;

            case D:
                node = new Node(head.row+1,head.col,head.dir);
                break;
            case R:
                node = new Node(head.row,head.col+1,head.dir);
                break;
            case U:
                node = new Node(head.row-1,head.col,head.dir);
                break;
        }
        node.next=head;
        head=node;
        size++;
    }
    public void draw(Graphics g){
        if(size<=0) return ;
        for(Node n = head;n!=null;n=n.next){
            n.draw(g);
        }
        move();
    }

    private void move() {//Ϊʲô����move�Ժ��ٻ�������
        addToHead();
        deleteFormTail();
    }

    private void deleteFormTail() {
        if(size==0) return;
        //ɾ�����һ���ڵ�
        Node temp = null;
        for(Node n =head;n.next!=null;n=n.next){
            temp = n;
        }
        n.next = null;
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                head.dir = Dir.L;
                break;
            case KeyEvent.VK_RIGHT:
                head.dir = Dir.R;
                break;
            case KeyEvent.VK_UP:
                head.dir = Dir.U;
                break;
            case KeyEvent.VK_DOWN:
                head.dir = Dir.D;
                break;
        }
    }

    private class Node{
        int w = Yard.BLOCK_SIZE;
        int h = Yard.BLOCK_SIZE;
        int row,col;//�ڼ��еڼ���
        Dir dir = Dir.L;
        Node next = null;
        Node(int row ,int col,Dir dir){
            this.row =row;
            this.col = col;
            this.dir = dir;
        }
        void draw(Graphics g){
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(Yard.BLOCK_SIZE*col,Yard.BLOCK_SIZE*row,w,h);
            g.setColor(c);
        }
    }
}
