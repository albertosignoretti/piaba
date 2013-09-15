package br.piaba.piabadroid.system.world.percepts;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.percepts.exceptions.PerceptNotFoundException;

/**
 * Classe Util que ajuda a gerenciar percep��es
 * **/
public class PerceptUtil {

	private List<Percept> percepts;
	
	public PerceptUtil(List<Percept> percepts){
		this.percepts = percepts;
	}
	
	/**
	 * Retorna lista de percep��es de acordo com um nome fornecido.
	 * Avalia todas as percep��es para encontrar aquelas cujo padr�o de nome seja respeitado.
	 * 
	 * @param name nome da percep��o
	 * @return Lista de percep��es com o nome informado
	 * **/
	public List<Percept> getPerceptsByName(String name){
		List<Percept> returned = new ArrayList<Percept>();
		
		for(Percept percept : percepts){
			if(name.equals(percept.getName())){
				returned.add(percept.clone());
			}
		}
		
		return returned;
	}
	
	/**
	 * Retorna percep��o �nica de acordo com nome fornecido.
	 * Recomendado para se utilizar quando for sabido que existe apenas uma percep��o com esse nome.
	 * � geralmente mais r�pido que o {@link #getPerceptsByName(String)} pois s� percorre a lista at� encontrar
	 * a primeira ocorr�ncia.
	 * 
	 *  @param name Nome da percep��o
	 *  @return primeira ocorr�ncia da percep��o com esse nome
	 * **/
	public Percept getUnicPerceptByName(String name){
		
		Percept retorno = null;
		
		for(Percept percept : percepts){
			if(percept.getName().equals(name)){
				retorno = percept.clone();
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Retorna percep��es de acordo com express�o regular de valor informado.
	 * Uma percep��o � constitu�da por nome(param1, param2, param3, ..., paramN).
	 * O valor informado deve ser referente aos nomes. Ex:. x,y* informado trar� todas
	 * as percep��es que possuem o valor come�ando por x,y.
	 *
	 * @param pattern Padr�o de valor a ser encontrado
	 * @return Lista de percepts cujo padr�o de valor foi encontrado
	 * **/
	
	
	public List<Percept> getPerceptsByValuePattern(String regex){
		List<Percept> perceptsMatch = new ArrayList<Percept>();
		
		for(Percept percept : percepts){
			if(percept.getValue().matches(regex)){
				perceptsMatch.add(percept.clone());
			}
			
		}
		
		return perceptsMatch;
	}
	
	
	/**
	 * Retorna primeira percep��o encontrada de acordo com express�o regular de valor informado.
	 * Uma percep��o � constitu�da por nome(param1, param2, param3, ..., paramN).
	 * O valor informado deve ser referente aos nomes. Ex:. x,y* informado trar� 
	 * a primera percep��o encontrada cujo valor comece com x, y
	 *
	 * @param pattern Padr�o de valor a ser encontrado
	 * @return primeira ocorr�ncia encontrada cujo padr�o de valor foi encontrado
	 * **/
	
	
	public Percept getUnicPerceptsByValuePattern(String regex){
		Percept retorno = null;
		
		
		for(Percept percept : percepts){
			if(percept.getValue().matches(regex)){
				retorno =  percept.clone();
			}
			
		}
		
		return retorno;
	}
	
	/**
	 * Retorna os valores de uma percep��o (value1, value2, value3... valueN)
	 * como arrays de String
	 * **/
	public static String[] perceptValueAsArray(Percept percept){
		return percept.getValue().split(",");
	}

	/**
	 * Atualiza percep��o
	 * @param namePercept Nome da percep��o a ser atualizada
	 * @param valuePercept novo valor
	 * @throws Exception 
	 * **/
	public void updatePercept(String namePercept, String valuePercept) throws PerceptNotFoundException {
		
		boolean found = false;
		
		for(Percept percept : percepts){
			if(percept.getName().equals(namePercept)){
				percept.setValue(valuePercept);
				found = true;
				break;
			}
		}
		
		if(!found){
			throw new PerceptNotFoundException("Perception " + namePercept + " not found.");
		}
		
	}

	/**
	 * Remove percep��o
	 * @param namePercept Nome da percep��o a remover
	 * @return se conseguiu remover
	 * **/
	public boolean removePercept(String namePercept) {
		Percept perceptToRemove = null;
		
		for(Percept percept : percepts){
			if(percept.getName().equals(namePercept)){
				perceptToRemove = percept;
				break;
			}
		}
		
		if(perceptToRemove == null){
			return false;
		}else{
			return percepts.remove(perceptToRemove);
		}		
	}

	public boolean removePercept(Percept perceptToRemove){
		Percept perceptRemoved = null;
		for(Percept percept : getPercepts()){
			if(percept.equals(perceptToRemove)){
				perceptRemoved = percept;
				break;
			}
		}
		
		if(perceptRemoved == null){
			return false;
		}else{
			return percepts.remove(perceptToRemove);
		}
	}
	
	/**
	 * Adiciona percep��o.
	 * 
	 * @param percept {@link Percept}
	 * **/
	public void addPercept(Percept percept) {
		
		this.percepts.add(percept.clone());
	}

	public void updatePercept(String namePercept, int valuePercept) throws PerceptNotFoundException {
		updatePercept(namePercept, valuePercept + "");
		
	}

	public List<Percept> getPercepts() {
		return this.percepts;
	}

	public void updatePercepts(List<Percept> percepts) {
		
		if(percepts != null){
			for(Percept percept : percepts){
				boolean found = false;
				
				for(Percept worldPercept : getPercepts()){
					
					if(percept.getName().equals(worldPercept.getName())){
						if(percept.getRelatedAgent().equals(worldPercept.getRelatedAgent()) && !percept.isSelf()) {
							worldPercept.setValue(percept.getValue());
							found = true;
							break;
						}
					}
					
				}
				
				if(!found){
					addPercept(percept);
				}
				
				if(percept.isToRemove()){
					removePercept(percept);
					continue;
				}
			}
			
		}
	
		
	}

	public Percept getUnicPercept(String namePercept, String relatedAgent) {
		for(Percept percept : getPercepts()){
			if(percept.getName().equals("ocioso")){
				System.err.println();
			}
			if(percept.getName().equals(namePercept) && percept.getRelatedAgent().equals(relatedAgent)){
				return percept;
			}
		}
		return null;
	}

	public void updatePercept(String namePercept, String valuePercept,
			String relatedAgent) {

		for(Percept percept : percepts){
			if(percept.getName().equals(namePercept) && percept.getRelatedAgent().equals(relatedAgent)){
				percept.setValue(valuePercept);
				break;
			}
		}
	}
}
