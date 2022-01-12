//Converts Svg files to a simpler format, for easier reading in Main. Supports only basic svg files (from calligrapher.io)
import java.io.*;
import javax.xml.parsers.SAXParser;
public class Svg {
    static BufferedReader br;
    static BufferedWriter bw;
    static boolean write;
    static boolean newPath = true;
    static int ctr = -1; //incremented on start of new word
    static String prev = "";
    public static void main (String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Edwin\\bezier.svg")));
        bw = new BufferedWriter(new FileWriter(new File("converted.txt")));
        search('>');
        while(br.ready()) {
            char c = (char)br.read();
            if(Character.isDigit(c) || c == '.') {
                if (!write) {
                    ++ctr;
                    write=true;
                    if (ctr%8 == 0){ //a before word procedure
                        //bw.newLine();
                        if (!newPath) {
                            bw.write(prev);
                            ctr += 2;
                        }
                        prev = "";
                        newPath = false;
                    }
                }
                bw.write(impure(c));
            }
            else if (write){ //after word procedure
                bw.write(impure(' '));
                write = false;
            }
            if(c == '\"'){
                newPath = true;
            }
        }
        bw.close();
    }
    static char impure(char c){
        if (ctr/2%4 == 3)
            prev += c;
        return c;
    }
    static void search(char c) throws IOException {
        while ((char)br.read() != c);
    }
}
