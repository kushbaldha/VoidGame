package orangeboat.voidgame.States.Game;

import android.content.res.Resources;
import android.graphics.Canvas;

import orangeboat.voidgame.Input.TextLoader;

/**
 * Created by Jay on 12/12/2015.
 */
public class PlatformMap {
    /**
     * size of the map
     */
    public static int width, height;
    /**
     * map array
     */
    public static int[][] layout;
    /**
     * initial location of the player
     */
    public int spawnX, spawnY;
    public Resources resources;
    /**
     * creates map
     * @param path
     */
    public PlatformMap(String path, Resources resources){
        this.resources = resources;
        loadMap(path);
    }
    /**
     * creates map 2d array for render purposes
     * @param path file path
     */
    private void loadMap(String path){
        String file = TextLoader.loadFile(path ,resources);
        String[] items = file.split("\\s+");
        width = TextLoader.parseInt(items[0]);
        height = TextLoader.parseInt(items[1]);
        layout = new int[width][height];
        for(int y = 0; y< height; y++){
            for( int x = 0; x<width;x++){
                layout[x][y]= TextLoader.parseInt(items[(x+y*width)+2]);
            }

        }
    }
    public void draw(Canvas canvas){
        for(int y = 0; y< height; y++){
            for(int x = 0; x < width; x++) {
                if(getTile(x, y).img != null) {
                    canvas.drawBitmap(getTile(x, y).img, (int) (x * Platform.TW), (int) (y * Platform.TH), null);
                }
            }
        }

    }
    /**
     * gets tile based on number id
     * @param x
     * @param y
     * @return tile	tile object
     */
    public Platform getTile(int x, int y){
        Platform t = Platform.tiles[layout[x][y]];
        if(x<0||y<0||x>=width||y>=height){
            return Platform.flat;
        }
        if (t == null){
            return null;
        }
        return t;
    }
}
