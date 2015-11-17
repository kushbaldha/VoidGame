package orangeboat.voidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Kush on 11/15/2015.
 */
public class MenuPanel extends Display
{
    private Bitmap background;
    private Bitmap play;
    // images for background and play button
    private Rect rectPlay;
    //hitbox for the play button
    private int x = CANVASWIDTH/2;
    private int y = CANVASHEIGHT-300;

    public MenuPanel(Bitmap res, Bitmap res2)
    {
        background = res;
        play = res2;
        //rectPlay = new Rect(x,y,x-res2.getWidth(),res2.getHeight());
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
       // Paint paint = new Paint();
       // paint.setColor(Color.GREEN);
        canvas.drawBitmap(background,0,0, null);
        canvas.drawBitmap(play,x,y-300,null);
        //canvas.drawRect(rectPlay,paint);
    }
}


