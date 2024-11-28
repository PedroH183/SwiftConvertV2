public class ConverterServiceImpl{
    /*
    *   Classe responsável por fazer o intermedio entre a chamada de conversão e a conversão propriamente
    *   Depdende diretamente da existencia do serviço convert instalado na máquina que está utilizando.
    * */

    FileStructure file_structure;
    private static final String CommandPdfToJpg = "convert -verbose -density 150 -trim %s -quality 100 -flatten -sharpen 0x1.0 %s";

    public ConverterServiceImpl(String filename){
        this.file_structure = new FileStructure(filename);
    }

    public Boolean convertMimeTypeFile(String resultMimeType) {

        String filename = file_structure.filename;

        String extensionDestiny = MimeTypeSupport.getExtension(resultMimeType);
        String extensionOrigin = MimeTypeSupport.getExtension(file_structure.mimeType);

        assert extensionOrigin != null;
        assert extensionDestiny != null;

        String outputName = filename.replace(extensionOrigin, extensionDestiny);

        String fileNameOutput = file_structure.getFileNameDestiny(outputName);
        Process processIO = CommandService.executeAcommand(this.getCommand(filename, fileNameOutput));

        boolean exitCode = !(processIO.exitValue() == 1);

        if(!exitCode){ return false; }

        return true;
    }

    public String[] getCommand(String pathOriginal, String pathDestino){
        return String.format(CommandPdfToJpg, pathOriginal, pathDestino).split(" ");
    }
}