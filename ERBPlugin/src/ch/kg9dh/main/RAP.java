package ch.kg9dh.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RAP implements CommandExecutor {
	
	public ERB plugin;
	
	public RAP(ERB instance) {
        plugin = instance;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("rap")){
			if(p.hasPermission("erb.rap")){
					if((plugin.rap==true)&&(p.equals(plugin.rapper_1)||p.equals(plugin.rapper_2))){
						if(p.equals(plugin.rapper_1)&&plugin.session_1==false){
							p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You can't rap at the moment!");
							return true;
						}else if(p.equals(plugin.rapper_2)&&plugin.session_2==false){
							p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You can't rap at the moment!");
							return true;
						}
												
						String message = "";
						for(int x=0; x<args.length; x++){
							message = message+args[x]+" ";
						}
						Bukkit.broadcastMessage(ChatColor.DARK_RED+"[ERB/"+p.getDisplayName().toString()+"] "+ChatColor.BLUE+message);
						message = "";
					}else{
	 				p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You can't rap at the moment!");
				}
			}else{
					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
				} 	
		}
		
		return false;
	}

}
