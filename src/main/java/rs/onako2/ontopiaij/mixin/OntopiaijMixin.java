package rs.onako2.ontopiaij.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;

import java.io.File;
import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class OntopiaijMixin extends Screen {

    protected OntopiaijMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "initWidgetsNormal")
    private void addCustomButton(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("custombutton.ontopia.joinontopia"), button -> {
            ServerInfo selectedEntry = new ServerInfo("Ontopia", "Ontopia.de", false);


            try {
			File myObj = new File("ontopia");
			if (myObj.createNewFile()) {
				ConnectScreen.connect(this, MinecraftClient.getInstance(), new ServerAddress("ontopia.de", 25566), selectedEntry, false);
			} else {
                ConnectScreen.connect(this, MinecraftClient.getInstance(), new ServerAddress("ontopia.de", 25565), selectedEntry, false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        

            ConnectScreen.connect(this, MinecraftClient.getInstance(), new ServerAddress("ontopia.de", 25565), selectedEntry, false);
        }).dimensions(this.width / 2 - 100 + 205, y + 14, 80, 20).build());
    }
}