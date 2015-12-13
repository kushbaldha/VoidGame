package orangeboat.voidgame.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import orangeboat.voidgame.R;

import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * Created by Jay on 12/12/2015.
 */
public class TextLoader {
    public static String loadFile(String path, Resources resources){ //txt file loading method
        StringBuilder build = new StringBuilder();
        try{
            //InputStream iS = AssetManager.open(path);
            AssetManager am = resources.getAssets();
            InputStream iS= am.open(path);
            //InputStream iS = resources.getAssets.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
           // BufferedReader br = new BufferedReader(new FileReader(path));
            String l;
            while((l = reader.readLine()) != null)
                build.append(l+ "\n");

            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return build.toString();
    }

    public static int parseInt(String num){ //number parser for txt files
        try{
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
