package orangeboat.voidgame.States.Game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.voidgame.Entities.Enemies.Rotor;
import orangeboat.voidgame.Entities.Enemies.Tank;
import orangeboat.voidgame.Entities.Enemies.Umbrack;
import orangeboat.voidgame.Entities.Enemies.Enemy;
import orangeboat.voidgame.Entities.Enemies.Flippy;
import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.Entities.Enemies.Landie;
import orangeboat.voidgame.Input.TextLoader;
import orangeboat.voidgame.PhoneSpecs;
import orangeboat.voidgame.States.Game.Items.HeartDrop;
import orangeboat.voidgame.States.Game.Items.Item;


/**
 * Created by Jay on 12/12/2015.
 */
public class PlatformMap {
    static GameObjects objects;
    int offset;
    public static Platform flat = new Flat(objects.flat, 1);
    public static Platform spike = new Spike(objects.spike, 2);
    public static Rect baseRect;
    public Landie landie;
    public Landie landie2;
    public Umbrack umbrack;
    int phoneWidth;
    String path;
    public ArrayList<Enemy> allLandies = new ArrayList<>();
    public ArrayList<Rect> inFrameHitboxes = new ArrayList<>();
    public ArrayList<Boolean> inFrameSpikes = new ArrayList<>();
    public ArrayList<Item> allItems = new ArrayList<>();
    Paint paint;
    /**
     * size of the map
     */
    public static int width, height;
    /**
     * map array
     */
    public static int[][] layout;
    public Resources resources;

    /**
     * creates map
     *
     * @param path
     */
    public PlatformMap(String path, Resources resources) {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        this.resources = resources;
        this.path = path;
    }

