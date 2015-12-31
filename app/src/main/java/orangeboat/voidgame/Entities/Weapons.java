package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 12/5/2015.
 */
public class Weapons {
    int slashX, slashY, slashImgX, slashImgY;
    int shootX, shootY, shootImgX, shootImgY;
    int phoneWidth, phoneHeight;
    public int randomSlash = 0;
    int gunWait = 0;
    public Rect rectSlash, rectShoot;
    public boolean showSlash = false, showGun = false;

    public Animation slash1 = new Animation();
    public Animation slash2 = new Animation();
    public Animation slash3 = new Animation();
    public Animation slash4 = new Animation();

    Bitmap slash;
    Bitmap[] slashes1 = new Bitmap[4];
    Bitmap[] slashes2 = new Bitmap[4];
    Bitmap[] slashes3 = new Bitmap[4];
    Bitmap[] slashes4 = new Bitmap[4];
    Bitmap fullShoot, fullShootRev;
    public ArrayList<Bullet> bullets = new ArrayList<>();

    Paint paint;

    public Weapons(Bitmap slash, Bitmap shoot, Bitmap shootRev) {
        fullShoot = shoot;
        fullShootRev = shootRev;
        shootImgX = shoot.getWidth();
        shootImgY = shoot.getHeight();
        slashImgX = slash.getWidth() / 16;
        slashImgY = slash.getHeight();
        this.slash = slash;
        paint = new Paint();
        paint.setColor(Color.GREEN);
    }

    public void load(int charX) {
        phoneWidth = (PhoneSpecs.width);
        phoneHeight = (PhoneSpecs.height);

        slashX = charX;
        slashY = (int) (phoneHeight / 1.49);
        rectSlash = new Rect(slashX, slashY, (slashX + slashImgX), (slashY + slashImgY));
        rectShoot = new Rect(shootX, shootY, (shootX + shootImgX), (shootY + shootImgY));
        int d = 100;
        for (int i = 0; i < slashes1.length; i++) {
            slashes1[i] = Bitmap.createBitmap(slash, i * d, 0, d, d);
        }
        slash1.setFrames(slashes1);
        slash1.setDelay(30);
        for (int i = 0; i < slashes2.length; i++) {
            slashes2[i] = Bitmap.createBitmap(slash, i * d + 400, 0, d, d);
        }

        slash2.setFrames(slashes2);
        slash2.setDelay(30);
        for (int i = 0; i < slashes3.length; i++) {
            slashes3[i] = Bitmap.createBitmap(slash, i * d + 800, 0, d, d);
        }

        slash3.setFrames(slashes3);
        slash3.setDelay(30);
        for (int i = 0; i < slashes4.length; i++) {
            slashes4[i] = Bitmap.createBitmap(slash, i * d + 1200, 0, d, d);
        }

        slash4.setFrames(slashes4);
        slash4.setDelay(30);
    }

    public void update(int charY, boolean lastMove, boolean state, int charX) {
        if (state) //gun state
        {
            shootY = charY;
            if (showGun) {
                gunWait++;
                if (gunWait == 5) {
                    shootBullet(lastMove,charX);
                    gunWait = 0;
                }
            }


            rectShoot = new Rect(shootX, shootY, (shootX + shootImgX), (shootY + shootImgY));
        }
        if (!state) // sword state
        {
            slashY = charY;
            if (lastMove) {
                slashX = charX - slashImgX;

            } else {
                slashX = charX + 152;
            }
            rectSlash = new Rect(slashX, slashY, (slashX + slashImgX), (slashY + slashImgY));
            switch(randomSlash) {
                case 0:
                    slash1.update();
                    break;
                case 1:
                    slash2.update();
                    break;
                case 2:
                    slash3.update();
                    break;
                case 3:
                    slash4.update();
            }

        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(charY);
        }
        deleteBullet(-1);
        //slash2.update();
        //slash3.update();
        //slash4.update();

    }

    public void draw(Canvas canvas) {
        if (showSlash) {
            switch(randomSlash) {
                case 0:
                    canvas.drawBitmap(slash1.getImage(), slashX, slashY, null);
                    break;
                case 1:
                    canvas.drawBitmap(slash2.getImage(), slashX, slashY, null);
                    break;
                case 2:
                    canvas.drawBitmap(slash3.getImage(), slashX, slashY, null);
                    break;
                case 3:
                    canvas.drawBitmap(slash4.getImage(), slashX, slashY, null);
                    break;
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(canvas);
        }
    }

    public void setShowSlash(boolean b) {
        showSlash = b;
    }

    public void setShowGun(boolean b) {
        showGun = b;
    }

    public boolean getWeapon() {
        return (showGun || showSlash); // returns if Gun or Slash animation is being shown
    }

    public void shootBullet(boolean lastMove, int charX) {
        Bullet temp;
        if(lastMove)
            temp = new Bullet(fullShootRev,lastMove);
        else
            temp = new Bullet(fullShoot, lastMove);
        temp.load(charX);
        bullets.add(temp);
    }

    public void deleteBullet(int num) {
        if (num >= 0)
            bullets.remove(num);
        else {

            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).total == (10 * ((int) (PhoneSpecs.width * 0.05))))
                    bullets.remove(i);
            }
        }
    }
}
