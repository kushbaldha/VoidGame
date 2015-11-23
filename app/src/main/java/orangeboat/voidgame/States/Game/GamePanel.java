package orangeboat.voidgame.States.Game;

import android.graphics.Canvas;

import orangeboat.voidgame.Entities.GameObjects;

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
        objects.gameMenu.draw(canvas);
    }
    public void touch(int x, int y)
    {

    }
}
