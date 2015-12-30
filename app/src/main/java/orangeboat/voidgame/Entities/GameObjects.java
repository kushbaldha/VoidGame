package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import orangeboat.voidgame.Display;
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
    ArrayList<MediaPlayer> sfxloader = new ArrayList<>();
    public Weapons weapons;
    public GameMenu gameMenu;
    public EnemyPanel enemyPanel;
    public Bitmap gameBackgroundFloor,gameBackgroundSky;

    public static Bitmap flat;
    public static Bitmap spike;

    public int phoneHeight,phoneWidth;
    public GameObjects( Bitmap gameBackgroundFloor, Bitmap gameBackgroundSky, Bitmap flat, Bitmap spike)
    {
        this.gameBackgroundFloor = gameBackgroundFloor;
        this.gameBackgroundSky = gameBackgroundSky;
        this.flat = flat;
        this.spike = spike;
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
    public void sfxLoad(MediaPlayer sfx){ sfxloader.add(sfx);}
    public void playerLoad()
    {
        player = new Player(loader.get(0),loader.get(1),loader.get(2),loader.get(3),loader.get(4),loader.get(5),loader.get(6) ,6);
        loader.clear();
    }
    public void gameMenuLoad()
    {
        gameMenu = new GameMenu(loader.get(0),loader.get(1),loader.get(2),loader.get(3),loader.get(4), loader.get(5), loader.get(6), loader.get(7),loader.get(8), loader.get(9), sfxloader.get(0), sfxloader.get(1));
        loader.clear();
    }
    public void weaponsLoad()
    {
        weapons = new Weapons(loader.get(0),loader.get(1),loader.get(2));
        loader.clear();
    }
    public void enemyPanelLoad()
    {
        enemyPanel = new EnemyPanel(loader.get(0),loader.get(1),loader.get(2),loader.get(3),loader.get(4),loader.get(5),loader.get(6));
        loader.clear();
    }
}
