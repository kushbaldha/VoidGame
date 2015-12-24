package orangeboat.voidgame.States.Game;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;

import orangeboat.voidgame.Entities.Enemy;
import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.Entities.Landie;
import orangeboat.voidgame.Input.TextLoader;
import orangeboat.voidgame.PhoneSpecs;


/**
 * Created by Jay on 12/12/2015.
 */
public class PlatformMap {
    static GameObjects objects;
    int offset;
    public static Platform flat = new Flat(objects.flat, 1);
    public static Platform spike = new Spike(objects.spike, 2);
    public static Landie landie;
    int phoneWidth;
    String path;
    public ArrayList<Enemy> allLandies = new ArrayList<>();
    public ArrayList<Platform> inFrameList = new ArrayList<>();
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
        this.resources = resources;
        this.path = path;
    }

    /**
     * creates map 2d array for render purposes
     */
    public void loadMap(GameObjects objects, int offset) {
        this.offset = offset;
        phoneWidth = PhoneSpecs.width;
        landie = new Landie(objects.enemyPanel.landieAnimation, objects.enemyPanel.singleLandieImage, 10);
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
            }
            objects.enemyPanel.loadList(allLandies);
        }
        allLandies = null;
    }

    public void update(int offset) {
        //inFrameList.clear();
       /* for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getTile(x, y).img != null)
                {int two = 2;}
                    *//*{
                    getTile(x, y).update();
                    getTile(x,y).x = (int) (x * Platform.TW) + offset;
                    getTile(x,y).y = (int) (y * Platform.TH);
                    if(inFrame(getTile(x,y)))
                        inFrameList.add(getTile(x,y));
                }*//*
            }
        }*/

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
      /* for(int i = 0; i< inFrameList.size();i++)
        {
            canvas.drawBitmap(inFrameList.get(i).img, inFrameList.get(i).x, inFrameList.get(i).y, null);
        }
        inFrameList.clear();*/

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
            return flat;
        }
        if (layout[x][y] == 2) {
            return spike;
        }
        //if (layout[x][y] == 0)
        //  return flat;
        return null;

    }

    public Enemy getEnemy(int x, int y) {
        if (layout[x][y] == Landie.id) {
            int landieX = (x * Platform.TW);
            int landieY = (y * Platform.TH);
            landie.load(landieX, landieY, offset);
            return landie;
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