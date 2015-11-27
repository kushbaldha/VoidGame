package orangeboat.voidgame.States.Game;

import android.graphics.Canvas;

import orangeboat.voidgame.Entities.GameObjects;

public class GamePanel {
    public GameObjects objects;
    int x = 0;

    public GamePanel(GameObjects objects) {
        this.objects = objects;
    }

    public void update() {
        objects.player.update();
        if (objects.player.moveLeft && x < 0) {
            x += ((int) (objects.player.phoneWidth * 0.01));
        }
        if (objects.player.moveRight && x > -1 * (objects.gameBackgroundSky.getWidth())) {
            x -= ((int) (objects.player.phoneWidth * 0.01));
        }

    }

    public void load() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(objects.gameBackgroundSky, x / 2, 0, null);
        canvas.drawBitmap(objects.gameBackgroundFloor, x, 840, null);
        objects.player.draw(canvas);
        objects.gameMenu.draw(canvas);
    }

    public void downTouch(int x, int y) {
        /*switch (objects.gameMenu.checkGameButton(x, y)) {
            case 1:
                objects.player.moveLeft();
            case 2:
                objects.player.moveRight();
        }*/
        int check = objects.gameMenu.checkGameButton(x, y);
        if (check == 1) {
            objects.player.moveLeft();

        }
        if (check == 2) {
            objects.player.moveRight();

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

    public void upTouch(int x, int y) {
        if (objects.player.checkIfMoving())
            objects.player.moveStop();
       /* switch (objects.gameMenu.checkGameButton(x, y)) {
            case 1:
                objects.player.moveStop();
            case 2:*/
        //int check = objects.gameMenu.checkGameButton(x, y);

    }
}

