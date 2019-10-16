package cn.nukkit;

import cn.nukkit.utils.TextFormat;

import java.util.HashMap;

/**
 * Created by CreeperFace on 9. 11. 2016. Edited by Marci0
 */
public class Achievement {

    public static final HashMap<String, Achievement> achievements = new HashMap<String, Achievement>() {
        {
            put("mineWood", new Achievement("Looks like he wants to destroy the eco-environement!"));
            put("buildWorkBench", new Achievement("Time to craft!", "mineWood"));
            put("buildPickaxe", new Achievement("It's dangerous to go alone", "buildWorkBench"));
            put("buildFurnace", new Achievement("Hot hot hot hot hot!", "buildPickaxe"));
            put("acquireIron", new Achievement("Ironification begun!", "buildFurnace"));
            put("buildHoe", new Achievement("Ecology", "buildWorkBench"));
            put("makeBread", new Achievement("Baguettes", "buildHoe"));
            put("bakeCake", new Achievement("The Lie", "buildHoe"));
            put("buildBetterPickaxe", new Achievement("Yea Better", "buildPickaxe"));
            put("buildSword", new Achievement("Time to rekt some people", "buildWorkBench"));
            put("diamonds", new Achievement("Yeaaaaaaah Diamondsssssssss!", "acquireIron"));
        }
    };

    public static boolean broadcast(Player player, String achievementId) {
        if (!achievements.containsKey(achievementId)) {
            return false;
        }
        String translation = Server.getInstance().getLanguage().translateString("chat.type.achievement", player.getDisplayName(), TextFormat.GREEN + achievements.get(achievementId).getMessage() + TextFormat.RESET);

        if (Server.getInstance().getPropertyBoolean("announce-player-achievements", true)) {
            Server.getInstance().broadcastMessage(translation);
        } else {
            player.sendMessage(translation);
        }
        return true;
    }

    public static boolean add(String name, Achievement achievement) {
        if (achievements.containsKey(name)) {
            return false;
        }

        achievements.put(name, achievement);
        return true;
    }

    public final String message;
    public final String[] requires;

    public Achievement(String message, String... requires) {
        this.message = message;
        this.requires = requires;
    }

    public String getMessage() {
        return message;
    }

    public void broadcast(Player player) {
        String translation = Server.getInstance().getLanguage().translateString("chat.type.achievement", player.getDisplayName(), TextFormat.GREEN + this.getMessage(), null);

        if (Server.getInstance().getPropertyBoolean("announce-player-achievements", true)) {
            Server.getInstance().broadcastMessage(translation);
        } else {
            player.sendMessage(translation);
        }
    }
}
