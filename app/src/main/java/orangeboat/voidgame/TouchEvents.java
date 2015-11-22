package orangeboat.voidgame;

import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;


public class TouchEvents
{
    boolean touched = false;
    MotionEvent event;
    int x,y;
    public TouchEvents(MotionEvent event)
    {
        this.event = event;
        x = (int) event.getX();
        y = (int) event.getY();
    }
    public void check(MenuPanel menu)
    {
        int action = MotionEventCompat.getActionMasked(event);
        if(MotionEvent.ACTION_DOWN == action)
        {
            System.out.println("Action down!");
            //if(menu.rectPlay.contains(event.getX(),event.getY()))
            if(menu.rectPlay.contains(x,y))
            {
                System.out.println("Play button pressed");
            }
            System.out.println(PhoneSpecs.height + " " + PhoneSpecs.width);
        }
    }

    public void onUserInteraction(){
        touched = true;
        System.out.println("PRESSSSSSSS");
    }
    //random comment
}
