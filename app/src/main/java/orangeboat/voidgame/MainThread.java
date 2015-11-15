package orangeboat.voidgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Kush on 11/6/2015.
 */
//keeps the game running
    // game loop

public class MainThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }
    @Override
    public void run()
    {
       long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long targetTime = 1000/FPS; // essentially millisecond per frame
        int frameCount = 0;

        while(running)
        {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) // called 30 times a second that will make it flow.
            {
                this.gamePanel.update(); // update game once
                this.gamePanel.draw(canvas); // draw game once
            }
            }catch(Exception e){
            }
            finally{
                if(canvas!=null)
                {
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e) {e.printStackTrace();}
                }
            }




            timeMillis = (System.nanoTime()- startTime)/1000000;
            waitTime = targetTime - timeMillis;

         try{
             this.sleep(waitTime);
         }catch(Exception e){}
            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
         }
        }

    }
    public void setRunning(boolean b)
    {
        running = b;
    }
}
