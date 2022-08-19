package com.slymask3.instantblocks.util;

import net.minecraft.util.Mth;
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
	
	public static Block getWoolColor(Color color) {
		VanillaColor inputColor = new VanillaColor(color);

		VanillaColor woolColor = null;
		float diff_lowest = -1F;
		for(VanillaColor vanillaColor : VANILLA_COLORS) {
			float diff = vanillaColor.diff(inputColor);
			if(diff_lowest == -1F || diff < diff_lowest) {
				diff_lowest = diff;
				woolColor = vanillaColor;
			}
		}

		return woolColor.getBlock();
	}
	
	public static Color getColorAt(BufferedImage img, int x, int y) {
		return new Color(img.getRGB(x,y));
	}

	public static Color getColorBetween(Color one, Color two, int per1, int per2) {
		double p1 = per1 / 100.0;
		double p2 = per2 / 100.0;
		return new Color((int)Math.floor(one.getRed()*p1+two.getRed()*p2),(int)Math.floor(one.getGreen()*p1+two.getGreen()*p2),(int)Math.floor(one.getBlue()*p1+two.getBlue()*p2));
	}

	public static Color generateRandomColor() {
		Random rand = new Random();
		int color = Mth.hsvToRgb(rand.nextFloat(),rand.nextFloat(0.5F)+0.5F,rand.nextFloat(0.5F)+0.5F);
		return new Color(color);
	}

	public static Color textToColor(String input) {
		int color;
		if(input.equalsIgnoreCase("red")) {
			color = 0xFF0000;
		} else if(input.equalsIgnoreCase("orange")) {
			color = 0xFF8000;
		} else if(input.equalsIgnoreCase("yellow")) {
			color = 0xFFFF00;
		} else if(input.equalsIgnoreCase("lime")) {
			color = 0x80FF00;
		} else if(input.equalsIgnoreCase("green")) {
			color = 0x00FF00;
		} else if(input.equalsIgnoreCase("cyan")) {
			color = 0x00FFFF;
		} else if(input.equalsIgnoreCase("light blue") || input.equalsIgnoreCase("lightblue")) {
			color = 0x0080FF;
		} else if(input.equalsIgnoreCase("blue")) {
			color = 0x0000FF;
		} else if(input.equalsIgnoreCase("purple")) {
			color = 0x8000FF;
		} else if(input.equalsIgnoreCase("magenta")) {
			color = 0xFF00FF;
		} else if(input.equalsIgnoreCase("pink")) {
			color = 0xFF0080;
		} else if(input.equalsIgnoreCase("white")) {
			color = 0xFFFFFF;
		} else if(input.equalsIgnoreCase("gray") || input.equalsIgnoreCase("grey")) {
			color = 0x808080;
		} else if(input.equalsIgnoreCase("light gray") || input.equalsIgnoreCase("lightgray") || input.equalsIgnoreCase("light grey") || input.equalsIgnoreCase("lightgrey")) {
			color = 0xC0C0C0;
		} else if(input.equalsIgnoreCase("brown")) {
			color = 0x663300;
		} else if(input.equalsIgnoreCase("black")) {
			color = 0x000000;
		} else {
			try {
				color = (int)Long.parseLong(input, 16);
			} catch(Exception e) {
				color = 0xA0A0A0;
			}
		}
		return new Color(color);
	}

	public static boolean isTransparent(int color) {
		return color >> 24 != 0x00;
	}

	public static class VanillaColor {
		private float hue;
		private float saturation;
		private float value;
		private final Color color;
		private final Block block;
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
			if(cmax == cmin)
				h = 0;

				// if cmax equal r then compute h
			else if(cmax == r)
				h = (60 * ((g - b) / diff) + 360) % 360;

				// if cmax equal g then compute h
			else if(cmax == g)
				h = (60 * ((b - r) / diff) + 120) % 360;

				// if cmax equal b then compute h
			else if(cmax == b)
				h = (60 * ((r - g) / diff) + 240) % 360;

			// if cmax equal zero
			if(cmax == 0)
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