package br.piaba.piabadroid.system.world;



/**
 * Classe que representa o mundo que, nesse caso, se aplica a uma representa��o
 * do ambiente de trabalho dos funcion�rios da equipe.
 * 
 * @author pedrovitorlima
 * **/
public interface World {

	
	/**
	 * Inicializa par�metros do {@link World}, lendo os eventos internos e externos
	 * definodos nos arquivos XML para o aplicativo.
	 * Entre outras coisas, cria depend�ncia Agent->World e World->Agent.
	 * 
	 * **/
	public void initWorld();
	
	public void updateGUI();
	
}
