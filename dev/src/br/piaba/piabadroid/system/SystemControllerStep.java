package br.piaba.piabadroid.system;

import android.os.AsyncTask;
import br.piaba.piabadroid.system.world.GenericWorld;

/**
 * Classe que representa um passo de aplica��o. Executa como uma {@link AsyncTask} para tornar a execu��o
 * das a��es necess�rias ass�ncrona.
 * @author pedrovitorlima
 * **/
public class SystemControllerStep extends AsyncTask<Void, Void, Void>{

	/**
	 * Inst�ncia do mundo da aplica��o
	 * **/
	private GenericWorld world;
	
	/**
	 * Setter para o mundo da aplica��o.
	 * 
	 * @param world Inst�ncia de classe filha de {@link GenericWorld}.
	 * **/
	public void setWorld(GenericWorld world) {
		this.world = world;
	}

	@Override
	protected Void doInBackground(Void... params) {
		for(int i=0; i<world.getWorldData().getCyclesByStep(); i++){
			world.cycle();
		}
		
		return null;
	}

}
