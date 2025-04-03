import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PdfScrapperTest {

    // teste que verifica se encontra pelo menos 1 pdf
    @Test
    public void testRealWebsiteScraping() throws IOException {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        List<String> pdfLinks = PdfScrapper.getPdfLinks(url);


        assertFalse(pdfLinks.isEmpty());
        assertTrue(pdfLinks.stream().anyMatch(link -> link.endsWith(".pdf")));
    }
}