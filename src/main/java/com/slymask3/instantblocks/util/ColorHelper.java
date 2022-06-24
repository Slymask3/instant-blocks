package com.slymask3.instantblocks.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ColorHelper {
	public static final VanillaColor[] VANILLA_COLORS = new VanillaColor[]{
		new VanillaColor(new Color(221,221,221), Blocks.WHITE_WOOL),
		new VanillaColor(new Color(219,125,62), Blocks.ORANGE_WOOL),
		new VanillaColor(new Color(179,80,188), Blocks.MAGENTA_WOOL),
		new VanillaColor(new Color(107,138,201), Blocks.LIGHT_BLUE_WOOL),
		new VanillaColor(new Color(177,166,39), Blocks.YELLOW_WOOL),
		new VanillaColor(new Color(65,174,56), Blocks.LIME_WOOL),
		new VanillaColor(new Color(208,132,153), Blocks.PINK_WOOL),
		new VanillaColor(new Color(64,64,64), Blocks.GRAY_WOOL),
		new VanillaColor(new Color(154,161,161), Blocks.LIGHT_GRAY_WOOL),
		new VanillaColor(new Color(46,110,137), Blocks.CYAN_WOOL),
		new VanillaColor(new Color(126,61,181), Blocks.PURPLE_WOOL),
		new VanillaColor(new Color(46,56,141), Blocks.BLUE_WOOL),
		new VanillaColor(new Color(79,50,31), Blocks.BROWN_WOOL),
		new VanillaColor(new Color(53,70,27), Blocks.GREEN_WOOL),
		new VanillaColor(new Color(150,52,48), Blocks.RED_WOOL),
		new VanillaColor(new Color(25,22,22), Blocks.BLACK_WOOL)
	};

	public static String colorEveryWord(String msg, String color) {
	    StringBuilder builder = new StringBuilder(msg.length());
		builder.append(color);
	    for(int i = 0; i < msg.length(); i++) {
	        char c = msg.charAt(i);
			if(c == ' ') {
				builder.append(" ").append(color);
			} else {
				builder.append(c);
			}
	    }
	    return builder.toString();
	}
	
	public static Block getWoolColor(Color color) {
		VanillaColor inputColor = new VanillaColor(color);

		VanillaColor woolColor = null;
		float diff_lowest = 1F;
		for(VanillaColor vanillaColor : VANILLA_COLORS) {
			float diff = vanillaColor.diff(inputColor);
			if(diff_lowest == 1F || diff < diff_lowest) {
				diff_lowest = diff;
				woolColor = vanillaColor;
			}
		}

		return woolColor.getBlock();


//		double r = color.getRed();
//		double g = color.getGreen();
//		double b = color.getBlue();
//
//		int r4 = (int)Math.round(r/64);
//		int g4 = (int)Math.round(g/64);
//		int b4 = (int)Math.round(b/64);
//
//		String sb = String.valueOf(r4) + g4 + b4;
//
//		return switch (sb) {
//			case "000", "002", "003" -> Blocks.BLACK_WOOL;
//			case "001", "112", "104", "013", "012", "004", "114", "113", "014" -> Blocks.BLUE_WOOL;
//			case "010", "120", "021", "121", "020" -> Blocks.GREEN_WOOL;
//			case "011", "122", "032", "143", "022" -> Blocks.CYAN_WOOL;
//			case "023", "123", "024", "234", "134", "124", "044", "034", "033", "224", "223", "334", "144", "043", "344", "244", "233", "133" -> Blocks.LIGHT_BLUE_WOOL;
//			case "030", "240", "230", "140", "130", "040", "031", "343", "243", "242", "241", "232", "231", "142", "141", "132", "131", "042", "041", "342" -> Blocks.LIME_WOOL;
//			case "100", "300", "301", "401", "400", "211", "200" -> Blocks.RED_WOOL;
//			case "101", "202", "314", "304", "214", "102", "204", "203", "103" -> Blocks.PURPLE_WOOL;
//			case "110" -> Blocks.BROWN_WOOL;
//			case "111" -> Blocks.GRAY_WOOL;
//			case "201", "212", "302", "313", "403", "323", "413", "213", "414", "404", "324", "303" -> Blocks.MAGENTA_WOOL;
//			case "210", "320", "310", "321", "432", "421", "410", "420", "431" -> Blocks.ORANGE_WOOL;
//			case "220", "330", "430", "440", "221", "443", "442", "441", "341", "332", "331", "340" -> Blocks.YELLOW_WOOL;
//			case "222", "333" -> Blocks.LIGHT_GRAY_WOOL;
//			case "311", "433", "422", "411", "322", "312", "412", "434", "424", "423", "402" -> Blocks.PINK_WOOL;
//			default -> Blocks.WHITE_WOOL;
//		};
	}
	
	public static Color getColorAt(BufferedImage img, int x, int y) {
		int rgb = img.getRGB(x, y);
        int red = (rgb & 0x00ff0000) >> 16;
        int green = (rgb & 0x0000ff00) >> 8;
        int blue = rgb & 0x000000ff;
		return new Color(red,green,blue);
	}

	public static int hsvToRgb(int hue, float saturation, float value) {
		// Source: en.wikipedia.org/wiki/HSL_and_HSV#Converting_to_RGB#From_HSV
		hue %= 360;
		float s = (float) saturation / 100;
		float v = (float) value / 100;
		float c = v * s;
		float h = (float) hue / 60;
		float x = c * (1 - Math.abs(h % 2 - 1));
		float r, g, b;
		switch (hue / 60) {
			case 0:
				r = c;
				g = x;
				b = 0;
				break;
			case 1:
				r = x;
				g = c;
				b = 0;
				break;
			case 2:
				r = 0;
				g = c;
				b = x;
				break;
			case 3:
				r = 0;
				g = x;
				b = c;
				break;
			case 4:
				r = x;
				g = 0;
				b = c;
				break;
			case 5:
				r = c;
				g = 0;
				b = x;
				break;
			default:
				return 0;
		}
		float m = v - c;
		return ((int) ((r + m) * 255) << 16) | ((int) ((g + m) * 255) << 8) | ((int) ((b + m) * 255));
	}

	public static Color getColorBetween(Color one, Color two, int per1, int per2) {
		double p1 = per1 / 100.0;
		double p2 = per2 / 100.0;
		return new Color((int)Math.floor(one.getRed()*p1+two.getRed()*p2),(int)Math.floor(one.getGreen()*p1+two.getGreen()*p2),(int)Math.floor(one.getBlue()*p1+two.getBlue()*p2));
	}

	public static Color generateRandomColor() {
		Random rand = new Random();
		int hue = rand.nextInt(360);
		int color = hsvToRgb(hue,100F,100F);
		return new Color(color);
	}

	public static Color textToColor(String input) {
		int color;
		if(input.equalsIgnoreCase("red")) {
			color = 0x00FF0000;
		} else if(input.equalsIgnoreCase("orange")) {
			color = 0x00FF8000;
		} else if(input.equalsIgnoreCase("yellow")) {
			color = 0x00FFFF00;
		} else if(input.equalsIgnoreCase("lime")) {
			color = 0x0080FF00;
		} else if(input.equalsIgnoreCase("green")) {
			color = 0x0000FF00;
		} else if(input.equalsIgnoreCase("cyan")) {
			color = 0x0000FFFF;
		} else if(input.equalsIgnoreCase("light blue") || input.equalsIgnoreCase("lightblue")) {
			color = 0x000080FF;
		} else if(input.equalsIgnoreCase("blue")) {
			color = 0x000000FF;
		} else if(input.equalsIgnoreCase("purple")) {
			color = 0x008000FF;
		} else if(input.equalsIgnoreCase("magenta")) {
			color = 0x00FF00FF;
		} else if(input.equalsIgnoreCase("pink")) {
			color = 0x00FF0080;
		} else if(input.equalsIgnoreCase("white")) {
			color = 0x00FFFFFF;
		} else if(input.equalsIgnoreCase("gray") || input.equalsIgnoreCase("grey")) {
			color = 0x00808080;
		} else if(input.equalsIgnoreCase("light gray") || input.equalsIgnoreCase("lightgray") || input.equalsIgnoreCase("light grey") || input.equalsIgnoreCase("lightgrey")) {
			color = 0x00C0C0C0;
		} else if(input.equalsIgnoreCase("brown")) {
			color = 0x00663300;
		} else if(input.equalsIgnoreCase("black")) {
			color = 0x00000000;
		} else {
			try {
				color = (int)Long.parseLong(input, 16);
			} catch(Exception e) {
				color = 0x00FFFFFF;
			}
		}
		return new Color(color);
	}

	public static String indexRainbowToString(int index) {
		return switch (index) {
			case 0 -> "red";
			case 1 -> "orange";
			case 2 -> "yellow";
			case 3 -> "lime";
			case 4 -> "green";
			case 5 -> "cyan";
			case 6 -> "light blue";
			case 7 -> "blue";
			case 8 -> "purple";
			case 9 -> "magenta";
			case 10 -> "pink";
			default -> "white";
		};
	}

	public static class VanillaColor {
		private float hue;
		private float saturation;
		private float value;
		private final Color color;
		private final Block block;
		public VanillaColor(float hue, float saturation, float value, Block block) {
			this.hue = hue;
			this.saturation = saturation;
			this.value = value;
			this.color = Color.getHSBColor(hue,saturation,value);
			this.block = block;
		}
		public VanillaColor(Color color, Block block) {
			this.color = color;
			this.block = block;
			this.setHsvFromColor(color);
		}
		public VanillaColor(Color color) {
			this(color,null);
		}
		public float getHue() {
			return this.hue;
		}
		public float getSaturation() {
			return this.saturation;
		}
		public float getValue() {
			return this.value;
		}
		public Color getColor() {
			return this.color;
		}
		public Block getBlock() {
			return this.block != null ? this.block : Blocks.WHITE_WOOL;
		}
		public float diff(VanillaColor other) {
			float hue_diff = Math.abs(this.hue - other.getHue()) / 360 * 0.475F;
			float saturation_diff = Math.abs(this.saturation - other.getSaturation()) / 100 * 0.2875F;
			float value_diff = Math.abs(this.value - other.getValue()) / 100 * 0.2375F;
			return hue_diff + saturation_diff + value_diff;
		}
		private void setHsvFromColor(Color color) {
			double r = color.getRed();
			double g = color.getGreen();
			double b = color.getBlue();

			// R, G, B values are divided by 255
			// to change the range from 0..255 to 0..1
			r = r / 255.0;
			g = g / 255.0;
			b = b / 255.0;

			// h, s, v = hue, saturation, value
			double cmax = Math.max(r, Math.max(g, b)); // maximum of r, g, b
			double cmin = Math.min(r, Math.min(g, b)); // minimum of r, g, b
			double diff = cmax - cmin; // diff of cmax and cmin.
			double h = -1, s = -1;

			// if cmax and cmax are equal then h = 0
			if (cmax == cmin)
				h = 0;

				// if cmax equal r then compute h
			else if (cmax == r)
				h = (60 * ((g - b) / diff) + 360) % 360;

				// if cmax equal g then compute h
			else if (cmax == g)
				h = (60 * ((b - r) / diff) + 120) % 360;

				// if cmax equal b then compute h
			else if (cmax == b)
				h = (60 * ((r - g) / diff) + 240) % 360;

			// if cmax equal zero
			if (cmax == 0)
				s = 0;
			else
				s = (diff / cmax) * 100;

			// compute v
			double v = cmax * 100;

			this.hue = (float)h;
			this.saturation = (float)s;
			this.value = (float)v;
		}
	}
}