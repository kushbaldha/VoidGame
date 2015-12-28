package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 12/19/2015.
 */
public class Enemy
{
    public int phoneWidth;
    /**
     * image of enemy
     */
    public Bitmap img;
    /**
     * animation of enemy
     */
    protected Animation animation;
    /**
     * x location of tile
     */
    public int x;
    /**
     * y location of tile
     */
    public int y;
    /**
     * health of enemy
     */
    public int health;
    public static int TW,TH;
    /**
     * hitbox of enemy
     */
    public  Rect hitbox;
    /**
     * The rate at which the screen scrolls
     */
    int dx;
    Paint paint = new Paint();
    public Enemy(Bitmap img, Animation animation , int health){
        this.img = img;
        this.animation = animation;
        TW = img.getWidth();
        TH = img.getHeight();
        this.health = health;
        paint.setColor(Color.BLUE);
    }
    public void load(int x, int y, int offset){
        phoneWidth=  (PhoneSpecs.width);
        dx = ((int) (phoneWidth * 0.01));
        this.x = x+offset;
        this.y = y;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyx, int levellength)
    {
        if(moveLeft && skyx > levellength)
        {
            x -= dx;
        }
        else if(moveRight && skyx < 0)
        {
            x += dx;
        }
        hitbox = new Rect(x, y, x + TW, y + TH);
    }
    public int hit()
    {
        health--;
        return health;
    }
    public Rect getHitbox()
    {
        return hitbox;
    }
    public void draw(Canvas canvas)
    {
        //canvas.drawRect(hitbox,paint);
    }
}
