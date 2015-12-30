package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Kush on 11/30/2015.
 */
public class Landie extends Enemy
{
    Animation landieAnimation;
    Bitmap singleLandieImage;
    public static final int id = 3;
    public Landie(Animation landieAnimation, Bitmap singleLandieImage, int health)
    {
        super(singleLandieImage,landieAnimation , health);// takes 5 bullets to kill. Means that one visual bullet is actually 2 bullets?
        this.landieAnimation = landieAnimation;
        this.singleLandieImage = singleLandieImage;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength)
    {
        landieAnimation.update();
        super.update(moveLeft, moveRight, skyX, levelLength);
    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);
    }
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawBitmap(landieAnimation.getImage(),x, y,null); //the plus 500 lowers the platforms, landie, and hitbox

    }
    public Rect getRectLandie()
    {
        return hitbox;
    }
}
