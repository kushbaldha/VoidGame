package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.Entities.GameObjects;

/**
 * Created by Jay on 12/12/2015.
 */
public class Platform {
    static GameObjects objects;
    public static Platform[] tiles = new Platform[1];
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
    public static int TW = 250, TH =250;
    /**
     * hitbox of tile
     */
    public static Rect hitbox;
    /**
     * grass tile with id of 0
     */
    public static Platform flat = new Flat(objects.flat, 1);
    /**
     *
     * @param img
     * @param id
     * Sets up tile with specific id and adds to tile array
     */
    public Platform(Bitmap img, int id){
        this.img = img;
        this.id= id;

        tiles[id] = this;
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
        hitbox = new Rect(x, y, TW, TH);
    }
}
