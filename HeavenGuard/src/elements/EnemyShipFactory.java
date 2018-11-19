package elements;

public class EnemyShipFactory {
	
	public EnemyShip createShip(String tag) {
		
		if(tag.equals(new BasicEnemyShip().getTag())) {
			
			return new BasicEnemyShip();
			
		}
		
		else {
			
			return null;
			
		}
		
	}

}
