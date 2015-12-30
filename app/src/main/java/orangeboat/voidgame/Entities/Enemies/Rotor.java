package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.Entities.Enemy;

/**
 * Created by Jay on 12/25/2015.
 */
public class Rotor extends Enemy{
    Animation rotorAnimation;
    Bitmap rotorImg;
    int dx;
    public static final int id = 7;
    public Rotor(Animation rotorAnimation, Bitmap rotorImg , int health){
        super(rotorImg,rotorAnimation,health);
        this.rotorAnimation = rotorAnimation;
        this.rotorImg = rotorImg;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength)
    {
        rotorAnimation.update();
        super.update(moveLeft, moveRight, skyX, levelLength);
    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(rotorAnimation.getImage(),x, y,null);
        super.draw(canvas);
    }
    public Rect getRectRotor(){
        return hitbox;
    }
}
