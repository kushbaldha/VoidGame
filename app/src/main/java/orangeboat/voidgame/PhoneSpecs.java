package orangeboat.voidgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Kush on 11/17/2015.
 */
public class PhoneSpecs
{
    public static int height,width;
    public PhoneSpecs()
    {
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
}
