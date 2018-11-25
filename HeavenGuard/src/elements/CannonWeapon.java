package elements;

public class CannonWeapon extends BaseWeapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int maxPower;
	public CannonWeapon(int maxPower, int damage) {
		
		this.maxPower = maxPower;
		this.damage = damage;
	}


}
