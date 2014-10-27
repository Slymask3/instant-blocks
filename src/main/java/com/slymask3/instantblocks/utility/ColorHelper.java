package com.slymask3.instantblocks.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorHelper {
	
	public static String colorEveryWord(String msg, String color) {
		// Output will be at least as long as input
	    StringBuilder builder = new StringBuilder(msg.length());

	    for (int i = 0; i < msg.length(); i++)
	    {
	        char c = msg.charAt(i);
	        switch (c)
	        {
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
		
		/*if (r<=255 && g<=255 && b<=255 && r>=210 && g>=210 && b>=210) {
			return 0; //white
		} else if (r<=209 && g<=209 && b<=209 && r>=100 && g>=100 && b>=100) {
			return 8; //light gray
		} else if (r<=99 && g<=99 && b<=99 && r>=50 && g>=50 && b>=50) {
			return 7; //gray
		} else if (r<=49 && g<=49 && b<=49 && r>=0 && g>=0 && b>=0) {
			return 15; //black
		} else if (r<=255 && g<=70 && b<=70 && r>=100 && g>=50 && b>=50) {
			return 14; //red questionable
		}*/
		
		
		if (sb.toString().equals("000")) {
			return 15; //black
		} else if (sb.toString().equals("001")) {
			return 11; //blue
		} else if (sb.toString().equals("002")) {
			return 11; //blue
		} else if (sb.toString().equals("003")) {
			return 11; //blue
		} else if (sb.toString().equals("004")) {
			return 11; //blue
		} else if (sb.toString().equals("010")) {
			return 13; //green
		} else if (sb.toString().equals("011")) {
			return 9; //cyan COULD ALSO BE BLUE OR GREEN
		} else if (sb.toString().equals("012")) {
			return 11; //blue COULD ALSO BE CYAN
		} else if (sb.toString().equals("013")) {
			return 11; //blue
		} else if (sb.toString().equals("014")) {
			return 11; //blue MAYBE CYAN? OR LIGHT?
		} else if (sb.toString().equals("020")) {
			return 13; //green
		} else if (sb.toString().equals("021")) {
			return 13; //green COUDL ALSO BE CYAN
		} else if (sb.toString().equals("022")) {
			return 9; //cyan COULD ALSO BE BLUE OR GREEN
		} else if (sb.toString().equals("023")) {
			return 3; //light blue
		} else if (sb.toString().equals("024")) {
			return 3; //light blue
		} else if (sb.toString().equals("030")) {
			return 5; //lime
		} else if (sb.toString().equals("031")) {
			return 5; //lime
		} else if (sb.toString().equals("032")) {
			return 9; //cyan ??
		} else if (sb.toString().equals("033")) {
			return 3; //light blue
		} else if (sb.toString().equals("034")) {
			return 3; //light blue
		} else if (sb.toString().equals("040")) {
			return 5; //lime
		} else if (sb.toString().equals("041")) {
			return 5; //lime
		} else if (sb.toString().equals("042")) {
			return 5; //lime
		} else if (sb.toString().equals("043")) {
			return 3; //light blue
		} else if (sb.toString().equals("044")) {
			return 3; //light blue
		}
		
		//--------------------- 1 series
		
		else if (sb.toString().equals("100")) {
			return 14; //red DARK RED ALMOST BLACK
		} else if (sb.toString().equals("101")) {
			return 10; //purple
		} else if (sb.toString().equals("102")) {
			return 10; //purple
		} else if (sb.toString().equals("103")) {
			return 10; //purple COULD BE BLUE
		} else if (sb.toString().equals("104")) {
			return 11; //blue
		} else if (sb.toString().equals("110")) {
			return 12; //brown COULD BE YELLOW
		} else if (sb.toString().equals("111")) {
			return 7; //dark gray
		} else if (sb.toString().equals("112")) {
			return 11; //blue
		} else if (sb.toString().equals("113")) {
			return 11; //blue
		} else if (sb.toString().equals("114")) {
			return 11; //blue COULD BE LIGHT BLUE
		} else if (sb.toString().equals("120")) {
			return 13; //green
		} else if (sb.toString().equals("121")) {
			return 13; //green COULD BE GRAY
		} else if (sb.toString().equals("122")) {
			return 9; //cyan COULD ALSO BE BLUE OR GREEN
		} else if (sb.toString().equals("123")) {
			return 3; //light blue
		} else if (sb.toString().equals("124")) {
			return 3; //light blue
		} else if (sb.toString().equals("130")) {
			return 5; //lime
		} else if (sb.toString().equals("131")) {
			return 5; //lime
		} else if (sb.toString().equals("132")) {
			return 5; //lime
		} else if (sb.toString().equals("133")) {
			return 3; //light blue
		} else if (sb.toString().equals("134")) {
			return 3; //light blue
		} else if (sb.toString().equals("140")) {
			return 5; //lime
		} else if (sb.toString().equals("141")) {
			return 5; //lime
		} else if (sb.toString().equals("142")) {
			return 5; //lime
		} else if (sb.toString().equals("143")) {
			return 9; //cyan
		} else if (sb.toString().equals("144")) {
			return 3; //light blue
		}
		
		//------------------- 2 series
		
		else if (sb.toString().equals("200")) {
			return 14; //red
		} else if (sb.toString().equals("201")) {
			return 2; //magenta
		} else if (sb.toString().equals("202")) {
			return 10; //purple
		} else if (sb.toString().equals("203")) {
			return 10; //purple
		} else if (sb.toString().equals("204")) {
			return 10; //purple
		} else if (sb.toString().equals("210")) {
			return 1; //orange COULD BE BROWN
		} else if (sb.toString().equals("211")) {
			return 14; //red
		} else if (sb.toString().equals("212")) {
			return 2; //magenta
		} else if (sb.toString().equals("213")) {
			return 2; //magenta
		} else if (sb.toString().equals("214")) {
			return 10; //purple
		} else if (sb.toString().equals("220")) {
			return 4; //yellow
		} else if (sb.toString().equals("221")) {
			return 4; //yellow
		} else if (sb.toString().equals("222")) {
			return 8; //light gray
		} else if (sb.toString().equals("223")) {
			return 3; //light blue ??
		} else if (sb.toString().equals("224")) {
			return 3; //light blue
		} else if (sb.toString().equals("230")) {
			return 5; //lime
		} else if (sb.toString().equals("231")) {
			return 5; //lime
		} else if (sb.toString().equals("232")) {
			return 5; //lime
		} else if (sb.toString().equals("233")) {
			return 3; //light blue
		} else if (sb.toString().equals("234")) {
			return 3; //light blue
		} else if (sb.toString().equals("240")) {
			return 5; //lime
		} else if (sb.toString().equals("241")) {
			return 5; //lime
		} else if (sb.toString().equals("242")) {
			return 5; //lime
		} else if (sb.toString().equals("243")) {
			return 5; //lime
		} else if (sb.toString().equals("244")) {
			return 3; //light blue
		}
		
		//----------------- 3 series
		
		else if (sb.toString().equals("300")) {
			return 14; //red
		} else if (sb.toString().equals("301")) {
			return 14; //red COULD BE MAGENTA OR PINK
		} else if (sb.toString().equals("302")) {
			return 2; //magenta
		} else if (sb.toString().equals("303")) {
			return 2; //magenta
		} else if (sb.toString().equals("304")) {
			return 10; //purple
		} else if (sb.toString().equals("310")) {
			return 1; //orange
		} else if (sb.toString().equals("311")) {
			return 6; //pink ??
		} else if (sb.toString().equals("312")) {
			return 6; //pink ??
		} else if (sb.toString().equals("313")) {
			return 2; //magenta
		} else if (sb.toString().equals("314")) {
			return 10; //purple
		} else if (sb.toString().equals("320")) {
			return 1; //orange
		} else if (sb.toString().equals("321")) {
			return 1; //orange
		} else if (sb.toString().equals("322")) {
			return 6; //pink
		} else if (sb.toString().equals("323")) {
			return 2; //magenta
		} else if (sb.toString().equals("324")) {
			return 2; //magenta
		} else if (sb.toString().equals("330")) {
			return 4; //yellow
		} else if (sb.toString().equals("331")) {
			return 4; //yellow
		} else if (sb.toString().equals("332")) {
			return 4; //yellow
		} else if (sb.toString().equals("333")) {
			return 8; //light gray
		} else if (sb.toString().equals("334")) {
			return 3; //light blue
		} else if (sb.toString().equals("340")) {
			return 4; //yellow
		} else if (sb.toString().equals("341")) {
			return 4; //yellow
		} else if (sb.toString().equals("342")) {
			return 5; //lime
		} else if (sb.toString().equals("343")) {
			return 5; //lime
		} else if (sb.toString().equals("344")) {
			return 3; //light blue
		}
		
		//--------------- 4 series
		
		else if (sb.toString().equals("400")) {
			return 14; //red
		} else if (sb.toString().equals("401")) {
			return 14; //red
		} else if (sb.toString().equals("402")) {
			return 6; //pink COULD BE MAGENTA
		} else if (sb.toString().equals("403")) {
			return 2; //magenta
		} else if (sb.toString().equals("404")) {
			return 2; //magenta
		} else if (sb.toString().equals("410")) {
			return 1; //orange
		} else if (sb.toString().equals("411")) {
			return 6; //pink
		} else if (sb.toString().equals("412")) {
			return 6; //pink
		} else if (sb.toString().equals("413")) {
			return 2; //magenta
		} else if (sb.toString().equals("414")) {
			return 2; //magenta
		} else if (sb.toString().equals("420")) {
			return 1; //orange
		} else if (sb.toString().equals("421")) {
			return 1; //orange
		} else if (sb.toString().equals("422")) {
			return 6; //pink
		} else if (sb.toString().equals("423")) {
			return 6; //pink
		} else if (sb.toString().equals("424")) {
			return 6; //pink
		} else if (sb.toString().equals("430")) {
			return 4; //yellow
		} else if (sb.toString().equals("431")) {
			return 1; //orange
		} else if (sb.toString().equals("432")) {
			return 1; //orange
		} else if (sb.toString().equals("433")) {
			return 6; //pink
		} else if (sb.toString().equals("434")) {
			return 6; //pink
		} else if (sb.toString().equals("440")) {
			return 4; //yellow
		} else if (sb.toString().equals("441")) {
			return 4; //yellow
		} else if (sb.toString().equals("442")) {
			return 4; //yellow
		} else if (sb.toString().equals("443")) {
			return 4; //yellow
		} else if (sb.toString().equals("444")) {
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
        Color color = new Color(red,green,blue);
		return color;
	}
	
	public static String toColorLower(int meta) {
		if (meta == 0) {
			return "white";
		} else if (meta == 1) {
			return "orange";
		} else if (meta == 2) {
			return "magenta";
		} else if (meta == 3) {
			return "light blue";
		} else if (meta == 4) {
			return "yellow";
		} else if (meta == 5) {
			return "lime";
		} else if (meta == 6) {
			return "pink";
		} else if (meta == 7) {
			return "gray";
		} else if (meta == 8) {
			return "light gray";
		} else if (meta == 9) {
			return "cyan";
		} else if (meta == 10) {
			return "purple";
		} else if (meta == 11) {
			return "blue";
		} else if (meta == 12) {
			return "brown";
		} else if (meta == 13) {
			return "green";
		} else if (meta == 14) {
			return "red";
		} else if (meta == 15) {
			return "black";
		}
		
		return null;
	}
	
	public static String toColor(int meta) {
		if (meta == 0) {
			return "White";
		} else if (meta == 1) {
			return "Orange";
		} else if (meta == 2) {
			return "Magenta";
		} else if (meta == 3) {
			return "Light Blue";
		} else if (meta == 4) {
			return "Yellow";
		} else if (meta == 5) {
			return "Lime";
		} else if (meta == 6) {
			return "Pink";
		} else if (meta == 7) {
			return "Gray";
		} else if (meta == 8) {
			return "Light Gray";
		} else if (meta == 9) {
			return "Cyan";
		} else if (meta == 10) {
			return "Purple";
		} else if (meta == 11) {
			return "Blue";
		} else if (meta == 12) {
			return "Brown";
		} else if (meta == 13) {
			return "Green";
		} else if (meta == 14) {
			return "Red";
		} else if (meta == 15) {
			return "Black";
		}
		
		return null;
	}
	
	public static String toColor2(int meta) {
		if (meta == 0) {
			return "\u00a7fWhite\u00a7f";
		} else if (meta == 1) {
			return "\u00a76Orange\u00a7f";
		} else if (meta == 2) {
			return "\u00a7dMagenta\u00a7f";
		} else if (meta == 3) {
			return "\u00a7bLight Blue\u00a7f";
		} else if (meta == 4) {
			return "\u00a7eYellow\u00a7f";
		} else if (meta == 5) {
			return "\u00a7aLime\u00a7f";
		} else if (meta == 6) {
			return "\u00a7dPink\u00a7f";
		} else if (meta == 7) {
			return "\u00a78Gray\u00a7f";
		} else if (meta == 8) {
			return "\u00a77Light Gray\u00a7f";
		} else if (meta == 9) {
			return "\u00a73Cyan\u00a7f";
		} else if (meta == 10) {
			return "\u00a75Purple\u00a7f";
		} else if (meta == 11) {
			return "\u00a71Blue\u00a7f";
		} else if (meta == 12) {
			return "\u00a78Brown\u00a7f";
		} else if (meta == 13) {
			return "\u00a72Green\u00a7f";
		} else if (meta == 14) {
			return "\u00a7cRed\u00a7f";
		} else if (meta == 15) {
			return "\u00a70Black\u00a7f";
		}
		
		return null;
	}
	
	public static int toMeta(String color) {
		if (color.equalsIgnoreCase("white")) {
			return 0;
		} else if (color.equalsIgnoreCase("orange")) {
			return 1;
		} else if (color.equalsIgnoreCase("magenta")) {
			return 2;
		} else if (color.equalsIgnoreCase("lightblue")) {
			return 3;
		} else if (color.equalsIgnoreCase("yellow")) {
			return 4;
		} else if (color.equalsIgnoreCase("lime")) {
			return 5;
		} else if (color.equalsIgnoreCase("pink")) {
			return 6;
		} else if (color.equalsIgnoreCase("gray")) {
			return 7;
		} else if (color.equalsIgnoreCase("lightgray")) {
			return 8;
		} else if (color.equalsIgnoreCase("cyan")) {
			return 9;
		} else if (color.equalsIgnoreCase("purple")) {
			return 10;
		} else if (color.equalsIgnoreCase("blue")) {
			return 11;
		} else if (color.equalsIgnoreCase("brown")) {
			return 12;
		} else if (color.equalsIgnoreCase("green")) {
			return 13;
		} else if (color.equalsIgnoreCase("red")) {
			return 14;
		} else if (color.equalsIgnoreCase("black")) {
			return 15;
		}
		
		return 0;
	}
	
	//Color color = getColorAt(img, 1, 1);
    
    //setWoolByColor(world, x, y, z, color);
    
    /*world.setBlock(x, y, z, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 0, 0)), 1);
    world.setBlock(x+1, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 1, 0)), 1);
    world.setBlock(x+2, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 2, 0)), 1);
    world.setBlock(x+3, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 3, 0)), 1);
    world.setBlock(x+0, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 0, 1)), 1);
    world.setBlock(x+1, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 1, 1)), 1);
    world.setBlock(x+2, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 2, 1)), 1);
    world.setBlock(x+3, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 3, 1)), 1);
    world.setBlock(x+0, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 0, 2)), 1);
    world.setBlock(x+1, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 1, 2)), 1);
    world.setBlock(x+2, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 2, 2)), 1);
    world.setBlock(x+3, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 3, 2)), 1);
    world.setBlock(x+0, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 0, 3)), 1);
    world.setBlock(x+1, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 1, 3)), 1);
    world.setBlock(x+2, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 2, 3)), 1);
    world.setBlock(x+3, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 3, 3)), 1);*/
	
	
	/*world.setBlock(x+0, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 8)), 1);
    world.setBlock(x+1, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 8)), 1);
    world.setBlock(x+2, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 8)), 1);
    world.setBlock(x+3, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 8)), 1);
	world.setBlock(x+4, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 8)), 1);
    world.setBlock(x+5, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 8)), 1);
    world.setBlock(x+6, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 8)), 1);
    world.setBlock(x+7, y, z+0, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 8)), 1);
    
    world.setBlock(x+0, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 9)), 1);
    world.setBlock(x+1, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 9)), 1);
    world.setBlock(x+2, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 9)), 1);
    world.setBlock(x+3, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 9)), 1);
	world.setBlock(x+4, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 9)), 1);
    world.setBlock(x+5, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 9)), 1);
    world.setBlock(x+6, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 9)), 1);
    world.setBlock(x+7, y, z+1, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 9)), 1);

    world.setBlock(x+0, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 10)), 1);
    world.setBlock(x+1, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 10)), 1);
    world.setBlock(x+2, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 10)), 1);
    world.setBlock(x+3, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 10)), 1);
	world.setBlock(x+4, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 10)), 1);
    world.setBlock(x+5, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 10)), 1);
    world.setBlock(x+6, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 10)), 1);
    world.setBlock(x+7, y, z+2, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 10)), 1);
    
    world.setBlock(x+0, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 11)), 1);
    world.setBlock(x+1, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 11)), 1);
    world.setBlock(x+2, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 11)), 1);
    world.setBlock(x+3, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 11)), 1);
	world.setBlock(x+4, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 11)), 1);
    world.setBlock(x+5, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 11)), 1);
    world.setBlock(x+6, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 11)), 1);
    world.setBlock(x+7, y, z+3, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 11)), 1);
    
    world.setBlock(x+0, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 12)), 1);
    world.setBlock(x+1, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 12)), 1);
    world.setBlock(x+2, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 12)), 1);
    world.setBlock(x+3, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 12)), 1);
	world.setBlock(x+4, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 12)), 1);
    world.setBlock(x+5, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 12)), 1);
    world.setBlock(x+6, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 12)), 1);
    world.setBlock(x+7, y, z+4, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 12)), 1);
    
    world.setBlock(x+0, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 13)), 1);
    world.setBlock(x+1, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 13)), 1);
    world.setBlock(x+2, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 13)), 1);
    world.setBlock(x+3, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 13)), 1);
	world.setBlock(x+4, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 13)), 1);
    world.setBlock(x+5, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 13)), 1);
    world.setBlock(x+6, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 13)), 1);
    world.setBlock(x+7, y, z+5, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 13)), 1);
    
    world.setBlock(x+0, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 14)), 1);
    world.setBlock(x+1, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 14)), 1);
    world.setBlock(x+2, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 14)), 1);
    world.setBlock(x+3, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 14)), 1);
	world.setBlock(x+4, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 14)), 1);
    world.setBlock(x+5, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 14)), 1);
    world.setBlock(x+6, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 14)), 1);
    world.setBlock(x+7, y, z+6, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 14)), 1);
    
    world.setBlock(x+0, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 8, 15)), 1);
    world.setBlock(x+1, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 9, 15)), 1);
    world.setBlock(x+2, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 10, 15)), 1);
    world.setBlock(x+3, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 11, 15)), 1);
	world.setBlock(x+4, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 12, 15)), 1);
    world.setBlock(x+5, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 13, 15)), 1);
    world.setBlock(x+6, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 14, 15)), 1);
    world.setBlock(x+7, y, z+7, Blocks.wool, ColorHelper.getWoolColor(getColorAt(img, 15, 15)), 1);*/
	
	
	
	
	
	
	
	
	//FACE
	
			/*ibf.setColorBlock(world, x-4, y+31, z+2, img, 8, 8);
			ibf.setColorBlock(world, x-3, y+31, z+2, img, 9, 8);
			ibf.setColorBlock(world, x-2, y+31, z+2, img, 10, 8);
			ibf.setColorBlock(world, x-1, y+31, z+2, img, 11, 8);
			ibf.setColorBlock(world, x+0, y+31, z+2, img, 12, 8);
			ibf.setColorBlock(world, x+1, y+31, z+2, img, 13, 8);
			ibf.setColorBlock(world, x+2, y+31, z+2, img, 14, 8);
			ibf.setColorBlock(world, x+3, y+31, z+2, img, 15, 8);

			ibf.setColorBlock(world, x-4, y+30, z+2, img, 8, 9);
			ibf.setColorBlock(world, x-3, y+30, z+2, img, 9, 9);
			ibf.setColorBlock(world, x-2, y+30, z+2, img, 10, 9);
			ibf.setColorBlock(world, x-1, y+30, z+2, img, 11, 9);
			ibf.setColorBlock(world, x+0, y+30, z+2, img, 12, 9);
			ibf.setColorBlock(world, x+1, y+30, z+2, img, 13, 9);
			ibf.setColorBlock(world, x+2, y+30, z+2, img, 14, 9);
			ibf.setColorBlock(world, x+3, y+30, z+2, img, 15, 9);

			ibf.setColorBlock(world, x-4, y+29, z+2, img, 8, 10);
			ibf.setColorBlock(world, x-3, y+29, z+2, img, 9, 10);
			ibf.setColorBlock(world, x-2, y+29, z+2, img, 10, 10);
			ibf.setColorBlock(world, x-1, y+29, z+2, img, 11, 10);
			ibf.setColorBlock(world, x+0, y+29, z+2, img, 12, 10);
			ibf.setColorBlock(world, x+1, y+29, z+2, img, 13, 10);
			ibf.setColorBlock(world, x+2, y+29, z+2, img, 14, 10);
			ibf.setColorBlock(world, x+3, y+29, z+2, img, 15, 10);

			ibf.setColorBlock(world, x-4, y+28, z+2, img, 8, 11);
			ibf.setColorBlock(world, x-3, y+28, z+2, img, 9, 11);
			ibf.setColorBlock(world, x-2, y+28, z+2, img, 10, 11);
			ibf.setColorBlock(world, x-1, y+28, z+2, img, 11, 11);
			ibf.setColorBlock(world, x+0, y+28, z+2, img, 12, 11);
			ibf.setColorBlock(world, x+1, y+28, z+2, img, 13, 11);
			ibf.setColorBlock(world, x+2, y+28, z+2, img, 14, 11);
			ibf.setColorBlock(world, x+3, y+28, z+2, img, 15, 11);

			ibf.setColorBlock(world, x-4, y+27, z+2, img, 8, 12);
			ibf.setColorBlock(world, x-3, y+27, z+2, img, 9, 12);
			ibf.setColorBlock(world, x-2, y+27, z+2, img, 10, 12);
			ibf.setColorBlock(world, x-1, y+27, z+2, img, 11, 12);
			ibf.setColorBlock(world, x+0, y+27, z+2, img, 12, 12);
			ibf.setColorBlock(world, x+1, y+27, z+2, img, 13, 12);
			ibf.setColorBlock(world, x+2, y+27, z+2, img, 14, 12);
			ibf.setColorBlock(world, x+3, y+27, z+2, img, 15, 12);

			ibf.setColorBlock(world, x-4, y+26, z+2, img, 8, 13);
			ibf.setColorBlock(world, x-3, y+26, z+2, img, 9, 13);
			ibf.setColorBlock(world, x-2, y+26, z+2, img, 10, 13);
			ibf.setColorBlock(world, x-1, y+26, z+2, img, 11, 13);
			ibf.setColorBlock(world, x+0, y+26, z+2, img, 12, 13);
			ibf.setColorBlock(world, x+1, y+26, z+2, img, 13, 13);
			ibf.setColorBlock(world, x+2, y+26, z+2, img, 14, 13);
			ibf.setColorBlock(world, x+3, y+26, z+2, img, 15, 13);

			ibf.setColorBlock(world, x-4, y+25, z+2, img, 8, 14);
			ibf.setColorBlock(world, x-3, y+25, z+2, img, 9, 14);
			ibf.setColorBlock(world, x-2, y+25, z+2, img, 10, 14);
			ibf.setColorBlock(world, x-1, y+25, z+2, img, 11, 14);
			ibf.setColorBlock(world, x+0, y+25, z+2, img, 12, 14);
			ibf.setColorBlock(world, x+1, y+25, z+2, img, 13, 14);
			ibf.setColorBlock(world, x+2, y+25, z+2, img, 14, 14);
			ibf.setColorBlock(world, x+3, y+25, z+2, img, 15, 14);

			ibf.setColorBlock(world, x-4, y+24, z+2, img, 8, 15);
			ibf.setColorBlock(world, x-3, y+24, z+2, img, 9, 15);
			ibf.setColorBlock(world, x-2, y+24, z+2, img, 10, 15);
			ibf.setColorBlock(world, x-1, y+24, z+2, img, 11, 15);
			ibf.setColorBlock(world, x+0, y+24, z+2, img, 12, 15);
			ibf.setColorBlock(world, x+1, y+24, z+2, img, 13, 15);
			ibf.setColorBlock(world, x+2, y+24, z+2, img, 14, 15);
			ibf.setColorBlock(world, x+3, y+24, z+2, img, 15, 15);
			
			
			//HAIR
			
			ibf.setColorBlock(world, x-4, y+31, z-5, img, 8, 0);
			ibf.setColorBlock(world, x-3, y+31, z-5, img, 9, 0);
			ibf.setColorBlock(world, x-2, y+31, z-5, img, 10, 0);
			ibf.setColorBlock(world, x-1, y+31, z-5, img, 11, 0);
			ibf.setColorBlock(world, x+0, y+31, z-5, img, 12, 0);
			ibf.setColorBlock(world, x+1, y+31, z-5, img, 13, 0);
			ibf.setColorBlock(world, x+2, y+31, z-5, img, 14, 0);
			ibf.setColorBlock(world, x+3, y+31, z-5, img, 15, 0);

			ibf.setColorBlock(world, x-4, y+31, z-4, img, 8, 1);
			ibf.setColorBlock(world, x-3, y+31, z-4, img, 9, 1);
			ibf.setColorBlock(world, x-2, y+31, z-4, img, 10, 1);
			ibf.setColorBlock(world, x-1, y+31, z-4, img, 11, 1);
			ibf.setColorBlock(world, x+0, y+31, z-4, img, 12, 1);
			ibf.setColorBlock(world, x+1, y+31, z-4, img, 13, 1);
			ibf.setColorBlock(world, x+2, y+31, z-4, img, 14, 1);
			ibf.setColorBlock(world, x+3, y+31, z-4, img, 15, 1);

			ibf.setColorBlock(world, x-4, y+31, z-3, img, 8, 2);
			ibf.setColorBlock(world, x-3, y+31, z-3, img, 9, 2);
			ibf.setColorBlock(world, x-2, y+31, z-3, img, 10, 2);
			ibf.setColorBlock(world, x-1, y+31, z-3, img, 11, 2);
			ibf.setColorBlock(world, x+0, y+31, z-3, img, 12, 2);
			ibf.setColorBlock(world, x+1, y+31, z-3, img, 13, 2);
			ibf.setColorBlock(world, x+2, y+31, z-3, img, 14, 2);
			ibf.setColorBlock(world, x+3, y+31, z-3, img, 15, 2);

			ibf.setColorBlock(world, x-4, y+31, z-2, img, 8, 3);
			ibf.setColorBlock(world, x-3, y+31, z-2, img, 9, 3);
			ibf.setColorBlock(world, x-2, y+31, z-2, img, 10, 3);
			ibf.setColorBlock(world, x-1, y+31, z-2, img, 11, 3);
			ibf.setColorBlock(world, x+0, y+31, z-2, img, 12, 3);
			ibf.setColorBlock(world, x+1, y+31, z-2, img, 13, 3);
			ibf.setColorBlock(world, x+2, y+31, z-2, img, 14, 3);
			ibf.setColorBlock(world, x+3, y+31, z-2, img, 15, 3);

			ibf.setColorBlock(world, x-4, y+31, z-1, img, 8, 4);
			ibf.setColorBlock(world, x-3, y+31, z-1, img, 9, 4);
			ibf.setColorBlock(world, x-2, y+31, z-1, img, 10, 4);
			ibf.setColorBlock(world, x-1, y+31, z-1, img, 11, 4);
			ibf.setColorBlock(world, x+0, y+31, z-1, img, 12, 4);
			ibf.setColorBlock(world, x+1, y+31, z-1, img, 13, 4);
			ibf.setColorBlock(world, x+2, y+31, z-1, img, 14, 4);
			ibf.setColorBlock(world, x+3, y+31, z-1, img, 15, 4);

			ibf.setColorBlock(world, x-4, y+31, z+0, img, 8, 5);
			ibf.setColorBlock(world, x-3, y+31, z+0, img, 9, 5);
			ibf.setColorBlock(world, x-2, y+31, z+0, img, 10, 5);
			ibf.setColorBlock(world, x-1, y+31, z+0, img, 11, 5);
			ibf.setColorBlock(world, x+0, y+31, z+0, img, 12, 5);
			ibf.setColorBlock(world, x+1, y+31, z+0, img, 13, 5);
			ibf.setColorBlock(world, x+2, y+31, z+0, img, 14, 5);
			ibf.setColorBlock(world, x+3, y+31, z+0, img, 15, 5);

			ibf.setColorBlock(world, x-4, y+31, z+1, img, 8, 6);
			ibf.setColorBlock(world, x-3, y+31, z+1, img, 9, 6);
			ibf.setColorBlock(world, x-2, y+31, z+1, img, 10, 6);
			ibf.setColorBlock(world, x-1, y+31, z+1, img, 11, 6);
			ibf.setColorBlock(world, x+0, y+31, z+1, img, 12, 6);
			ibf.setColorBlock(world, x+1, y+31, z+1, img, 13, 6);
			ibf.setColorBlock(world, x+2, y+31, z+1, img, 14, 6);
			ibf.setColorBlock(world, x+3, y+31, z+1, img, 15, 6);
			
			//THROAT?
			
			ibf.setColorBlock(world, x-3, y+24, z-4, img, 17, 1);
			ibf.setColorBlock(world, x-2, y+24, z-4, img, 18, 1);
			ibf.setColorBlock(world, x-1, y+24, z-4, img, 19, 1);
			ibf.setColorBlock(world, x+0, y+24, z-4, img, 20, 1);
			ibf.setColorBlock(world, x+1, y+24, z-4, img, 21, 1);
			ibf.setColorBlock(world, x+2, y+24, z-4, img, 22, 1);

			ibf.setColorBlock(world, x-3, y+24, z-3, img, 17, 2);
			ibf.setColorBlock(world, x+2, y+24, z-3, img, 22, 2);

			ibf.setColorBlock(world, x-3, y+24, z-2, img, 17, 3);
			ibf.setColorBlock(world, x+2, y+24, z-2, img, 22, 3);

			ibf.setColorBlock(world, x-3, y+24, z-1, img, 17, 4);
			ibf.setColorBlock(world, x+2, y+24, z-1, img, 22, 4);

			ibf.setColorBlock(world, x-3, y+24, z+0, img, 17, 5);
			ibf.setColorBlock(world, x+2, y+24, z+0, img, 22, 5);

			ibf.setColorBlock(world, x-3, y+24, z+1, img, 17, 6);
			ibf.setColorBlock(world, x-2, y+24, z+1, img, 18, 6);
			ibf.setColorBlock(world, x-1, y+24, z+1, img, 19, 6);
			ibf.setColorBlock(world, x+0, y+24, z+1, img, 20, 6);
			ibf.setColorBlock(world, x+1, y+24, z+1, img, 21, 6);
			ibf.setColorBlock(world, x+2, y+24, z+1, img, 22, 6);
			
			//SIDE 1 LEFT
			
			ibf.setColorBlock(world, x-4, y+31, z-5, img, 0, 8);
			ibf.setColorBlock(world, x-4, y+31, z-4, img, 1, 8);
			ibf.setColorBlock(world, x-4, y+31, z-3, img, 2, 8);
			ibf.setColorBlock(world, x-4, y+31, z-2, img, 3, 8);
			ibf.setColorBlock(world, x-4, y+31, z-1, img, 4, 8);
			ibf.setColorBlock(world, x-4, y+31, z+0, img, 5, 8);
			ibf.setColorBlock(world, x-4, y+31, z+1, img, 6, 8);

			ibf.setColorBlock(world, x-4, y+30, z-5, img, 0, 9);
			ibf.setColorBlock(world, x-4, y+30, z-4, img, 1, 9);
			ibf.setColorBlock(world, x-4, y+30, z-3, img, 2, 9);
			ibf.setColorBlock(world, x-4, y+30, z-2, img, 3, 9);
			ibf.setColorBlock(world, x-4, y+30, z-1, img, 4, 9);
			ibf.setColorBlock(world, x-4, y+30, z+0, img, 5, 9);
			ibf.setColorBlock(world, x-4, y+30, z+1, img, 6, 9);

			ibf.setColorBlock(world, x-4, y+29, z-5, img, 0, 10);
			ibf.setColorBlock(world, x-4, y+29, z-4, img, 1, 10);
			ibf.setColorBlock(world, x-4, y+29, z-3, img, 2, 10);
			ibf.setColorBlock(world, x-4, y+29, z-2, img, 3, 10);
			ibf.setColorBlock(world, x-4, y+29, z-1, img, 4, 10);
			ibf.setColorBlock(world, x-4, y+29, z+0, img, 5, 10);
			ibf.setColorBlock(world, x-4, y+29, z+1, img, 6, 10);

			ibf.setColorBlock(world, x-4, y+28, z-5, img, 0, 11);
			ibf.setColorBlock(world, x-4, y+28, z-4, img, 1, 11);
			ibf.setColorBlock(world, x-4, y+28, z-3, img, 2, 11);
			ibf.setColorBlock(world, x-4, y+28, z-2, img, 3, 11);
			ibf.setColorBlock(world, x-4, y+28, z-1, img, 4, 11);
			ibf.setColorBlock(world, x-4, y+28, z+0, img, 5, 11);
			ibf.setColorBlock(world, x-4, y+28, z+1, img, 6, 11);

			ibf.setColorBlock(world, x-4, y+27, z-5, img, 0, 12);
			ibf.setColorBlock(world, x-4, y+27, z-4, img, 1, 12);
			ibf.setColorBlock(world, x-4, y+27, z-3, img, 2, 12);
			ibf.setColorBlock(world, x-4, y+27, z-2, img, 3, 12);
			ibf.setColorBlock(world, x-4, y+27, z-1, img, 4, 12);
			ibf.setColorBlock(world, x-4, y+27, z+0, img, 5, 12);
			ibf.setColorBlock(world, x-4, y+27, z+1, img, 6, 12);

			ibf.setColorBlock(world, x-4, y+26, z-5, img, 0, 13);
			ibf.setColorBlock(world, x-4, y+26, z-4, img, 1, 13);
			ibf.setColorBlock(world, x-4, y+26, z-3, img, 2, 13);
			ibf.setColorBlock(world, x-4, y+26, z-2, img, 3, 13);
			ibf.setColorBlock(world, x-4, y+26, z-1, img, 4, 13);
			ibf.setColorBlock(world, x-4, y+26, z+0, img, 5, 13);
			ibf.setColorBlock(world, x-4, y+26, z+1, img, 6, 13);

			ibf.setColorBlock(world, x-4, y+25, z-5, img, 0, 14);
			ibf.setColorBlock(world, x-4, y+25, z-4, img, 1, 14);
			ibf.setColorBlock(world, x-4, y+25, z-3, img, 2, 14);
			ibf.setColorBlock(world, x-4, y+25, z-2, img, 3, 14);
			ibf.setColorBlock(world, x-4, y+25, z-1, img, 4, 14);
			ibf.setColorBlock(world, x-4, y+25, z+0, img, 5, 14);
			ibf.setColorBlock(world, x-4, y+25, z+1, img, 6, 14);

			ibf.setColorBlock(world, x-4, y+24, z-5, img, 0, 15);
			ibf.setColorBlock(world, x-4, y+24, z-4, img, 1, 15);
			ibf.setColorBlock(world, x-4, y+24, z-3, img, 2, 15);
			ibf.setColorBlock(world, x-4, y+24, z-2, img, 3, 15);
			ibf.setColorBlock(world, x-4, y+24, z-1, img, 4, 15);
			ibf.setColorBlock(world, x-4, y+24, z+0, img, 5, 15);
			ibf.setColorBlock(world, x-4, y+24, z+1, img, 6, 15);
			
			// SIDE 2 RIGHT
			
			ibf.setColorBlock(world, x+3, y+31, z+1, img, 17, 8);
			ibf.setColorBlock(world, x+3, y+31, z+0, img, 18, 8);
			ibf.setColorBlock(world, x+3, y+31, z-1, img, 19, 8);
			ibf.setColorBlock(world, x+3, y+31, z-2, img, 20, 8);
			ibf.setColorBlock(world, x+3, y+31, z-3, img, 21, 8);
			ibf.setColorBlock(world, x+3, y+31, z-4, img, 22, 8);
			ibf.setColorBlock(world, x+3, y+31, z-5, img, 23, 8);
			
			ibf.setColorBlock(world, x+3, y+30, z+1, img, 17, 9);
			ibf.setColorBlock(world, x+3, y+30, z+0, img, 18, 9);
			ibf.setColorBlock(world, x+3, y+30, z-1, img, 19, 9);
			ibf.setColorBlock(world, x+3, y+30, z-2, img, 20, 9);
			ibf.setColorBlock(world, x+3, y+30, z-3, img, 21, 9);
			ibf.setColorBlock(world, x+3, y+30, z-4, img, 22, 9);
			ibf.setColorBlock(world, x+3, y+30, z-5, img, 23, 9);

			ibf.setColorBlock(world, x+3, y+29, z+1, img, 17, 10);
			ibf.setColorBlock(world, x+3, y+29, z+0, img, 18, 10);
			ibf.setColorBlock(world, x+3, y+29, z-1, img, 19, 10);
			ibf.setColorBlock(world, x+3, y+29, z-2, img, 20, 10);
			ibf.setColorBlock(world, x+3, y+29, z-3, img, 21, 10);
			ibf.setColorBlock(world, x+3, y+29, z-4, img, 22, 10);
			ibf.setColorBlock(world, x+3, y+29, z-5, img, 23, 10);

			ibf.setColorBlock(world, x+3, y+28, z+1, img, 17, 11);
			ibf.setColorBlock(world, x+3, y+28, z+0, img, 18, 11);
			ibf.setColorBlock(world, x+3, y+28, z-1, img, 19, 11);
			ibf.setColorBlock(world, x+3, y+28, z-2, img, 20, 11);
			ibf.setColorBlock(world, x+3, y+28, z-3, img, 21, 11);
			ibf.setColorBlock(world, x+3, y+28, z-4, img, 22, 11);
			ibf.setColorBlock(world, x+3, y+28, z-5, img, 23, 11);

			ibf.setColorBlock(world, x+3, y+27, z+1, img, 17, 12);
			ibf.setColorBlock(world, x+3, y+27, z+0, img, 18, 12);
			ibf.setColorBlock(world, x+3, y+27, z-1, img, 19, 12);
			ibf.setColorBlock(world, x+3, y+27, z-2, img, 20, 12);
			ibf.setColorBlock(world, x+3, y+27, z-3, img, 21, 12);
			ibf.setColorBlock(world, x+3, y+27, z-4, img, 22, 12);
			ibf.setColorBlock(world, x+3, y+27, z-5, img, 23, 12);

			ibf.setColorBlock(world, x+3, y+26, z+1, img, 17, 13);
			ibf.setColorBlock(world, x+3, y+26, z+0, img, 18, 13);
			ibf.setColorBlock(world, x+3, y+26, z-1, img, 19, 13);
			ibf.setColorBlock(world, x+3, y+26, z-2, img, 20, 13);
			ibf.setColorBlock(world, x+3, y+26, z-3, img, 21, 13);
			ibf.setColorBlock(world, x+3, y+26, z-4, img, 22, 13);
			ibf.setColorBlock(world, x+3, y+26, z-5, img, 23, 13);

			ibf.setColorBlock(world, x+3, y+25, z+1, img, 17, 14);
			ibf.setColorBlock(world, x+3, y+25, z+0, img, 18, 14);
			ibf.setColorBlock(world, x+3, y+25, z-1, img, 19, 14);
			ibf.setColorBlock(world, x+3, y+25, z-2, img, 20, 14);
			ibf.setColorBlock(world, x+3, y+25, z-3, img, 21, 14);
			ibf.setColorBlock(world, x+3, y+25, z-4, img, 22, 14);
			ibf.setColorBlock(world, x+3, y+25, z-5, img, 23, 14);

			ibf.setColorBlock(world, x+3, y+24, z+1, img, 17, 15);
			ibf.setColorBlock(world, x+3, y+24, z+0, img, 18, 15);
			ibf.setColorBlock(world, x+3, y+24, z-1, img, 19, 15);
			ibf.setColorBlock(world, x+3, y+24, z-2, img, 20, 15);
			ibf.setColorBlock(world, x+3, y+24, z-3, img, 21, 15);
			ibf.setColorBlock(world, x+3, y+24, z-4, img, 22, 15);
			ibf.setColorBlock(world, x+3, y+24, z-5, img, 23, 15);
			
			
			//BACK

			ibf.setColorBlock(world, x+2, y+30, z-5, img, 25, 9);
			ibf.setColorBlock(world, x+1, y+30, z-5, img, 26, 9);
			ibf.setColorBlock(world, x+0, y+30, z-5, img, 27, 9);
			ibf.setColorBlock(world, x-1, y+30, z-5, img, 28, 9);
			ibf.setColorBlock(world, x-2, y+30, z-5, img, 29, 9);
			ibf.setColorBlock(world, x-3, y+30, z-5, img, 30, 9);

			ibf.setColorBlock(world, x+2, y+29, z-5, img, 25, 10);
			ibf.setColorBlock(world, x+1, y+29, z-5, img, 26, 10);
			ibf.setColorBlock(world, x+0, y+29, z-5, img, 27, 10);
			ibf.setColorBlock(world, x-1, y+29, z-5, img, 28, 10);
			ibf.setColorBlock(world, x-2, y+29, z-5, img, 29, 10);
			ibf.setColorBlock(world, x-3, y+29, z-5, img, 30, 10);

			ibf.setColorBlock(world, x+2, y+28, z-5, img, 25, 11);
			ibf.setColorBlock(world, x+1, y+28, z-5, img, 26, 11);
			ibf.setColorBlock(world, x+0, y+28, z-5, img, 27, 11);
			ibf.setColorBlock(world, x-1, y+28, z-5, img, 28, 11);
			ibf.setColorBlock(world, x-2, y+28, z-5, img, 29, 11);
			ibf.setColorBlock(world, x-3, y+28, z-5, img, 30, 11);

			ibf.setColorBlock(world, x+2, y+27, z-5, img, 25, 12);
			ibf.setColorBlock(world, x+1, y+27, z-5, img, 26, 12);
			ibf.setColorBlock(world, x+0, y+27, z-5, img, 27, 12);
			ibf.setColorBlock(world, x-1, y+27, z-5, img, 28, 12);
			ibf.setColorBlock(world, x-2, y+27, z-5, img, 29, 12);
			ibf.setColorBlock(world, x-3, y+27, z-5, img, 30, 12);

			ibf.setColorBlock(world, x+2, y+26, z-5, img, 25, 13);
			ibf.setColorBlock(world, x+1, y+26, z-5, img, 26, 13);
			ibf.setColorBlock(world, x+0, y+26, z-5, img, 27, 13);
			ibf.setColorBlock(world, x-1, y+26, z-5, img, 28, 13);
			ibf.setColorBlock(world, x-2, y+26, z-5, img, 29, 13);
			ibf.setColorBlock(world, x-3, y+26, z-5, img, 30, 13);

			ibf.setColorBlock(world, x+2, y+25, z-5, img, 25, 14);
			ibf.setColorBlock(world, x+1, y+25, z-5, img, 26, 14);
			ibf.setColorBlock(world, x+0, y+25, z-5, img, 27, 14);
			ibf.setColorBlock(world, x-1, y+25, z-5, img, 28, 14);
			ibf.setColorBlock(world, x-2, y+25, z-5, img, 29, 14);
			ibf.setColorBlock(world, x-3, y+25, z-5, img, 30, 14);

			ibf.setColorBlock(world, x+2, y+24, z-5, img, 25, 15);
			ibf.setColorBlock(world, x+1, y+24, z-5, img, 26, 15);
			ibf.setColorBlock(world, x+0, y+24, z-5, img, 27, 15);
			ibf.setColorBlock(world, x-1, y+24, z-5, img, 28, 15);
			ibf.setColorBlock(world, x-2, y+24, z-5, img, 29, 15);
			ibf.setColorBlock(world, x-3, y+24, z-5, img, 30, 15);*/
}