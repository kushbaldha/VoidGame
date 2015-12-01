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
    public GameMenu gameMenu;
    public EnemyPanel enemyPanel;
    public Bitmap gameBackgroundFloor,gameBackgroundSky;
    int phoneHeight,phoneWidth;
    public GameObjects(Bitmap mainChar, Bitmap charAnimationLeft, Bitmap charAnimationRight, Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton, Bitmap okButton, Bitmap gameBackgroundFloor, Bitmap gameBackgroundSky, Bitmap slash, Bitmap landie)
    {
       player = new Player(mainChar,charAnimationLeft,charAnimationRight);
       gameMenu = new GameMenu(leftButton,rightButton,menuButton,jumpButton, okButton, slash);
       enemyPanel = new EnemyPanel(landie);
        this.gameBackgroundFloor = gameBackgroundFloor;
        this.gameBackgroundSky = gameBackgroundSky;
    }
    public void update()
    {

    }
    public void load() {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        player.load();
        gameMenu.load();
        enemyPanel.load();
    }



}
