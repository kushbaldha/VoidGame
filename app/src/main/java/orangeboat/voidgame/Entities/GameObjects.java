package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

import orangeboat.voidgame.R;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.States.Game.GameMenu;
import orangeboat.voidgame.PhoneSpecs;
import orangeboat.voidgame.R;
import orangeboat.voidgame.R;


/**
 * Created by Kush on 11/21/2015.
 */
public class GameObjects
{
    public Player player;
    ArrayList <Bitmap> loader = new ArrayList<>();
    public Weapons weapons;
    public GameMenu gameMenu;
    public EnemyPanel enemyPanel;
    public Bitmap gameBackgroundFloor,gameBackgroundSky;
    int phoneHeight,phoneWidth;
    public GameObjects( Bitmap gameBackgroundFloor, Bitmap gameBackgroundSky)
    {
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
        weapons.load();
    }

    public void imgLoad(Bitmap image)
    {
        loader.add(image);
    }
    public void playerLoad()
    {
        player = new Player(loader.get(0),loader.get(1),loader.get(2),loader.get(3),loader.get(4));
        loader.clear();
    }
    public void gameMenuLoad()
    {
        gameMenu = new GameMenu(loader.get(0),loader.get(1),loader.get(2),loader.get(3),loader.get(4));
        loader.clear();
    }
    public void weaponsLoad()
    {
        weapons = new Weapons(loader.get(0));
        loader.clear();
    }
    public void enemyPanelLoad()
    {
        enemyPanel = new EnemyPanel(loader.get(0));
        loader.clear();
    }
}
