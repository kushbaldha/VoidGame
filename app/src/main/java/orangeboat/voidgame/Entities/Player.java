package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 11/22/2015.
 */
public class Player
{
    int phoneHeight,phoneWidth;
    int charX,charY,charImgX,charImgY;
    Bitmap mainChar;
    Bitmap charAnimationLeft;
    Bitmap charAnimationRight;
    Rect rectChar;
    Paint paint;
    Animation animation = new Animation();
    public Player(Bitmap mainChar, Bitmap charAnimationLeft, Bitmap charAnimationRight)
    {
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        this.mainChar = mainChar;
        charImgX = mainChar.getWidth();
        charImgY = mainChar.getHeight();
        this.charAnimationLeft = charAnimationLeft;
        this.charAnimationRight = charAnimationRight;
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(mainChar,charX,charY,null);
        canvas.drawRect(rectChar,paint);
    }
    public void load()
    {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        charX = (int) (phoneWidth/2);
        charY = (int) (phoneHeight/2);
        rectChar = new Rect(charX,charY,(charX+charImgX),(charY+charImgY));
    }
}
