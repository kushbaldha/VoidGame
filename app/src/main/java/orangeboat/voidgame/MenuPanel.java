package orangeboat.voidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class MenuPanel
{
    //private Bitmap background;
    private Bitmap play;
    // images for background and play button
    public Rect rectPlay;
    private int x,y,playX,playY,height,width;
    Paint paint = new Paint();
    //hitbox for the play button
    private Bitmap spritesheet;
    private Animation animation = new Animation();
    public MenuPanel(int numFrames, int w , int h, Bitmap res, Bitmap res2)
    {
        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;
        height = h;
        width = w;
        for(int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet,i*width,0,width,height);
        }
        animation.setFrames(image);
        animation.setDelay(95);
        play = res2;
        playX = res2.getWidth();
        playY = res2.getHeight();
    }
    public void update()
    {
        animation.update();
    }
    public void load()
    {
        x= (int) (PhoneSpecs.width/3.3);
        y= (int) (PhoneSpecs.height/1.3);
        rectPlay = new Rect(x,y,(x+playX),(y+playY));
    }
    public void draw(Canvas canvas){

        paint.setColor(Color.TRANSPARENT);
        canvas.drawBitmap(animation.getImage(), 0, 0, null);
        canvas.drawBitmap(play,x,y,null);
        canvas.drawRect(rectPlay, paint);
    }
    //random comment
}

