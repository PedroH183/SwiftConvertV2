import java.util.List;
import java.util.ArrayList;

public class ConverterService{
    /*
    *   Classe responsável por fazer o intermedio entre a chamada de conversão e a conversão propriamente
    *   Depdende diretamente da existencia do serviço convert instalado na máquina que está utilizando.
    * */

    List<FileStructure> filesToConvert = new ArrayList<FileStructure>();
    private static final String CommandPdfToJpg = "convert -verbose -density 150 -trim %s -quality 100 -flatten -sharpen 0x1.0 %s";

    public boolean addFileToConvert(String filename) throws NotFoundFile{
        this.filesToConvert.add(new FileStructure(filename));
        return true;
    }

    public boolean convertFiles(){
        for(FileStructure fileStructure: this.filesToConvert){
            this.convertMimeTypeFile(fileStructure);
        }
        return true;
    }

    public Boolean convertMimeTypeFile(FileStructure file_structure){
        String filename = file_structure.filename;

        String extensionDestiny = MimeTypeSupport.getDestinyMimeType();
        String extensionOrigin = MimeTypeSupport.getExtension(file_structure.mimeType);

        assert extensionOrigin != null;
        assert extensionDestiny != null;

        String outputName = filename.replace(extensionOrigin, extensionDestiny);

        String fileNameOutput = this.getFileNameDestiny(outputName);
        Process processIO = CommandService.executeAcommand(this.getCommand(filename, fileNameOutput));

        boolean exitCode = !(processIO.exitValue() == 1);

        if(!exitCode){ return false; }

        return true;
    }

    protected String getFileNameDestiny(String outString){
        return outString;
    }

    protected String[] getCommand(String pathOriginal, String pathDestino){
        return String.format(CommandPdfToJpg, pathOriginal, pathDestino).split(" ");
    }
}