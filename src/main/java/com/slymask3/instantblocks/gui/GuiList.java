package com.slymask3.instantblocks.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiList extends Gui
{
    private final FontRenderer font;
    public int xPosition;
    public int yPosition;
    /** The width of this text field. */
    public int width;
    public int height;
    /** Has the current text being edited on the textbox. */
    //private String text = "";
    //private int maxStringLength = 32;
    //private int cursorCounter;
    //private boolean enableBackgroundDrawing = true;
    /** if true the textbox can lose focus by clicking elsewhere on the screen */
    //private boolean canLoseFocus = true;
    /** If this value is true along with isEnabled, keyTyped will process the keys. */
    //private boolean isFocused;
    /** If this value is true along with isFocused, keyTyped will process the keys. */
    private boolean isEnabled = true;
    /** The current character index that should be used as start of the rendered text. */
    //private int lineScrollOffset;
    //private int cursorPosition;
    /** other selection position, maybe the same as the cursor */
    //private int selectionEnd;
    private int color = 14737632;
    //private int disabledColor = 7368816;
    /** True if this textbox is visible */
    private boolean visible = true;
    //private static final String __OBFID = "CL_00000670";

    private int xScrollOriginal;
    private int yScrollOriginal;
    private int xScroll;
    private int yScroll;

    private int xText;
    private int yText;
    
    private int colorScroll = getColor(0, 255, 255)*-1;
    
    private boolean drag;
    private float dragY;
    
    private ArrayList<String> contents = new ArrayList<String>();

    public GuiList(FontRenderer font, int x, int y, int width, int height)
    {
        this.font = font;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
        this.xScrollOriginal = x + width - 12;
        this.yScrollOriginal = y;
        this.xScroll = x + width - 12;
        this.yScroll = y;
        this.xText = x+2;
        this.yText = y+2;
    }

    public int getColor(int r, int g, int b) {
		return (r * 65536) + (g * 256) + b;
	}
    
//    /**
//     * Increments the cursor counter ????
//     */
//    public void updateCursorCounter()
//    {
//        ++this.cursorCounter;
//    }
    
    public void cleanLines() {
//    	for(int i=0; i <this.contents.size(); i++) {
//        	this.contents.remove(i);
//    	}
    	
    	this.contents = new ArrayList<String>();
    }
    
    public void addLine(String line) {
    	this.contents.add(line);
    }
    
    public String getLine(int index) {
    	return this.contents.get(index);
    }

    /**
     * Args: x, y, buttonClicked
     */
    public void mouseClicked(int x, int y, int clicked) {
        boolean onScroll = x >= this.xScroll && x <= this.xPosition + this.xScroll+12 && y >= this.yScrollOriginal && y <= this.yScrollOriginal + height;

        if (onScroll) {
        	this.colorScroll = getColor(0, 255, 0)*-1;
        	this.yScroll=y-6;
        	
        	this.yText = y+6;
        } else {
        	this.colorScroll = getColor(0, 255, 255)*-1;
        	
        	
        }
        
        if(this.yScroll < this.yScrollOriginal) {
        	this.yScroll = this.yScrollOriginal;
        }
        
        if(this.yScroll+15 > this.yScrollOriginal+height) {
        	this.yScroll = this.yScrollOriginal+width-15;
        }
        
        if (y >= this.yScroll && y <= this.yScroll + 15){
			//drag = true;
			//dragY = y;
			
			this.yScroll=y-6;
			
//			int listRight = width / 2 + 124;
//			int scrollRight = listRight + 6;
//			if(mouseX >= listRight && mouseX <= scrollRight) {
//				scrollMultiplier = -1F;
//				int contentHeight = getContentHeight() - (bottom - top - 4);
//				if(contentHeight < 1) {
//					contentHeight = 1;
//				}
//				int i4 = (int)((float)((bottom - top) * (bottom - top)) / (float)getContentHeight());
//				if(i4 < 32) {
//					i4 = 32;
//				}
//				if(i4 > bottom - top - 8) {
//					i4 = bottom - top - 8;
//				}
//				scrollMultiplier /= (float)(bottom - top - i4) / (float)contentHeight;
			//} else {
				//scrollMultiplier = 1F;
			//}
		}
    }
    
    public void mouseMovedOrUp(int mouseX, int mouseY, int button){
    	
    }

    public void drawString(String text, int x, int y, int color) {
    	if(y+8 < this.yPosition + this.height && y > this.yPosition) {
    		this.font.drawStringWithShadow(text, x, y, color);
    	} else {
    		//do not print
    	}
    }
    
   
    public void drawImage() {
    	//this.renderEngine.bindTexture(this.locationFontTexture);
    	
    	//this.font
    	//this.drawTexturedModelRectFromIcon(p_94065_1_, p_94065_2_, p_94065_3_, p_94065_4_, p_94065_5_)
    }
    
    public void drawList()
    {
        if (this.getVisible())
        {

            drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
            
            drawRect(this.xPosition+width-12-1, this.yPosition-1, this.xPosition+this.width+1, this.yPosition + this.height+1, getColor(0, 0, 200)*-1); //scrollbar backgroudn border
            drawRect(this.xPosition+width-12, this.yPosition, this.xPosition+width, this.yPosition + this.height, getColor(200, 0, 0)*-1); //scrollbar background
            drawRect(this.xScroll, this.yScroll, this.xScroll + 12, this.yScroll + 15, this.colorScroll); //scrollbar

            //int x1 = this.xPosition + 2;
            //int y1 = this.yPosition + 2;
            
            if(!contents.isEmpty()) {
	            for(int i=0; i<contents.size(); i++) {
	            	drawString(getLine(i), xText, yText+(i*10), this.color);
	            }
            }
            
            //this.drawTexturedModelRectFromIcon(p_94065_1_, p_94065_2_, p_94065_3_, p_94065_4_, p_94065_5_)

            
            
            //LogHelper.info(contents.size());
            
//            int i = this.isEnabled ? this.enabledColor : this.disabledColor;
//            int j = this.cursorPosition - this.lineScrollOffset;
//            int k = this.selectionEnd - this.lineScrollOffset;
//            String s = this.font.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
//            boolean flag = j >= 0 && j <= s.length();
//            boolean flag1 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag;
//            int l = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
//            int i1 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
//            int j1 = l;
//
//            if (k > s.length())
//            {
//                k = s.length();
//            }
//
//            if (s.length() > 0)
//            {
//                String s1 = flag ? s.substring(0, j) : s;
//                j1 = this.font.drawStringWithShadow(s1, l, i1, i);
//            }
//
//            boolean flag2 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
//            int k1 = j1;
//
//            if (!flag)
//            {
//                k1 = j > 0 ? l + this.width : l;
//            }
//            else if (flag2)
//            {
//                k1 = j1 - 1;
//                --j1;
//            }
//
//            if (s.length() > 0 && flag && j < s.length())
//            {
//                this.font.drawStringWithShadow(s.substring(j), j1, i1, i);
//            }

//            if (flag1)
//            {
//                if (flag2)
//                {
//                    Gui.drawRect(k1, i1 - 1, k1 + 1, i1 + 1 + this.font.FONT_HEIGHT, -3092272);
//                }
//                else
//                {
//                    this.font.drawStringWithShadow("_", k1, i1, i);
//                }
//            }
//
//            if (k != j)
//            {
//                int l1 = l + this.font.getStringWidth(s.substring(0, k));
//                this.drawCursorVertical(k1, i1 - 1, l1 - 1, i1 + 1 + this.font.FONT_HEIGHT);
//            }
        }
    }

    /**
     * draws the vertical line cursor in the textbox
     */
//    private void drawCursorVertical(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_)
//    {
//        int i1;
//
//        if (p_146188_1_ < p_146188_3_)
//        {
//            i1 = p_146188_1_;
//            p_146188_1_ = p_146188_3_;
//            p_146188_3_ = i1;
//        }
//
//        if (p_146188_2_ < p_146188_4_)
//        {
//            i1 = p_146188_2_;
//            p_146188_2_ = p_146188_4_;
//            p_146188_4_ = i1;
//        }
//
//        if (p_146188_3_ > this.xPosition + this.width)
//        {
//            p_146188_3_ = this.xPosition + this.width;
//        }
//
//        if (p_146188_1_ > this.xPosition + this.width)
//        {
//            p_146188_1_ = this.xPosition + this.width;
//        }
//
//        Tessellator tessellator = Tessellator.instance;
//        GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glEnable(GL11.GL_COLOR_LOGIC_OP);
//        GL11.glLogicOp(GL11.GL_OR_REVERSE);
//        tessellator.startDrawingQuads();
//        tessellator.addVertex((double)p_146188_1_, (double)p_146188_4_, 0.0D);
//        tessellator.addVertex((double)p_146188_3_, (double)p_146188_4_, 0.0D);
//        tessellator.addVertex((double)p_146188_3_, (double)p_146188_2_, 0.0D);
//        tessellator.addVertex((double)p_146188_1_, (double)p_146188_2_, 0.0D);
//        tessellator.draw();
//        GL11.glDisable(GL11.GL_COLOR_LOGIC_OP);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//    }

//    public void setMaxStringLength(int p_146203_1_)
//    {
//        this.maxStringLength = p_146203_1_;
//
//        if (this.text.length() > p_146203_1_)
//        {
//            this.text = this.text.substring(0, p_146203_1_);
//        }
//    }
//
//    /**
//     * returns the maximum number of character that can be contained in this textbox
//     */
//    public int getMaxStringLength()
//    {
//        return this.maxStringLength;
//    }
//
//    /**
//     * returns the current position of the cursor
//     */
//    public int getCursorPosition()
//    {
//        return this.cursorPosition;
//    }

//    /**
//     * get enable drawing background and outline
//     */
//    public boolean getEnableBackgroundDrawing()
//    {
//        return this.enableBackgroundDrawing;
//    }

//    /**
//     * enable drawing background and outline
//     */
//    public void setEnableBackgroundDrawing(boolean p_146185_1_)
//    {
//        this.enableBackgroundDrawing = p_146185_1_;
//    }

    /**
     * Sets the text colour for this textbox (disabled text will not use this colour)
     */
    public void setTextColor(int c)
    {
        this.color = c;
    }
//
//    public void setDisabledTextColour(int p_146204_1_)
//    {
//        this.disabledColor = p_146204_1_;
//    }

//    /**
//     * Sets focus to this gui element
//     */
//    public void setFocused(boolean p_146195_1_)
//    {
//        if (p_146195_1_ && !this.isFocused)
//        {
//            this.cursorCounter = 0;
//        }
//
//        this.isFocused = p_146195_1_;
//    }
//
//    /**
//     * Getter for the focused field
//     */
//    public boolean isFocused()
//    {
//        return this.isFocused;
//    }

    public void setEnabled(boolean p_146184_1_)
    {
        this.isEnabled = p_146184_1_;
    }

//    /**
//     * the side of the selection that is not the cursor, may be the same as the cursor
//     */
//    public int getSelectionEnd()
//    {
//        return this.selectionEnd;
//    }

    /**
     * returns the width of the textbox depending on if background drawing is enabled
     */
    public int getWidth()
    {
        return this.width - 8;
    }

//    /**
//     * Sets the position of the selection anchor (i.e. position the selection was started at)
//     */
//    public void setSelectionPos(int p_146199_1_)
//    {
//        int j = this.text.length();
//
//        if (p_146199_1_ > j)
//        {
//            p_146199_1_ = j;
//        }
//
//        if (p_146199_1_ < 0)
//        {
//            p_146199_1_ = 0;
//        }
//
//        this.selectionEnd = p_146199_1_;
//
//        if (this.font != null)
//        {
//            if (this.lineScrollOffset > j)
//            {
//                this.lineScrollOffset = j;
//            }
//
//            int k = this.getWidth();
//            String s = this.font.trimStringToWidth(this.text.substring(this.lineScrollOffset), k);
//            int l = s.length() + this.lineScrollOffset;
//
//            if (p_146199_1_ == this.lineScrollOffset)
//            {
//                this.lineScrollOffset -= this.font.trimStringToWidth(this.text, k, true).length();
//            }
//
//            if (p_146199_1_ > l)
//            {
//                this.lineScrollOffset += p_146199_1_ - l;
//            }
//            else if (p_146199_1_ <= this.lineScrollOffset)
//            {
//                this.lineScrollOffset -= this.lineScrollOffset - p_146199_1_;
//            }
//
//            if (this.lineScrollOffset < 0)
//            {
//                this.lineScrollOffset = 0;
//            }
//
//            if (this.lineScrollOffset > j)
//            {
//                this.lineScrollOffset = j;
//            }
//        }
//    }

//    /**
//     * if true the textbox can lose focus by clicking elsewhere on the screen
//     */
//    public void setCanLoseFocus(boolean p_146205_1_)
//    {
//        this.canLoseFocus = p_146205_1_;
//    }

    /**
     * returns true if this textbox is visible
     */
    public boolean getVisible()
    {
        return this.visible;
    }

    /**
     * Sets whether or not this textbox is visible
     */
    public void setVisible(boolean p_146189_1_)
    {
        this.visible = p_146189_1_;
    }
}