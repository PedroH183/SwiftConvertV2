import java.io.IOException;

class CommandService {

    public static Process executeAcommand(String... command){

        Process process = null;
        ProcessBuilder processContext = new ProcessBuilder(command);

        try{
            process = processContext.start();
            // vai bloquear minha thread original e esperar por um retorno do meu processo
            process.waitFor();

        } catch( IOException err ){
            System.out.println("Ocorreu um erro ao executar o processo !!");
        } catch ( InterruptedException e ) {
            throw new RuntimeException(e);
        }
        return process;
    }
}