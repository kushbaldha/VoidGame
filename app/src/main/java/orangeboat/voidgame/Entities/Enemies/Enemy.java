package orangeboat.voidgame.Entities.Enemies;

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
    public boolean hit;
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
    /**
     * The bullet splatter;
     */
    public Bitmap splatter, splatterRev;
    int splatX,splatY;
    Boolean lastMove;
    Boolean awake;
    Paint paint = new Paint();
    public Enemy(Bitmap img, Animation animation , int health){
        hit = false;
        this.img = img;
        this.animation = animation;
        TW = img.getWidth();
        TH = img.getHeight();
        this.health = health;
        paint.setColor(Color.BLUE);
    }
    public void load(int x, int y, int offset, Bitmap splatter, Bitmap splatterRev){
        phoneWidth=  (PhoneSpecs.width);
        dx = ((int) (phoneWidth * 0.01));
        this.splatter = splatter;
        this.splatterRev = splatterRev;
        this.x = x+offset-TW;
        this.y = y;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyx, int levellength, boolean hitWall)
    {
        if(!hitWall){
            if(moveLeft && skyx > levellength)
            {
                x -= dx;
            }
            else if(moveRight && skyx < 0)
            {
                x += dx;
            }
        }
        hitbox = new Rect(x, y, x + TW, y + TH);
        awake = inFrame();

    }
    public int hit(int x, int y, Boolean lastMove)
    {
        this.lastMove = lastMove;
        health--;
        hit = true;
        splatX = x;
        splatY = y;
        return health;
    }
    public Rect getHitbox()
    {
        return hitbox;
    }
    public void draw(Canvas canvas) {
        if (hit) {
            if(lastMove)
            canvas.drawBitmap(splatterRev,splatX,splatY,null);
            else
            canvas.drawBitmap(splatter,splatX,splatY,null);
            hit = false;
        }
        //canvas.drawRect(hitbox,paint);
    }
    public boolean inFrame()
    {
        if((hitbox.left >= 0 && hitbox.left<=(phoneWidth)) || ( hitbox.right >= 0 && hitbox.right<=phoneWidth))
            return true;
     return false;
    }

}
