import java.io.*;

public class Zad1 {
    private static byte[] szyfruj(byte[] doszyfru, int shift){
        byte[] result = new byte[doszyfru.length];
        for(int i =0;i<doszyfru.length;i++){

            result[i] = (byte) (doszyfru[i]+shift);

        }
        return result;
    }
    public static void main(String[] args) {
        String outfile = "output.txt";
        int shift = 3;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz dane: \n");
        try {
            String strinreader = reader.readLine();
            String uinput = strinreader.toUpperCase();
            byte[] zaszyfrowanyciag = szyfruj(uinput.getBytes(),shift);
            OutputStream outuuu = new FileOutputStream(outfile);
            outuuu.write(zaszyfrowanyciag);
            outuuu.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }






    }
    }
