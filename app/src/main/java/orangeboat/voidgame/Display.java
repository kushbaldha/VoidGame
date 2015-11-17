package orangeboat.voidgame;

import android.graphics.Canvas;

/**
 * Created by Kush on 11/16/2015.
 */
public class Display
{
    public static final int WIDTH= 1900;
    public static final int HEIGHT = 1200;
    int phoneWidth,phoneHeight;
    public static int CANVASWIDTH;
    public static int CANVASHEIGHT;


    public MenuPanel menu;
    public Display()
    {

    }
    public Display(int phoneWidth,int phoneHeight, MenuPanel menu)
    {
    this.phoneWidth = phoneWidth;
    this.phoneHeight = phoneHeight;
    this.menu = menu;
    }
    public void draw(Canvas canvas)
    {
        CANVASWIDTH = canvas.getWidth();
        CANVASHEIGHT = canvas.getHeight();
        final float scaleFactorX = phoneWidth/(WIDTH*1.f);//make sure they are floats
        final float scaleFactorY = phoneHeight/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            menu.draw(canvas);
            //return to savedstate. If we didn't have this, it would keep on scaling. So we do this to return it to original state
            canvas.restoreToCount(savedState);
        }
    }
}
