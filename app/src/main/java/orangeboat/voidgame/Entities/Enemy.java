package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Kush on 12/19/2015.
 */
public class Enemy
{
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
     * size of enemy
     */
    public static int TW,TH;
    /**
     * hitbox of enemy
     */
    public static Rect hitbox;

    public Enemy(Bitmap img, Animation animation){
        this.img = img;
        this.animation = animation;
        TW = img.getWidth();
        TH = img.getHeight();
    }
    public void update(){
        hitbox = new Rect(x, y, TW, TH);
    }

}
