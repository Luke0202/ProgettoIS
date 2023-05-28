package is.parser;

import is.builder.TextBuilderIF;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.StringTokenizer;

public class TextParser {
    //Analizza un file.txt e lo converte in un Azienda
    //Lettura di un file.txt e conversione in un Aziendaa
    private final String urlString;
    private final TextBuilderIF builder;
    private BufferedReader br;
    private String line;
    private StringTokenizer st;
    public TextParser(TextBuilderIF builder, String urlString){
        this.builder=builder;
        this.urlString=urlString;
    }
    public void build() {
        try {
            doParse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void doParse() throws IOException{
        URL url;
        try {
            url = new URI(urlString).toURL();
            br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Errore di lettura");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
