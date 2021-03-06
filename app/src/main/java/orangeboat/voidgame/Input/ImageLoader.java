package orangeboat.voidgame.Input;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
    public ImageLoader(GameObjects objects,Resources resources)
    {
        this.objects = objects;
        this.resources = resources;
        start();

    }
    public void start()
    {
        //PLAYER LOAD
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.walking)), 630, 192, true); // charAnimationRight
        temp = (Bitmap.createBitmap(temp, 0, 0, 120, 192)); // MAIN CHAR21 width 31 height. Scale factor is 6
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.walkingrev)),630,192,true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.walking)), 630, 192, true); // charAnimationright
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.slashing)), 630, 192, true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.slashingrev)), 630, 192, true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.gunwalk)), 630, 192, true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.gunwalkrev)), 630, 192, true);
        objects.imgLoad(temp);
        objects.playerLoad();

        // GAMEMENU LOAD
        temp = BitmapFactory.decodeResource(resources, R.drawable.leftarrow);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.rightarrow);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.menuv2);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.redbutton);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.bluebutton);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.sword)), 250, 250, true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.gun)), 250, 250, true);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.healthbar)), 800, 80, true);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.resume);
        objects.imgLoad(temp);
        temp =  BitmapFactory.decodeResource(resources, R.drawable.quit);
        objects.imgLoad(temp);
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.win)), 1766, 1080, true);
        objects.imgLoad(temp);
        objects.gameMenuLoad();

        //WEAPONS LOAD
        temp = BitmapFactory.decodeResource(resources, R.drawable.slash);
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.bullet); //60 x 10
        objects.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.bulletrev); //60 x 10
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
        temp = Bitmap.createScaledBitmap((BitmapFactory.decodeResource(resources, R.drawable.heart)), 64, 64, true);
        objects.imgLoad(temp);
        objects.itemPanelLoad();
    }
}

