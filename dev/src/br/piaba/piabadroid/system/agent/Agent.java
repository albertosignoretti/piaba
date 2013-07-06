package br.piaba.piabadroid.system.agent;

import java.util.List;

import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Interface que deve ser implementada pelos agentes do sistema
 * 
 * @author pedrovitorlima
 * **/
public interface Agent{

	int cycle = 0;
	String name = "";
	PerceptUtil perceptUtil = null;
	/**
	 * Executa a��es particulares do agente.
	 * **/
	public void executeAgent();
	
	/**
	 * Retorna nome usado como identificador do agente.
	 * 
	 * @return id do agente
	 * **/
	public String getName();
	
	/**
	 * Configura percentual de chance de execu��o do agente no ciclo.
	 * **/
	public void setChanceExecution();
	
	/**
	 * Configura periodicidade em ciclos de tentativa de execu��o do agente
	 * **/
	public void setCycleExecution();
	
	/**
	 * Retorna a quantidade de ciclos por passo de execu��o
	 * 
	 * @return quantidade de ciclos por passo de execu��o
	 * **/
	public int getCycle();
	
	/**
	 * Percebe o mundo atrav�s da atualiza��o da base de cren�as do agente pelas percep��es
	 * contidas no mundo.
	 * **/
	public void perceptWorld(List<Percept> worldPercepts);
	
	/**
	 * Retorna inst�ncia de {@link PerceptUtil}, classe auxiliar utilizada para manipular
	 * a base de cren�as do agente.
	 * 
	 * @return {@link PerceptUtil}
	 * **/
	public PerceptUtil getPerceptUtil();
	
	public PerceptUtil getMyPerceptsUtil();
	
	public void addOldAction(WorldAction action);

	public List<WorldAction> getOldActions();
	
	public void clearOldActions();

	public void clearActions();
	
	public String getType();
	
}
