package rpc.cliente;

import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.parser.StringParser;

public class ClienteRPC {

	//DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185"; 
    private XmlRpcClient cliente;
	
    public ClienteRPC() {
		try {
			//configura o cliente para que ele possa se conectar ao servidor
			XmlRpcClientConfigImpl configuracaoCliente = new XmlRpcClientConfigImpl();
            configuracaoCliente.setServerURL(new URL(URL_SERVIDOR));
			//seta a configuração no cliente
            cliente = new XmlRpcClient();
            cliente.setConfig(configuracaoCliente);
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }

    public Map<String, Object> validarCpf(String Cpf) throws Exception {
        Object[] parametros = new Object[]{new String(Cpf)};
        Map<String, Object> resultado =  (Map<String, Object>) cliente.execute("Cpf.validar", parametros);
        return resultado;
    }


    public static void main(String[] args) throws Exception {
        ClienteRPC x = new ClienteRPC();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o cpf para validacao: ");
        String input = sc.nextLine();    
//        String input = "05075878121";
        
       Map<String, Object> resultado = x.validarCpf(input);
        
       System.out.println("O CPF " + (String) resultado.get("Cpf") + ((boolean) resultado.get("Valid") ? " é valido" : " é invalido") + "!");

    }
}
