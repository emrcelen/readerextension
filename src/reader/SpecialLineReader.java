package reader;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;

public class SpecialLineReader extends LineNumberReader {
    private Reader reader;
    private ArrayList<String> words;
    public SpecialLineReader(Reader reader) {
        super(reader);
        this.reader = reader;
        this.words = new ArrayList<>();
    }

    @Override
    public String readLine() throws IOException {
        String value = super.readLine();
        readWords(value);
        return value;
    }
    // Kelimelere ayırıyorum:
    private void readWords(String kelime) {
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < kelime.length(); i++) {
            if (kelime.charAt(i) != ' ')
                value.append(kelime.charAt(i));
            else if (kelime.charAt(i) == ' ') {
                words.add(value.toString());
                value.delete(0, value.length());
            }
            if ((i + 1) == kelime.length())
                words.add(value.toString());
        }
    }
    public ArrayList<String> readWords() {
        if (this.words.isEmpty())
            throw new RuntimeException("Henüz dosyayı okumadınız, lütfen önce dosyayı okuyun.");
        return this.words;
    }

    public String readLineAt(int line) throws IOException{
        if (line <= 0)
            return "Lütfen aramak istediğiniz satırı pozitif tam sayı olarak girin.";
        String  value = "";
        while (super.ready()){
            value = super.readLine();
            if(super.getLineNumber() == line)
                return value;
        }
        return "Dosya üzerinde ulaşmaya çalıştığınız satır kadar eleman bulunmadı.\n(Maksimum Girilebileceğiniz Değer: "+ super.getLineNumber()+")";

    }

}
