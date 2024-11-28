import java.nio.file.Path;
import java.nio.file.Paths;


public class FileStructure {
    /*
     * Classe responsável por fazer toda a validação da exitencia 
     * de um determinado arquivo,
     */

    protected String filename;
    protected String mimeType;

    public FileStructure(String nome_arquivo){

        Path search_files_into_fileSystem = Paths.get("./FilesToConvert/" + nome_arquivo);

        if(!search_files_into_fileSystem.toFile().exists()){
            System.out.println("O arquivo não foi encontrado no sistema de arquivos");
        }

        // saving metadatas
        this.filename = nome_arquivo;
        String[] all_words = this.filename.split(".");
        this.mimeType = all_words[this.filename.split(".").length - 1];
    }

    public String getFileNameDestiny(String outputFileName){
        return "";
    }
}
