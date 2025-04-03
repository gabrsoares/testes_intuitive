import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.List;

public class PdfScrapper {

    public static List<String> getPdfLinks(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.select("a[href*=.pdf]").stream()
                .filter(element -> element.text().contains("Anexo I") || element.text().contains("Anexo II"))
                .map(element -> element.absUrl("href"))
                .toList();
    }

    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

        try {
            List<String> anexosPdf = getPdfLinks(url);
            Downloader.downloadFile(anexosPdf);
            Zipper.zipFiles();
        } catch (IOException e) {
            System.out.println("Ocorreu um problema ao buscar os dados, insira uma URL válida.");
        }
    }
}
