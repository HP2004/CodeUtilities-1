package io.github.codeutilities.features;

import io.github.codeutilities.CodeUtilities;
import io.github.codeutilities.config.Config;
import io.github.codeutilities.event.ReceiveChatEvent;
import io.github.codeutilities.event.system.EventManager;
import io.github.codeutilities.features.sidedchat.ChatRule;
import io.github.codeutilities.loader.Loadable;
import io.github.codeutilities.mixin.MChatHud;
import io.github.codeutilities.util.chat.ChatUtil;
import io.github.codeutilities.util.hypercube.HypercubePrivateMessage;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivateMessageManipulator implements Loadable {
    @Override
    public void load() {
        EventManager.getInstance().register(ReceiveChatEvent.class, (event -> {
            String message = event.getMessage().getString();

            try {
                Pattern pattern = Pattern.compile("^\\[(.+) → You\\] (.+)$");
                Matcher matcher = pattern.matcher(message);

                if (matcher.find()) {
                    // TODO: Fix this from breaking sided chat
                    /*
                    event.setCancelled(true);

                    Text msg = new HypercubePrivateMessage(matcher.group(1), matcher.group(2)).getText();
                    CodeUtilities.MC.player.sendSystemMessage(msg, CodeUtilities.MC.player.getUuid());

                     */
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
}
