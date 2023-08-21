package rs.onako2.ontopiaij.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SocialInteractionsScreen.class)
public abstract class Tag extends SocialInteractionsScreen {

    protected Tag(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "initWidgetsNormal")
    private void addCustomButton(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("custombutton.ontopia.joinontopia"), button -> {
            ServerInfo selectedEntry = new ServerInfo("Get bonus", "minecraft.de.majic.rs", false);
            ConnectScreen.connect(this, MinecraftClient.getInstance(), new ServerAddress("minecraft.de.majic.rs", 25565), selectedEntry);
        }).dimensions(this.width / 2 - 100 + 205, y + 14, 80, 20).build());
    }

}