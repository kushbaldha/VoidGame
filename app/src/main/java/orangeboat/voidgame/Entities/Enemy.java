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
    int phoneWidth;
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
    public static Rect hitbox;
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
        this.x = x+offset;
        this.y = y;
    }
    public void update(){
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
