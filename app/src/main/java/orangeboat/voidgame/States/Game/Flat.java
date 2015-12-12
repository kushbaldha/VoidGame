package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;

import orangeboat.voidgame.Entities.GameObjects;

/**
 * Created by Jay on 12/12/2015.
 */
public class Flat extends Platform{

    public Flat(Bitmap img, int id){
        super(img, id);
    }
    /**
     * overrides boolean to true
     */
    @Override
    public boolean isSolid(){
        return true;
    }
}