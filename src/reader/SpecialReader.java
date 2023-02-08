package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class SpecialReader extends BufferedReader {
    private Reader reader;
    private ArrayList<String> words;

    public SpecialReader(Reader reader) {
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

    // Ascii table "\n" = 10
    public String readLineAt(int line) throws IOException {
        if (line <= 0)
            return "Lütfen aramak istediğiniz satırı pozitif tam sayı olarak girin.";

        int lineCount = 1;
        int temp;
        StringBuilder value = new StringBuilder();

        while (reader.ready()) {
            temp = reader.read();
            value.append((char) temp);
            if (temp == 10) { // alt satırı kontrol ediyoruz.
                if (line == 1) { // eğer istediğim satır 1'se direkt döngüyü kırıyorum.
                    break;
                } else if (lineCount < line) { // satır sayım istediğim satırdan küçükse eldeki veriyi siliyorum.
                    lineCount++;
                    value.delete(0, value.length());
                } else
                    break;
            }
        }

        if (lineCount == line)
            return value.toString().trim();
        return "Dosya üzerinde ulaşmaya çalıştığınız satır kadar eleman bulunmadı.\n(Maksimum Girilebileceğiniz Değer: "+ lineCount+")";

    }

}
