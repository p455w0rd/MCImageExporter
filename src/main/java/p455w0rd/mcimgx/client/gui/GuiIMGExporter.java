package p455w0rd.mcimgx.client.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import p455w0rdslib.util.EntityUtils;
import p455w0rdslib.util.GuiUtils;
import p455w0rdslib.util.ImageUtils;
import p455w0rdslib.util.MathUtils;
import p455w0rdslib.util.RenderUtils;

/**
 * @author p455w0rd
 *
 */
public class GuiIMGExporter extends GuiScreen {

	private int xSize = 176, ySize = 166, guiLeft, guiTop, titleOffsetX = 5, titleOffsetY = 5, titleColor = 0xFFDDDDDD,
			rotX = 0, rotY = 0, tmpRotX = -1, tmpRotY = -1;
	private float titleScale = 1.0F;
	ResourceLocation backgroundTexture = new ResourceLocation("textures/gui/demo_background.png");
	Entity displayedEntity = null;
	PositionMode positionMode = PositionMode.STATIC;
	private GuiButton selectedButton;
	private boolean ortho = false;

	public GuiIMGExporter() {
	}

	@Override
	public void initGui() {
		super.initGui();
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		addButton(new GuiButton(0, guiLeft + 5, guiTop + 110, 100, 14, "Save Image"));
		addButton(new GuiButton(1, guiLeft + 5, guiTop + 125, 100, 14, "Orthgraphic View"));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		int i = guiLeft;
		int j = guiTop;
		drawGuiBackgroundLayer(partialTicks, mouseX, mouseY);
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		super.drawScreen(mouseX, mouseY, partialTicks);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate(i, j, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		RenderHelper.disableStandardItemLighting();
		drawGuiForegroundLayer(mouseX, mouseY);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
		RenderHelper.enableStandardItemLighting();
	}

	protected void drawGuiForegroundLayer(int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GuiUtils.drawScaledString(getTitle(), titleOffsetX, titleOffsetY, titleScale, titleColor);
		//drawElements(0, mouseX, mouseY, true);
		GlStateManager.enableBlend();
		GlStateManager.enableLighting();
	}

	protected void drawGuiBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		GuiUtils.bindTexture(backgroundTexture);
		//int x = (width - xSize) / 2;
		//int y = (height - ySize) / 2;
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if (getEntity() != null) {
			//GuiInventory.drawEntityOnScreen(guiLeft + titleOffsetX, guiTop+titleOffsetY+1, 32, guiLeft + titleOffsetX, guiTop+titleOffsetY+1, getEntity());
			if (getPositionMode() == PositionMode.FOLLOW_MOUSE) {
				rotX = guiLeft + titleOffsetX + 50 - mouseX;
				rotY = guiTop + titleOffsetY + 70 - mouseY;
				tmpRotX = guiLeft + titleOffsetX + 50 - mouseX;
				tmpRotY = guiTop + titleOffsetY + 70 - mouseY;
			}
			else {
				if (tmpRotX != -1) {
					rotX = tmpRotX;
					tmpRotX = -1;
				}
				if (tmpRotY != -1) {
					rotY = tmpRotY;
					tmpRotY = -1;
				}
			}
			if (getEntity() instanceof EntityLivingBase) {
				RenderUtils.renderLivingEntity(guiLeft + titleOffsetX + 50, guiTop + titleOffsetY + 90, MathUtils.ceil(getEntity().height) + (70 / MathUtils.ceil(getEntity().height)), rotX, rotY, (EntityLivingBase) getEntity(), !isOrtho());
				if (isOrtho()) {
					//setOrtho(false);
				}
			}
			else {
				RenderUtils.renderEntity(getEntity(), guiLeft + titleOffsetX + 50, guiTop + titleOffsetY + 70, getEntity().height + 32);
			}
		}
		//GlStateManager.pushMatrix();
		//GlStateManager.translate(guiLeft, guiTop, 0.0F);
		//drawElements(partialTicks, x, y, false);
		//GlStateManager.popMatrix();
	}

	private String getTitle() {
		return "MC Image Exporter";
	}

	public GuiIMGExporter setEntity(Entity entity) {
		displayedEntity = EntityUtils.cloneEntity(entity);
		return this;
	}

	public Entity getEntity() {
		return displayedEntity;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (mouseButton == 0) {
			for (int i = 0; i < buttonList.size(); ++i) {
				GuiButton guibutton = buttonList.get(i);

				if (guibutton.mousePressed(mc, mouseX, mouseY)) {
					selectedButton = guibutton;
					guibutton.playPressSound(mc.getSoundHandler());
					actionPerformed(guibutton);
				}
			}
			return;
		}
		if (mouseButton == 1) {
			if (isOrtho()) {
				setOrtho(false);
			}
			if (getPositionMode() == PositionMode.STATIC) {
				setPositionMode(PositionMode.FOLLOW_MOUSE);
			}
			else {
				setPositionMode(PositionMode.STATIC);
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			if (getEntity() != null) {
				ImageUtils.saveEntity(getEntity(), rotX, rotY, !isOrtho());
			}
			break;
		case 1:
			if (getEntity() != null) {

				if (getPositionMode() != PositionMode.STATIC) {
					setPositionMode(PositionMode.STATIC);
				}
				setOrtho(true);
				rotX = -45;
				rotY = -45;
			}
		}
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {

	}

	public PositionMode getPositionMode() {
		return positionMode;
	}

	public void setPositionMode(PositionMode mode) {
		positionMode = mode;
	}

	public boolean isOrtho() {
		return ortho;
	}

	public void setOrtho(boolean isOrtho) {
		ortho = isOrtho;
	}

	public static enum PositionMode {
			STATIC, FOLLOW_MOUSE
	}

}
