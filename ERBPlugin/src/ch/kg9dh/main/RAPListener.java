package ch.kg9dh.main;

import org.bukkit.Bukkit;
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
					 event.setMessage(newmessage);
				 }	
				 
			 }   
			 stars = "";
		 }	    	    	
	 }
	
}
