import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {

    //有几个横的小格子，有几个竖的小格子
    public   static final int ROWS = 25;
    public  static final int COLS = 25;
    public static final int BLOCK_SIZE = 20;
    Snake snake = new Snake();
    /*Image offScreenImage = null;解决双缓冲，每次都要刷背景*/
    public static void main(String[] args) {
        new Yard().launch();
    }
    public void launch(){
        this.setLocation(200,200);//设置位置
        this.setSize(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);//设置大小
        this.addWindowListener(new WindowAdapter() {//增加窗口关闭按钮事件
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        this.setVisible(true);//可视化
        new Thread(new PaintThread()).start();
    }
    @Override
    public void paint(Graphics graphics){
        Color c = graphics.getColor();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0,0,COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
        //划出横线
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
    //让小蛇动起来，用线程,让小蛇动起来就是每个一段时间刷新一下图案，也就要用到线程每过一段时间就刷新蛇的状态,在launch（）中让线程起来
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

    //键盘监听让蛇移动起来
    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            snake.keyPressed(e);
        }

    }
}
