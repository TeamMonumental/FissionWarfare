package tm.fissionwarfare.util;

public enum EnumColorUtil {

	DARK_BLUE("1"),
	DARK_GREEN("2"),
	DARK_AQUA("3"),
	DARK_RED("4"),
	DARK_PURPLE("5"),
	GOLD("6"),
	BLUE("9"),
	GREEN("a"),
	AQUA("b"),
	RED("c"),
	LIGHT_PURPLE("d"),
	YELLOW("e"),
	WHITE("f");
	
	public String colorID;
	
	private EnumColorUtil(String colorID) {
		this.colorID = colorID;
	}
	
	public static EnumColorUtil getColorByPrefix(String colorPrefix) {
			
		if (!colorPrefix.isEmpty()) {
			
			String colorID = colorPrefix.substring(1);
			
			for (EnumColorUtil color : values()) {
			
				if (colorID.equals(color.colorID)) {			
					return color;
				}
			}
		}	
		
		return WHITE;
	}
	
	public static String getPrefixByColor(EnumColorUtil enumColor) {
		
		return "\u00a7" + enumColor.colorID;		
	}
}

