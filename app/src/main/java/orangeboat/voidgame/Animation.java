package orangeboat.voidgame;

/**
 * Created by Jay on 11/18/2015.
 */
public class Animation{

    int framenum, frames;

    public Animation(int frame){
       frames = frame;
    }

    public void animate(){
        Thread anime = new Thread () {
            /**
             * run method for animation
             */
            public void run () {
                for(;;){
                    framenum++;
                    if(framenum == frames) framenum = 0;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
