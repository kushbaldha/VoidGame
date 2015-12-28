package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Animation.Animation;
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
    boolean moveLeft = false, moveRight;
    public EnemyPanel(Bitmap landie, Bitmap umbrack, Bitmap rotor, Bitmap tank, Bitmap flippy)
    {
        this.fullLandieImage = landie;
        singleLandieImage = Bitmap.createBitmap(landie, 0, 0, 152, 192);
        allLandies = new ArrayList<>();
        this.fullUmbrackImage= umbrack;
        singleUmbrackImage= Bitmap.createBitmap(umbrack, 0, 0, 300,400);
        this.fullRotorImage = rotor;
        singleRotorImage = Bitmap.createBitmap(rotor, 0, 0, 128, 128);
        this.fullTankImage = tank;
        singleTankImage = Bitmap.createBitmap(tank, 0, 0, 159, 120);
        this.fullFlippyImage = flippy;
        singleFlippyImage = Bitmap.createBitmap(flippy, 0, 0, 100, 100);
    }
    public void update(int skyx, int levellength)
    {
        for(int i = 0; i < allLandies.size();i++)
        {
            if(allLandies.get(i) instanceof Landie){
                ( (Landie) allLandies.get(i)).update(moveLeft, moveRight, skyx, levellength);
            }
            else if(allLandies.get(i) instanceof Umbrack){
                ((Umbrack) allLandies.get(i)).update(moveLeft, moveRight, skyx, levellength);
            }
            else allLandies.get(i).update(moveLeft,moveRight,skyx,levellength);

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
        width = 300;
        height = 400;
        for (int i = 0; i < umbrackImage.length; i++){
            umbrackImage[i] = Bitmap.createBitmap(fullUmbrackImage, i*width, 0, width, height);
        }
        umbrackAnimation.setFrames(umbrackImage);
        umbrackAnimation.setDelay(60);
        width =128;
        height =128;
        for (int i = 0; i < rotorImage.length; i++){
            rotorImage[i] = Bitmap.createBitmap(fullRotorImage, i*width, 0, width, height);
        }
        rotorAnimation.setFrames(rotorImage);
        rotorAnimation.setDelay(100);
        width =159;
        height =120;
        for (int i = 0; i < tankImage.length; i++){
            tankImage[i] = Bitmap.createBitmap(fullTankImage, i*width, 0, width, height);
        }
        tankAnimation.setFrames(tankImage);
        tankAnimation.setDelay(100);
        width =100;
        height =100;
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
    public void killEnemySword(Rect weaponHitbox)
    {
            for(int i = 0; i < allLandies.size();i++)
            {
                boolean temp = (allLandies.get(i)).getHitbox().intersect(weaponHitbox);
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
                    boolean temp = ((Landie)(allLandies.get(i))).getRectLandie().intersect(rectTemp);
                    if (temp) {
                        int temp1 = (allLandies.get(i).hit());
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
    public boolean checkEnemyKill(Rect rectChar) {
        for (int i = 0; i < allLandies.size(); i++) {
                if(Rect.intersects(rectChar,allLandies.get(i).hitbox))
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
