package cerveja.actions;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

public class EntregarCerveja extends WorldAction {

	@Override
	public boolean verify(PerceptUtil bbAgent){
		Percept beer = bbAgent.getUnicPerceptByName("beer");
		int qtdBeer = beer.getIntValue();
		
		if(qtdBeer == 0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<Percept> action(PerceptUtil bbAgent) {
		Percept beer = bbAgent.getUnicPerceptByName("beer");
		beer.setValue("100");
		
		List<Percept> percepts = new ArrayList<Percept>();
		
		Percept ocioso = bbAgent.getUnicPercept("ocioso", getRequestorName());
		if(ocioso != null){
			ocioso.setToRemove(true);
			percepts.add(ocioso);
		}else{
			Percept interrompeuAcao = new Percept("interrompeuAcao", "");
			interrompeuAcao.setRelatedAgent(getRequestorName());
			percepts.add(interrompeuAcao);
		}
				
		percepts.add(beer);
		
		return percepts;
	}

}
