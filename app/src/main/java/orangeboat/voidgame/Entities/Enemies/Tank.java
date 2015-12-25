package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.Entities.Enemy;

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
    public void update(){

    }
    public void load(int flippyX, int flippyY, int offset){
        super.load(flippyX,flippyY,offset);
        dx = ((int) (phoneWidth * 0.01));
        super.update();
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(tankAnimation.getImage(),x, y,null);
        super.draw(canvas);
    }
    public Rect getRectTank(){
        return hitbox;
    }
}
