package dmz.faction.icefeeling.entities.chargedtnt;

import com.mojang.blaze3d.matrix.MatrixStack;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TNTMinecartRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class IFChargedTNTRenderer extends EntityRenderer<IFChargedTNTEntity> {
	
	private static final ResourceLocation CHARGED_TNT_TEXTURE = new ResourceLocation(Main.MOD_ID + "block/charged_tnt");
	

    public IFChargedTNTRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(IFChargedTNTEntity entity) {
        System.out.println(CHARGED_TNT_TEXTURE.toString());

        return CHARGED_TNT_TEXTURE;

    }

    @Override
    public void render(IFChargedTNTEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) 
    {
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        if ((float)entity.getFuse() - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)entity.getFuse() - partialTicks + 1.0F) / 10.0F;
            f = MathHelper.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            float f1 = 1.0F + f * 0.3F;
            matrixStackIn.scale(f1, f1, f1);
        }

        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStackIn.translate(-0.5D, -0.5D, 0.5D);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F));
        TNTMinecartRenderer.renderTntFlash(IFBlocks.CHARGED_TNT.get().getDefaultState(), matrixStackIn, bufferIn, packedLightIn, entity.getFuse() / 5 % 2 == 0);
        matrixStackIn.pop();
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
   
    
}