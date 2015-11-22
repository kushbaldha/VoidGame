package orangeboat.voidgame;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Kush on 11/21/2015.
 */
public class GamePanel
{
    GameObjects objects;
    public GamePanel(GameObjects objects)
    {
    this.objects = objects;
    }
    public void update()
    {

    }
    public void load()
    {
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(objects.gameBackground,0,0,null);
        objects.player.draw(canvas);
    }
    public void touch(int x, int y)
    {

    }
}
