package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 12/13/2015.
 */
public class Bullet
{
    Rect rectBullet;
    Bitmap bullet;
    int bulletX, bulletY, bulletImgX, bulletImgY;
    int dx;
    int dy;
    int total;
    boolean lastMove;
    public Bullet(Bitmap bullet,  boolean lastMove)
    {
        this.lastMove = lastMove;
        this.bullet = bullet;
        bulletImgX = bullet.getWidth();
        bulletImgY = bullet.getHeight();

    }
    public void update(int charY)
    {
        bulletY = (charY + dy);
        if(lastMove)
            bulletX -= dx;
        else
            bulletX += dx;
        total +=dx;
        rectBullet = new Rect(bulletX,bulletY,(bulletX + bulletImgX),(bulletY + bulletImgY));
    }
    public void load()
    {
        bulletX = bulletImgX + (PhoneSpecs.width/2);
        bulletY = (PhoneSpecs.height/2);
        rectBullet = new Rect(bulletX,bulletY,(bulletX + bulletImgX),(bulletY + bulletImgY));
        dx = ((int) (PhoneSpecs.width * 0.05));
        dy = ((int)(PhoneSpecs.height/8.5));

    }
    public void draw(Canvas canvas)
    {
            canvas.drawBitmap(bullet,bulletX,bulletY,null);
    }
    public Rect getRect()
    {
        return rectBullet;
    }
}
