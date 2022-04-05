package rpc.servidor;
import java.io.IOException;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class ServidorRPC {
	private boolean running = false;
	private WebServer ws;
	public  void onLog();
    ServidorRPC() {
        try {
        	
			// Cria um servidor web na porta 8185
            ws = new WebServer(8185); 
            XmlRpcServer servidor = ws.getXmlRpcServer(); 
			// Adiciona um novo "handler" ao PHM
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("Cpf", Cpf.class);
            phm.addHandler("Log", getClass());
			// Define um handler no servidor
            servidor.setHandlerMapping(phm);		
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }

    public static void main(String[] args) {
		new ServidorRPC();
    }
    
    public void stop() {
    	running = false;
    	ws.shutdown();
    	System.out.println("Servidor pausado com sucesso!");
    }
    
    public boolean isRuning() {
    	return running;
    }
    
    public void start() throws IOException {    	
    	running = true;
    	ws.start();
    	System.out.println("Servidor iniciado com sucesso!");
    }
}
