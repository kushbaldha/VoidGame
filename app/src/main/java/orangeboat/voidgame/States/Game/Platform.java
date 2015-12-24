package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Entities.GameObjects;

/**
 * Created by Jay on 12/12/2015.
 */
public class Platform {
    /**
     * image of tile
     */
    protected Bitmap img;
    /**
     * num id of tile
     */
    protected final int id;
    /**
     * x location of tile
     */
    public int x;
    /**
     * y location of tile
     */
    public int y;
    /**
     * size of tile
     */
    public static int TW = 500, TH =100;
    /**
     * hitbox of tile
     */
    public  Rect hitbox;
    /**
     *
     * @param img
     * @param id
    */
    public Platform(Bitmap img, int id){
        this.img = img;
        this.id= id;
        hitbox = new Rect(x, y, x + TW, y + TH);
    }
    /**
     *
     * @return		false for tangibility
     */
    public boolean isSolid(){
        return false;
    }
    /**
     * updates hitbox
     */
    public void update(){
        hitbox = new Rect(x, y, x + TW, y + TH);
    }
}
