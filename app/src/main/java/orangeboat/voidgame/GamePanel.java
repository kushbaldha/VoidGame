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
    public static final int WIDTH= 1900;
    public static final int HEIGHT = 1200;
    private MainThread thread;
    private Background bg;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    public GamePanel(Context context)
    {

     super(context);

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
        bg = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.ideallandscape));
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
    bg.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
       final float scaleFactorX = getWidth()/(WIDTH*1.f);//make sure they are floats
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            //return to savedstate. If we didn't have this, it would keep on scaling. So we do this to return it to original state
            canvas.restoreToCount(savedState);
        }
    }

}
