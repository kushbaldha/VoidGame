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
    int landieX,landieY;
    Animation landieAnimation;
    Bitmap landieImage;
    Paint paint;
    Rect rectLandie;
    int health;
    int phoneWidth,phoneHeight;
    int dx;
    public static final int id = 2;
    public Landie(Animation landieAnimation, Bitmap landieImage)
    {
        super(landieImage,landieAnimation);
        this.landieAnimation = landieAnimation;
        this.landieImage = landieImage;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        health = 10; // takes 5 bullets to kill. Means that one visual bullet is actually 2 bullets?
    }
    public void update(boolean moveLeft, boolean moveRight, int skyx, int levellength)
    {
        if(moveLeft && skyx > levellength)
        {
            landieX -= dx;
        }
        else if(moveRight && skyx < 0)
        {
            landieX += dx;
        }
        landieAnimation.update();
        rectLandie = new Rect(landieX,landieY,(landieX+TH),(landieY+TH));
    }
    public void load(int landieX, int landieY, int offset)
    {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        dx = ((int) (phoneWidth * 0.01));
        this.landieX = landieX + offset;
        this.landieY = landieY;
        rectLandie = new Rect(landieX,landieY,(landieX+TW),(landieY+TH));
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(rectLandie,paint);
        canvas.drawBitmap(landieAnimation.getImage(),landieX, landieY,null);
    }
    public Rect getRectLandie()
    {
        return rectLandie;
    }
    public int hit()
    {
        health--;
        return health;
    }
}
