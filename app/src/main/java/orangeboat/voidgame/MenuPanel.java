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
    public Rect rectPlay;
    private int x,y,playX,playY;
    x= (int) (PhoneSpecs.width/3.3);
    y= (int) (PhoneSpecs.height/1.3);
    //hitbox for the play button

    public MenuPanel(Bitmap res, Bitmap res2)
    {
        background = res;
        play = res2;
        playX = res2.getWidth();
        playY = res2.getHeight();
        rectPlay = new Rect(x,y,(x+playX),(y+playY));
}
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        canvas.drawBitmap(background,0,0, null);
        canvas.drawBitmap(play,x,y,null);
        canvas.drawRect(rectPlay, paint);
    }
}


