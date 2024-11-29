import java.nio.file.Path;
import java.nio.file.Paths;


public class FileStructure {
    /*
     * Classe responsável por fazer toda a validação da exitencia 
     * de um determinado arquivo,
     */

    protected String filename;
    protected String mimeType;

    public FileStructure(String nome_arquivo) throws NotFoundFile{

        Path search_files_into_fileSystem = Paths.get("./FilesToConvert/" + nome_arquivo);

        if(!search_files_into_fileSystem.toFile().exists()){
            throw new NotFoundFile(String.format("O arquivo %s não foi encontrado no sistema de arquivos", nome_arquivo));
        }

        // saving metadatas
        this.filename = nome_arquivo;
        String[] all_words = this.filename.split(".");
        this.mimeType = all_words[all_words.length - 1];
        System.out.println(String.format("Arquivo %s adicionado no vetor de conversão...", nome_arquivo));
    }
}
