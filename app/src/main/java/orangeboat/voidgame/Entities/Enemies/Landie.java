package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.Entities.Enemies.Enemy;
import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 11/30/2015.
 */
public class Landie extends Enemy
{
    Animation landieAnimation;
    Bitmap singleLandieImage;
    public static final int id = 3;
    boolean enemyMoveLeft = true;
    int max,dx;
    public Landie(Animation landieAnimation, Bitmap singleLandieImage, int health)
    {
        super(singleLandieImage, landieAnimation, health);// takes 5 bullets to kill. Means that one visual bullet is actually 2 bullets?
        this.landieAnimation = landieAnimation;
        this.singleLandieImage = singleLandieImage;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength) {
        super.update(moveLeft, moveRight, skyX, levelLength);
        if (awake) {
            /*if (max == dx * 30) {
                this.enemyMoveLeft = !this.enemyMoveLeft;
                max = 0;
            }
            if (enemyMoveLeft) {
                x -= dx;
                max += dx;
            }
            else {
                x += dx;
                max += dx;
            }
            */
          //  if(moveRight){x -= dx;}
          //  if(moveLeft){x+= dx;}

        }
        landieAnimation.update();
    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);
        max = 0;
        dx = (int)(PhoneSpecs.width*0.007);
    }
    public void draw(Canvas canvas)
    {
            super.draw(canvas);
        if(awake)
            canvas.drawBitmap(landieAnimation.getImage(), x, y, null);
        //the plus 500 lowers the platforms, landie, and hitbox

    }
    public Rect getRectLandie()
    {
        return hitbox;
    }
}
