import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        File dir = new File("C://Games/savegames");

        if (dir.mkdir()) {
            System.out.println("Folder created");
        } else {
            System.out.println("Folder not created");
        }

        String outputDir = "C://Games/savegames";

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("C://Games/savegames/archive.zip"))) {

            ZipEntry entry;
            String name;
            while ((entry=zin.getNextEntry()) != null){
                name = entry.getName();

                FileOutputStream fout = new FileOutputStream(outputDir+'/'+name);
                for (int c = zin.read();c != -1; c=zin.read()){
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress gameProgressNew = null;
        try (FileInputStream fis = new FileInputStream("C://Games/savegames/packedSaveFile1.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgressNew = (GameProgress) ois.readObject();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgressNew);
    }
}
