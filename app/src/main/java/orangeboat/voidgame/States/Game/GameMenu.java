package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

public class GameMenu{

    Bitmap leftButton, rightButton, menuButton, jumpButton, okButton , swordButton, gunButton, healthBar ;
    int phoneHeight, phoneWidth;
    int leftImgX, leftImgY, rightImgX, rightImgY, menuImgX, menuImgY, jumpImgX, jumpImgY, okImgX, okImgY, swordImageX, swordImageY, gunImageX, gunImageY, healthBarImageX, healthBarImageY;
    int leftX, leftY, rightX, rightY, menuX, menuY, jumpX, jumpY, okX, okY,swordX,swordY,healthBarX,healthBarY;
    Rect rectLeft, rectRight, rectMenu, rectJump, rectOk, rectWeapons;
    int healthRectX, healthRectY;
    boolean showGun = false;
    ArrayList<Rect> healthRects = new ArrayList <>();
    Paint paint;

    public GameMenu(Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton, Bitmap okButton, Bitmap swordButton, Bitmap gunButton, Bitmap healthBar) {
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
    }

    public void update() {
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
        rectWeapons = new Rect(swordX, swordY, (swordX + swordImageX),(swordY + swordImageY));
        healthBarX = phoneWidth/2 -healthBarImageX/2;
        healthBarY = 0;
        healthRectX = 114;
        healthRectY = 48;
        for(int i = 0; i <= 6; i++)
        {
            healthRects.add(new Rect(i * (healthRectX)+healthBarX+16,i*(healthRectY) + healthBarY + 16, healthRectX , healthRectY));
        }
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(leftButton, leftX, leftY, null);
        canvas.drawBitmap(rightButton, rightX, rightY, null);
        //Paint paint = new Paint();
        // USE THE PAINT OBJECT TO CHECK WHERE THE HITBOXES ARE
        //paint.setColor(Color.GREEN);
        //canvas.drawRect(rectRight,paint);
         paint.setColor(Color.RED);
        // canvas.drawRect(rectLeft,paint);
//        canvas.drawBitmap(menuButton,menuX,menuY,null);
        canvas.drawBitmap(jumpButton, jumpX, jumpY, null);
        //paint.setColor(Color.GREEN);
        //canvas.drawRect(rectJump,paint);
        //paint.setColor(Color.RED);
        // canvas.drawRect(rectOk,paint);
        canvas.drawBitmap(menuButton, menuX, menuY, null);
        canvas.drawBitmap(okButton, okX, okY, null);
       if(showGun)
            canvas.drawBitmap(gunButton, swordX, swordY, null);
       else
           canvas.drawBitmap(swordButton,swordX,swordY,null);
        canvas.drawBitmap(healthBar,healthBarX,healthBarY,null);
        //canvas.drawRect(rectWeapons,paint);

//        canvas.drawRect(rectLeft, paint);
//        canvas.drawRect(rectRight,paint);
//        canvas.drawRect(rectMenu,paint);
//        canvas.drawRect(rectJump,paint);
        for(int i = 0; i < healthRects.size(); i++)
        {
            canvas.drawRect(healthRects.get(i),paint);
        }
    }
    public void switchStates()
    {
        showGun = !showGun;
    }
    public int checkGameButton(int x, int y) {
        // returns which button was pressed
        if (rectLeft.contains(x, y)) {
            return 1;
        } else if (rectRight.contains(x, y)) {
            return 2;
        } else if (rectJump.contains(x, y)) {
            return 3;
        } else if(rectOk.contains(x,y)) {
            return 4;
        }
        else if(rectMenu.contains(x,y)) {
            return 5;
        }
        else if(rectWeapons.contains(x,y))
            return 6;
        return 0;
    }
}

