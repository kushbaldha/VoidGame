package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;

import orangeboat.voidgame.States.Game.GameMenu;
import orangeboat.voidgame.PhoneSpecs;


/**
 * Created by Kush on 11/21/2015.
 */
public class GameObjects {

    public Player player;
    public GameMenu gameMenu;
    public Bitmap gameBackground;

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
        gameMenu.load();

    }



}
