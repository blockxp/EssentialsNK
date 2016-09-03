package cn.yescallop.essentialsnk.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsNK;
import cn.yescallop.essentialsnk.command.CommandBase;

public class TPAllCommand extends CommandBase {

    public TPAllCommand(EssentialsNK plugin) {
        super("tpall", plugin);
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        Player player;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(TextFormat.RED + lang.translateString("commands.generic.ingame"));
                return false;
            }
            player = (Player) sender;
        } else if (args.length == 1) {
            player = plugin.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(TextFormat.RED + lang.translateString("commands.generic.player.notFound", args[0]));
                return false;
            }
        } else {
            this.sendUsage(sender);
            return false;
        }
        for (Player p : plugin.getServer().getOnlinePlayers().values()) {
            if (p != player) {
                p.teleport(player);
                p.sendMessage(lang.translateString("commands.tpall.other"));
            }
        }
        player.sendMessage(lang.translateString("commands.tpall.success"));
        return true;
    }
}
