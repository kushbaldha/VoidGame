package orangeboat.voidgame.States.Game;

import android.content.res.Resources;
import android.graphics.Canvas;

import orangeboat.voidgame.Entities.GameObjects;
import orangeboat.voidgame.Entities.Landie;
import orangeboat.voidgame.Input.TextLoader;


/**
 * Created by Jay on 12/12/2015.
 */
public class PlatformMap {
    static GameObjects objects;
    public static Platform flat = new Flat(objects.flat, 1);
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
        for(int y = 0; y < height; y++){
            for( int x = 0; x < width;x++){
                layout[x][y]= TextLoader.parseInt(items[(x+y*width)+2]);
            }
        }
    }
    public void update(){
        for(int y = 0; y< height; y++){
            for(int x = 0; x < width; x++) {
                if(getTile(x, y).img != null) {
                    getTile(x, y).update();
                }
            }
        }
    }
    public void draw(Canvas canvas, int offset){
        for(int y = 0; y< height; y++){
            for(int x = 0; x < width; x++) {
                if(getTile(x, y) != null) {
                    canvas.drawBitmap(getTile(x, y).img, (int) (x * Platform.TW) + offset, (int) (y * Platform.TH), null);
                }
                if(getEnemy(x, y) != null){
                    canvas.drawBitmap(getEnemy(x, y).img, (int) (x * Platform.TW) + offset, (int) (y * Platform.TH), null);
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
        //Platform t = Platform.tiles[layout[x][y]];
            if(layout[x][y] == 1) {
                return flat;
            }
        //if (layout[x][y] == 0)
      //  return flat;
        return null;

    }
    public Landie getEnemy(int x , int y)
    {
        if(layout[x][y] == Landie.id){
            return objects.enemyPanel.allLandies.get(0);
        }

        return null;
    }
}
