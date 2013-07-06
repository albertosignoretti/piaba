package br.piaba.piabadroid.system.world.action;

import java.util.List;

import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Representa uma Action executada.
 * Uma action possui um m�todo para verifica��o e um m�todo para execu��o das a��es.
 * Em geral, as a��es executadas alteram percep��es
 * @author pedrovitorlima
 * **/
public interface Action {

	/**
	 * Verifica as pr�-condi��es de execu��o da a��o.
	 * @param bbAgent base de cren�as do agente
	 * @return
	 * **/
	boolean verify(PerceptUtil bbAgent);

	/**
	 * Executa os passos da a��o.
	 * @param bbAgent base de cren�as do agente relacionado � a��o
	 * 
	 * @return lista de percep��es a serem alteradas
	 * **/
	List<Percept> action(PerceptUtil bbAgent);

}
