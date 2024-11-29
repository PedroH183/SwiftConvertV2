public class SwiftConvert {

    public static void main(String[] args) throws NotFoundFile{
        ConverterService conversor = new ConverterService();

        for(String arg: args){
            try{
                conversor.addFileToConvert(arg);
            } catch(NotFoundFile err){
                continue;
            }
        }
    }
}