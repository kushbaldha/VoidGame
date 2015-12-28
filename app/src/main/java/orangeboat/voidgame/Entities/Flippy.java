package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Jay on 12/24/2015.
 */
public class Flippy extends Enemy{
    Animation flippyAnimation;
    Bitmap flippyImg;
    int dx;
    public static final int id = 4;
    public Flippy(Animation flippyAnimation, Bitmap flippyImg , int health){
        super(flippyImg,flippyAnimation,health);
        this.flippyAnimation = flippyAnimation;
        this.flippyImg = flippyImg;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength)
    {
        flippyAnimation.update();
        super.update(moveLeft, moveRight, skyX, levelLength);
    }
    public void load(int flippyX, int flippyY, int offset){
        super.load(flippyX,flippyY,offset);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(flippyAnimation.getImage(),x, y,null);
        super.draw(canvas);
    }
    public Rect getRectFlippy(){
        return hitbox;
    }
}
