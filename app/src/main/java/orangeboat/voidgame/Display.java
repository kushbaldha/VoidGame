package orangeboat.voidgame;

import android.content.Context;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Rect;
        import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;

import java.io.IOException;

import orangeboat.voidgame.Entities.GameObjects;
        import orangeboat.voidgame.Input.ImageLoader;
import orangeboat.voidgame.Input.MusicLoader;
import orangeboat.voidgame.Input.TouchEvents;
        import orangeboat.voidgame.States.Game.GamePanel;
        import orangeboat.voidgame.States.Title.MenuPanel;
        import orangeboat.voidgame.States.Game.MainThread;

public class Display extends SurfaceView implements SurfaceHolder.Callback

{
    MediaPlayer j;
    MediaPlayer gameoversfx;
    private MainThread secondthread;
    private MenuPanel menu;
    DisplayMetrics metrics;
    Resources resources = getResources();
    Bitmap flat;
    Bitmap spike;
    Bitmap gameBackgroundFloor;
    Bitmap gameBackgroundSky;
    Bitmap gameOverScreen;
    Bitmap retryButton;
    GameObjects objects;
    ImageLoader tempLoader;
    MusicLoader sfxLoader;
    Rect rectRetryButton;
    GamePanel gamePanel;
    boolean showMenu = true;
    boolean showGame = false;
    boolean newGame = false;
    float scaleFactorX;
    float scaleFactorY;
    public static final int WIDTH = 1900;
    public static final int HEIGHT = 1200;
    boolean check = true;
    public PhoneSpecs phone = new PhoneSpecs();
    SurfaceHolder contextHolder;
    TouchEvents touch;

    public Display(Context context, DisplayMetrics m) {

        super(context);
        this.metrics = m;
        j = MediaPlayer.create(context, R.raw.sad);
        gameoversfx = MediaPlayer.create(context, R.raw.gameoversfx);
        spike =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.spike), metrics.widthPixels * 500 / 1900, metrics.heightPixels * 50 / 1200, false);
        flat = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.flat), metrics.widthPixels*500/1900, metrics.heightPixels*50/1200, false);
        gameBackgroundFloor = BitmapFactory.decodeResource(getResources(), R.drawable.newyork1floor);
        gameBackgroundSky = BitmapFactory.decodeResource(getResources(), R.drawable.newyork1back);
        gameOverScreen = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
        retryButton =  BitmapFactory.decodeResource(getResources(), R.drawable.retry);
        objects = new GameObjects (gameBackgroundFloor,gameBackgroundSky,flat, spike);
        sfxLoader = new MusicLoader(objects, context);
        tempLoader = new ImageLoader(objects,resources);

        //voido
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
        menu = new MenuPanel(20,BitmapFactory.decodeResource(getResources(), R.drawable.titleanimationscaleddown40), BitmapFactory.decodeResource(getResources(), R.drawable.playbutton1));
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
        checkNewGame();
        checkQuit();

    }
    public boolean onTouchEvent(MotionEvent event)
    {
        touch = new TouchEvents(event);

        if(newGame)
        {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if(rectRetryButton.contains(x,y))
            {
                gameoversfx.stop();
                gameoversfx.prepareAsync();
                j.start();

                newGame = false;
                gamePanel = new GamePanel(objects,resources);
                gamePanel.load();
                gamePanel.objects.player.health = 6;
                gamePanel.objects.player.charX = PhoneSpecs.width/ 2;
                gamePanel.objects.player.charY = (int) (PhoneSpecs.height / 1.49);

            }
        }
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

        if (canvas != null) {

            // this is to load the phoneSpecs. We need to iterate once before allowing everything to start drawing.
            if (check)
            {
                phone.setHeight(getHeight());
                phone.setWidth(getWidth());
                menu.load(getWidth(), getHeight());
                check = false;
                objects.load();
                rectRetryButton = new Rect((getWidth()/2-retryButton.getWidth()/2),(int)(getHeight()/1.5),getWidth()/2+retryButton.getWidth(),((int)(getHeight()/1.5)+retryButton.getHeight()));
            }
            else {
                // draws Menu is showMenu is true
                if(showMenu) {
                    menu.draw(canvas);
                }
                // draw Game is showGame is true
                if(showGame)
                {
                    gamePanel.draw(canvas);
                }
                if(newGame)
                {
                    j.pause();
                    gameoversfx.start();
                    canvas.drawBitmap(gameOverScreen,0,0,null);
                    canvas.drawBitmap(retryButton, (getWidth() / 2 - retryButton.getWidth() / 2), (int) (getHeight() / 1.5), null);
                    //canvas.drawRect(rectRetryButton,null);
                }
            }

        }
    }
    public void newThread() {
        secondthread = new MainThread(contextHolder, this);
    }
    public void checkNewGame()
    {
        if(objects.player.health<=0)
        {
            newGame = true;
        }
    }
    public void checkQuit()
    {
            if(objects.gameMenu.quitGame == true) {
                menu = new MenuPanel(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen), metrics.widthPixels*BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen).getWidth() / 1900, metrics.heightPixels* BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen).getHeight() / 1200, false), BitmapFactory.decodeResource(getResources(), R.drawable.playbutton1));
                menu.load(getWidth(),getHeight());
                showMenu = true;
                showGame = false;
                objects.gameMenu.quitGame = false;
                gamePanel = new GamePanel(objects,resources);
                gamePanel.load();
                gamePanel.objects.player.health = 6;
                gamePanel.objects.player.charX = PhoneSpecs.width/ 2;
                gamePanel.objects.player.charY = (int) (PhoneSpecs.height / 1.49);
            }
    }
    //random comment
}
