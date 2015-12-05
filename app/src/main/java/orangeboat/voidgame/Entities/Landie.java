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
public class Landie
{
    int landieX,landieY,landieImgX,landieImgY;
    Animation landieAnimation;
    Bitmap landieImage;
    Paint paint;
    Rect rectLandie;
    int phoneWidth,phoneHeight;
    int dx;
    public Landie(Animation landieAnimation, Bitmap landieImage)
    {
        this.landieAnimation = landieAnimation;
        this.landieImage = landieImage;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        landieImgX = landieImage.getWidth();
        landieImgY = landieImage.getHeight();
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
        rectLandie = new Rect(landieX,landieY,(landieX+landieImgX),(landieY+landieImgY));
    }
    public void load()
    {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        dx = ((int) (phoneWidth * 0.01));
        landieX = (int) (phoneWidth/1.5);
        landieY = (int)(phoneWidth/2.5);
        rectLandie = new Rect(landieX,landieY,(landieX+landieImgX),(landieY+landieImgY));
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
}
