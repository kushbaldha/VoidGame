package orangeboat.voidgame.States.Game;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.Random;

import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.PhoneSpecs;
import orangeboat.voidgame.States.Game.Items.HeartDrop;

public class GamePanel {
    static PlatformMap map;
    int moving = 0;
    int jumping = 0;
    int weapon = 0;
    public GameObjects objects;
    int floorx = 0;
    int skyx = 0;
    int dx;
    int timer;
    boolean check = false;
    boolean gamePaused = false;
    Random rand = new Random();

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
        if(!gamePaused) {

            //-28050 f, 13200 s
            objects.player.update(objects.weapons.getWeapon(), skyx+objects.gameBackgroundSky.getWidth(), 1+(-1 * (objects.gameBackgroundSky.getWidth()) + objects.player.phoneWidth));
            if (objects.player.notBlockedByPlatform && !objects.player.hitBossWall) {
                if (objects.player.moveLeft && skyx < 0) {
                    floorx += dx;
                    skyx += dx / 2;
                }
                if (objects.player.moveRight && skyx+objects.gameBackgroundSky.getWidth() > -1 * (objects.gameBackgroundSky.getWidth()) + objects.player.phoneWidth) {
                    floorx -= dx;
                    skyx -= dx / 2;
                }
            }
            objects.weapons.update(objects.player.getCharY(), objects.player.getLastMove(), objects.player.getState(),objects.player.charX);
            objects.itemPanel.update(objects.player.moveRight, objects.player.moveLeft, skyx, -1 * (objects.gameBackgroundSky.getWidth()) + objects.player.phoneWidth,objects.player.notBlockedByPlatform);
            objects.enemyPanel.update(skyx, -1 * (objects.gameBackgroundSky.getWidth()) + objects.player.phoneWidth, objects.player.notBlockedByPlatform, objects.player.charX, objects.player.charY);
            if (objects.weapons.showSlash)
                objects.enemyPanel.killEnemySword(objects.weapons.rectSlash);

            int num = objects.enemyPanel.killEnemyBullet(objects.weapons.bullets);
            if (num >= 0)
                objects.weapons.deleteBullet(num);
            if (timer > 0) {
                timer--;
            }
            if (objects.enemyPanel.checkEnemyKill(objects.player.rectChar) && timer == 0) {
                objects.player.hit();
                timer = 30;
            }
            int itemDrop = objects.itemPanel.checkCollision(objects.player.rectChar);
            switch(itemDrop)
            {
                case HeartDrop.id:
                {
                    if(objects.player.health<6)
                        objects.player.health++;
                }
            }
            objects.gameMenu.update(objects.player.health);
            map.update(floorx);
            timer = objects.player.checkOnPlatform(map.inFrameHitboxes, map.inFrameSpikes, timer);


        }
    }

    public void draw(Canvas canvas) {
        if(!check)
            check = true;
        canvas.drawBitmap(objects.gameBackgroundSky, skyx, 0, null);
        canvas.drawBitmap(objects.gameBackgroundSky, skyx+objects.gameBackgroundSky.getWidth(), 0, null);
        canvas.drawBitmap(objects.gameBackgroundFloor, floorx, 840, null);
        canvas.drawBitmap(objects.gameBackgroundFloor, floorx+objects.gameBackgroundFloor.getWidth(), 840, null);
        objects.player.draw(canvas);
        objects.enemyPanel.draw(canvas);
        objects.weapons.draw(canvas);
        map.draw(canvas, floorx);
        objects.gameMenu.draw(canvas);
        objects.itemPanel.draw(canvas);
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
        if (check == 4) {
            weapon = pointerNumber;
            if(objects.player.getState()) {
                objects.weapons.setShowGun(true);
            }//actual gun animation
            else
                objects.weapons.setShowSlash(true); // actual slash animation
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
        }
        if (check == 4) {
            weapon = pointerNumber;
            if(objects.player.getState()) {
                objects.weapons.setShowGun(true);
            }//actual gun animation
            else {
                objects.weapons.randomSlash = rand.nextInt(3);
                objects.weapons.setShowSlash(true);
            }
        }
        if(check == 5)
        {
            pauseGame(!gamePaused);
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
    public void pauseGame(boolean b)
    {
        gamePaused = b;
        objects.gameMenu.gamePaused = b;
    }
}

