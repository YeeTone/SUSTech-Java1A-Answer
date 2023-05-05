package labs.lab11.before;

import java.awt.Color;

public enum CircleColor {
	GREEN("The circle is in the Screen", Color.GREEN), RED("The circle is not in the Screen",
			Color.RED), GRAY("Haven't tested", Color.GRAY);

	private String desc;// The description of instance
	private Color color;// The color of instance

	CircleColor(String desc, Color color) {
		this.desc = desc;
		this.color = color;
	}

	public String getDesc() {
		return this.desc;
	}

	public Color getColor() {
		return this.color;
	}
}
