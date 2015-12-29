package orangeboat.voidgame.States.Title;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;


public class MenuPanel
{
    //private Bitmap background;
    private Bitmap play;
    // images for background and play button
    public Rect rectPlay;
    int numFrames;
    private int x,y,playX,playY,height,width;
    Paint paint = new Paint();
    //hitbox for the play button
    private Bitmap spritesheet;
    private Animation animation = new Animation();
    public MenuPanel(int numFrames, int w , int h, Bitmap res, Bitmap res2)
    {
        this.numFrames = numFrames;
        if(numFrames == 0)
            spritesheet = res;
        else {
            Bitmap[] image = new Bitmap[numFrames];
            spritesheet = res;
            height = h;
            width = w;
            for (int i = 0; i < image.length; i++) {
                image[i] = Bitmap.createBitmap(spritesheet, i * width, 0, width, height);
            }
            animation.setFrames(image);
            animation.setDelay(95);
        }
        play = res2;
        playX = res2.getWidth();
        playY = res2.getHeight();
    }
    public void update()
    {
        if(numFrames!=0)
        animation.update();
    }
    public void load()
    {
        x= (int) (PhoneSpecs.width/3.3);
        y= (int) (PhoneSpecs.height/1.3);
        rectPlay = new Rect(x,y, (x + playX), (y + playY));
    }
    public void draw(Canvas canvas){

        paint.setColor(Color.TRANSPARENT);
        if(numFrames==0)
            canvas.drawBitmap(spritesheet,0,0,null);
        else
        canvas.drawBitmap(animation.getImage(), 0, 0, null);
        canvas.drawBitmap(play,x,y,null);
        canvas.drawRect(rectPlay, paint);
    }
    public void remove()
    {
        paint = null;
        animation = null;
        rectPlay = null;
        play = null;
        // images for background and play button
        //hitbox for the play button
        spritesheet = null;

    }
    }


