import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {

    //�м������С���ӣ��м�������С����
    public   static final int ROWS = 25;
    public  static final int COLS = 25;
    public static final int BLOCK_SIZE = 20;
    Snake snake = new Snake();
    /*Image offScreenImage = null;���˫���壬ÿ�ζ�Ҫˢ����*/
    public static void main(String[] args) {
        new Yard().launch();
    }
    public void launch(){
        this.setLocation(200,200);//����λ��
        this.setSize(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);//���ô�С
        this.addWindowListener(new WindowAdapter() {//���Ӵ��ڹرհ�ť�¼�
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        this.setVisible(true);//���ӻ�
        new Thread(new PaintThread()).start();
    }
    @Override
    public void paint(Graphics graphics){
        Color c = graphics.getColor();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0,0,COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
        //��������
        graphics.setColor(Color.DARK_GRAY);
        for(int i =1;i<ROWS;i++){
            graphics.drawLine(0,BLOCK_SIZE*i,COLS*BLOCK_SIZE,BLOCK_SIZE*i);
        }
        for (int i =1;i<COLS;i++){
            graphics.drawLine(BLOCK_SIZE*i,0,BLOCK_SIZE*i,ROWS*BLOCK_SIZE);
        }
        graphics.setColor(c);
        snake.draw(graphics);
    }
    //��С�߶����������߳�,��С�߶���������ÿ��һ��ʱ��ˢ��һ��ͼ����Ҳ��Ҫ�õ��߳�ÿ��һ��ʱ���ˢ���ߵ�״̬,��launch���������߳�����
    private class PaintThread implements Runnable{
        @Override
        public void run() {
            while (true){
                repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //���̼��������ƶ�����
    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            snake.keyPressed(e);
        }

    }
}
