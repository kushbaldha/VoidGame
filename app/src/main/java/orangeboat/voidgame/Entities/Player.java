package orangeboat.voidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Animation.Animation;
import orangeboat.voidgame.PhoneSpecs;
import orangeboat.voidgame.States.Game.Platform;

public class Player
{
    public int phoneHeight,phoneWidth;
    int charX,charY,charImgX,charImgY;
    int dy,max = 0;
    public int health;
    Bitmap mainChar;
    int stupidPlat = 2; //      0  is in the air      1 is first instance on platform       2 is platform

    Bitmap fullPlayerSwordLeftImage;
    Bitmap [] playerSwordLeftImage = new Bitmap[5];
    public Animation playerLeft = new Animation();

    Bitmap fullPlayerSwordRightImage;
    Bitmap [] playerSwordRightImage = new Bitmap[5];
    public Animation playerRight = new Animation();

    Bitmap fullPlayerSlashingLeftImage;
    Bitmap [] playerSlashingLeftImage = new Bitmap[5];
    public Animation playerSlashingLeft = new Animation();

    Bitmap fullPlayerSlashingRightImage;
    Bitmap [] playerSlashingRightImage = new Bitmap[5];
    public Animation playerSlashingRight = new Animation();

    Bitmap fullPlayerGunLeftImage;
    Bitmap [] playerGunLeftImage = new Bitmap [5];
    public Animation playerGunLeft = new Animation();

    Bitmap fullPlayerGunRightImage;
    Bitmap [] playerGunRightImage = new Bitmap [5];
    public Animation playerGunRight = new Animation();


