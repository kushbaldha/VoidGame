package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 11/30/2015.
 */
public class Landie extends Enemy
{
    Animation landieAnimation;
    Bitmap landieImage;
    int phoneWidth;
    int dx;
    public static final int id = 3;
    public Landie(Animation landieAnimation, Bitmap landieImage , int health)
    {
        super(landieImage,landieAnimation , health);// takes 5 bullets to kill. Means that one visual bullet is actually 2 bullets?
        this.landieAnimation = landieAnimation;
        this.landieImage = landieImage;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyx, int levellength)
    {
        if(moveLeft && skyx > levellength)
        {
            x -= dx;
        }
        else if(moveRight && skyx < 0)
        {
            x += dx;
        }
        landieAnimation.update();
        super.update();
    }
    public void load(int landieX, int landieY, int offset)
    {
        phoneWidth=  (PhoneSpecs.width);
        dx = ((int) (phoneWidth * 0.01));
        x = landieX + offset;
        y= landieY;
        super.update();
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(landieAnimation.getImage(),x, y,null); //the plus 500 lowers the platforms, landie, and hitbox
        super.draw(canvas);
    }
    public Rect getRectLandie()
    {
        return hitbox;
    }
}
