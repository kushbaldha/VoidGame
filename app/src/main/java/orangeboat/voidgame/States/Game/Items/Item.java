package orangeboat.voidgame.States.Game.Items;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 12/31/2015.
 */
public class Item
{
    public int x;
    public int y;
    public int phoneWidth;
    public int dx;
    public int TW;
    public int TH;
    Rect hitbox;
    Bitmap img;
    public int id;
    public Item(Bitmap img,int id)
    {
        this.img = img;
        TW = img.getWidth();
        TH = img.getHeight();
        this.id = id;
    }
    public void load(int x, int y, int offset){
        phoneWidth=  (PhoneSpecs.width);
        dx = ((int) (phoneWidth * 0.01));
        this.x = x+offset-TW;
        this.y = y;
    }
    public void update(boolean moveLeft, boolean moveRight, int skyx, int levellength,boolean notBlocked, boolean hitwall)
    {
        if(!hitwall && notBlocked) {
            if (moveLeft && skyx > levellength) {
                x -= dx;
            } else if (moveRight && skyx < 0) {
                x += dx;
            }
        }
        hitbox = new Rect(x, y, x + TW, y + TH);
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(img,x,y,null);
    }
}
