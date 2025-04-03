import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Downloader {
    private static final int THREAD_COUNT = 2;
    //realiza o download dos arquivos e envia para a pasta src/files
    public static void downloadFile(List<String> urls){
        try {
            //cria o diretório de files caso não exista.
            Files.createDirectories(Paths.get("src/files"));
            //define o número maximo de downloads por vez (quantidade de threads)
            ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

            int counter = 1;
            for (String url : urls) {
                int fileIndex = counter++;
                pool.execute(() -> downloadSingleFile(url, fileIndex));
            }
            pool.shutdown();
            //define tempo máximo de download para 1 minuto.
            if(!pool.awaitTermination(1, TimeUnit.MINUTES)){
                System.out.println("Tempo de execução esgotado, alguns downloads podem não ter sido finalizados.");
            }
        } catch (InterruptedException e) {
            System.out.println("O processo foi interrompido.");
            //encerra a execução das threads paralelas
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os arquivos.");
        }
    }

    private static void downloadSingleFile(String url, int counter){
        try{
            String fileName = "Anexo_" + counter + ".pdf";
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream out = new FileOutputStream("src/files/" + fileName);

            in.transferTo(out);
            System.out.println("Download " + fileName + " concluido.");
            in.close();
            out.close();
        } catch(IOException e){
            System.out.println("Ocorreu um erro ao realizar o download do arquivo.");
        }

    }
}
