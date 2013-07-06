package br.piaba.piabadroid.system.world;

import java.util.List;

import br.piaba.piabadroid.system.world.action.Param;

/**
 * Interface utilizada para designar interfaces gr�ficas com o usu�rio referente ao mundo.
 * @author pedrovitorlima
 * 
 * **/
public interface WorldGUI {

	/**
	 * M�todo utilizado para atualizar interface gr�fica. 
	 * **/
	public void updateGUI(List<Param> parameters);
	
}
