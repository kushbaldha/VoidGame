package orangeboat.voidgame.Input;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.R;

/**
 * Created by Kush on 12/3/2015.
 */
public class ImageLoader
{
    GameObjects objects;
    Bitmap temp;
    Resources resources;
    DisplayMetrics metrics;
    public static final int WIDTH = 1900;
    public static final int HEIGHT = 1200;
    public ImageLoader(GameObjects objects,Resources resources, DisplayMetrics metrics)
    {
        this.metrics = metrics;
        this.objects = objects;
        this.resources = resources;
        start();

    }
    public void start()
    {
        //PLAYER LOAd
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.walking)), 630, 192, true); // charAnimationRight
        temp = (Bitmap.createBitmap(temp, 0, 0, 120, 192));
        // MAIN CHAR21 width 31 height. Scale factor is 6
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.walking), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.walking).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.walking).getHeight() / HEIGHT, false);
        //temp = (Bitmap.createBitmap(temp, 0, 0, 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.walking).getWidth() / (WIDTH*5), 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.walking).getHeight() / HEIGHT)); // MAIN CHAR21 width 31 height. Scale factor is 6
        objects.imgLoad(temp);
       // temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.walkingrev)),630,192,true);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.walkingrev), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.walkingrev).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.walkingrev).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.walking)),630,192,true);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.walking), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.walking).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.walking).getHeight() / HEIGHT, false); // charAnimationright
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.slashing)),630,192,true);
       // temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.slashing), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.slashing).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.slashing).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.slashingrev)),630,192,true);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.slashingrev), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.slashingrev).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.slashingrev).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.gunwalk)),630,192,true);
       // temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.gunwalk), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.gunwalk).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.gunwalk).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.gunwalkrev)),630,192,true);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.gunwalkrev), 6* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.gunwalkrev).getWidth() / WIDTH, 6* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.gunwalkrev).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        objects.playerLoad();

        // GAMEMENU LOAD
        temp =  BitmapFactory.decodeResource(resources, R.drawable.leftarrow);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.leftarrow), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.leftarrow).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.leftarrow).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.rightarrow);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.rightarrow), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.rightarrow).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.rightarrow).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.menuv2);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.menuv2), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.menuv2).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.menuv2).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.redbutton);
       // temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.redbutton), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.redbutton).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.redbutton).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.bluebutton);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.bluebutton), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.bluebutton).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.bluebutton).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.sword)), 250, 250, true);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.sword), 5*metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.sword).getWidth() / WIDTH, 5*metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.sword).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.gun)), 250, 250, true);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.gun),5*  metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.gun).getWidth() / WIDTH, 5*metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.gun).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.healthbar)), 800, 80, true);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.healthbar), 16*metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.healthbar).getWidth() / WIDTH, 16*metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.healthbar).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.resume);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.resume), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.resume).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.resume).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.quit);
        //temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.quit), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.quit).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.quit).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        objects.gameMenuLoad();

        //WEAPONS LOAD
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.slash), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.slash).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.slash).getHeight() / HEIGHT, false);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.bullet), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.bullet).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.bullet).getHeight() / HEIGHT, false);
        //60 x 10
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.bulletrev), metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.bulletrev).getWidth() / WIDTH, metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.bulletrev).getHeight() / HEIGHT, false);
        //60 x 10
        objects.imgLoad(temp);
        objects.weaponsLoad();

        //ENEMYPANEL LOAD
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.tempenemy)),942,192,true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.umbrackidle),1800,600,true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.rotor),1664,128,true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.tank), 1590, 240, true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.flippy), 840, 190, true);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.spray);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.sprayrev);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.rollumbrack),1800,600,true);
        objects.imgLoad(temp);
        objects.enemyPanelLoad();

        //ITEMPANEL LOAD
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.heart), 2* metrics.widthPixels * BitmapFactory.decodeResource(resources, R.drawable.heart).getWidth() / WIDTH,2* metrics.heightPixels * BitmapFactory.decodeResource(resources, R.drawable.heart).getHeight() / HEIGHT, false);

        objects.imgLoad(temp);
        objects.itemPanelLoad();
    }
}
