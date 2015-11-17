package orangeboat.voidgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kush on 11/6/2015.
 */
public class Display extends SurfaceView implements SurfaceHolder.Callback

{
    private MenuThread firstthread;
    private MainThread secondthread;
    private MenuPanel menu;
    public static final int WIDTH = 1900;
    public static final int HEIGHT = 1200;

    public Display(Context context) {

        super(context);

        //add callback to surfaceholders to intercepts events like fingerpresses
        getHolder().addCallback(this);

        firstthread = new MenuThread(getHolder(), this);
        secondthread = new MainThread(getHolder(), this);
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
        menu = new MenuPanel(BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen), BitmapFactory.decodeResource(getResources(), R.drawable.playbutton1));
        //once surface is created, we can safely start gameloop
        secondthread.setRunning(true);
        secondthread.start();
    }

    @Override


    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        // it might take several tries to stop secondthread so this is needed
        // a try catch loop is created

        while (retry) {
            try {
                secondthread.setRunning(false);
                secondthread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        menu.update();
    }

    @Override
    public void draw(Canvas canvas) {

        final float scaleFactorX = getWidth() / (WIDTH * 1.f);//make sure they are floats
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            menu.draw(canvas);
            //return to savedstate. If we didn't have this, it would keep on scaling. So we do this to return it to original state
            canvas.restoreToCount(savedState);
        }

    }
}
