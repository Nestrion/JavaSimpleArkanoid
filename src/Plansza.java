import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class Plansza extends JPanel implements MouseMotionListener, MouseListener
{
    Belka b;
    BelkaEnemy[] bs;
    Kulka a;
    SilnikKulki s;
    int offset;
    CustomInt score;
    int MAX_BELKI;
    CustomBool game_running;
    boolean game_started;
    Plansza()
    {
        super();
        game_started = false;
        game_running = new CustomBool(false);
        score = new CustomInt(0);
        MAX_BELKI = 16;
        addMouseMotionListener(this);
        addMouseListener(this);
        offset = 45;
        b=new Belka(100);


        // Belki "wrogie" zaimplementowane przy pomocy klasy dziedziczącej z Rectangle2D.Float
        // oraz przechowywane w tablicy
        bs= new BelkaEnemy[]{new BelkaEnemy(10,10),new BelkaEnemy(80,10),new BelkaEnemy(150,10),new BelkaEnemy(220,10),
                new BelkaEnemy(10,30),new BelkaEnemy(80,30),new BelkaEnemy(150,30),new BelkaEnemy(220,30),
                new BelkaEnemy(10,50),new BelkaEnemy(80,50),new BelkaEnemy(150,50),new BelkaEnemy(220,50),
                new BelkaEnemy(10,70),new BelkaEnemy(80,70),new BelkaEnemy(150,70),new BelkaEnemy(220,70)};
        a=new Kulka(this,100,100,1,1);
        s=new SilnikKulki(a, bs, b, score, game_running);
        for (int i = 0; i < bs.length; i++)
        {
            bs[i].x += offset;
        }
    }
    public boolean checkIfHit()
    {
        boolean revert = true;

        for (int i = 0; i < bs.length; i++)
        {

        }

        return revert;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        g2d.fill(a);
        g2d.fill(b);
        for (int i = 0; i < bs.length; i++)
        {
            if (i < 4)
            {
                g2d.setColor(Color.BLACK);
                g2d.fill(bs[i]);

            }
            else if (i < 8)
            {
                g2d.setColor(Color.DARK_GRAY);
                g2d.fill(bs[i]);

            }
            else if (i < 12)
            {
                g2d.setColor(Color.GRAY);
                g2d.fill(bs[i]);
            }
            else if (i < 16)
            {
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fill(bs[i]);
            }


        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("TimesNewRoman",Font.PLAIN,24));
        if (score.i >= MAX_BELKI)
        {
            game_running.x = false;
            g2d.setFont(new Font("TimesNewRoman",Font.BOLD,24));
            g2d.drawString("You won!",140,140);
        }
        else
        {
            g2d.drawString("Score: " + score.i,0,320);
        }


        if (a.y > 320)
        {
            game_running.x=false;
            g2d.setFont(new Font("TimesNewRoman",Font.BOLD,24));
            g2d.drawString("You lost!",140,140);
        }

        if (!game_running.x && !game_started)
        {
            a.x = b.x + b.width/2 - a.width/2;
            a.y = b.y - 20;
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        b.setX(e.getX()- (int)b.width/2);
        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse clicked event
        if (!game_started)
        {
            game_running.x = true;
            game_started = true;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Handle mouse pressed event
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse released event
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse entered event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exited event
    }


}
