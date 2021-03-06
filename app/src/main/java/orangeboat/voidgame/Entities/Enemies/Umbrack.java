package orangeboat.voidgame.Entities.Enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

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
    int dy;
    int max = 0;
    boolean rollBack = false;
    boolean rolling = false;
    boolean startRolling = false;
    boolean loading = false;
    int rollingAnimationUpdate = 0;
    public static final int id = 6;
    public Umbrack(Animation umbrackAnimation, Bitmap umbrackImg , int health, Animation umRollAnimation)
    {
        super(umbrackImg,umbrackAnimation,health);
        this.umbrackAnimation = umbrackAnimation;
        this.umbrackImg = umbrackImg;
        this.rollAnimation = umRollAnimation;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyX, int levelLength, boolean hitWall, boolean notBlockedByPlatform)
    {
        super.update(moveLeft, moveRight, skyX, levelLength, hitWall, notBlockedByPlatform);
        if(awake)
        {
            if(attack == -1)
            timer--;
            if(loading) // on it's way up after rolling forward and back
            {
              y-=dy;
              max+=dy;
                if(max == (dy*12))
                {
                    loading = false;
                    max = 0;
                }
            }
            else if(timer == 0)
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
                        if (rollBack) // on it's way back
                         {
                            max -= dx;
                            x += dx;
                            if (max == 0) {
                                loading = true;
                                rollBack = false;
                                attack = -1;
                                timer = 90;
                                rolling = false;
                                rollingAnimationUpdate = 0;
                                rollAnimation.setPlayedOnce(false);
                            }
                        } else // goes forward
                        {
                            // on it's way down. Rolling animation goes and the umbrack lowers
                            max += dx;
                            x -= dx;
                            if (max == (dx * 40)) // if it reaches this. then start rolling back
                                rollBack = true;
                        }
                        rollingAnimationUpdate++;
                        if (rollingAnimationUpdate == 5) {
                            rollAnimation.update();
                            rollingAnimationUpdate = 0;
                        }
                    }
                    else // if it's not loading aka going up, or moving forward and back do this.
                    {
                        rollingAnimationUpdate++;
                        y+=dy;
                        if(rollingAnimationUpdate == 2)
                        {
                            rollAnimation.update();
                            rollingAnimationUpdate = 0;
                        }
                        if(rollAnimation.playedOnce())
                        {
                            startRolling = false;
                            rollingAnimationUpdate = 0;
                            rollAnimation.setPlayedOnce(false);
                        }
                    }
                }
            }
            else
            umbrackAnimation.update();

        }

    }
    public void load(int landieX, int landieY, int offset , Bitmap splatter, Bitmap splatterRev)
    {
        super.load(landieX, landieY, offset, splatter, splatterRev);
        y = PhoneSpecs.height/2 - TH/2;
        x+=(phoneWidth/2-TW);
        dx = (int) (phoneWidth * 0.02);
        dy=  (int)(PhoneSpecs.height*0.04);
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
