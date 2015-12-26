package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.Entities.Enemy;

/**
 * Created by Jay on 12/25/2015.
 */
public class Umbrack extends Enemy {
    Animation umbrackAnimation;
    Bitmap umbrackImg;
    int dx;
    public static final int id = 6;
    public Umbrack(Animation umbrackAnimation, Bitmap umbrackImg , int health){
        super(umbrackImg,umbrackAnimation,health);
        this.umbrackAnimation = umbrackAnimation;
        this.umbrackImg = umbrackImg;
    }
    public void update(){

    }
    public void load(int umbrackX, int umbrackY, int offset){
        super.load(umbrackX,umbrackY,offset);
        dx = ((int) (phoneWidth * 0.01));
        super.update();
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(umbrackAnimation.getImage(),x, y,null);
        super.draw(canvas);
    }
    public Rect getRectUmbrack(){
        return hitbox;
    }
}
