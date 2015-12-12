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
    public Animation playerSlashingLeft = new Animation();
    public Animation playerSlashingRight = new Animation();

    Bitmap [] playerLeftImage = new Bitmap[5];
    Bitmap [] playerRightImage = new Bitmap[5];
    Bitmap [] playerSlashingLeftImage = new Bitmap[5];
    Bitmap [] playerSlashingRightImage = new Bitmap[5];
    Bitmap fullPlayerLeftImage;
    Bitmap fullPlayerRightImage;
    Bitmap fullPlayerSlashingLeftImage;
    Bitmap fullPlayerSlashingRightImage;
    Rect rectChar;
    Paint paint;
    public boolean stopSlash = false,moveLeft = false, moveRight = false, lastMove = true, moveJump = false,stoppingMoveJump=false,jumpDown = false,allMovement = true, showSlashing = false;
    public Player(Bitmap mainChar,Bitmap charAnimationLeft, Bitmap charAnimationRight, Bitmap slashing, Bitmap slashingRev)
    {
        fullPlayerLeftImage = charAnimationLeft;
        fullPlayerRightImage = charAnimationRight;
        fullPlayerSlashingLeftImage = slashingRev;
        fullPlayerSlashingRightImage = slashing;
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        this.mainChar = mainChar;
        charImgX = mainChar.getWidth();
        charImgY = mainChar.getHeight();
    }
    public void update(boolean slashing) {
        if (allMovement) {
            if (moveJump) {
                if (jumpDown)
                {
                    max -= dy;
                    charY += dy;
                    if (max == 0)
                    {
                        jumpDown = false;
                        if(stoppingMoveJump) {
                            moveJump = false;
                            stoppingMoveJump = false;
                        }
                    }
                }
                else if (max < (dy * 8))
                {
                    max += dy;
                    charY -= dy;
                    if (max == (dy * 8))
                        jumpDown = true;
                }
            }
            if(!slashing)
            {
                showSlashing = false;
            }
            if(slashing)
            {
                showSlashing = true;
                    if (lastMove)
                        playerSlashingLeft.update();
                    else
                        playerSlashingRight.update();

            }
            else {
                if (moveLeft) {
                    playerLeft.update();
                }
                if (moveRight) {
                    playerRight.update();
                }
            }
            // updates character hitbox
        }
        rectChar = new Rect(charX, charY, (charX + charImgX), (charY + charImgY));

    }
    public void moveLeft()
    {
        moveLeft = true;
        moveRight = false;
        setLastMove(true);
    }
    public void moveRight()
    {
        moveRight = true;
        moveLeft = false;
        setLastMove(false);
    }
    public void moveStop()
    {
        //sets which way the character was facing last
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
    public void stopJump()
    {
        stoppingMoveJump = true;
    }
    public void allMovement(boolean b)
    {
        allMovement = b;
    }
    public boolean getLastMove()
    {
        return lastMove;
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
        if(moveJump)
        {
            if(lastMove)
            {
                canvas.drawBitmap(playerLeftImage[4], charX, charY, null);

            }
            else
            {
                canvas.drawBitmap(playerRightImage[4], charX, charY, null);

            }
        }
        else if(showSlashing)
        {
            if(lastMove)
            {
                canvas.drawBitmap(playerSlashingLeft.getImage(), charX, charY, null);

            }
            else
            {
                canvas.drawBitmap(playerSlashingRight.getImage(), charX, charY, null);

            }
        }
        //if moving left. keep on changing frames

        else if(moveLeft)
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
    public int getCharY()
    {
        return charY;
    }
    public void setShowSlashing(boolean b)
    {
        showSlashing = b;
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
        for(int i = 0; i < playerSlashingRightImage.length;i++)
        {
            playerSlashingRightImage[i] = Bitmap.createBitmap(fullPlayerSlashingRightImage,i * width, 0 , width ,height);

        }
        for(int i = 0; i < playerSlashingLeftImage.length;i++)
        {
            playerSlashingLeftImage[i] = Bitmap.createBitmap(fullPlayerSlashingLeftImage,i * width, 0 , width ,height);
        }
        //loading up the animation classes
        playerLeft.setFrames(playerLeftImage);
        playerLeft.setDelay(30);
        playerRight.setFrames(playerRightImage);
        playerRight.setDelay(30);
        playerSlashingRight.setFrames(playerSlashingRightImage);
        playerSlashingRight.setDelay(30);
        playerSlashingLeft.setFrames(playerSlashingLeftImage);
        playerSlashingLeft.setDelay(30);
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        //starting position of the character
        charX = (phoneWidth/2);
        charY = (int) (phoneHeight/1.49);
        rectChar = new Rect(charX,charY,(charX+charImgX),(charY+charImgY));
        dy = (int) (phoneHeight*0.017);
    }
}
