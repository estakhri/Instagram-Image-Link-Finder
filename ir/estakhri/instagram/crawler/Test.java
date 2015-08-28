package ir.estakhri.instagram.crawler;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Nima
 */
public class Test {
    public static void main(String[] args) throws IOException {

        if (args.length == 1) {
            String line = args[0];
            File sourceFile = new File(line);
            if (sourceFile.isFile()) {
                System.out.println(ImageLinkFinder.findPhotoFromSource(ImageLinkFinder.readFile(line)));
            } else {

                line = ImageLinkFinder.getUrl(line);
                System.out.println(line);
                Test.addToClipBoard(line);
            }
        } else {

            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = ImageLinkFinder.getUrl(line);
                System.out.println(line);
                Test.addToClipBoard(line);
            }
        }
    }

    private static void addToClipBoard(String str) {
        StringSelection selection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
