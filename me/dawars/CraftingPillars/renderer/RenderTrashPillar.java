package me.dawars.CraftingPillars.renderer;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.api.CraftingPillarAPI;
import me.dawars.CraftingPillars.tiles.TileEntityDiskPlayerPillar;
import me.dawars.CraftingPillars.tiles.TileEntityTrashPillar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTrashPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_TRASHPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	private ModelRenderer BunnyTail1;
	private ModelRenderer BunnyTail2;
	private ModelRenderer BunnyTail3;
	private ModelRenderer BunnyEar1;
	private ModelRenderer BunnyEar2;

	private ModelRenderer Icicle1A;
	private ModelRenderer Icicle1B;
	private ModelRenderer Icicle1C;
	private ModelRenderer Icicle2A;
	private ModelRenderer Icicle2B;
	private ModelRenderer Icicle2C;
	private ModelRenderer Icicle3A;
	private ModelRenderer Icicle3B;
	private ModelRenderer Icicle4A;
	private ModelRenderer Icicle4B;
	private ModelRenderer Icicle5A;
	private ModelRenderer Icicle5B;
	private ModelRenderer Icicle5C;
	private ModelRenderer Icicle6A;
	private ModelRenderer Icicle6B;
	private ModelRenderer Icicle6C;
	private ModelRenderer Icicle7A;
	private ModelRenderer Icicle7B;
	private ModelRenderer Icicle7C;
	private ModelRenderer Icicle8A;
	private ModelRenderer Icicle8B;
	private ModelRenderer Icicle8C;
	private ModelRenderer Icicle8D;

	private ModelRenderer Icicle9A;
	private ModelRenderer Icicle9B;
	private ModelRenderer Icicle10A;
	private ModelRenderer Icicle10B;
	private ModelRenderer Icicle10C;
	private ModelRenderer Icicle11A;
	private ModelRenderer Icicle11B;
	private ModelRenderer Icicle11C;

	private Random random;
	private RenderingHelper.ItemRender itemRenderer;
	private RenderingHelper.ItemRender resultRenderer;

	public IModelCustom trash;

	public RenderTrashPillar()
	{
		if (CraftingPillars.winter)
			this.TEXTURE_TRASHPILLAR = new ResourceLocation(CraftingPillars.id + ":textures/models/showoffPillarFrozen.png");
		else
			this.TEXTURE_TRASHPILLAR = new ResourceLocation(CraftingPillars.id + ":textures/models/showoffPillar.png");

		this.trash = AdvancedModelLoader.loadModel("/assets/" + CraftingPillars.id + "/textures/models/trashPillar.obj");

		this.random = new Random();
		this.itemRenderer = new RenderingHelper.ItemRender(false, true);
		this.resultRenderer = new RenderingHelper.ItemRender(true, true);

		model.textureWidth = 128;
		model.textureHeight = 64;

		if (CraftingPillars.easter)
		{
			BunnyTail1 = new ModelRenderer(model, 0, 35);
			BunnyTail1.addBox(0F, 0F, 0F, 2, 4, 2);
			BunnyTail1.setRotationPoint(-1F, 18F, 7F);
			BunnyTail1.setTextureSize(128, 64);
			BunnyTail1.mirror = true;
			setRotation(BunnyTail1, 0F, 0F, 0F);
			BunnyTail2 = new ModelRenderer(model, 0, 33);
			BunnyTail2.addBox(0F, 0F, 0F, 4, 2, 2);
			BunnyTail2.setRotationPoint(-2F, 19F, 7F);
			BunnyTail2.setTextureSize(128, 64);
			BunnyTail2.mirror = true;
			setRotation(BunnyTail2, 0F, 0F, 0F);
			BunnyTail3 = new ModelRenderer(model, 0, 36);
			BunnyTail3.addBox(0F, 0F, 0F, 2, 2, 4);
			BunnyTail3.setRotationPoint(-1F, 19F, 6F);
			BunnyTail3.setTextureSize(128, 64);
			BunnyTail3.mirror = true;
			setRotation(BunnyTail3, 0F, 0F, 0F);
			BunnyEar1 = new ModelRenderer(model, 1, 18);
			BunnyEar1.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar1.setRotationPoint(3.5F, 9F, 8F);
			BunnyEar1.setTextureSize(128, 64);
			BunnyEar1.mirror = true;
			setRotation(BunnyEar1, 0F, 0F, 0F);
			BunnyEar2 = new ModelRenderer(model, 1, 18);
			BunnyEar2.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar2.setRotationPoint(-3.5F, 9F, 8F);
			BunnyEar2.setTextureSize(128, 64);
			BunnyEar2.mirror = true;
			setRotation(BunnyEar2, 0F, 0F, 0F);
		}

		if (CraftingPillars.winter)
		{
			this.Icicle1A = new ModelRenderer(model, 122, 60);
			this.Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
			this.Icicle1A.setRotationPoint(6F, 11F, -5F);
			this.Icicle1A.setTextureSize(128, 64);
			this.Icicle1A.mirror = true;
			this.setRotation(this.Icicle1A, 0F, 0F, 0F);
			this.Icicle1B = new ModelRenderer(model, 124, 58);
			this.Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle1B.setRotationPoint(6F, 11F, -3F);
			this.Icicle1B.setTextureSize(128, 64);
			this.Icicle1B.mirror = true;
			this.setRotation(this.Icicle1B, 0F, 0F, 0F);
			this.Icicle1C = new ModelRenderer(model, 124, 56);
			this.Icicle1C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle1C.setRotationPoint(6F, 13F, -4F);
			this.Icicle1C.setTextureSize(128, 64);
			this.Icicle1C.mirror = true;
			this.setRotation(this.Icicle1C, 0F, 0F, 0F);
			this.Icicle2A = new ModelRenderer(model, 122, 50);
			this.Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
			this.Icicle2A.setRotationPoint(6F, 11F, 0F);
			this.Icicle2A.setTextureSize(128, 64);
			this.Icicle2A.mirror = true;
			this.setRotation(this.Icicle2A, 0F, 0F, 0F);
			this.Icicle2B = new ModelRenderer(model, 124, 47);
			this.Icicle2B.addBox(0F, 0F, 0F, 1, 2, 1);
			this.Icicle2B.setRotationPoint(6F, 13F, 0F);
			this.Icicle2B.setTextureSize(128, 64);
			this.Icicle2B.mirror = true;
			this.setRotation(this.Icicle2B, 0F, 0F, 0F);
			this.Icicle2C = new ModelRenderer(model, 124, 54);
			this.Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle2C.setRotationPoint(6F, 11F, -1F);
			this.Icicle2C.setTextureSize(128, 64);
			this.Icicle2C.mirror = true;
			this.setRotation(this.Icicle2C, 0F, 0F, 0F);
			this.Icicle3A = new ModelRenderer(model, 120, 43);
			this.Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
			this.Icicle3A.setRotationPoint(6F, 11F, 3F);
			this.Icicle3A.setTextureSize(128, 64);
			this.Icicle3A.mirror = true;
			this.setRotation(this.Icicle3A, 0F, 0F, 0F);
			this.Icicle3B = new ModelRenderer(model, 124, 40);
			this.Icicle3B.addBox(0F, 0F, 0F, 1, 2, 1);
			this.Icicle3B.setRotationPoint(6F, 12F, 4F);
			this.Icicle3B.setTextureSize(128, 64);
			this.Icicle3B.mirror = true;
			this.setRotation(this.Icicle3B, 0F, 0F, 0F);
			this.Icicle4A = new ModelRenderer(model, 122, 38);
			this.Icicle4A.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle4A.setRotationPoint(3F, 11F, 6F);
			this.Icicle4A.setTextureSize(128, 64);
			this.Icicle4A.mirror = true;
			this.setRotation(this.Icicle4A, 0F, 0F, 0F);
			this.Icicle4B = new ModelRenderer(model, 124, 36);
			this.Icicle4B.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle4B.setRotationPoint(4F, 12F, 6F);
			this.Icicle4B.setTextureSize(128, 64);
			this.Icicle4B.mirror = true;
			this.setRotation(this.Icicle4B, 0F, 0F, 0F);
			this.Icicle5A = new ModelRenderer(model, 114, 61);
			this.Icicle5A.addBox(0F, 0F, 0F, 3, 2, 1);
			this.Icicle5A.setRotationPoint(-1F, 11F, 6F);
			this.Icicle5A.setTextureSize(128, 64);
			this.Icicle5A.mirror = true;
			this.setRotation(this.Icicle5A, 0F, 0F, 0F);
			this.Icicle5B = new ModelRenderer(model, 116, 59);
			this.Icicle5B.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle5B.setRotationPoint(-1F, 13F, 6F);
			this.Icicle5B.setTextureSize(128, 64);
			this.Icicle5B.mirror = true;
			this.setRotation(this.Icicle5B, 0F, 0F, 0F);
			this.Icicle5C = new ModelRenderer(model, 120, 56);
			this.Icicle5C.addBox(0F, 0F, 0F, 1, 2, 1);
			this.Icicle5C.setRotationPoint(0F, 14F, 6F);
			this.Icicle5C.setTextureSize(128, 64);
			this.Icicle5C.mirror = true;
			this.setRotation(this.Icicle5C, 0F, 0F, 0F);
			this.Icicle6A = new ModelRenderer(model, 114, 54);
			this.Icicle6A.addBox(0F, 0F, 0F, 4, 1, 1);
			this.Icicle6A.setRotationPoint(-5F, 11F, 6F);
			this.Icicle6A.setTextureSize(128, 64);
			this.Icicle6A.mirror = true;
			this.setRotation(this.Icicle6A, 0F, 0F, 0F);
			this.Icicle6B = new ModelRenderer(model, 116, 52);
			this.Icicle6B.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle6B.setRotationPoint(-4F, 12F, 6F);
			this.Icicle6B.setTextureSize(128, 64);
			this.Icicle6B.mirror = true;
			this.setRotation(this.Icicle6B, 0F, 0F, 0F);
			this.Icicle6C = new ModelRenderer(model, 118, 50);
			this.Icicle6C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle6C.setRotationPoint(-4F, 13F, 6F);
			this.Icicle6C.setTextureSize(128, 64);
			this.Icicle6C.mirror = true;
			this.setRotation(this.Icicle6C, 0F, 0F, 0F);
			this.Icicle7A = new ModelRenderer(model, 104, 59);
			this.Icicle7A.addBox(0F, 0F, 0F, 1, 1, 4);
			this.Icicle7A.setRotationPoint(-7F, 11F, 1F);
			this.Icicle7A.setTextureSize(128, 64);
			this.Icicle7A.mirror = true;
			this.setRotation(this.Icicle7A, 0F, 0F, 0F);
			this.Icicle7B = new ModelRenderer(model, 114, 46);
			this.Icicle7B.addBox(0F, 0F, 0F, 1, 2, 2);
			this.Icicle7B.setRotationPoint(-7F, 12F, 2F);
			this.Icicle7B.setTextureSize(128, 64);
			this.Icicle7B.mirror = true;
			this.setRotation(this.Icicle7B, 0F, 0F, 0F);
			this.Icicle7C = new ModelRenderer(model, 116, 44);
			this.Icicle7C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle7C.setRotationPoint(-7F, 14F, 2F);
			this.Icicle7C.setTextureSize(128, 64);
			this.Icicle7C.mirror = true;
			this.setRotation(this.Icicle7C, 0F, 0F, 0F);
			this.Icicle8A = new ModelRenderer(model, 104, 54);
			this.Icicle8A.addBox(0F, 0F, 0F, 1, 1, 4);
			this.Icicle8A.setRotationPoint(-7F, 11F, -5F);
			this.Icicle8A.setTextureSize(128, 64);
			this.Icicle8A.mirror = true;
			this.setRotation(this.Icicle8A, 0F, 0F, 0F);
			this.Icicle8B = new ModelRenderer(model, 106, 50);
			this.Icicle8B.addBox(0F, 0F, 0F, 1, 1, 3);
			this.Icicle8B.setRotationPoint(-7F, 12F, -4F);
			this.Icicle8B.setTextureSize(128, 64);
			this.Icicle8B.mirror = true;
			this.setRotation(this.Icicle8B, 0F, 0F, 0F);
			this.Icicle8C = new ModelRenderer(model, 108, 46);
			this.Icicle8C.addBox(0F, 0F, 0F, 1, 2, 2);
			this.Icicle8C.setRotationPoint(-7F, 13F, -4F);
			this.Icicle8C.setTextureSize(128, 64);
			this.Icicle8C.mirror = true;
			this.setRotation(this.Icicle8C, 0F, 0F, 0F);
			this.Icicle8D = new ModelRenderer(model, 112, 44);
			this.Icicle8D.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle8D.setRotationPoint(-7F, 15F, -3F);
			this.Icicle8D.setTextureSize(128, 64);
			this.Icicle8D.mirror = true;
			this.setRotation(this.Icicle8D, 0F, 0F, 0F);

			this.Icicle9A = new ModelRenderer(model, 122, 38);
			this.Icicle9A.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle9A.setRotationPoint(3F, 11F, -7F);
			this.Icicle9A.setTextureSize(128, 64);
			this.Icicle9A.mirror = true;
			this.setRotation(this.Icicle9A, 0F, 0F, 0F);
			this.Icicle9B = new ModelRenderer(model, 124, 36);
			this.Icicle9B.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle9B.setRotationPoint(4F, 12F, -7F);
			this.Icicle9B.setTextureSize(128, 64);
			this.Icicle9B.mirror = true;
			this.setRotation(this.Icicle9B, 0F, 0F, 0F);
			this.Icicle10A = new ModelRenderer(model, 114, 61);
			this.Icicle10A.addBox(0F, 0F, 0F, 3, 2, 1);
			this.Icicle10A.setRotationPoint(-1F, 11F, -7F);
			this.Icicle10A.setTextureSize(128, 64);
			this.Icicle10A.mirror = true;
			this.setRotation(this.Icicle10A, 0F, 0F, 0F);
			this.Icicle10B = new ModelRenderer(model, 116, 59);
			this.Icicle10B.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle10B.setRotationPoint(-1F, 13F, -7F);
			this.Icicle10B.setTextureSize(128, 64);
			this.Icicle10B.mirror = true;
			this.setRotation(this.Icicle10B, 0F, 0F, 0F);
			this.Icicle10C = new ModelRenderer(model, 120, 56);
			this.Icicle10C.addBox(0F, 0F, 0F, 1, 2, 1);
			this.Icicle10C.setRotationPoint(0F, 14F, -7F);
			this.Icicle10C.setTextureSize(128, 64);
			this.Icicle10C.mirror = true;
			this.setRotation(this.Icicle10C, 0F, 0F, 0F);
			this.Icicle11A = new ModelRenderer(model, 114, 54);
			this.Icicle11A.addBox(0F, 0F, 0F, 4, 1, 1);
			this.Icicle11A.setRotationPoint(-5F, 11F, -7F);
			this.Icicle11A.setTextureSize(128, 64);
			this.Icicle11A.mirror = true;
			this.setRotation(this.Icicle11A, 0F, 0F, 0F);
			this.Icicle11B = new ModelRenderer(model, 116, 52);
			this.Icicle11B.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle11B.setRotationPoint(-4F, 12F, -7F);
			this.Icicle11B.setTextureSize(128, 64);
			this.Icicle11B.mirror = true;
			this.setRotation(this.Icicle11B, 0F, 0F, 0F);
			this.Icicle11C = new ModelRenderer(model, 118, 50);
			this.Icicle11C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle11C.setRotationPoint(-4F, 13F, -7F);
			this.Icicle11C.setTextureSize(128, 64);
			this.Icicle11C.mirror = true;
			this.setRotation(this.Icicle11C, 0F, 0F, 0F);
		}
	}

	public void render(float f)
	{
		if (CraftingPillars.winter)
		{
			this.Icicle1A.render(f);
			this.Icicle1B.render(f);
			this.Icicle1C.render(f);
			this.Icicle2A.render(f);
			this.Icicle2B.render(f);
			this.Icicle2C.render(f);
			this.Icicle3A.render(f);
			this.Icicle3B.render(f);
			this.Icicle4A.render(f);
			this.Icicle4B.render(f);
			this.Icicle5A.render(f);
			this.Icicle5B.render(f);
			this.Icicle5C.render(f);
			this.Icicle6A.render(f);
			this.Icicle6B.render(f);
			this.Icicle6C.render(f);
			this.Icicle7A.render(f);
			this.Icicle7B.render(f);
			this.Icicle7C.render(f);
			this.Icicle8A.render(f);
			this.Icicle8B.render(f);
			this.Icicle8C.render(f);
			this.Icicle8D.render(f);

			this.Icicle9A.render(f);
			this.Icicle9B.render(f);
			this.Icicle10A.render(f);
			this.Icicle10B.render(f);
			this.Icicle10C.render(f);
			this.Icicle11A.render(f);
			this.Icicle11B.render(f);
			this.Icicle11C.render(f);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{

		glPushMatrix();
		TileEntityTrashPillar workTile = (TileEntityTrashPillar) tile;

		glTranslated(x, y + 1.5D, z);
		if (workTile.isOpen)
			renderEndPortal(0, -1.5F, 0);

		glTranslated(0.5D, 0, 0.5D);
		glRotatef(90F * (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);
		glRotatef(180F, 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_TRASHPILLAR);
		this.render(0.0625F);

		glPopMatrix();

		glPushMatrix();
		glTranslated(x + 0.5F, y, z + 0.5F);
		float scale = 0.0255F;
		glScalef(scale, scale, scale);
		// glDisable(GL_LIGHTING);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_TRASHPILLAR);
		this.trash.renderPart("TrashPillar");
		glRotatef(90F * (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

		if (workTile.isOpen)
			this.trash.renderPart("DoorsOpen");
		else
			this.trash.renderPart("DoorsClosed");

		// glEnable(GL_LIGHTING);
		glPopMatrix();
		
		if (CraftingPillars.easter)
		{
			glPushMatrix();
			glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
			glRotatef(-90F * (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			glRotatef(180F, 1F, 0F, 0F);

			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
			glPopMatrix();
		}
	}

	private static final ResourceLocation enderPortalEndSkyTextures = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation endPortalTextures = new ResourceLocation("textures/entity/end_portal.png");
	private static final Random field_110644_e = new Random(31100L);
	FloatBuffer field_76908_a = GLAllocation.createDirectFloatBuffer(16);

	/**
	 * Renders the End Portal.
	 */
	private void renderEndPortal(double x, double y, double z)
	{
		float f1 = (float) this.tileEntityRenderer.playerX;
		float f2 = (float) this.tileEntityRenderer.playerY;
		float f3 = (float) this.tileEntityRenderer.playerZ;
		GL11.glDisable(GL11.GL_LIGHTING);
		field_110644_e.setSeed(31100L);
		float f4 = 11F / 16F;

		for (int i = 0; i < 16; ++i)
		{
			GL11.glPushMatrix();
			float f5 = (float) (16 - i);
			float f6 = 0.0625F;
			float f7 = 1.0F / (f5 + 1.0F);

			if (i == 0)
			{
				this.bindTexture(enderPortalEndSkyTextures);
				f7 = 0.1F;
				f5 = 65.0F;
				f6 = 0.125F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}

			if (i == 1)
			{
				this.bindTexture(endPortalTextures);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				f6 = 0.5F;
			}

			float f8 = (float) (-(y + (double) f4));
			float f9 = f8 + ActiveRenderInfo.objectY;
			float f10 = f8 + f5 + ActiveRenderInfo.objectY;
			float f11 = f9 / f10;
			f11 += (float) (y + (double) f4);
			GL11.glTranslatef(f1, f11, f3);
			GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
			GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, this.func_76907_a(1.0F, 0.0F, 0.0F, 0.0F));
			GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, this.func_76907_a(0.0F, 0.0F, 1.0F, 0.0F));
			GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, this.func_76907_a(0.0F, 0.0F, 0.0F, 1.0F));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_76907_a(0.0F, 1.0F, 0.0F, 0.0F));
			GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, (float) (Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
			GL11.glScalef(f6, f6, f6);
			GL11.glTranslatef(0.5F, 0.5F, 0.0F);
			GL11.glRotatef((float) (i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
			GL11.glTranslatef(-f1, -f3, -f2);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
			glBegin(GL_QUADS);
			f11 = field_110644_e.nextFloat() * 0.5F + 0.1F;
			float f12 = field_110644_e.nextFloat() * 0.5F + 0.4F;
			float f13 = field_110644_e.nextFloat() * 0.5F + 0.5F;

			if (i == 0)
			{
				f13 = 1.0F;
				f12 = 1.0F;
				f11 = 1.0F;
			}

			glColor4f(f11 * f7, f12 * f7, f13 * f7, 1.0F);
			glVertex3d(x + 3 / 16F, y + (double) f4, z + 3 / 16F);
			glVertex3d(x + 3 / 16F, y + (double) f4, z + 1.0D - 3 / 16F);
			glVertex3d(x + 1.0D - 3 / 16F, y + (double) f4, z + 1.0D - 3 / 16F);
			glVertex3d(x + 1.0D - 3 / 16F, y + (double) f4, z + 3 / 16F);

			glEnd();
			glColor4f(1, 1, 1, 1);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);

	}

	private FloatBuffer func_76907_a(float par1, float par2, float par3, float par4)
	{
		this.field_76908_a.clear();
		this.field_76908_a.put(par1).put(par2).put(par3).put(par4);
		this.field_76908_a.flip();
		return this.field_76908_a;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_TRASHPILLAR);
		this.render(0.0625F);
		glRotatef(180F, 1F, 0F, 0F);

		glTranslated(0, -1.5F, 0);
		float scale = 0.0255F;
		glScalef(scale, scale, scale);

		this.trash.renderPart("TrashPillar");
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return CraftingPillars.trashPillarRenderID;
	}
}
