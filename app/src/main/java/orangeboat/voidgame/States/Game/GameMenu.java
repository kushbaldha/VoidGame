package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

import java.util.ArrayList;

import orangeboat.voidgame.PhoneSpecs;

public class GameMenu {

    Bitmap leftButton, rightButton, menuButton, jumpButton, okButton, swordButton, gunButton, healthBar, resumeButton, quitButton;
    int phoneHeight, phoneWidth;
    int leftImgX, leftImgY, rightImgX, rightImgY, menuImgX, menuImgY, jumpImgX, jumpImgY, okImgX, okImgY, swordImageX, swordImageY, gunImageX, gunImageY, healthBarImageX, healthBarImageY;
    int resumeImgX, resumeImgY, resumeX, resumeY, quitImgX, quitImgY, quitX, quitY;
    int leftX, leftY, rightX, rightY, menuX, menuY, jumpX, jumpY, okX, okY, swordX, swordY, healthBarX, healthBarY;
    Rect rectLeft, rectRight, rectMenu, rectJump, rectOk, rectWeapons, rectResumeButton, rectQuitbutton;
    int healthRectX, healthRectY, healthToDraw;
    boolean showGun = false;
    MediaPlayer shootfx, swordfx, jumpsfx;
    public boolean quitGame = false;
    ArrayList<Rect> healthRects = new ArrayList<>();
    boolean gamePaused = false;
    Paint paint;

    public GameMenu(Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton, Bitmap okButton, Bitmap swordButton, Bitmap gunButton, Bitmap healthBar, Bitmap resumeButton, Bitmap quitButton, MediaPlayer shoot, MediaPlayer slash, MediaPlayer jump) {
        paint = new Paint();
        paint.setColor(Color.RED);
        this.swordfx = slash;
        this.shootfx = shoot;
        this.jumpsfx = jump;
        this.leftButton = leftButton;
        leftImgX = leftButton.getWidth();
        leftImgY = leftButton.getHeight();
        this.rightButton = rightButton;
        rightImgX = rightButton.getWidth();
        rightImgY = rightButton.getHeight();
        this.menuButton = menuButton;
        menuImgX = menuButton.getWidth();
        menuImgY = menuButton.getHeight();
        this.jumpButton = jumpButton;
        jumpImgX = jumpButton.getWidth();
        jumpImgY = jumpButton.getHeight();
        this.okButton = okButton;
        okImgX = okButton.getWidth();
        okImgY = okButton.getHeight();
        this.swordButton = swordButton;
        swordImageX = swordButton.getWidth();
        swordImageY = swordButton.getHeight();
        this.gunButton = gunButton;
        gunImageX = gunButton.getWidth();
        gunImageY = gunButton.getHeight();
        this.healthBar = healthBar;
        healthBarImageX = healthBar.getWidth();
        healthBarImageY = healthBar.getHeight();
        this.resumeButton = resumeButton;
        resumeImgX = resumeButton.getWidth();
        resumeImgY = resumeButton.getHeight();
        this.quitButton = quitButton;
        quitImgX = quitButton.getWidth();
        quitImgY = quitButton.getHeight();
    }

    public void update(int health) {
        healthToDraw = health;
    }

    public void load() {
        phoneWidth = (PhoneSpecs.width);
        phoneHeight = (PhoneSpecs.height);
        leftX = 0;
        leftY = (int) (phoneHeight / 1.15);
        rectLeft = new Rect(leftX, leftY, (leftX + leftImgX), (leftY + leftImgY));
        rightX = 250;
        rightY = (int) (phoneHeight / 1.15);
        rectRight = new Rect(rightX, rightY, (rightX + rightImgX), (rightY + rightImgY));
        menuX = 0;
        menuY = 0;
        rectMenu = new Rect(menuX, menuY, (menuX + menuImgX), (menuY + menuImgY));
        jumpX = phoneWidth - (int) (jumpImgX * 1.1);
        jumpY = (int) (phoneHeight / 1.15);
        rectJump = new Rect(jumpX, jumpY, (jumpX + jumpImgX), (jumpY + jumpImgY));
        okX = phoneWidth - (int) (2 * jumpImgX * 1.1);
        okY = (int) (phoneHeight / 1.15);
        rectOk = new Rect(okX, okY, (okX + okImgX), (okY + okImgY));
        swordX = phoneWidth - (int) (jumpImgX * 1.1);
        swordY = 0;
        rectWeapons = new Rect(swordX, swordY, (swordX + swordImageX), (swordY + swordImageY));
        healthBarX = phoneWidth / 2 - healthBarImageX / 2;
        healthBarY = 0;

        healthRectX = 114;
        healthRectY = 50;
        resumeX = phoneWidth / 2 - resumeImgX / 2;
        resumeY = phoneHeight / 3 - resumeImgY / 2;
        rectResumeButton = new Rect(resumeX, resumeY, resumeX + resumeImgX, resumeY + resumeImgY);
        quitX = phoneWidth / 2 - resumeImgX / 2;
        quitY = 2* phoneHeight / 3 - resumeImgY / 2;
        rectQuitbutton = new Rect(quitX,quitY,quitX+quitImgX,quitY +quitImgY);
        int temp;
        for (int i = 0; i < 6; i++) {
            temp = i * (healthRectX) + (i * 16) + healthBarX + 16;
            healthRects.add(new Rect(temp, 16, temp + healthRectX, healthRectY));
        }
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(leftButton, leftX, leftY, null);
        canvas.drawBitmap(rightButton, rightX, rightY, null);
        canvas.drawBitmap(jumpButton, jumpX, jumpY, null);
        canvas.drawBitmap(menuButton, menuX, menuY, null);
        canvas.drawBitmap(okButton, okX, okY, null);
        if (showGun)
            canvas.drawBitmap(swordButton, swordX, swordY, null);
        else
            canvas.drawBitmap(gunButton, swordX, swordY, null);
        canvas.drawBitmap(healthBar, healthBarX, healthBarY, null);
        for (int i = 0; i < healthToDraw; i++) {
            canvas.drawRect(healthRects.get(i), paint);
        }
        if (gamePaused) {
            canvas.drawBitmap(quitButton,quitX,quitY,null);
            canvas.drawBitmap(resumeButton, resumeX, resumeY, null);
        }
    }

    public void switchStates() {
        showGun = !showGun;
    }

    public int checkGameButton(int x, int y) {
        // returns which button was pressed
        if (gamePaused) {
            if (rectResumeButton.contains(x, y)) {
                return 5;
            }
            if(rectQuitbutton.contains(x,y))
            {
                quitGame = true;
                return 5;
            }
        }
        else {
            if (rectLeft.contains(x, y)) {
                return 1;
            } else if (rectRight.contains(x, y)) {
                return 2;
            } else if (rectJump.contains(x, y)) {
                jumpsfx.start();
                return 3;
            } else if (rectOk.contains(x, y)) {
                if(showGun){shootfx.start();}
                else swordfx.start();

                return 4;
            } else if (rectMenu.contains(x, y)) {
                return 5;
            } else if (rectWeapons.contains(x, y))
                return 6;
        }
        return 0;

    }
}

