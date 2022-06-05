package com.slymask3.instantblocks.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Colors {
	public static final String _0 = "\u00a70";
	public static final String _1 = "\u00a71";
	public static final String _2 = "\u00a72";
	public static final String _3 = "\u00a73";
	public static final String _4 = "\u00a74";
	public static final String _5 = "\u00a75";
	public static final String _6 = "\u00a76";
	public static final String _7 = "\u00a77";
	public static final String _8 = "\u00a78";
	public static final String _9 = "\u00a79";
	public static final String a = "\u00a7a";
	public static final String b = "\u00a7b";
	public static final String c = "\u00a7c";
	public static final String d = "\u00a7d";
	public static final String e = "\u00a7e";
	public static final String f = "\u00a7f";

	public static final String OBFUSCATED = "\u00a7k";
	public static final String BOLD = "\u00a7l";
	public static final String STRIKETHROUGH = "\u00a7m";
	public static final String UNDERLINE = "\u00a7n";
	public static final String RESET = "\u00a7r";
	
	public static String colorEveryWord(String msg, String color) {
	    StringBuilder builder = new StringBuilder(msg.length());
		builder.append(color);
	    for(int i = 0; i < msg.length(); i++) {
	        char c = msg.charAt(i);
			if(c == ' ') {
				builder.append(" " + color);
			} else {
				builder.append(c);
			}
	    }
	    return builder.toString();
	}
	
	public static Block getWoolColor(Color color) {
		double r = color.getRed();
		double g = color.getGreen();
		double b = color.getBlue();
		
		int r4 = (int)Math.round(r/64);
		int g4 = (int)Math.round(g/64);
		int b4 = (int)Math.round(b/64);
		
		StringBuilder sb = new StringBuilder();

		sb.append(r4);
		sb.append(g4);
		sb.append(b4);
		
		/** @myValues
		 * red = 255 0 0
		 * orange = 255 127 0
		 * yellow = 255 255 0
		 * green(lime) = 0 255 0
		 * cyan = 0 255 255
		 * blue = 0 0 255
		 * purple = 255 0 255
		 */
		
		/** @realValues
		 * white = 221 221 221
		 * orange = 219 125 62
		 * magenta = 179 80 188
		 * light blue = 107 138 201
		 * yellow = 177 166 39
		 * lime = 65 174 56
		 * pink = 208 132 153
		 * gray = 64 64 64
		 * light gray = 154 161 161
		 * cyan = 46 110 137
		 * purple = 126 61 181
		 * blue =  46 56 141
		 * brown = 79 50 31
		 * green = 53 70 27
		 * red = 150 52 48
		 * black = 25 22 22
		 */
		
		if(sb.toString().equals("000")) {
			return Blocks.BLACK_WOOL; //black
		} else if(sb.toString().equals("001")) {
			return Blocks.BLUE_WOOL; //blue
		} else if(sb.toString().equals("002")) {
			return Blocks.BLACK_WOOL; //blue
		} else if(sb.toString().equals("003")) {
			return Blocks.BLACK_WOOL; //blue
		} else if(sb.toString().equals("004")) {
			return Blocks.BLUE_WOOL; //blue
		} else if(sb.toString().equals("010")) {
			return Blocks.GREEN_WOOL; //green
		} else if(sb.toString().equals("011")) {
			return Blocks.CYAN_WOOL; //cyan COULD ALSO BE BLUE OR GREEN
		} else if(sb.toString().equals("012")) {
			return Blocks.BLUE_WOOL; //blue COULD ALSO BE CYAN
		} else if(sb.toString().equals("013")) {
			return Blocks.BLUE_WOOL; //blue
		} else if(sb.toString().equals("014")) {
			return Blocks.BLUE_WOOL; //blue MAYBE CYAN? OR LIGHT?
		} else if(sb.toString().equals("020")) {
			return Blocks.GREEN_WOOL; //green
		} else if(sb.toString().equals("021")) {
			return Blocks.GREEN_WOOL; //green COUDL ALSO BE CYAN
		} else if(sb.toString().equals("022")) {
			return Blocks.CYAN_WOOL; //cyan COULD ALSO BE BLUE OR GREEN
		} else if(sb.toString().equals("023")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("024")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("030")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("031")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("032")) {
			return Blocks.CYAN_WOOL; //cyan ??
		} else if(sb.toString().equals("033")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("034")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("040")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("041")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("042")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("043")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("044")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		}
		
		//--------------------- 1 series
		
		else if(sb.toString().equals("100")) {
			return Blocks.RED_WOOL; //red DARK RED ALMOST BLACK
		} else if(sb.toString().equals("101")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("102")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("103")) {
			return Blocks.PURPLE_WOOL; //purple COULD BE BLUE
		} else if(sb.toString().equals("104")) {
			return Blocks.BLUE_WOOL; //blue
		} else if(sb.toString().equals("110")) {
			return Blocks.BROWN_WOOL; //brown COULD BE YELLOW
		} else if(sb.toString().equals("111")) {
			return Blocks.GRAY_WOOL; //dark gray
		} else if(sb.toString().equals("112")) {
			return Blocks.BLUE_WOOL; //blue
		} else if(sb.toString().equals("113")) {
			return Blocks.BLUE_WOOL; //blue
		} else if(sb.toString().equals("114")) {
			return Blocks.BLUE_WOOL; //blue COULD BE LIGHT BLUE
		} else if(sb.toString().equals("120")) {
			return Blocks.GREEN_WOOL; //green
		} else if(sb.toString().equals("121")) {
			return Blocks.GREEN_WOOL; //green COULD BE GRAY
		} else if(sb.toString().equals("122")) {
			return Blocks.CYAN_WOOL; //cyan COULD ALSO BE BLUE OR GREEN
		} else if(sb.toString().equals("123")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("124")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("130")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("131")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("132")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("133")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("134")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("140")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("141")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("142")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("143")) {
			return Blocks.CYAN_WOOL; //cyan
		} else if(sb.toString().equals("144")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		}
		
		//------------------- 2 series
		
		else if(sb.toString().equals("200")) {
			return Blocks.RED_WOOL; //red
		} else if(sb.toString().equals("201")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("202")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("203")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("204")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("210")) {
			return Blocks.ORANGE_WOOL; //orange COULD BE BROWN
		} else if(sb.toString().equals("211")) {
			return Blocks.RED_WOOL; //red
		} else if(sb.toString().equals("212")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("213")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("214")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("220")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("221")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("222")) {
			return Blocks.LIGHT_GRAY_WOOL; //light gray
		} else if(sb.toString().equals("223")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue ??
		} else if(sb.toString().equals("224")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("230")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("231")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("232")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("233")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("234")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("240")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("241")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("242")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("243")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("244")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		}
		
		//----------------- 3 series
		
		else if(sb.toString().equals("300")) {
			return Blocks.RED_WOOL; //red
		} else if(sb.toString().equals("301")) {
			return Blocks.RED_WOOL; //red COULD BE MAGENTA OR PINK
		} else if(sb.toString().equals("302")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("303")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("304")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("310")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("311")) {
			return Blocks.PINK_WOOL; //pink ??
		} else if(sb.toString().equals("312")) {
			return Blocks.PINK_WOOL; //pink ??
		} else if(sb.toString().equals("313")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("314")) {
			return Blocks.PURPLE_WOOL; //purple
		} else if(sb.toString().equals("320")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("321")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("322")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("323")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("324")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("330")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("331")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("332")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("333")) {
			return Blocks.LIGHT_GRAY_WOOL; //light gray
		} else if(sb.toString().equals("334")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		} else if(sb.toString().equals("340")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("341")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("342")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("343")) {
			return Blocks.LIME_WOOL; //lime
		} else if(sb.toString().equals("344")) {
			return Blocks.LIGHT_BLUE_WOOL; //light blue
		}
		
		//--------------- 4 series
		
		else if(sb.toString().equals("400")) {
			return Blocks.RED_WOOL; //red
		} else if(sb.toString().equals("401")) {
			return Blocks.RED_WOOL; //red
		} else if(sb.toString().equals("402")) {
			return Blocks.PINK_WOOL; //pink COULD BE MAGENTA
		} else if(sb.toString().equals("403")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("404")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("410")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("411")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("412")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("413")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("414")) {
			return Blocks.MAGENTA_WOOL; //magenta
		} else if(sb.toString().equals("420")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("421")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("422")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("423")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("424")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("430")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("431")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("432")) {
			return Blocks.ORANGE_WOOL; //orange
		} else if(sb.toString().equals("433")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("434")) {
			return Blocks.PINK_WOOL; //pink
		} else if(sb.toString().equals("440")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("441")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("442")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("443")) {
			return Blocks.YELLOW_WOOL; //yellow
		} else if(sb.toString().equals("444")) {
			return Blocks.WHITE_WOOL; //white
		} else {
			return Blocks.WHITE_WOOL;
		}
	}
	
	public static Color getColorAt(BufferedImage img, int x, int y) {
		int rgb = img.getRGB(x, y);
        int red = (rgb & 0x00ff0000) >> 16;
        int green = (rgb & 0x0000ff00) >> 8;
        int blue = rgb & 0x000000ff;
		return new Color(red,green,blue);
	}

	public static Color[] generateRainbow(int array_size) {
		double jump = 360.0 / array_size;
		Color[] colors = new Color[array_size];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Color(hsvToRgb((int)Math.ceil(jump*i), 100, 100));
		}
		return colors;
	}

	public static int hsvToRgb(int hue, int saturation, int value) {
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
		int color = hsvToRgb(hue,100,100);
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
		switch(index) {
			case 0: return "red";
			case 1: return "orange";
			case 2: return "yellow";
			case 3: return "lime";
			case 4: return "green";
			case 5: return "cyan";
			case 6: return "light blue";
			case 7: return "blue";
			case 8: return "purple";
			case 9: return "magenta";
			case 10: return "pink";
			default: return "white";
		}
	}
}