    /**
     * creates map 2d array for render purposes
     */
    public void loadMap(GameObjects objects, int offset) {
        this.objects = objects;
        this.offset = offset;
        phoneWidth = PhoneSpecs.width;
        baseRect = new Rect(0,((int)(PhoneSpecs.height/1.49)+192),phoneWidth,((int)(PhoneSpecs.height/1.49)+1)+193);

        //landie = new Landie(objects.enemyPanel.landieAnimation, objects.enemyPanel.singleLandieImage, 10);
        //landie2 = new Landie(objects.enemyPanel.landieAnimation, objects.enemyPanel.singleLandieImage, 10);


        String file = TextLoader.loadFile(path, resources);
        String[] items = file.split("\\s+");
        width = TextLoader.parseInt(items[0]);
        height = TextLoader.parseInt(items[1]);
        layout = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                layout[x][y] = TextLoader.parseInt(items[(x + y * width) + 2]);
                if (getEnemy(x, y) != null) {
                    allLandies.add(getEnemy(x, y));
                }
                if(getItem(x,y)!= null)
                {
                    allItems.add(getItem(x,y));
                }
            }
        }
        objects.enemyPanel.loadList(allLandies);
        objects.itemPanel.loadList(allItems);
        allLandies = null;
        allItems = null;
    }

    public void update(int offset) {
        inFrameHitboxes.add(baseRect);
        inFrameSpikes.add(false);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Platform temp = getTile(x,y);
                if (temp != null) {
                    temp.x = (int) (x * Platform.TW) + offset;
                    temp.y = (int) (y * Platform.TH);
                    temp.update();
                    if(inFrame(temp)) {
                        inFrameHitboxes.add(temp.hitbox);
                        if(temp.id == 2){
                            inFrameSpikes.add(true);
                        }
                        else inFrameSpikes.add(false);
                    }
                }
            }
        }

       /* for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getTile(x, y) != null) {
                    Platform temp = getTile(x,y);
                    temp.x = (int) (x * Platform.TW) + offset;
                    temp.y = (int) (y * Platform.TH);
                    if(inFrame(temp))
                        inFrameList.add(temp);
                }
            }
        }*/
    }

    public void draw(Canvas canvas, int offset) {
       for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getTile(x, y) != null) {
                    Platform temp = getTile(x,y);
                    temp.x = (int) (x * Platform.TW) + offset;
                    temp.y = (int) (y * Platform.TH);
                    temp.update();
                    if(inFrame(temp))
                    canvas.drawBitmap(temp.img, temp.x, temp.y, null);
                }
            }
        }
       for(int i = 0; i< inFrameHitboxes.size();i++)
        {
         //   canvas.drawRect(inFrameHitboxes.get(i),paint);
        }
        inFrameHitboxes.clear();
        inFrameSpikes.clear();
    }

    /**
     * gets tile based on number id
     *
     * @param x
     * @param y
     * @return tile    tile object
     */
    public Platform getTile(int x, int y) {
        //Platform t = Platform.tiles[layout[x][y]];
        if (layout[x][y] == 1) {
            return new Flat(objects.flat, 1);
        }
        if (layout[x][y] == 2) {
            return new Spike(objects.spike, 2);
        }
        //if (layout[x][y] == 0)
        //  return flat;
        return null;

    }
    public Item getItem(int x, int y)
    {
        if(layout[x][y] == HeartDrop.id)
        {
            HeartDrop temp = new HeartDrop(objects.itemPanel.heartDrop);
            int itemX = (x * Platform.TW)+Platform.TW/2;
            int itemY = (y * Platform.TH);
            temp.load(itemX,itemY,offset);
            return temp;
        }
        return null;
    }
    public Enemy getEnemy(int x, int y) {
        if (layout[x][y] == Landie.id) {
            Landie tempLandie = new Landie(objects.enemyPanel.landieAnimation, objects.enemyPanel.singleLandieImage, 10);
            int landieX = (x * Platform.TW)+Platform.TW/2;
            int landieY = (y * Platform.TH);
            tempLandie.load(landieX, landieY, offset, objects.enemyPanel.splatter, objects.enemyPanel.splatterRev);
            return tempLandie;
        }
        else if (layout[x][y] == Umbrack.id) {
            Umbrack tempUmbrack = new Umbrack(objects.enemyPanel.umbrackAnimation, objects.enemyPanel.singleUmbrackImage, 22, objects.enemyPanel.rollAnimation);
            int uX = (x * Platform.TW)+Platform.TW/2;
            int uY = (y * Platform.TH);
            tempUmbrack.load(uX, uY, offset, objects.enemyPanel.splatter, objects.enemyPanel.splatterRev);
            return tempUmbrack;
        }
        else if (layout[x][y] == Rotor.id) {
            Rotor tempRotor = new Rotor(objects.enemyPanel.rotorAnimation, objects.enemyPanel.singleRotorImage, 22);
            int rX = (x * Platform.TW)+Platform.TW/2;
            int rY = (y * Platform.TH);
            tempRotor.load(rX, rY, offset, objects.enemyPanel.splatter, objects.enemyPanel.splatterRev);
            return tempRotor;
        }
        else if (layout[x][y] == Tank.id) {
            Tank tempTank = new Tank(objects.enemyPanel.tankAnimation, objects.enemyPanel.singleTankImage, 22);
            int tX = (x * Platform.TW)+Platform.TW/2;
            int tY = (y * Platform.TH);
            tempTank.load(tX, tY, offset, objects.enemyPanel.splatter, objects.enemyPanel.splatterRev);
            return tempTank;
        }
        else if (layout[x][y] == Flippy.id) {
            Flippy tempFlippy = new Flippy(objects.enemyPanel.flippyAnimation, objects.enemyPanel.singleFlippyImage, 22);
            int fX = (x * Platform.TW)+Platform.TW/2;
            int fY = (y * Platform.TH);
            tempFlippy.load(fX, fY, offset, objects.enemyPanel.splatter, objects.enemyPanel.splatterRev);
            return tempFlippy;
        }
        return null;
    }

    public boolean inFrame(Platform platform)
    {
        if((platform.hitbox.left >= 0 && platform.hitbox.left<=(phoneWidth)) || ( platform.hitbox.right >= 0 && platform.hitbox.right<=phoneWidth))
            return true;
        return false;
    }
}