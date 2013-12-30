package me.dawars.CraftingPillars.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviors;
import me.dawars.CraftingPillars.tiles.TileEntitySentryPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class SentryPillarBlock extends BaseBlockContainer
{
	public SentryPillarBlock(int id, Material mat)
	{
		super(id, mat);
	}

	@Override
	public int getRenderType()
	{
		return CraftingPillars.sentryPillarRenderID;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
			return true;

		TileEntitySentryPillar pillarTile = (TileEntitySentryPillar) world.getBlockTileEntity(x, y, z);

		if(hitY < 1F && !player.isSneaking())
		{
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if(hitY == 1F)
		{
			if(player.isSneaking())//pick out
			{
				pillarTile.dropItemFromSlot(0, 1, player);
			}
			else if(player.getCurrentEquippedItem() != null)
			{//put in
				ItemStack equipped = player.getCurrentEquippedItem();
				ItemStack itemstack = pillarTile.getStackInSlot(0);
				if(itemstack == null)
				{//slot empty
					if(SentryBehaviors.get(equipped.itemID) != null)
					{
						ItemStack in = equipped.copy();
						in.stackSize = 1;
						this.setSentryOwner(pillarTile, player);

						pillarTile.setInventorySlotContents(0, in);

						if(!player.capabilities.isCreativeMode)
							equipped.stackSize--;
					}
				}
				else if(itemstack.isItemEqual(equipped) && itemstack.stackSize < itemstack.getMaxStackSize())
				{//slot not empty
					if(!player.capabilities.isCreativeMode)
						equipped.stackSize--;

					pillarTile.decrStackSize(0, -1);
					this.setSentryOwner(pillarTile, player);

					pillarTile.onInventoryChanged();
				}
			}
		}
		return true;
	}

	private void setSentryOwner(TileEntitySentryPillar pillarTile, EntityPlayer player) {
		pillarTile.setOwnerEntity(player);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		if(!world.isRemote)
		{
			TileEntitySentryPillar workTile = (TileEntitySentryPillar) world.getBlockTileEntity(x, y, z);

			if(workTile.getStackInSlot(0) != null)
			{
				EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, workTile.getStackInSlot(0));
				itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

				if(workTile.getStackInSlot(0).hasTagCompound())
					itemDropped.getEntityItem().setTagCompound((NBTTagCompound) workTile.getStackInSlot(0).getTagCompound().copy());

				world.spawnEntityInWorld(itemDropped);
			}
		}

		super.breakBlock(world, x, y, z, par5, par6);
	}
	/**
	 * Determines if this block is can be destroyed by the specified entities normal behavior.
	 *
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z position
	 * @return True to allow the ender dragon to destroy this block
	 */
	@Override
	public boolean canEntityDestroy(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityWither)
		{
			return false;
		}
		else if (entity instanceof EntityDragon)
		{
			return false;
		}

		return true;
	}
	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack ItemStack)
	{
		if(world.getBlockTileEntity(i, j, k) instanceof TileEntitySentryPillar && entity instanceof EntityPlayer)
		{
			((TileEntitySentryPillar) world.getBlockTileEntity(i, j, k)).setOwnerEntity((EntityPlayer) entity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntitySentryPillar tile = new TileEntitySentryPillar();
		return tile;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister itemIcon)
	{
		this.blockIcon = itemIcon.registerIcon(CraftingPillars.id + ":craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
