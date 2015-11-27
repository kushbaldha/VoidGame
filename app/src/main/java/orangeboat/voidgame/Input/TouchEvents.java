package orangeboat.voidgame.Input;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import orangeboat.voidgame.PhoneSpecs;
import orangeboat.voidgame.States.Game.GamePanel;
import orangeboat.voidgame.States.Title.MenuPanel;


public class TouchEvents
{
    boolean menuShow = true;
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
            System.out.println(x +" " +y);
            //if(menu.rectPlay.contains(event.getX(),event.getY()))
                if (menu.rectPlay.contains(x, y)) {
                    System.out.println("Play button pressed");
                    menuShow = false;
            }
        }

    }
    public void gameTouch(GamePanel gamePanel) {
        int action = MotionEventCompat.getActionMasked(event);
        if(MotionEvent.ACTION_UP == action)
        {
            System.out.println("Action Up");
            gamePanel.upTouch(x, y);
        }
        if (MotionEvent.ACTION_DOWN == action) {
            gamePanel.downTouch(x,y);
            System.out.println("Action down!");
            System.out.println(PhoneSpecs.width + " " + PhoneSpecs.height );
            System.out.println(x + " " + y);
            //if(menu.rectPlay.contains(event.getX(),event.getY()))
        }
        if(MotionEvent.ACTION_POINTER_DOWN == action)
        {
            x= (int)event.getX(event.getPointerId(event.getActionIndex()));
            y = (int)event.getY(event.getPointerId(event.getActionIndex()));
            gamePanel.multiTouch(x,y);
            System.out.println("Action Pointer down!");
            System.out.println(PhoneSpecs.width + " " + PhoneSpecs.height );
            System.out.println(x + " " + y);
        }
        if(MotionEvent.ACTION_POINTER_UP == action) {
            System.out.println("Action Pointer Up");
            //gamePanel.upTouch(x,y);
        }
    }
    public boolean  checkMenu()
    {
        return menuShow;
    }
    //random comment
}
