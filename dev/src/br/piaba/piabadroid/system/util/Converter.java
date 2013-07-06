package br.piaba.piabadroid.system.util;

public class Converter {

	public static int StrToInt(String strValue) {
		try{
			int value = Integer.parseInt(strValue);
			
			return value;
		}catch(NumberFormatException e){
			System.err.println("Percep��o sem valor de inteiro v�lido. value=" + strValue + "\n");
			e.printStackTrace();
		}
		return 0;
	}

}
