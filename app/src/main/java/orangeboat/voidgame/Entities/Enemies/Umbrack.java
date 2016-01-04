package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Jay on 12/25/2015.
 */
public class Umbrack extends Enemy
{
    Animation umbrackAnimation;
    Animation rollAnimation;
    Bitmap umbrackImg;
    Random rand = new Random();
    int timer = 90;
    int attack = -1;
    int dx;
    int max = 0;
    boolean rollBack = false;
    boolean rolling = false;
    boolean startRolling = false;
    int rollingAnimationUpdate = 0;
    public static final int id = 6;
    public Umbrack(Animation umbrackAnimation, Bitmap umbrackImg , int health, Animation umRollAnimation)
    {
        super(umbrackImg,umbrackAnimation,health);
        this.umbrackAnimation = umbrackAnimation;
        this.umbrackImg = umbrackImg;
        this.rollAnimation = umRollAnimation;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength, int charX, int charY, boolean hitWall)
    {
        super.update(moveLeft, moveRight, skyX, levelLength, hitWall);
        if(awake)
        {
            if(attack == -1)
            timer--;
            if(timer == 0)
            {
                if(attack == -1)
                {
                    attack = rand.nextInt(1);
                    if(attack == 0)
                        startRolling = true;
                }
                if(attack == 0) // rolling
                {
                    rolling  = true;
                    if(!startRolling) {
                        if (rollBack) {
                            max -= dx;
                            x += dx;
                            if (max == 0) {
                                rollBack = false;
                                attack = -1;
                                timer = 90;
                                rolling = false;
                            }
                        } else {
                            max += dx;
                            x -= dx;
                            if (max == (dx * 40))
                                rollBack = true;
                        }
                        rollingAnimationUpdate++;
                        if (rollingAnimationUpdate == 5) {
                            rollAnimation.update();
                            rollingAnimationUpdate = 0;
                        }
                    }
                    else
                    {
                        rollingAnimationUpdate++;
                        if(rollingAnimationUpdate == 2)
                        {
                            rollAnimation.update();
                            rollingAnimationUpdate = 0;
                        }
                        if(rollAnimation.playedOnce())
                        {
                            startRolling = false;
                        }
                    }
                }
                if(attack == 1) // shooting
                {

                }
            }
            else
            umbrackAnimation.update();

        }

    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);
        x+=(phoneWidth/2-TW);
        dx = (int) (phoneWidth * 0.02);
    }
    public void draw(Canvas canvas)
    {
        if(awake)
        {
            if(rolling)
                canvas.drawBitmap(rollAnimation.getImage(),x,y,null);
            else
            canvas.drawBitmap(umbrackAnimation.getImage(),x, y,null);
        }
       // canvas.drawRect(hitbox,paint);
        super.draw(canvas);
    }
    public Rect getRectUmbrack(){
        return hitbox;
    }
}
