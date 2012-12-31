/*THIS CLASS IS NOT IN USE AT THE MOMENT
 * I CREATED IT TO MAKE A BETTER OVERVIEW BUT I AM STILL WORKING ON THAT 
 * */
package ch.kg9dh.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VOTE implements CommandExecutor{

	public ERB plugin;
	
	public VOTE(ERB instance) {
        plugin = instance;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("vote")){
							
 				if(p.hasPermission("erb.vote")){
 					if(plugin.vote==true){
	 					if(args[1].equalsIgnoreCase(plugin.rapper_1.getDisplayName().toString())){
	 						plugin.vote_1++;
	 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.BLUE + "You voted for: " + plugin.rapper_1.getDisplayName().toString());
	 					}else if(args[1].equalsIgnoreCase(plugin.rapper_2.getDisplayName().toString())){
	 						plugin.vote_2++;
	 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.BLUE + "You voted for: " + plugin.rapper_2.getDisplayName().toString());
	 					}else{
	 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You missspelled the name!");
	 					}
 					}else{
 		 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You can't vote at the moment!");
 					}
 				}else{
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
 				}			
			return true;
		}
		return false;
	}
	
}
