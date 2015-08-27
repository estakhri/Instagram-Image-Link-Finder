package ir.estakhri.instagram.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nima
 */
public class ImageLinkFinder {

    
    
    public static List<String> findPhotoFromSource(String source){
        List<String> out=new ArrayList<>();
        Document parsedDoc=Jsoup.parse(source);
        Elements photoDivs=parsedDoc.getElementsByTag("div");
        for(Element photo:photoDivs){
            if(photo.hasAttr("data-reactid")){
                String url=getUrl(photo.html());
                if(url!=null && !out.contains(url)){
                    out.add(url);
                }
            }
        }
        
        
        return out;
    }
    public static String getUrl(String mixedUrl){
        if(mixedUrl.indexOf("jpg")>0){
        String line=mixedUrl;
        line = line.substring(line.indexOf("https"), line.indexOf("jpg") + 3).replace("=2", ":").replace("=1", ".");
        return line;
        }
        return null;
    }
    public static String readFile(String path) throws FileNotFoundException, IOException{
        String text="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        String line;
        while((line=reader.readLine())!=null){
            text+=line;
        }
        return text;
    }
}
