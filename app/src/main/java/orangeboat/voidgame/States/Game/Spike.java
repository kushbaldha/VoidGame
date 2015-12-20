package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;

/**
 * Created by Jay on 12/19/2015.
 */
public class Spike extends Platform{
    public Spike(Bitmap img, int id){
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
