package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class HarvestBlockEntity extends InstantBlockEntity {
	public boolean logOak, logSpruce, logBirch, logJungle, logAcacia, logDark;
	public boolean wheat, carrot, potato;
	public boolean cactus, pumpkin, melon, sugarcane;
	public boolean cocoa, mushroom, netherwart;
	public boolean replant;

	public HarvestBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.HARVEST, pos, state);
		this.logOak=true;
		this.logSpruce=true;
		this.logBirch=true;
		this.logJungle=true;
		this.logAcacia=true;
		this.logDark=true;
		this.wheat=true;
		this.carrot=true;
		this.potato=true;
		this.cactus=true;
		this.pumpkin=true;
		this.melon=true;
		this.sugarcane=true;
		this.cocoa=true;
		this.mushroom=true;
		this.netherwart=true;
		this.replant=true;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		logOak = nbt.getBoolean("LogOak");
		logSpruce = nbt.getBoolean("LogSpruce");
		logBirch = nbt.getBoolean("LogBirch");
		logJungle = nbt.getBoolean("LogJungle");
		logAcacia = nbt.getBoolean("LogAcacia");
		logDark = nbt.getBoolean("LogDark");
		wheat = nbt.getBoolean("Wheat");
		carrot = nbt.getBoolean("Carrot");
		potato = nbt.getBoolean("Potato");
		cactus = nbt.getBoolean("Cactus");
		pumpkin = nbt.getBoolean("Pumpkin");
		melon = nbt.getBoolean("Melon");
		sugarcane = nbt.getBoolean("Sugarcane");
		cocoa = nbt.getBoolean("Cocoa");
		mushroom = nbt.getBoolean("Mushroom");
		netherwart = nbt.getBoolean("Netherwart");
		replant = nbt.getBoolean("Replant");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putBoolean("LogOak", logOak);
		nbt.putBoolean("LogSpruce", logSpruce);
		nbt.putBoolean("LogBirch", logBirch);
		nbt.putBoolean("LogJungle", logJungle);
		nbt.putBoolean("LogAcacia", logAcacia);
		nbt.putBoolean("LogDark", logDark);
		nbt.putBoolean("Wheat", wheat);
		nbt.putBoolean("Carrot", carrot);
		nbt.putBoolean("Potato", potato);
		nbt.putBoolean("Cactus", cactus);
		nbt.putBoolean("Pumpkin", pumpkin);
		nbt.putBoolean("Melon", melon);
		nbt.putBoolean("Sugarcane", sugarcane);
		nbt.putBoolean("Cocoa", cocoa);
		nbt.putBoolean("Mushroom", mushroom);
		nbt.putBoolean("Netherwart", netherwart);
		nbt.putBoolean("Replant", replant);
	}
}