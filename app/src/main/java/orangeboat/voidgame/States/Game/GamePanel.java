package orangeboat.voidgame.States.Game;

import android.graphics.Canvas;

import orangeboat.voidgame.Entities.GameObjects;

public class GamePanel {
    int moving = 0;
    public GameObjects objects;
    int floorx = 0;
    int skyx = 0;
    int dx;
    public GamePanel(GameObjects objects) {
        this.objects = objects;
        dx = ((int) (objects.player.phoneWidth * 0.01));
    }

    public void update() {
        objects.player.update();
        if (objects.player.moveLeft && skyx < 0) {
            floorx += dx;
            skyx += dx/2;
        }
        if (objects.player.moveRight && skyx > -1 * (objects.gameBackgroundSky.getWidth())+ objects.player.phoneWidth) {
            floorx -= dx;
            skyx -= dx/2;
        }

    }

    public void load() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(objects.gameBackgroundSky, skyx , 0, null);
        canvas.drawBitmap(objects.gameBackgroundFloor, floorx, 840, null);
        objects.player.draw(canvas);
        objects.gameMenu.draw(canvas);
    }


    public void  downTouch(int x, int y , int pointerNumber) {
        /*switch (objects.gameMenu.checkGameButton(x, y)) {
            case 1:
                objects.player.moveLeft();
            case 2:
                objects.player.moveRight();
        }*/
        int check = objects.gameMenu.checkGameButton(x, y);
        if (check == 1) {
            objects.player.moveLeft();
            moving = pointerNumber;
        }
        if (check == 2) {
            objects.player.moveRight();
            moving = pointerNumber;
        }
        if (check == 3) {
            objects.player.moveJump();
        }
        if (check == 4) {
            objects.gameMenu.showNoneInteract(true);
            objects.player.allMovement(false);
            objects.player.moveStop();
        } else if (objects.gameMenu.showNoneInteract) {
            objects.gameMenu.showNoneInteract(false);
            objects.player.allMovement(true);
        }
    }

    public void upTouch(int x, int y,int pointerNumber) {
        if (objects.player.checkIfMoving() && moving == pointerNumber)
            objects.player.moveStop();
       /* switch (objects.gameMenu.checkGameButton(x, y)) {
            case 1:
                objects.player.moveStop();
            case 2:*/
        //int check = objects.gameMenu.checkGameButton(x, y);

    }
}

