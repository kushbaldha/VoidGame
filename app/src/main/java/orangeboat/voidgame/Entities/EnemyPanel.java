package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Kush on 11/30/2015.
 */
public class EnemyPanel
{
    int score = 0;
    int numEnemies = 0;
    public ArrayList <Landie> allLandies = new ArrayList<>();
    Bitmap fullLandieImage;
    Bitmap singleLandieImage;
    Bitmap [] landieImage = new Bitmap[6];
    Animation landieAnimation = new Animation();
    boolean moveLeft = false, moveRight;
    public EnemyPanel(Bitmap landie)
    {
        this.fullLandieImage = landie;
        singleLandieImage = Bitmap.createBitmap(landie, 0, 0, 152, 192);
    }
    public void update()
    {
        if(numEnemies<=0) {
            Landie temp = new Landie(landieAnimation,singleLandieImage);
            temp.load();
            allLandies.add(temp);
            temp = null;
            numEnemies++;
        }
        for(int i = 0; i < allLandies.size();i++)
        {
            allLandies.get(i).update(moveLeft,moveRight);
        }
       // if(score == somenumber)
        // spawn an enemy. create a landie object with passing animation in.
        // Passing animation in this time so it doesn't have to load the image everytime
    }
    public void load()
    {
        int width = 156;
        int height = 192;
        for (int i = 0; i < landieImage.length; i++)
        {
            landieImage[i] = Bitmap.createBitmap(fullLandieImage,i * width, 0 , width ,height);
        }
        landieAnimation.setFrames(landieImage);
        landieAnimation.setDelay(120);
    }
    public void killEnemy(Rect weaponHitbox)
    {
        for(int i = 0; i < allLandies.size();i++)
    {
        boolean temp = allLandies.get(i).getRectLandie().intersect(weaponHitbox);
        if(temp)
        {
            allLandies.remove(i);
            numEnemies--;
            System.out.println("Killed a bogey");
            break;
        }
    }
        score++;
    }
    public void moveLeft()
    {
        moveLeft = true;
        moveRight = false;
    }
    public void moveRight()
    {
        moveLeft = false;
        moveRight = true;
    }
    public void moveStop()
    {
        moveLeft = false;
        moveRight = false;
    }
    public void draw(Canvas canvas) {
        for(int i = 0; i < allLandies.size();i++)
        {

           allLandies.get(i).draw(canvas);
        }
    }
}
