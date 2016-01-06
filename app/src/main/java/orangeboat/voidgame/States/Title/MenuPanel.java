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
    boolean loadAnimation;
    private int x,y,playX,playY,height,width;
    Paint paint = new Paint();
    //hitbox for the play button
    private Bitmap spritesheet;
    private Animation animation = new Animation();
    public MenuPanel(int numFrames, Bitmap res, Bitmap res2)
    {
        this.numFrames = numFrames;
        if(numFrames == 0)
            spritesheet = res;
        else {
            spritesheet = res;
            loadAnimation = true;
        }
        play = res2;
        playX = res2.getWidth();
        playY = res2.getHeight();
    }
    public void update()
    {
        if(numFrames!=0 && rectPlay!=null)
        animation.update();
    }
    public void load(int w, int h)
    {
        x= (int) (PhoneSpecs.width/3.3);
        y= (int) (PhoneSpecs.height/1.3);
        rectPlay = new Rect(x,y, (x + playX), (y + playY));
        height = h;
        width = w;
        if(loadAnimation)
        {
            Bitmap[] image = new Bitmap[numFrames];
            for (int i = 0; i < image.length; i++) {
                Bitmap bitmap = Bitmap.createBitmap(spritesheet, i * 1140, 0, 1140, 720);
                image[i] = Bitmap.createScaledBitmap(bitmap,w,h,false);
            }
            animation.setFrames(image);
            animation.setDelay(95);
        }
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


