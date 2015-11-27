package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;

public class Player
{
    public int phoneHeight,phoneWidth;
    int charX,charY,charImgX,charImgY;
    int dy,max = 0;
    Bitmap mainChar;
    public Animation playerLeft = new Animation();
    public Animation playerRight = new Animation();
    Bitmap [] playerLeftImage = new Bitmap[4];
    Bitmap [] playerRightImage = new Bitmap[4];
    Bitmap fullPlayerLeftImage;
    Bitmap fullPlayerRightImage;
    Rect rectChar;
    Paint paint;
    public boolean moveLeft = false, moveRight = false,lastMove = true, moveJump = false,jumpDown = false,allMovement = true;
    public Player(Bitmap mainChar,Bitmap charAnimationLeft, Bitmap charAnimationRight)
    {
        fullPlayerLeftImage = charAnimationLeft;
        fullPlayerRightImage = charAnimationRight;
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        this.mainChar = mainChar;
        charImgX = mainChar.getWidth();
        charImgY = mainChar.getHeight();
    }
    public void update() {
        if (allMovement) {
            if (moveJump) {
                if (jumpDown)
                {
                    max -= dy;
                    charY += dy;
                    if (max == 0)
                    {
                        jumpDown = false;
                        moveJump = false;
                    }
                }
                else if (max < (dy * 6))
                {
                    max += dy;
                    charY -= dy;
                    if (max == (dy * 6))
                        jumpDown = true;
                }
            }
            if (moveLeft)
            {
                playerLeft.update();
            }
            if (moveRight)
            {
                playerRight.update();
            }
            // updates character hitbox
        }
        rectChar = new Rect(charX, charY, (charX + charImgX), (charY + charImgY));

    }
    public void moveLeft()
    {
        moveLeft = true;
        moveRight = false;
    }
    public void moveRight()
    {
        moveRight = true;
        moveLeft = false;
    }
    public void moveStop()
    {
        //sets which way the character was facing last
        if(moveLeft)
        {
            setLastMove(true);
        }
        if(moveRight)
        {
            setLastMove(false);
        }
        moveLeft = false;
        moveRight = false;
    }
    public void moveJump()
    {
     moveJump = true;
    }
    public void setLastMove(boolean b)
    {
        lastMove = b;
        // true is left
        // false is right
    }
    public void allMovement(boolean b)
    {
        allMovement = b;
    }
    public boolean checkIfMoving()
    {
        if(moveLeft || moveRight)
            return true;
        else
            return false;
    }
    public void draw(Canvas canvas)
    {
        //if moving left. keep on changing frames
        if(moveLeft)
        canvas.drawBitmap(playerLeft.getImage(),charX,charY,null);
        // if moving right, keep on changing frames
        else if(moveRight)
        canvas.drawBitmap(playerRight.getImage(), charX, charY, null);
        else
        {
            //checks which way character was facing last.
            if(lastMove)
            {
                canvas.drawBitmap(playerLeftImage[0], charX, charY, null);

            }
            else
            {
                canvas.drawBitmap(playerRightImage[0], charX, charY, null);

            }
        }
        canvas.drawRect(rectChar,paint);
    }
    public void load()
    {
        // height and width for each frame in the spritesheet
        int height = 192;
        int width =  126;
        //loading up the images
        for (int i = 0; i < playerLeftImage.length; i++)
        {
            playerLeftImage[i] = Bitmap.createBitmap(fullPlayerLeftImage,i * width, 0 , width ,height);
        }
        for(int i = 0; i < playerRightImage.length;i++)
        {
            playerRightImage[i] = Bitmap.createBitmap(fullPlayerRightImage,i * width, 0 , width ,height);

        }
        //loading up the animation classes
        playerLeft.setFrames(playerLeftImage);
        playerLeft.setDelay(30);
        playerRight.setFrames(playerRightImage);
        playerRight.setDelay(30);
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        //starting position of the cahracter
        charX = (phoneWidth/2);
        charY = (int) (phoneHeight/1.49);
        rectChar = new Rect(charX,charY,(charX+charImgX),(charY+charImgY));
        dy = (int) (phoneHeight*0.017);
    }
}
