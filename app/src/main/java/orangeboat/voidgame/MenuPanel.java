package orangeboat.voidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Kush on 11/15/2015.
 */
public class MenuPanel
{
    private Bitmap background;
    private Bitmap play;
    // images for background and play button
    private Rect rectPlay;
    private int x,y,playX,playY;
    //hitbox for the play button

    public MenuPanel(Bitmap res, Bitmap res2)
    {
        background = res;
        play = res2;
        playX = res2.getWidth();
        playY = res2.getHeight();
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        canvas.drawBitmap(background,0,0, null);
        x=canvas.getWidth()/2;
        y= canvas.getHeight()-300;
        canvas.drawBitmap(play,x,y,null);
        rectPlay = new Rect(x,y,(x+playX),(y+playY));
        canvas.drawRect(rectPlay,paint);
    }
}


