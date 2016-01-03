package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Jay on 12/25/2015.
 */
public class Tank extends Enemy{
    Animation tankAnimation;
    Bitmap tankImg;
    int dx;
    public static final int id = 5;
    public Tank(Animation tankAnimation, Bitmap tankImg , int health){
        super(tankImg,tankAnimation,health);
        this.tankAnimation = tankAnimation;
        this.tankImg = tankImg;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength, int charX, int charY, boolean hitWall)
    {
        super.update(moveLeft, moveRight, skyX, levelLength, hitWall);
        if(awake)
        tankAnimation.update();
    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);
    }
    public void draw(Canvas canvas){
        if(awake)
        canvas.drawBitmap(tankAnimation.getImage(),x, y,null);
        super.draw(canvas);
    }
    public Rect getRectTank(){
        return hitbox;
    }
}
