package orangeboat.voidgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.Input.ImageLoader;
import orangeboat.voidgame.Input.TouchEvents;
import orangeboat.voidgame.States.Game.GamePanel;
import orangeboat.voidgame.States.Title.MenuPanel;
import orangeboat.voidgame.States.Game.MainThread;
import orangeboat.voidgame.States.Title.MenuThread;


public class Display extends SurfaceView implements SurfaceHolder.Callback

{
    MediaPlayer j;
    private MainThread secondthread;
    private MenuPanel menu;
    Resources resources = getResources();
    public Bitmap flat = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow);
    Bitmap gameBackgroundFloor = BitmapFactory.decodeResource(getResources(), R.drawable.newyork1floor);
    Bitmap gameBackgroundSky = BitmapFactory.decodeResource(getResources(), R.drawable.newyork1back);
    GameObjects objects = new GameObjects (gameBackgroundFloor,gameBackgroundSky,flat);
    ImageLoader tempLoader = new ImageLoader(objects,resources);
    GamePanel gamePanel;
    boolean showMenu = true;
    boolean showGame = false;
    float scaleFactorX;
    float scaleFactorY;
    public static final int WIDTH = 1900;
    public static final int HEIGHT = 1200;
    public PhoneSpecs phone = new PhoneSpecs();
    boolean check = true;
    SurfaceHolder contextHolder;
    TouchEvents touch;

    public Display(Context context) {

        super(context);
        j = MediaPlayer.create(context, R.raw.voidost);
        j.start();
        tempLoader = null;

        //add callback to surfaceholders to intercepts events like fingerpresses
        getHolder().addCallback(this);
        contextHolder = getHolder();
        secondthread = new MainThread(contextHolder, this);
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
        menu = new MenuPanel(20,1900,1200,BitmapFactory.decodeResource(getResources(), R.drawable.titleanimation), BitmapFactory.decodeResource(getResources(), R.drawable.playbutton1));
        Thread.State state = secondthread.getState();
        if(state == Thread.State.TERMINATED) {
            newThread();
            check = true;
        }
        //once surface is created, we can safely start gameloop
        secondthread.setRunning(true);
        { secondthread.start();}
    }

    @Override


    public void surfaceDestroyed(SurfaceHolder holder) {
        menu = null;
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
        if(showMenu)
        menu.update();
        if(showGame)
        gamePanel.update();
        j.start();
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        touch = new TouchEvents(event);

        if(showMenu)
        { touch.check(menu);}
        if(!touch.checkMenu()) {
            // sets the menu off from drawing
            showMenu = false;
            // allows the draw method to start drawing Game
            showGame = true;
            // instantiates gamePanel
            gamePanel = new GamePanel(objects, resources);
            gamePanel.load();
            // to save memory, deletes menu
            menu.remove();
            menu = null;
        }
        if(showGame)
        {
            touch.gameTouch(gamePanel);
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
            scaleFactorX = getWidth() / (WIDTH * 1.f);//make sure they are floats
            scaleFactorY = getHeight() / (HEIGHT * 1.f);
        if (canvas != null) {
            // this is to load the phoneSpecs. We need to iterate once before allowing everything to start drawing.
            if (check)
            {
                phone.setHeight(getHeight());
                phone.setWidth(getWidth());
                menu.load();
                check = false;
                objects.load();
            }
            else {
                final int savedState = canvas.save();
                canvas.scale(scaleFactorX, scaleFactorY);
                // draws Menu is showMenu is true
                if(showMenu) {
                    menu.draw(canvas);
                }
                // draw Game is showGame is true
                if(showGame)
                {
                    gamePanel.draw(canvas);
                }
                //return to savedstate. If we didn't have this, it would keep on scaling. So we do this to return it to original state
                canvas.restoreToCount(savedState);
            }
        }
    }
    public void newThread() {
        secondthread = new MainThread(contextHolder, this);
    }
    //random comment
}
