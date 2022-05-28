package com.slymask3.instantblocks.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorHelper {
	
	public static String colorEveryWord(String msg, String color) {
	    StringBuilder builder = new StringBuilder(msg.length());
		builder.append(color);
	    for(int i = 0; i < msg.length(); i++) {
	        char c = msg.charAt(i);
	        switch(c) {
	            case ' ': builder.append(" " + color); break;
	            default: builder.append(c); break;
	        }
	    }
	    return builder.toString();
	}
	
	public static int getWoolColor(Color color) {
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
			return 15; //black
		} else if(sb.toString().equals("001")) {
			return 11; //blue
		} else if(sb.toString().equals("002")) {
			return 11; //blue
		} else if(sb.toString().equals("003")) {
			return 11; //blue
		} else if(sb.toString().equals("004")) {
			return 11; //blue
		} else if(sb.toString().equals("010")) {
			return 13; //green
		} else if(sb.toString().equals("011")) {
			return 9; //cyan COULD ALSO BE BLUE OR GREEN
		} else if(sb.toString().equals("012")) {
			return 11; //blue COULD ALSO BE CYAN
		} else if(sb.toString().equals("013")) {
			return 11; //blue
		} else if(sb.toString().equals("014")) {
			return 11; //blue MAYBE CYAN? OR LIGHT?
		} else if(sb.toString().equals("020")) {
			return 13; //green
		} else if(sb.toString().equals("021")) {
			return 13; //green COUDL ALSO BE CYAN
		} else if(sb.toString().equals("022")) {
			return 9; //cyan COULD ALSO BE BLUE OR GREEN
		} else if(sb.toString().equals("023")) {
			return 3; //light blue
		} else if(sb.toString().equals("024")) {
			return 3; //light blue
		} else if(sb.toString().equals("030")) {
			return 5; //lime
		} else if(sb.toString().equals("031")) {
			return 5; //lime
		} else if(sb.toString().equals("032")) {
			return 9; //cyan ??
		} else if(sb.toString().equals("033")) {
			return 3; //light blue
		} else if(sb.toString().equals("034")) {
			return 3; //light blue
		} else if(sb.toString().equals("040")) {
			return 5; //lime
		} else if(sb.toString().equals("041")) {
			return 5; //lime
		} else if(sb.toString().equals("042")) {
			return 5; //lime
		} else if(sb.toString().equals("043")) {
			return 3; //light blue
		} else if(sb.toString().equals("044")) {
			return 3; //light blue
		}
		
		//--------------------- 1 series
		
		else if(sb.toString().equals("100")) {
			return 14; //red DARK RED ALMOST BLACK
		} else if(sb.toString().equals("101")) {
			return 10; //purple
		} else if(sb.toString().equals("102")) {
			return 10; //purple
		} else if(sb.toString().equals("103")) {
			return 10; //purple COULD BE BLUE
		} else if(sb.toString().equals("104")) {
			return 11; //blue
		} else if(sb.toString().equals("110")) {
			return 12; //brown COULD BE YELLOW
		} else if(sb.toString().equals("111")) {
			return 7; //dark gray
		} else if(sb.toString().equals("112")) {
			return 11; //blue
		} else if(sb.toString().equals("113")) {
			return 11; //blue
		} else if(sb.toString().equals("114")) {
			return 11; //blue COULD BE LIGHT BLUE
		} else if(sb.toString().equals("120")) {
			return 13; //green
		} else if(sb.toString().equals("121")) {
			return 13; //green COULD BE GRAY
		} else if(sb.toString().equals("122")) {
			return 9; //cyan COULD ALSO BE BLUE OR GREEN
		} else if(sb.toString().equals("123")) {
			return 3; //light blue
		} else if(sb.toString().equals("124")) {
			return 3; //light blue
		} else if(sb.toString().equals("130")) {
			return 5; //lime
		} else if(sb.toString().equals("131")) {
			return 5; //lime
		} else if(sb.toString().equals("132")) {
			return 5; //lime
		} else if(sb.toString().equals("133")) {
			return 3; //light blue
		} else if(sb.toString().equals("134")) {
			return 3; //light blue
		} else if(sb.toString().equals("140")) {
			return 5; //lime
		} else if(sb.toString().equals("141")) {
			return 5; //lime
		} else if(sb.toString().equals("142")) {
			return 5; //lime
		} else if(sb.toString().equals("143")) {
			return 9; //cyan
		} else if(sb.toString().equals("144")) {
			return 3; //light blue
		}
		
		//------------------- 2 series
		
		else if(sb.toString().equals("200")) {
			return 14; //red
		} else if(sb.toString().equals("201")) {
			return 2; //magenta
		} else if(sb.toString().equals("202")) {
			return 10; //purple
		} else if(sb.toString().equals("203")) {
			return 10; //purple
		} else if(sb.toString().equals("204")) {
			return 10; //purple
		} else if(sb.toString().equals("210")) {
			return 1; //orange COULD BE BROWN
		} else if(sb.toString().equals("211")) {
			return 14; //red
		} else if(sb.toString().equals("212")) {
			return 2; //magenta
		} else if(sb.toString().equals("213")) {
			return 2; //magenta
		} else if(sb.toString().equals("214")) {
			return 10; //purple
		} else if(sb.toString().equals("220")) {
			return 4; //yellow
		} else if(sb.toString().equals("221")) {
			return 4; //yellow
		} else if(sb.toString().equals("222")) {
			return 8; //light gray
		} else if(sb.toString().equals("223")) {
			return 3; //light blue ??
		} else if(sb.toString().equals("224")) {
			return 3; //light blue
		} else if(sb.toString().equals("230")) {
			return 5; //lime
		} else if(sb.toString().equals("231")) {
			return 5; //lime
		} else if(sb.toString().equals("232")) {
			return 5; //lime
		} else if(sb.toString().equals("233")) {
			return 3; //light blue
		} else if(sb.toString().equals("234")) {
			return 3; //light blue
		} else if(sb.toString().equals("240")) {
			return 5; //lime
		} else if(sb.toString().equals("241")) {
			return 5; //lime
		} else if(sb.toString().equals("242")) {
			return 5; //lime
		} else if(sb.toString().equals("243")) {
			return 5; //lime
		} else if(sb.toString().equals("244")) {
			return 3; //light blue
		}
		
		//----------------- 3 series
		
		else if(sb.toString().equals("300")) {
			return 14; //red
		} else if(sb.toString().equals("301")) {
			return 14; //red COULD BE MAGENTA OR PINK
		} else if(sb.toString().equals("302")) {
			return 2; //magenta
		} else if(sb.toString().equals("303")) {
			return 2; //magenta
		} else if(sb.toString().equals("304")) {
			return 10; //purple
		} else if(sb.toString().equals("310")) {
			return 1; //orange
		} else if(sb.toString().equals("311")) {
			return 6; //pink ??
		} else if(sb.toString().equals("312")) {
			return 6; //pink ??
		} else if(sb.toString().equals("313")) {
			return 2; //magenta
		} else if(sb.toString().equals("314")) {
			return 10; //purple
		} else if(sb.toString().equals("320")) {
			return 1; //orange
		} else if(sb.toString().equals("321")) {
			return 1; //orange
		} else if(sb.toString().equals("322")) {
			return 6; //pink
		} else if(sb.toString().equals("323")) {
			return 2; //magenta
		} else if(sb.toString().equals("324")) {
			return 2; //magenta
		} else if(sb.toString().equals("330")) {
			return 4; //yellow
		} else if(sb.toString().equals("331")) {
			return 4; //yellow
		} else if(sb.toString().equals("332")) {
			return 4; //yellow
		} else if(sb.toString().equals("333")) {
			return 8; //light gray
		} else if(sb.toString().equals("334")) {
			return 3; //light blue
		} else if(sb.toString().equals("340")) {
			return 4; //yellow
		} else if(sb.toString().equals("341")) {
			return 4; //yellow
		} else if(sb.toString().equals("342")) {
			return 5; //lime
		} else if(sb.toString().equals("343")) {
			return 5; //lime
		} else if(sb.toString().equals("344")) {
			return 3; //light blue
		}
		
		//--------------- 4 series
		
		else if(sb.toString().equals("400")) {
			return 14; //red
		} else if(sb.toString().equals("401")) {
			return 14; //red
		} else if(sb.toString().equals("402")) {
			return 6; //pink COULD BE MAGENTA
		} else if(sb.toString().equals("403")) {
			return 2; //magenta
		} else if(sb.toString().equals("404")) {
			return 2; //magenta
		} else if(sb.toString().equals("410")) {
			return 1; //orange
		} else if(sb.toString().equals("411")) {
			return 6; //pink
		} else if(sb.toString().equals("412")) {
			return 6; //pink
		} else if(sb.toString().equals("413")) {
			return 2; //magenta
		} else if(sb.toString().equals("414")) {
			return 2; //magenta
		} else if(sb.toString().equals("420")) {
			return 1; //orange
		} else if(sb.toString().equals("421")) {
			return 1; //orange
		} else if(sb.toString().equals("422")) {
			return 6; //pink
		} else if(sb.toString().equals("423")) {
			return 6; //pink
		} else if(sb.toString().equals("424")) {
			return 6; //pink
		} else if(sb.toString().equals("430")) {
			return 4; //yellow
		} else if(sb.toString().equals("431")) {
			return 1; //orange
		} else if(sb.toString().equals("432")) {
			return 1; //orange
		} else if(sb.toString().equals("433")) {
			return 6; //pink
		} else if(sb.toString().equals("434")) {
			return 6; //pink
		} else if(sb.toString().equals("440")) {
			return 4; //yellow
		} else if(sb.toString().equals("441")) {
			return 4; //yellow
		} else if(sb.toString().equals("442")) {
			return 4; //yellow
		} else if(sb.toString().equals("443")) {
			return 4; //yellow
		} else if(sb.toString().equals("444")) {
			return 0; //white
		} else {
			return 0;
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
}