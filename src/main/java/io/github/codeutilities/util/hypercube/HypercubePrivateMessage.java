package io.github.codeutilities.util.hypercube;

import io.github.codeutilities.config.Config;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.Date;
import java.util.List;

public class HypercubePrivateMessage {
    private String user;
    private String message;
    private Date date;

    public HypercubePrivateMessage(String user, String message) {
        this.user = user;
        this.message = message;
        this.date = new Date();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateFormat() {
        long mins = (new Date().getTime() - date.getTime()) / 60000;

        if (mins == 0) {
            return "Just now";
        } else if (mins == 1) {
            return "1min ago";
        } else {
            return mins + "mins ago";
        }
    }

    public MutableText getText() {

        // Message Example
        // TextComponent{text='[', siblings=[], style=Style{ color=#FF7F55, bold=null, italic=null, underlined=null, strikethrough=null, obfuscated=null, clickEvent=null, hoverEvent=null, insertion=null, font=minecraft:default}}
        // TextComponent{text='TechStreet', siblings=[], style=Style{ color=aqua, bold=null, italic=null, underlined=null, strikethrough=null, obfuscated=null, clickEvent=null, hoverEvent=null, insertion=null, font=minecraft:default}}
        // TextComponent{text=' → ', siblings=[], style=Style{ color=#FF7F55, bold=null, italic=null, underlined=null, strikethrough=null, obfuscated=null, clickEvent=null, hoverEvent=null, insertion=null, font=minecraft:default}}
        // TextComponent{text='You', siblings=[], style=Style{ color=#FFD47F, bold=null, italic=null, underlined=null, strikethrough=null, obfuscated=null, clickEvent=null, hoverEvent=null, insertion=null, font=minecraft:default}}
        // TextComponent{text='] ', siblings=[], style=Style{ color=#FF7F55, bold=null, italic=null, underlined=null, strikethrough=null, obfuscated=null, clickEvent=null, hoverEvent=null, insertion=null, font=minecraft:default}}
        // TextComponent{text='e', siblings=[], style=Style{ color=gray, bold=null, italic=null, underlined=null, strikethrough=null, obfuscated=null, clickEvent=null, hoverEvent=null, insertion=null, font=minecraft:default}}

        MutableText text = new LiteralText("[")
                .setStyle(Style.EMPTY
                        .withColor(TextColor.fromRgb(0xFF7F55)));

        MutableText part1 = new LiteralText(getUser())
                .setStyle(Style.EMPTY
                        .withColor(Formatting.AQUA));

        MutableText part2 = new LiteralText(" → ")
                .setStyle(Style.EMPTY
                        .withColor(TextColor.fromRgb(0xFF7F55)));

        MutableText part3 = new LiteralText("You")
                .setStyle(Style.EMPTY
                        .withColor(TextColor.fromRgb(0xFFD47F)));

        MutableText part4 = new LiteralText("] ")
                .setStyle(Style.EMPTY
                        .withColor(TextColor.fromRgb(0xFF7F55)));

        MutableText part5 = new LiteralText(getMessage() + " ")
                .setStyle(Style.EMPTY
                        .withColor(Formatting.GRAY));

        List<MutableText> textList = List.of(text, part1, part2, part3, part4, part5);
        for (MutableText t : textList) {
            t.setStyle(t.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + getUser() + " ")));
            t.setStyle(t.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new LiteralText("Click to reply!")
                            .setStyle(Style.EMPTY
                                    .withColor(Formatting.GRAY)))));

        }

        text.append(part1);
        text.append(part2);
        text.append(part3);
        text.append(part4);
        text.append(part5);

        return text;
    }
}
