import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    public static void zipFiles() throws IOException {
        List<String> files = getPdfFiles();

        //
        if(!files.isEmpty()){
            try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get("src/files/anexo.zip")))){
                for (String file : files){
                    //armazena o diretório do arquivo e compacta dentro do anexo.zip
                    Path filePath = Paths.get(file);
                    ZipEntry zipEntry = new ZipEntry(filePath.getFileName().toString());
                    zipOutputStream.putNextEntry(zipEntry);
                    Files.copy(filePath, zipOutputStream);
                    zipOutputStream.closeEntry();

                    //apaga o arquivo original ao ser compactado
                    Files.delete(filePath);
                }
            } catch(Exception e){
                System.out.println("Não foi possível compactar os arquivos.");
            }
            System.out.println("Arquivo anexo.zip criado com sucesso.");
        }
    }
    //monta uma lista com todos os arquivos com extensão de .pdf
    private static List<String> getPdfFiles(){
        File folder = new File("src/files");
        List<String> pdfFiles = new ArrayList<>();

        if(folder.listFiles() != null){
            List<File> listOfFiles = new ArrayList<>(List.of(folder.listFiles()));
            for(File f : listOfFiles){
                String name = f.getName();
                int aux = name.lastIndexOf(".");
                //utiliza o index auxiliar para pegar o tipo de extensão (.pdf, .txt, .docx, etc.)
                String extension = name.substring(aux);
                if(extension.equals(".pdf")){
                    pdfFiles.add(f.toString());
                }
            }
        }
        return pdfFiles;
    }
}
