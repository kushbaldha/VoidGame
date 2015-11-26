package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.States.Game.GameMenu;
import orangeboat.voidgame.PhoneSpecs;


/**
 * Created by Kush on 11/21/2015.
 */
public class GameObjects
{
    public Player player;
    public Animation playerLeft = new Animation();
    public Animation playerRight = new Animation();
    Bitmap [] playerLeftImage = new Bitmap[4];
    Bitmap fullPlayerLeftImage;
    Bitmap fullPlayerRightImage;
    public GameMenu gameMenu;
    public Bitmap gameBackground;

    int phoneHeight,phoneWidth;
    public GameObjects(Bitmap mainChar, Bitmap charAnimationLeft, Bitmap charAnimationRight, Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton,Bitmap gameBackground)
    {
        fullPlayerLeftImage = charAnimationLeft;
        fullPlayerRightImage = charAnimationRight;
       player = new Player(mainChar);
       gameMenu = new GameMenu(leftButton,rightButton,menuButton,jumpButton);
        this.gameBackground = gameBackground;
    }
    public void update()
    {

    }
    public void load() {
        int height = 192;
        int width =  126;
        for (int i = 0; i < playerLeftImage.length; i++)
        {
            playerLeftImage[i] = Bitmap.createBitmap(fullPlayerLeftImage,i * width, 0 , width ,height);
        }
        playerLeft.setFrames(playerLeftImage);
        playerLeft.setDelay(30);
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        player.load();
        gameMenu.load();
    }



}
