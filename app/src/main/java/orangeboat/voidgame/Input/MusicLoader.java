package orangeboat.voidgame.Input;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.R;

/**
 * Created by Jay on 12/30/2015.
 */
public class MusicLoader {
    GameObjects objects;
    MediaPlayer temp;
    Context context;
    public MusicLoader(GameObjects objects,Context context)
    {
        this.objects = objects;
        this.context = context;
        start();
    }
    public void start(){
        temp = MediaPlayer.create(context, R.raw.shoot);
        objects.sfxLoad(temp);
        temp = MediaPlayer.create(context, R.raw.swordsfx);
        objects.sfxLoad(temp);
        temp = MediaPlayer.create(context, R.raw.jumpsfx);
        objects.sfxLoad(temp);
        temp = null;
    }

}
