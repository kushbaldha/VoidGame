package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;

import orangeboat.voidgame.Animation.Animation;

/**
 * Created by Kush on 11/30/2015.
 */
public class EnemyPanel
{
    int score = 0;
    Bitmap fullLandieImage;
    Bitmap [] landieImage = new Bitmap[6];
    Animation landieAnimation;
    public EnemyPanel(Bitmap landie)
    {
        this.fullLandieImage = landie;
    }
    public void update()
    {
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
        landieAnimation.setDelay(30);
    }
    public void killEnemy()
    {
        score++;
    }
    public void draw()
    {

    }
}
