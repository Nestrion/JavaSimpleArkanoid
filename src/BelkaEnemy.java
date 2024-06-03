import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;


class BelkaEnemy extends Rectangle2D.Float
{
    int dx;
    BelkaEnemy(int x, int y)
    {
        this.x=x;
        this.y=y;
        this.width=60;
        this.height=10;
    }

    void setX(int x)
    {
        this.x=x;
    }
    void setDX(int dx)
    {
        this.dx=dx;
    }

}