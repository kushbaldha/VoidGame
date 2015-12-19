package orangeboat.voidgame.States.Game;

import android.content.res.Resources;
import android.graphics.Canvas;

import orangeboat.voidgame.Entities.GameObjects;

public class GamePanel {
    static PlatformMap map;
    int moving = 0;
    int jumping = 0;
    int weapon = 0;
    public GameObjects objects;
    int floorx = 0;
    int skyx = 0;
    int dx;
    public GamePanel(GameObjects objects , Resources resources) {
        this.objects = objects;
        dx = ((int) (objects.player.phoneWidth * 0.01));
        map = new PlatformMap("lvls/text.txt", resources);

    }
    public void load()
    {
        map.loadMap(objects, floorx);
    }
    public void update() {
        objects.player.update(objects.weapons.getWeapon());
        if (objects.player.moveLeft && skyx < 0) {
            floorx += dx;
            skyx += dx/2;
        }
        if (objects.player.moveRight && skyx > -1 * (objects.gameBackgroundSky.getWidth())+ objects.player.phoneWidth) {
            floorx -= dx;
            skyx -= dx/2;
        }
        objects.weapons.update(objects.player.getCharY(),objects.player.getLastMove(),objects.player.getState());
        objects.enemyPanel.update(skyx, -1 * (objects.gameBackgroundSky.getWidth())+ objects.player.phoneWidth);

        if(objects.weapons.showSlash)
        objects.enemyPanel.killEnemySword(objects.weapons.rectSlash);

        int num =objects.enemyPanel.killEnemyBullet(objects.weapons.bullets);
        if(num>=0)
            objects.weapons.deleteBullet(num);
        if(objects.enemyPanel.checkEnemyKill(objects.player.rectChar))
        {
           // objects.player.allMovement(false);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(objects.gameBackgroundSky, skyx, 0, null);
        canvas.drawBitmap(objects.gameBackgroundFloor, floorx, 840, null);
        objects.player.draw(canvas);
        objects.enemyPanel.draw(canvas);
        objects.weapons.draw(canvas);
        map.draw(canvas, floorx);
        objects.gameMenu.draw(canvas);

    }


    public void  downTouch(int x, int y , int pointerNumber) {
        int check = objects.gameMenu.checkGameButton(x, y);
        if (check == 1) {
            objects.player.moveLeft();
            objects.enemyPanel.moveRight();
            moving = pointerNumber;
        }
        if (check == 2) {
            objects.player.moveRight();
            objects.enemyPanel.moveLeft();
            moving = pointerNumber;
        }
        if (check == 3) {
            objects.player.moveJump();
            jumping = pointerNumber;
        }
        if (check == 4) {
            weapon = pointerNumber;
            if(objects.player.getState()) {
                objects.weapons.setShowGun(true);
            }//actual gun animation
            else
                objects.weapons.setShowSlash(true); // actual slash animation
            //objects.player.allMovement(false);
            //objects.player.moveStop();
        }
    }
    public void singleDownTouch(int x, int y, int pointerNumber)
    {
        int check = objects.gameMenu.checkGameButton(x, y);
        if (check == 1) {
            objects.player.moveLeft();
            objects.enemyPanel.moveRight();
            moving = pointerNumber;
        }
        if (check == 2) {
            objects.player.moveRight();
            objects.enemyPanel.moveLeft();
            moving = pointerNumber;
        }
        if (check == 3) {
            objects.player.moveJump();
            jumping = pointerNumber;
        }
        if (check == 4) {
            weapon = pointerNumber;
            if(objects.player.getState()) {
                objects.weapons.setShowGun(true);
            }//actual gun animation
            else
                objects.weapons.setShowSlash(true); // actual slash animation
            //objects.player.allMovement(false);
            //objects.player.moveStop();
        }
        if(check == 5)
        {
            objects.player.allMovement(false);
        }
        if(check == 6)
        {
            objects.player.switchStates();
            objects.gameMenu.switchStates();
        }
    }
    public void upTouch(int x, int y,int pointerNumber) {
        if (objects.player.checkIfMoving() && moving == pointerNumber) {
            objects.player.moveStop();
            objects.enemyPanel.moveStop();
        }
        if(jumping == pointerNumber)
        {
            objects.player.stopJump();
        }
        if(weapon == pointerNumber)
        {
            objects.weapons.setShowGun(false); // gun animation
            objects.weapons.setShowSlash(false); // slash animation
        }
    }
    public int getMoving()
    {
        return moving;
    }
    public int getJumping()
    {
        return jumping;
    }
}

