package orangeboat.voidgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;


/**
 * Created by Kush on 11/21/2015.
 */
public class GameObjects {

    Player player;
    GameMenu gameMenu;
    Bitmap gameBackground;

    int phoneHeight,phoneWidth;
    public GameObjects(Bitmap mainChar, Bitmap charAnimationLeft, Bitmap charAnimationRight, Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton,Bitmap gameBackground) {

       player = new Player(mainChar, charAnimationLeft,charAnimationRight);
       gameMenu = new GameMenu(leftButton,rightButton,menuButton,jumpButton);
        this.gameBackground = gameBackground;


    }
    public void update()
    {

    }
    public void load()
    {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        player.load();

    }



}
