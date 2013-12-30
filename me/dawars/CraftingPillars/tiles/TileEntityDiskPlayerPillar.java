package me.dawars.CraftingPillars.tiles;

import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityDiskPlayerPillar extends BaseTileEntity
{
	/** ID of record which is in Jukebox */
	private ItemStack record;
	public boolean showNum = false;
	public boolean isEmpty = true;

	public float rot = 0F;
	@Override
	public void updateEntity()
	{
		if(this.worldObj.isRemote)
		{
			this.rot  += 4F;
			if(this.rot >= 360F)
				this.rot -= 360F;
		}
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if(nbt.hasKey("RecordItem"))
		{
			this.setDisk(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("RecordItem")));
		}
		else if(nbt.getInteger("Record") > 0)
		{
			this.setDisk(new ItemStack(nbt.getInteger("Record"), 1, 0));
		}
		this.isEmpty = nbt.getBoolean("isEmpty");
		this.showNum = nbt.getBoolean("showNum");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if(this.getDisk() != null)
		{
			nbt.setCompoundTag("RecordItem", this.getDisk().writeToNBT(new NBTTagCompound()));
			nbt.setInteger("Record", this.getDisk().itemID);
		}
		nbt.setBoolean("isEmpty", this.isEmpty);
		nbt.setBoolean("showNum", this.showNum);
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
		if(this.worldObj != null && !this.worldObj.isRemote)
			CraftingPillars.proxy.sendToPlayers(this.getDescriptionPacket(),
					this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
	}

	public ItemStack getDisk()
	{
		return this.record;
	}

	public void setDisk(ItemStack item)
	{
		if(item == null)
			this.isEmpty = true;
		else
			this.isEmpty = false;
		this.record = item;

		this.onInventoryChanged();
	}
}
