package orangeboat.voidgame.States.Game.Items;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.PhoneSpecs;

/**
 * Created by Kush on 12/31/2015.
 */
public class ItemPanel
{
    public Bitmap heartDrop;
    ArrayList<Item> allItems = new ArrayList<>();
    public ItemPanel(Bitmap heartDrop)
    {
        this.heartDrop = heartDrop;
    }
    public void update(boolean moveLeft,boolean moveRight , int skyx, int levellength, boolean notBlockedByPlatform)
    {
        for(int i = 0; i< allItems.size();i++)
        {
            allItems.get(i).update(moveLeft,moveRight,skyx,levellength,notBlockedByPlatform);
        }
    }
    public void loadList(ArrayList<Item> allItems)
    {
        this.allItems = allItems;
    }
    public void draw(Canvas canvas)
    {
        for(int i = 0; i< allItems.size();i++)
        {
            allItems.get(i).draw(canvas);
        }
    }
    public int checkCollision(Rect playerRect)
    {
        for(int i = 0; i < allItems.size();i++)
        {
            if(Rect.intersects(playerRect,allItems.get(i).hitbox)) {
                int temp = allItems.get(i).id;
                allItems.remove(i);
                return temp;
            }
        }
        return 0;
    }
}
