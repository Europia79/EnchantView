package thebombzen.mods.enchantview.client;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.Project;

import thebombzen.mods.enchantview.Configuration;
import thebombzen.mods.enchantview.EnchantView;
import thebombzen.mods.thebombzenapi.ThebombzenAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EVGuiEnchantment extends GuiEnchantment {

	public static final ResourceLocation enchantingTableGuiTextures = ThebombzenAPI
			.getPrivateField(null, GuiEnchantment.class, new String[] {
					"enchantingTableGuiTextures", "field_147078_C", "C" });
	public static final ResourceLocation enchantingTableBookTextures = ThebombzenAPI
			.getPrivateField(null, GuiEnchantment.class, new String[] {
					"enchantingTableBookTextures", "field_147070_D", "D" });

	public static final ModelBook bookModel = ThebombzenAPI.getPrivateField(
			null, GuiEnchantment.class, new String[] { "bookModel",
					"field_147072_E", "E" });

	public static EVGuiEnchantment instance;
	public static int prevLevelsHashCode = 0;
	private int drawMe = -1;
	public final Minecraft mc = Minecraft.getMinecraft();

	public ContainerEnchantment containerEnchantment;

	public EVGuiEnchantment(GuiEnchantment parent) {
		super(null, null, 0, 0, 0, (String)ThebombzenAPI.getPrivateField(parent, GuiEnchantment.class, "field_147079_H", "H"));
		this.containerEnchantment = ThebombzenAPI.getPrivateField(parent, GuiEnchantment.class, "containerEnchantment", "field_147075_G", "G");
		ThebombzenAPI.setPrivateField(this, GuiEnchantment.class, this.containerEnchantment, "containerEnchantment", "field_147075_G", "G");
		this.inventorySlots = this.containerEnchantment;
		instance = this;
		
		boolean shouldAsk = EnchantView.instance.getConfiguration().getSingleMultiProperty(Configuration.ENABLE);
		if (shouldAsk) {
			EnchantView.instance.askingIfEnchantViewExists = true;
			mc.getNetHandler().addToSendQueue(
					new C01PacketChatMessage("/doesenchantviewexist"));
		} else {
			EnchantView.instance.enchantViewExists = false;
		}
	}

	@Override
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	public void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(enchantingTableGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		ScaledResolution scaledresolution = new ScaledResolution(
				mc, mc.displayWidth, mc.displayHeight);
		GL11.glViewport((scaledresolution.getScaledWidth() - 320) / 2
				* scaledresolution.getScaleFactor(),
				(scaledresolution.getScaledHeight() - 240) / 2
						* scaledresolution.getScaleFactor(),
				320 * scaledresolution.getScaleFactor(),
				240 * scaledresolution.getScaleFactor());
		GL11.glTranslatef(-0.34F, 0.23F, 0.0F);
		Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
		float f1 = 1.0F;
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		RenderHelper.enableStandardItemLighting();
		GL11.glTranslatef(0.0F, 3.3F, -16.0F);
		GL11.glScalef(f1, f1, f1);
		float f2 = 5.0F;
		GL11.glScalef(f2, f2, f2);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		mc.getTextureManager().bindTexture(enchantingTableBookTextures);
		GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
		float f3 = this.field_147076_A
				+ (this.field_147080_z - this.field_147076_A) * par1;
		GL11.glTranslatef((1.0F - f3) * 0.2F, (1.0F - f3) * 0.1F,
				(1.0F - f3) * 0.25F);
		GL11.glRotatef(-(1.0F - f3) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		float f4 = this.field_147069_w
				+ (this.field_147071_v - this.field_147069_w) * par1 + 0.25F;
		float f5 = this.field_147069_w
				+ (this.field_147071_v - this.field_147069_w) * par1 + 0.75F;
		f4 = (f4 - MathHelper.truncateDoubleToInt(f4)) * 1.6F - 0.3F;
		f5 = (f5 - MathHelper.truncateDoubleToInt(f5)) * 1.6F - 0.3F;

		if (f4 < 0.0F) {
			f4 = 0.0F;
		}

		if (f5 < 0.0F) {
			f5 = 0.0F;
		}

		if (f4 > 1.0F) {
			f4 = 1.0F;
		}

		if (f5 > 1.0F) {
			f5 = 1.0F;
		}

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		bookModel.render((Entity) null, 0.0F, f4, f5, f3, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		EnchantmentNameParts.instance
				.reseedRandomGenerator(this.containerEnchantment.nameSeed);

		if (EnchantView.instance.enchantViewExists
				&& containerEnchantment.enchantLevels[0] != 0
				&& containerEnchantment.tableInventory.getStackInSlot(0) != null) {
			if (!EnchantView.instance.askingForEnchantments
					&& (EnchantView.instance.newItemStacks[0] == null || prevLevelsHashCode != Arrays
							.hashCode(containerEnchantment.enchantLevels))) {
				EnchantView.instance
						.requestEnchantmentListFromServer(this.containerEnchantment.windowId);
			}
		} else {
			Arrays.fill(EnchantView.instance.newItemStacks, null);
		}

		prevLevelsHashCode = Arrays
				.hashCode(containerEnchantment.enchantLevels);
		drawMe = -1;

		for (int i1 = 0; i1 < 3; ++i1) {
			String s = EnchantmentNameParts.instance.generateNewRandomName();
			this.zLevel = 0.0F;
			mc.getTextureManager().bindTexture(enchantingTableGuiTextures);
			int j1 = this.containerEnchantment.enchantLevels[i1];
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			if (j1 == 0) {
				this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0, 185,
						108, 19);
			} else {
				String s1 = "" + j1;
				FontRenderer fontrenderer = this.mc.standardGalacticFontRenderer;
				int k1 = 6839882;

				if (this.mc.thePlayer.experienceLevel < j1
						&& !this.mc.thePlayer.capabilities.isCreativeMode) {
					this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0,
							185, 108, 19);
					fontrenderer.drawSplitString(s, k + 62, l + 16 + 19 * i1,
							104, (k1 & 16711422) >> 1);
					fontrenderer = this.mc.fontRenderer;
					k1 = 4226832;
					fontrenderer.drawStringWithShadow(s1, k + 62 + 104
							- fontrenderer.getStringWidth(s1), l + 16 + 19 * i1
							+ 7, k1);
				} else {
					int l1 = par2 - (k + 60);
					int i2 = par3 - (l + 14 + 19 * i1);

					if (l1 >= 0 && i2 >= 0 && l1 < 108 && i2 < 19) {
						this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0,
								204, 108, 19);
						k1 = 16777088;
						drawMe = i1;
					} else {
						this.drawTexturedModalRect(k + 60, l + 14 + 19 * i1, 0,
								166, 108, 19);
					}

					fontrenderer.drawSplitString(s, k + 62, l + 16 + 19 * i1,
							104, k1);
					fontrenderer = this.mc.fontRenderer;
					k1 = 8453920;
					fontrenderer.drawStringWithShadow(s1, k + 62 + 104
							- fontrenderer.getStringWidth(s1), l + 16 + 19 * i1
							+ 7, k1);
				}
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		if (drawMe != -1
				&& EnchantView.instance.enchantViewExists
				&& containerEnchantment.tableInventory.getStackInSlot(0) != null
				&& EnchantView.instance.newItemStacks[drawMe] != null) {
			this.renderToolTip(EnchantView.instance.newItemStacks[drawMe],
					par1 + 8, par2 + 8);
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		int l = (this.width - this.xSize) / 2;
		int i1 = (this.height - this.ySize) / 2;

		for (int j1 = 0; j1 < 3; ++j1) {
			int k1 = par1 - (l + 60);
			int l1 = par2 - (i1 + 14 + 19 * j1);

			if (k1 >= 0 && l1 >= 0 && k1 < 108 && l1 < 19
					&& this.containerEnchantment.enchantItem(mc.thePlayer, j1)) {
				if (EnchantView.instance.enchantViewExists) {
					EnchantView.instance.sendAcceptEnchantment(
							this.containerEnchantment.windowId, j1);
				} else {
					mc.playerController.sendEnchantPacket(
							this.containerEnchantment.windowId, j1);
				}
			}
		}
	}

}
