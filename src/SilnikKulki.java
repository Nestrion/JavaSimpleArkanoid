import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;


class SilnikKulki extends Thread
{
    Kulka a;
    BelkaEnemy[] belki;
    Belka gracz;
    CustomInt score;
    CustomBool game_running;
    boolean ball_hit;

    SilnikKulki(Kulka a,  BelkaEnemy[] belki, Belka gracz, CustomInt score, CustomBool game_running)
    {
        this.game_running = game_running;
        this.a=a;
        this.belki=belki;
        this.gracz=gracz;
        this.score=score;
        start();
    }

    public void checkIfHitBelka(Rectangle2D b)
    {
        // zmień dy dla góry i dołu
        // zmień dx dla lewej i prawej
        ball_hit = false;


        // Odbicie kulki od belek zaimplementowane dzięki metodzie intersects
        if (a.intersects(b))
        {
            // kolizje
            if (b.equals(gracz))
            {
                float top_distance = Math.abs((a.y + a.height/2) - (float)b.getY());
                float bot_distance = Math.abs((a.y + a.height/2) - (float)(b.getY() + b.getHeight()));
                float left_distance = Math.abs((a.x + a.width/2) - (float)b.getX());
                float right_distance = Math.abs((a.x + a.width/2) - (float)(b.getX() + b.getWidth()));

                if (bot_distance < left_distance && bot_distance < top_distance && bot_distance < right_distance)
                {
                    a.y = (float)(b.getY()+b.getHeight());
                }
                else if (top_distance < left_distance && top_distance < bot_distance && top_distance < right_distance)
                {
                    a.y = (float)(b.getY()) - a.height;
                }
                else if (left_distance < bot_distance && left_distance < top_distance && left_distance < right_distance)
                {
                    a.x = (float)(b.getX()) - a.width;
                }
                else if (right_distance < left_distance && right_distance < top_distance && right_distance < bot_distance)
                {
                    a.x = (float)(b.getX()+b.getWidth());
                }
            }


            if (a.x + a.width/2 >= b.getX() && a.x + a.width/2 <= b.getX() + b.getWidth() )
            {
                a.dy = -a.dy;
                ball_hit = true;

            }
            else if (a.y + a.height/2 >= b.getY() && a.y + a.height/2<= b.getY() + b.getHeight() )
            {
                a.dx = -a.dx;
                ball_hit = true;
            }

            // usuwanie belek z pola widzenia oraz naliczanie punktów
            if (!b.equals(gracz))
            {
                if (!ball_hit)
                {
                    a.dy = -a.dy;
                }
                b.setRect(20000,1,1,1);
                score.i += 1;
            }
        }
    }



    public void run()
    {
        try
        {
            while(true)
            {
                System.out.println(game_running.x);
                if (game_running.x)
                {
                    a.nextKrok();
                    for (int i = 0; i < belki.length; i++)
                    {
                        checkIfHitBelka(belki[i]);
                    }
                    checkIfHitBelka(gracz);
                    sleep(5);
                }
            }
        }
        catch(InterruptedException e){}
    }
}