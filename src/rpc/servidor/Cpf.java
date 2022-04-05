package rpc.servidor;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Cpf {	
		
	private String desmascara(String Cpf) {		
		return Cpf.replace(".", "").replace("-", "").replace(" ", "");
	}
	
    private String mascara(String Cpf) {
    	String ret = "";
    	ret = Cpf.substring(0, 3) + ".";
    	ret = ret + Cpf.substring(3, 6) + ".";
    	ret = ret + Cpf.substring(6, 9) + "-";
    	ret = ret + Cpf.substring(9, 11);
    	    	
    	return ret;    	
    }	
	
    public Map<String, Object> validar(String Cpf) {   
    	Cpf = desmascara(Cpf);
    	Map<String, Object> result = new HashMap<String, Object>();
    	 // considera-se erro CPF's formados por uma sequencia de numeros iguais    	
        if (Cpf.equals("00000000000") || Cpf.equals("11111111111") ||
            Cpf.equals("22222222222") || Cpf.equals("33333333333") ||
            Cpf.equals("44444444444") || Cpf.equals("55555555555") ||
            Cpf.equals("66666666666") || Cpf.equals("77777777777") ||
            Cpf.equals("88888888888") || Cpf.equals("99999999999") ||
            (Cpf.length() != 11))
        {
        	result.put("Cpf", Cpf);
        	result.put("Valid", false);
        	return result;           
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(Cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(Cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == Cpf.charAt(9)) && (dig11 == Cpf.charAt(10)))
            {
            	result.put("Cpf", mascara(Cpf));
            	result.put("Valid", true);
            	return result;  
            }else {
        	result.put("Cpf", Cpf);
        	result.put("Valid", false);
            	return result;
            	}
                } catch (InputMismatchException erro) {
            	result.put("Cpf", Cpf);
            	result.put("Valid", false);
                return result;
            }
    }


}