package orangeboat.voidgame;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;


public class TouchEvents
{
    MotionEvent event;
    public TouchEvents(MotionEvent event)
    {
        this.event = event;
    }
    public void check()
    {
        int action = MotionEventCompat.getActionMasked(event);
        if(MotionEvent.ACTION_DOWN == action)
        {
            System.out.println("Action down!");
        }
    }
}
