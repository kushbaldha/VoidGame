package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.Entities.Enemies.Enemy;
import orangeboat.voidgame.Entities.Enemies.Flippy;
import orangeboat.voidgame.Entities.Enemies.Landie;
import orangeboat.voidgame.Entities.Enemies.Umbrack;

/**
 * Created by Kush on 11/30/2015.
 */
public class EnemyPanel
{
    int score = 0;
    int numEnemies = 0;
    public ArrayList <Enemy> allLandies;
    Bitmap fullLandieImage;
    public Bitmap singleLandieImage;
    Bitmap [] landieImage = new Bitmap[6];
    public Animation landieAnimation = new Animation();
    Bitmap fullUmbrackImage;
    public Bitmap singleUmbrackImage;
    Bitmap [] umbrackImage = new Bitmap[4];
    public Animation umbrackAnimation = new Animation();
    Bitmap fullRotorImage;
    public Bitmap singleRotorImage;
    Bitmap [] rotorImage = new Bitmap[12];
    public Animation rotorAnimation = new Animation();
    Bitmap fullTankImage;
    public Bitmap singleTankImage;
    Bitmap [] tankImage = new Bitmap[4];
    public Animation tankAnimation = new Animation();
    Bitmap fullFlippyImage;
    public Bitmap singleFlippyImage;
    Bitmap [] flippyImage = new Bitmap[4];
    public Animation flippyAnimation = new Animation();
    public Bitmap fullRollUmbrackImage;
    Bitmap [] rollUmbrackImage = new Bitmap[4];
    public Animation rollAnimation = new Animation();
    boolean moveLeft = false, moveRight;
    public Bitmap splatter, splatterRev;
    public EnemyPanel(Bitmap landie, Bitmap umbrack, Bitmap rotor, Bitmap tank, Bitmap flippy, Bitmap spray, Bitmap sprayRev, Bitmap rollUmbrack)
    {
        this.fullLandieImage = landie;
        singleLandieImage = Bitmap.createBitmap(landie, 0, 0, 152, 192);
        allLandies = new ArrayList<>();
        this.fullUmbrackImage= umbrack;
        singleUmbrackImage= Bitmap.createBitmap(umbrack, 0, 0, 450,600);
        this.fullRotorImage = rotor;
        singleRotorImage = Bitmap.createBitmap(rotor, 0, 0, 128, 128);
        this.fullTankImage = tank;
        singleTankImage = Bitmap.createBitmap(tank, 0, 0, 318, 240);
        this.fullFlippyImage = flippy;
        singleFlippyImage = Bitmap.createBitmap(flippy, 0, 0, 210, 190);
        splatter = spray;
        this.splatterRev = sprayRev;
        fullRollUmbrackImage = rollUmbrack;
    }
    public void update(int skyx, int levellength, boolean notBlockedByPlatform, int charX, int charY, boolean hitWall)
    {
            for (int i = 0; i < allLandies.size(); i++) {
                if (allLandies.get(i) instanceof Landie) {
                    ((Landie) allLandies.get(i)).update(moveLeft, moveRight, skyx, levellength, hitWall, notBlockedByPlatform);
                } else if (allLandies.get(i) instanceof Umbrack) {
                    ((Umbrack) allLandies.get(i)).update(moveLeft, moveRight, skyx, levellength, hitWall, notBlockedByPlatform);
                }
                else if (allLandies.get(i) instanceof Flippy) {
                    ((Flippy) allLandies.get(i)).update(moveLeft, moveRight, skyx, levellength, charX, charY, hitWall, notBlockedByPlatform);
                }
                else allLandies.get(i).update(moveLeft, moveRight, skyx, levellength, hitWall,notBlockedByPlatform);

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
            landieImage[i] = Bitmap.createBitmap(fullLandieImage,i * width, 0, width,height);
        }
        landieAnimation.setFrames(landieImage);
        landieAnimation.setDelay(120);
        width = 450;
        height = 600;
        for (int i = 0; i < umbrackImage.length; i++){
            umbrackImage[i] = Bitmap.createBitmap(fullUmbrackImage, i*width, 0, width, height);
        }
        umbrackAnimation.setFrames(umbrackImage);
        umbrackAnimation.setDelay(90);
        for(int i = 0; i < rollUmbrackImage.length; i++)
        {
            rollUmbrackImage[i] = Bitmap.createBitmap(fullRollUmbrackImage, i*width, 0, width, height);
        }
        rollAnimation.setFrames(rollUmbrackImage);
        rollAnimation.setDelay(90);
        width =128;
        height =128;
        for (int i = 0; i < rotorImage.length; i++){
            rotorImage[i] = Bitmap.createBitmap(fullRotorImage, i*width, 0, width, height);
        }
        rotorAnimation.setFrames(rotorImage);
        rotorAnimation.setDelay(100);
        width =318;
        height =240;
        for (int i = 0; i < tankImage.length; i++){
            tankImage[i] = Bitmap.createBitmap(fullTankImage, i*width, 0, width, height);
        }
        tankAnimation.setFrames(tankImage);
        tankAnimation.setDelay(100);
        width =210;
        height =190;
        for (int i = 0; i < flippyImage.length; i++){
            flippyImage[i] = Bitmap.createBitmap(fullFlippyImage, i*width, 0, width, height);
        }
        flippyAnimation.setFrames(flippyImage);
        flippyAnimation.setDelay(100);
    }
    public void loadList(ArrayList<Enemy> landieArrayList)
    {
        allLandies = landieArrayList;
    }
    public boolean killEnemySword(Rect weaponHitbox)
    {
            for(int i = 0; i < allLandies.size();i++)
            {
                boolean temp = (Rect.intersects(allLandies.get(i).hitbox,weaponHitbox));
                if(temp)
                {
                    int healthOfEnemy = allLandies.get(i).hit(0,0,false,6,false);
                    if(healthOfEnemy <= 0)
                    {
                        if(allLandies.get(i) instanceof Umbrack)
                        {
                            allLandies.remove(i);
                            System.out.println("Killed a bogey bullet");
                            return true;
                        }
                        allLandies.remove(i);
                        System.out.println("Killed a bogey bullet");
                    }
                    System.out.println("Killed a bogey");
                    break;
                }
            }
        return false;
    }
    public int killEnemyBullet( ArrayList<Bullet> bulletList)
    {

            for (int i = 0; i < allLandies.size(); i++) {
                for (int p = 0; p < bulletList.size(); p++) {
                    Rect rectTemp = bulletList.get(p).getRect();
                    boolean temp = Rect.intersects(allLandies.get(i).hitbox, rectTemp);
                    if (temp) {
                        int healthOfEnemy;
                        if(bulletList.get(p).lastMove)
                            healthOfEnemy = (allLandies.get(i).hit(rectTemp.right,rectTemp.top, bulletList.get(p).lastMove, 2,true));
                        else
                            healthOfEnemy = (allLandies.get(i).hit(rectTemp.left,rectTemp.top,bulletList.get(p).lastMove, 2,true));
                        if(healthOfEnemy == 0) {
                            if (allLandies.get(i) instanceof Umbrack) {
                                allLandies.remove(i);
                                System.out.println("Killed a bogey bullet");
                                return 999;
                            }
                            allLandies.remove(i);
                            System.out.println("Killed a bogey bullet");
                        }
                        return p;
                    }
                }
            }
        return -1;
    }
    public boolean checkEnemyKill(Rect rectChar) {
        for (int i = 0; i < allLandies.size(); i++) {
            if (Rect.intersects(rectChar,allLandies.get(i).hitbox))
                {
                    return true;
                }
        }
        return false;
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

            ((allLandies.get(i))).draw(canvas);
        }

    }
}
