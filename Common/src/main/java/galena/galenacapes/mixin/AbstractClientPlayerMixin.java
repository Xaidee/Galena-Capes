package galena.galenacapes.mixin;

import galena.galenacapes.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Objects;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {
    @Shadow @Nullable
    protected abstract PlayerInfo getPlayerInfo();

    @Inject(method= "getCloakTextureLocation()Lnet/minecraft/resources/ResourceLocation;", at=@At("RETURN"), cancellable = true)

    public void getCloakTextureLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        assert Minecraft.getInstance().player != null;
        if(!(Objects.requireNonNull(this.getPlayerInfo()).getProfile().getId().equals(Minecraft.getInstance().player.getUUID()))) {
            cir.setReturnValue(null);
        } else {
            cir.setReturnValue(new ResourceLocation(Constants.MOD_ID, "textures/capes/dev.png"));
        }
    }
}
