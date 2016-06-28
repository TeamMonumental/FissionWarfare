package tm.fissionwarfare.explosion.type;

import tm.fissionwarfare.api.IExplosionType;

public enum EnumExplosionType {

	BASIC("Basic", new BasicExplosion(), 80),
	EMP("EMP", new EMPExplosion(), 100),
	PYRO("Pyro", new PyroExplosion(), 100),
	CHEMICAL("Chemical", new ChemicalExplosion(), 100),
	ATOMIC("Atomic", new AtomicExplosion(), 100);

	private String name;
	private IExplosionType explosionType;
	private int fuseTime;

	private EnumExplosionType(String name, IExplosionType explosionType, int fuseTime) {
		this.name = name;
		this.explosionType = explosionType;
		this.fuseTime = fuseTime;
	}
	
	public String getName() {
		return name;
	}
	
	public IExplosionType getExplosionType() {
		return explosionType;
	}
	
	public int getFuseTime() {
		return fuseTime;
	}
}
