package ch.kg9dh.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RAPListener implements Listener{

	public ERB plugin;
	
	public RAPListener(ERB instance) {
        plugin = instance;
    }
	
	 @EventHandler
	 public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
    	
		 if(event.getMessage().startsWith("/rap")){  
			 String stars = "";
			 for(String s : plugin.blacklist){			 		
				 if(event.getMessage().contains(s)){
					 for(int x=0; x<=s.length(); x++){
						 stars = stars+"*";
					 }
					 String newmessage = event.getMessage().replace(s, stars);
					 if(newmessage.length()>8){
						 event.setMessage(newmessage);
					 }else{
						 event.setCancelled(true);
						 event.getPlayer().sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Your rap needs to be atleast 8 characters long!");
					 }
				 }	
				 
			 }   
			 stars = "";
		 }	    	    	
	 }
	
}
