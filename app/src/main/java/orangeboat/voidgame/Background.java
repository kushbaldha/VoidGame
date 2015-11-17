package orangeboat.voidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Kush on 11/6/2015.
 */
public class Background
{
    private Bitmap image;
    private int x,y;
    private Rect playRect;

    public Background(Bitmap res)
    {
        image = res;
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
    canvas.drawBitmap(image,x,y, null);
    }
}
