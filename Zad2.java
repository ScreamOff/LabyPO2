import java.io.*;
public class Zad2 {
    private static String przetworz(String tekst){
        String result = "";
        for(char ch : tekst.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                result += Character.toLowerCase(ch);

            } else if (Character.isLowerCase(ch)) {
                result += Character.toUpperCase(ch);

            }
            else{
                result += ch;
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String buffor = reader.readLine();
        //System.out.println(przetworz(buffor));
        CharArrayWriter writer = new CharArrayWriter();
        writer.write(przetworz(buffor));
        char[] result = writer.toCharArray();
        for(char ch: result) System.out.print(ch);
        }



    }
