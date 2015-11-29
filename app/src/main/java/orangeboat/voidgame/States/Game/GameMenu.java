package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

public class GameMenu{

    Bitmap leftButton, rightButton, menuButton, jumpButton, okButton, slash;
    int phoneHeight, phoneWidth;
    int leftImgX, leftImgY, rightImgX, rightImgY, menuImgX, menuImgY, jumpImgX, jumpImgY, okImgX, okImgY, slashImgX, slashImgY;
    int leftX, leftY, rightX, rightY, menuX, menuY, jumpX, jumpY, okX, okY, slashX, slashY;
    Rect rectLeft, rectRight, rectMenu, rectJump, rectOk, rectslash;
    boolean showslash = false;
    Paint paint;

    public Animation slash1 = new Animation();
    Bitmap [] slashes1 = new Bitmap[4];
    public Animation slash2 = new Animation();
    public Animation slash3 = new Animation();
    public Animation slash4 = new Animation();
    Bitmap [] slashes2 = new Bitmap[4];
    Bitmap [] slashes3 = new Bitmap[4];
    Bitmap [] slashes4 = new Bitmap[4];

    public GameMenu(Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton, Bitmap okButton, Bitmap slash) {
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
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
        this.slash = slash;
        slashImgX = slash.getWidth()/16;
        slashImgY = slash.getHeight();
    }

    public void update() {
        if(slash1.playedOnce()) {
            showslash(false);
        }
        slash1.update();
        slash2.update();
        slash3.update();
        slash4.update();
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

        int d = 100;
        for (int i = 0; i < slashes1.length; i++)
        {
            slashes1[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
        for (int i = 4; i < 4+slashes2.length; i++)
        {
            slashes2[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
        for (int i = 8; i < 8+slashes3.length; i++)
        {
            slashes3[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
        for (int i = 12; i < 12+slashes4.length; i++)
        {
            slashes4[i] = Bitmap.createBitmap(slash,i * d, 0 ,d,d);
        }
        //loading up the animation classes
        slash1.setFrames(slashes1);
        slash1.setDelay(30);
        slash2.setFrames(slashes2);
        slash2.setDelay(30);
        slash3.setFrames(slashes3);
        slash3.setDelay(30);
        slash4.setFrames(slashes4);
        slash4.setDelay(30);
        slashX = slashImgX + (phoneWidth/2);
        slashY = (int) (phoneHeight/1.49);
        rectslash = new Rect(slashX, slashY,(slashX + slashImgX),(slashY + slashImgY));
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(leftButton, leftX, leftY, null);
        canvas.drawBitmap(rightButton, rightX, rightY, null);
        Paint paint = new Paint();
        // USE THE PAINT OBJECT TO CHECK WHERE THE HITBOXES ARE
        //paint.setColor(Color.GREEN);
        //canvas.drawRect(rectRight,paint);
        // paint.setColor(Color.RED);
        // canvas.drawRect(rectLeft,paint);
//        canvas.drawBitmap(menuButton,menuX,menuY,null);
        canvas.drawBitmap(jumpButton, jumpX, jumpY, null);
        //paint.setColor(Color.GREEN);
        //canvas.drawRect(rectJump,paint);
        //paint.setColor(Color.RED);
        // canvas.drawRect(rectOk,paint);
        canvas.drawBitmap(menuButton, menuX, menuY, null);
        canvas.drawBitmap(okButton, okX, okY, null);
        if(showslash)
        {
            //if(moveLeft)
            canvas.drawBitmap(slash1.getImage(),slashX,slashY,null);
            //else if(moveRight)
           // canvas.drawBitmap(slash1.getImage(), slashX, slashY, null);
        }
//        canvas.drawRect(rectLeft, paint);
//        canvas.drawRect(rectRight,paint);
//        canvas.drawRect(rectMenu,paint);
//        canvas.drawRect(rectJump,paint);

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
        return 0;
    }
    public void showslash(boolean b)
    {
        showslash = b;
    }
}

