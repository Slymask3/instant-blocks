package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockColor extends Block implements ITileEntityProvider {
    public static Block blockType = Blocks.wool;
    public static int blockMeta = 0;

    public BlockColor() {
        super(blockType.getMaterial());
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName(Reference.MOD_ID + ":" + Names.Blocks.COLOR);
        setHardness(0.5F);
        setStepSound(blockType.stepSound);
        setBlockTextureName(Textures.Color.SIDE);
        setLightOpacity(blockType.getLightOpacity());
        setLightLevel(blockType.getLightValue());
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public int getRenderBlockPass() {
        return blockType.getRenderBlockPass();
    }

    public boolean isOpaqueCube() {
        return blockType.isOpaqueCube();
    }

    public boolean renderAsNormalBlock() {
        return blockType.isOpaqueCube();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return blockType.getIcon(side, blockMeta);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, int meta) {
        return new TileEntityColor();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
        return ((TileEntityColor) access.getTileEntity(x, y, z)).color;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        if(IBHelper.isServer(world)) {
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            ((TileEntityColor) world.getTileEntity(x, y, z)).color = ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
        }
        world.markAndNotifyBlock(x,y,z,world.getChunkFromBlockCoords(x,z),this,this,2);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        world.markAndNotifyBlock(x,y,z,world.getChunkFromBlockCoords(x,z),this,this,2);
        return false;
    }

    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        world.markBlockForUpdate(x,y,z);
    }
}