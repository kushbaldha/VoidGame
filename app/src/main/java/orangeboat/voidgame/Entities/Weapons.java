package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 12/5/2015.
 */
public class Weapons {
    int slashX, slashY, slashImgX, slashImgY;
    int phoneWidth,phoneHeight;
    Rect rectSlash;
    public boolean showSlash;
    public Animation slash1 = new Animation();
    public Animation slash2 = new Animation();
    public Animation slash3 = new Animation();
    public Animation slash4 = new Animation();
    Bitmap slash;
    Bitmap[] slashes1 = new Bitmap[4];
    Bitmap[] slashes2 = new Bitmap[4];
    Bitmap[] slashes3 = new Bitmap[4];
    Bitmap[] slashes4 = new Bitmap[4];

    public Weapons(Bitmap slash)
    {
        slashImgX = slash.getWidth() / 16;
        slashImgY = slash.getHeight();
        this.slash = slash;
    }
    public void load()
    {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
      /*for (int i = 4; i < 4+slashes2.length; i++)
        {
            slashes2[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
        for (int i = 8; i < 8+slashes3.length; i++)
        {
            slashes3[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
        for (int i = 12; i < 12+slashes4.length; i++)
        {
            slashes4[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }*/
        //loading up the animation classes
        slash1.setFrames(slashes1);
        slash1.setDelay(30);
        /*slash2.setFrames(slashes2);
        slash2.setDelay(30);
        slash3.setFrames(slashes3);
        slash3.setDelay(30);
        slash4.setFrames(slashes4);
        slash4.setDelay(30);*/
        slashX = slashImgX + (phoneWidth/2);
        slashY = (int) (phoneHeight/1.49);
        rectSlash = new Rect(slashX, slashY,(slashX + slashImgX),(slashY + slashImgY));
        int d = 100;
        for (int i = 0; i < slashes1.length; i++)
        {
            slashes1[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
    }
    public void update()
    {

        if(slash1.playedOnce()) {
            setShowSlash(false);
        }
        slash1.update();
        //slash2.update();
        //slash3.update();
        //slash4.update();

    }
    public void draw(Canvas canvas)
    {
        if(showSlash)
        {
            //if(moveLeft)
            canvas.drawBitmap(slash1.getImage(),slashX,slashY,null);
            //else if(moveRight)
            // canvas.drawBitmap(slash1.getImage(), slashX, slashY, null);
        }
    }
    public void setShowSlash(boolean b)
    {
        showSlash = b;
    }

}
