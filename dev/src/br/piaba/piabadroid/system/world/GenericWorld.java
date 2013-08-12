package br.piaba.piabadroid.system.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.agent.executor.Executor;
import br.piaba.piabadroid.system.world.action.Param;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.gui.GenericCycleUpdateGUI;
import br.piaba.piabadroid.system.world.model.WorldData;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Classe que implementa m�todos gen�ricos do mundo de uma aplica��o multiagente no Piaba.
 * @author pedrovitorlima
 * **/
public abstract class GenericWorld implements World{

	private WorldData worldData;
	private PerceptUtil perceptUtil;
	protected Activity activity;
	private GenericCycleUpdateGUI cycleUpdateGUI;
	
	public GenericWorld(){
		worldData = new WorldData();
	}
	
	@Override
	public void initWorld(){
		perceptUtil = new PerceptUtil(worldData.getPercepts());
		
		for(GenericAgent agent : worldData.getAgents()){
			agent.init();
		}
		
		worldData.getExecutor().setAgents(worldData.getAgents());
		worldData.getExecutor().init();
	}
	
	
	/**
	 * M�todo utilizado para obter os par�metros a serem enviados a GUI para atualiza��o, ap�s a execu��o de um ciclo
	 * @return Lista de {@link Param} a ser enviadas a GUI
	 * **/
	public List<Param> processGuiParams(){
		return new ArrayList<Param>();
	}
	
	/**
	 * Realiza um ciclo de execu��o.
	 * 
	 * O ciclo de execu��o utiliza um {@link Executor} configurado via XML para executar 
	 * os agentes ap�s eles perceberem o mundo. No pr�ximo ciclo, � verificada as a��es que os
	 * agentes desejam realizar.
	 * 
	 * **/
	public void cycle(){
		Map<Agent, List<WorldAction>> actions = worldData.getExecutor().execute();
	
		//verifica execu��o das a��es para cada agente
		Set<Agent> keySet = actions.keySet();
	
		for(Agent agent : keySet){
			List<WorldAction> actionsAgent = actions.get(agent);
			
			agent.clearOldActions();
			
			for(WorldAction action : actionsAgent){
				boolean executed = action.verify(agent.getPerceptUtil());
				action.setExecuted(executed);
				
				if(executed){
					List<Percept> percepts = action.action(agent.getPerceptUtil());
					
					getPerceptUtil().updatePercepts(percepts);
				}
				agent.addOldAction(action);
			}
			
			agent.clearActions();
		}
		
		//Atualiza percep��es dos agentes
		for(Agent agent: worldData.getAgents()){
			agent.perceptWorld(getPerceptUtil().getPercepts());
		}
			
		if(cycleUpdateGUI != null){
			updateGUI();
		}
	}
	
	public void updateGUI(){
		cycleUpdateGUI.setWorldData(getWorldData());
		activity.runOnUiThread(cycleUpdateGUI);
	}

	public WorldData getWorldData() {
		return worldData;
	}


	public void setWorldData(WorldData worldData) {
		this.worldData = worldData;
	}


	public PerceptUtil getPerceptUtil() {
		return perceptUtil;
	}

	public void setAndroidLayout(Activity activity) {
		this.activity = activity;
	}

	public GenericCycleUpdateGUI getCycleUpdateGUI() {
		return cycleUpdateGUI;
	}

	public void setCycleUpdateGUI(GenericCycleUpdateGUI cycleUpdateGUI) {
		this.cycleUpdateGUI = cycleUpdateGUI;
	}	
}