    public Rect rectChar;
    Paint paint;
    public boolean moveLeft = false, moveRight = false, lastMove = true, moveJump = false,stoppingMoveJump=false,jumpDown = false,allMovement = true, showWeapon = false;
    public boolean state = false;
    // false is sword and true is gun
    public Player(Bitmap mainChar,Bitmap charAnimationLeft, Bitmap charAnimationRight, Bitmap slashing, Bitmap slashingRev, Bitmap gunWalking, Bitmap gunWalkingRev , int health)
    {
        fullPlayerSwordLeftImage = charAnimationLeft;
        fullPlayerSwordRightImage = charAnimationRight;
        fullPlayerSlashingLeftImage = slashingRev;
        fullPlayerSlashingRightImage = slashing;
        fullPlayerGunLeftImage = gunWalkingRev;
        fullPlayerGunRightImage = gunWalking;
        paint = new Paint();
        paint.setColor(Color.GREEN);
        this.mainChar = mainChar;
        charImgX = mainChar.getWidth();
        charImgY = mainChar.getHeight();
        this.health = health;
    }
    public void update(boolean weapon) {
        if (allMovement) {
            if (moveJump) {
                stupidPlat = 0;
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
                else if (max < (dy * 20))
                {
                    max += dy;
                    charY -= dy;
                    if (max == (dy * 20))
                        jumpDown = true;
                }
            }
            if(!state)  // false is sword
            {
                if (!weapon) {
                    showWeapon = false;
                }
                if (weapon) {
                    showWeapon = true;
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
            }
            if(state)
            {
                if (moveLeft) {
                    playerGunLeft.update();
                }
                if (moveRight) {
                    playerGunRight.update();
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
        if(!state) // Sword state
         {
            if (showWeapon) {
                if (lastMove) {
                    canvas.drawBitmap(playerSlashingLeft.getImage(), charX, charY, null);

                } else {
                    canvas.drawBitmap(playerSlashingRight.getImage(), charX, charY, null);

                }
            } else if (moveJump) {
                if (lastMove) {
                    canvas.drawBitmap(playerSwordLeftImage[4], charX, charY, null);

                } else {
                    canvas.drawBitmap(playerSwordRightImage[4], charX, charY, null);

                }
            }
            //if moving left. keep on changing frames
            else if (moveLeft)
                canvas.drawBitmap(playerLeft.getImage(), charX, charY, null);
                // if moving right, keep on changing frames
            else if (moveRight)
                canvas.drawBitmap(playerRight.getImage(), charX, charY, null);
            else {
                //checks which way character was facing last.
                if (lastMove) {
                    canvas.drawBitmap(playerSwordLeftImage[0], charX, charY, null);

                } else {
                    canvas.drawBitmap(playerSwordRightImage[0], charX, charY, null);

                }
            }
        }
        if(state) // Gun state
        {
            if (moveJump) {
                if (lastMove) {
                    canvas.drawBitmap(playerGunLeftImage[1], charX, charY, null);

                } else {
                    canvas.drawBitmap(playerGunRightImage[4], charX, charY, null);

                }
            }
            //if moving left. keep on changing frames
            else if (moveLeft)
                canvas.drawBitmap(playerGunLeft.getImage(), charX, charY, null);
                // if moving right, keep on changing frames
            else if (moveRight)
                canvas.drawBitmap(playerGunRight.getImage(), charX, charY, null);
            else {
                //checks which way character was facing last.
                if (lastMove) {
                    canvas.drawBitmap(playerGunLeftImage[4], charX, charY, null);

                } else {
                    canvas.drawBitmap(playerGunRightImage[0], charX, charY, null);

                }
            }
        }
        canvas.drawRect(rectChar,paint);
    }
    public int getCharY()
    {
        return charY;
    }
    public void load()
    {
        // height and width for each frame in the spritesheet
        int height = 192;
        int width =  126;
        //loading up the images
        for (int i = 0; i < playerSwordLeftImage.length; i++)
        {
            playerSwordLeftImage[i] = Bitmap.createBitmap(fullPlayerSwordLeftImage,i * width, 0 , width ,height);
        }
        for(int i = 0; i < playerSwordRightImage.length;i++)
        {
            playerSwordRightImage[i] = Bitmap.createBitmap(fullPlayerSwordRightImage,i * width, 0 , width ,height);

        }
        for(int i = 0; i < playerSlashingRightImage.length;i++)
        {
            playerSlashingRightImage[i] = Bitmap.createBitmap(fullPlayerSlashingRightImage,i * width, 0 , width ,height);

        }
        for(int i = 0; i < playerSlashingLeftImage.length;i++)
        {
            playerSlashingLeftImage[i] = Bitmap.createBitmap(fullPlayerSlashingLeftImage,i * width, 0 , width ,height);
        }
        for(int i = 0; i < playerGunLeftImage.length;i++)
        {
            playerGunLeftImage[i] = Bitmap.createBitmap(fullPlayerGunLeftImage,i * width, 0 , width ,height);
        }
        for(int i = 0; i < playerGunRightImage.length;i++)
        {
            playerGunRightImage[i] = Bitmap.createBitmap(fullPlayerGunRightImage,i * width, 0 , width ,height);
        }

        //loading up the animation classes
        playerLeft.setFrames(playerSwordLeftImage);
        playerLeft.setDelay(30);
        playerRight.setFrames(playerSwordRightImage);
        playerRight.setDelay(30);
        playerSlashingRight.setFrames(playerSlashingRightImage);
        playerSlashingRight.setDelay(30);
        playerSlashingLeft.setFrames(playerSlashingLeftImage);
        playerSlashingLeft.setDelay(30);
        playerGunLeft.setFrames(playerGunLeftImage);
        playerGunLeft.setDelay(30);
        playerGunRight.setFrames(playerGunRightImage);
        playerGunRight.setDelay(30);
        phoneWidth=  (PhoneSpecs.width);
        phoneHeight=  (PhoneSpecs.height);
        //starting position of the character
        charX = (phoneWidth/2);
        charY = (int) (phoneHeight/1.49);
        rectChar = new Rect(charX,charY,(charX+charImgX),(charY+charImgY));
        dy = (int) (phoneHeight*0.017); // 0.017
    }
    public void switchStates()
    {
        state = !state;
        // false is sword and true is gun
    }
    public boolean getState()
    {
        return state;
    }
    public void hit()
    {
        health--;
    }
    public void checkOnPlatform(ArrayList<Platform> platforms)
    {
        for(int i = 0; i < platforms.size();i++) {
            Rect temp = platforms.get(i).hitbox;
            if (temp.top <= (rectChar.bottom + 26) && temp.top >= (rectChar.bottom - 26)) {
                if(stupidPlat == 1)
                {
                    if(lastMove) {
                        if (rectChar.right >= temp.right) {
                            moveJump = true;
                            jumpDown = true;
                        }
                    }
                    else
                    if (rectChar.left <= temp.left) {
                        moveJump = true;
                        jumpDown = true;
                    }

                }
                else if(jumpDown == true && stupidPlat < 1 && temp.left <= rectChar.centerX() && temp.right >= rectChar.exactCenterX()) {
                        stupidPlat++;
                    jumpDown = false;
                    moveJump = false;
                    stoppingMoveJump = false;
                    max = 0;
                    charY = temp.top - charImgY;
                    rectChar = new Rect(charX, charY, charX+charImgX,charY+charImgY);
                }
            }
        }
    }
}
