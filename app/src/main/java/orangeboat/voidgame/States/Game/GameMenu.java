package orangeboat.voidgame.States.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 11/22/2015.
 */
public class GameMenu
{
    Bitmap leftButton;
    Bitmap rightButton;
    Bitmap menuButton;
    Bitmap jumpButton;
    int phoneHeight,phoneWidth;
    int leftImgX,leftImgY,rightImgX,rightImgY,menuImgX,menuImgY,jumpImgX,jumpImgY;
    int leftX, leftY, rightX, rightY, menuX, menuY, jumpX, jumpY;
    Rect rectLeft, rectRight, rectMenu, rectJump;
    Paint paint;
    public GameMenu(Bitmap leftButton, Bitmap rightButton, Bitmap menuButton, Bitmap jumpButton)
    {
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
        jumpImgY  = jumpButton.getHeight();
    }
    public void update()
    {

    }
    public void load()
    {
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        leftX = 0;
        leftY = (phoneHeight-140);
        rectLeft = new Rect(leftX,leftY, (leftX+leftImgX),(leftY+leftImgY));
        rightX = 250;
        rightY =(phoneHeight-140);
        rectRight = new Rect(rightX,rightY,(rightX+rightImgX),(rightY + rightImgY));
        jumpX = phoneWidth-140;
        jumpY = (phoneHeight-140);
        rectJump = new Rect(jumpX,jumpY,(jumpX + jumpImgX),(jumpY + jumpImgY));
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(leftButton,leftX,leftY,null);
       canvas.drawBitmap(rightButton,rightX,rightY,null);
//        canvas.drawBitmap(menuButton,menuX,menuY,null);
        canvas.drawBitmap(jumpButton,jumpX,jumpY,null);
//        canvas.drawRect(rectLeft, paint);
//        canvas.drawRect(rectRight,paint);
//        canvas.drawRect(rectMenu,paint);
//        canvas.drawRect(rectJump,paint);

    }
}
