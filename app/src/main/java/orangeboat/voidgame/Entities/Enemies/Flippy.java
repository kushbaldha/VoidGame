package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Jay on 12/24/2015.
 */
public class Flippy extends Enemy {
    Animation flippyAnimation;
    Bitmap flippyImg;
    int dFlippyX, dFlippyY;
    public static final int id = 4;
    public Flippy(Animation flippyAnimation, Bitmap flippyImg , int health){
        super(flippyImg,flippyAnimation,health);
        this.flippyAnimation = flippyAnimation;
        this.flippyImg = flippyImg;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength, int charX, int charY, boolean hitWall)
    {
        super.update(moveLeft, moveRight, skyX, levelLength, hitWall);
        if(awake) {
            dFlippyX = (int)( (x- charX) / 20);
            dFlippyY = (int)( (y- charY) / 20);

        } x -= dFlippyX;
        y -= dFlippyY;
        if(awake) flippyAnimation.update();
    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);

    }
    public void draw(Canvas canvas){
        if(awake)
        canvas.drawBitmap(flippyAnimation.getImage(),x, y,null);
        super.draw(canvas);
    }
    public Rect getRectFlippy(){
        return hitbox;
    }
}
