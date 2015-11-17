package orangeboat.voidgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kush on 11/6/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback

{
    int width, height;
    private MainThread thread;
    private MenuPanel menu;
    Display display;
    public GamePanel(Context context, int width, int height)
    {

     super(context);

        this.width = width;
        this.height = height;

        //add callback to surfaceholders to intercepts events like fingerpresses
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        //getholder() is the surfaceholder or the screen
        //this is the gamePanel

        //make gamePanel focusable so it can handle events
         setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        menu = new MenuPanel(BitmapFactory.decodeResource(getResources(),R.drawable.titlescreen),BitmapFactory.decodeResource(getResources(),R.drawable.playbutton));
        display = new Display(getWidth(),getHeight(),menu);
        //once surface is created, we can safely start gameloop
    thread.setRunning(true);
        thread.start();
    }
    @Override


    public void surfaceDestroyed(SurfaceHolder holder)
    {
    boolean retry = true;

        // it might take several tries to stop thread so this is needed
        // a try catch loop is created

        while(retry)
        {
            try{
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e) {e.printStackTrace();}
            retry=false;
        }
    }
    public void update()
    {
    menu.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
        display.draw(canvas);
    }

}
