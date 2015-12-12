package orangeboat.voidgame.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Jay on 12/12/2015.
 */
public class TextLoader {
    public static String loadFile(String path){ //txt file loading method
        StringBuilder build = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String l;
            while((l = br.readLine()) != null)
                build.append(l+ "\n");

            br.close();
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
