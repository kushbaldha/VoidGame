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
    public ArrayList <Landie> allLandies;
    Bitmap fullLandieImage;
    public Bitmap singleLandieImage;
    Bitmap [] landieImage = new Bitmap[6];
    public Animation landieAnimation = new Animation();
    boolean moveLeft = false, moveRight;
    public EnemyPanel(Bitmap landie)
    {
        this.fullLandieImage = landie;
        singleLandieImage = Bitmap.createBitmap(landie, 0, 0, 152, 192);
        allLandies = new ArrayList<>();
    }
    public void update(int skyx, int levellength)
    {
        for(int i = 0; i < allLandies.size();i++)
        {
            allLandies.get(i).update(moveLeft,moveRight, skyx, levellength);
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
        allLandies.add(0, new Landie(landieAnimation, singleLandieImage));
    }
    public void killEnemySword(Rect weaponHitbox)
    {
            for(int i = 0; i < allLandies.size();i++)
            {
                boolean temp = allLandies.get(i).getRectLandie().intersect(weaponHitbox);
                if(temp)
                {
                    allLandies.remove(i);
                    numEnemies--;
                    System.out.println("Killed a bogey");
                    score++;
                    break;
                }
            }
    }
    public int killEnemyBullet( ArrayList<Bullet> bulletList)
    {

            for (int i = 0; i < allLandies.size(); i++) {
                for (int p = 0; p < bulletList.size(); p++) {
                    Rect rectTemp = bulletList.get(p).getRect();
                    boolean temp = allLandies.get(i).getRectLandie().intersect(rectTemp);
                    if (temp) {
                        int temp1 = allLandies.get(i).hit();
                        if(temp1 == 0)
                        {allLandies.remove(i);
                            numEnemies--;
                            System.out.println("Killed a bogey bullet");
                            score++;
                            return p;}
                    }
                }
            }
        return -1;
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
        /*
        for(int i = 0; i < allLandies.size();i++)
        {

           allLandies.get(i).draw(canvas);
        }
        */
    }
}
