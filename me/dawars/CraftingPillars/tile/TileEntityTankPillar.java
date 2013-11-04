package me.dawars.CraftingPillars.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.dawars.CraftingPillars.Blobs;
import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class TileEntityTankPillar extends BaseTileEntity implements IFluidHandler
{
	private static final int MAX_Fluid = FluidContainerRegistry.BUCKET_VOLUME * 10;
	public final FluidTank tank = new FluidTank((int) MAX_Fluid);
	
	public List<Blobs> blobs;
	
	private Random random = new Random();
	
	public TileEntityTankPillar()
	{
		
		this.blobs = new ArrayList<Blobs>();
		generateBlobs();
	}
	
	private void generateBlobs()
	{
		for(int i = 0; i < 16; i++)
		{
			// x, z: 2.5-13.5 y: 4.5-12.5
			blobs.add(new Blobs(random.nextInt(12)+2.5F, random.nextInt(9)+4.5F, random.nextInt(12)+2.5F, 1));
		}
	}
	
	public void updateEntity()
	{
		if(CraftingPillars.proxy.isRenderWorld(worldObj))
		{
			for(int i = 0; i < this.blobs.size(); i++)
			{
				this.blobs.get(i).update(0.1F);
			}
		}
		if(tank.getFluid() != null && !worldObj.isRemote)
			System.out.println(tank.getFluid().amount + " " + FluidRegistry.getFluidName(tank.getFluid()));
	}
	
	/* SAVING & LOADING */
	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);
		FluidStack liquid = new FluidStack(0, 0);
		
		if(data.hasKey("stored") && data.hasKey("FluidId"))
		{
			liquid = new FluidStack(data.getInteger("FluidId"), data.getInteger("stored"));
		}
		else
		{
			liquid = FluidStack.loadFluidStackFromNBT(data.getCompoundTag("tank"));
		}
		tank.setFluid(liquid);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		super.writeToNBT(data);
		if(tank.getFluid() != null)
		{
			data.setTag("tank", tank.getFluid().writeToNBT(new NBTTagCompound()));
		}
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound nbt = pkt.data;
		this.readFromNBT(nbt);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, nbt);
	}
	
	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
		
		if(!this.worldObj.isRemote)
		{
			CraftingPillars.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 128);
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return this.tank.fill(resource, doFill);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return this.drain(from, resource.amount, doDrain);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.tank.drain(maxDrain, doDrain);
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return true;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { tank.getInfo() };
	}
}