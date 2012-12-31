package ch.kg9dh.main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ERB extends JavaPlugin{
	
	/** //////////TODO:////////////////////////
	 *  - use more than 1 class
	 *  - make it generally more clear (what does what)
	 *  - add config
	 */ 
	
	public Player rapper_1;
	public Player rapper_2;
	
	public int max;
	public int vote_1;
	public int vote_2;
	
	public int taskID;

	public boolean vote;
	public boolean rap;
	public boolean enabled;
	
	public ItemStack priceitem;
	public ItemStack tie_prize;
	
	public List<String>blacklist;
	public List<Player>hasvoted;
	
	public RAP RAPexecutor;
	public VOTE VOTEexecutor;
	
	@Override
	public void onEnable() {		
		loadConfig();
		
		blacklist = this.getConfig().getStringList("blacklist");		
		max = 0;
		vote_1 = 0;
		vote_2 = 0;
		rapper_1 = null;
		rapper_2 = null;
		vote = false;
		rap = false;
		enabled = true;
		priceitem = new ItemStack(Material.getMaterial(this.getConfig().getInt("price.item")), this.getConfig().getInt("price.ammount"));
		tie_prize = new ItemStack(Material.getMaterial(this.getConfig().getInt("price.tie.item")), this.getConfig().getInt("price.tie.ammount"));
		
		RAPexecutor = new RAP(this);
		getCommand("rap").setExecutor(RAPexecutor);
		
		////NOT IN USE ATM/////////////////
		//VOTEexecutor = new VOTE(this);
		//getCommand("vote").setExecutor(VOTEexecutor);
		
		this.getServer().getPluginManager().registerEvents(new RAPListener(this), this);
	}
	
 	@Override
    public void onDisable() {
		Bukkit.getScheduler().cancelTask(ERB.this.taskID);	 	
		blacklist.clear();
		hasvoted.clear();
	}
 	
 	@Override
 	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
 		
 		Player p = (Player) sender;
 		
 		if(cmd.getName().equalsIgnoreCase("erb")){
 			
 			if(args.length==0){
 				p.sendMessage(ChatColor.DARK_RED+"[ERB HELP PAGE]");
 				p.sendMessage(ChatColor.DARK_RED+"[/erb join] "+ ChatColor.BLUE + "Join as a rapper.");
 				p.sendMessage(ChatColor.DARK_RED+"[/erb vote <player>] "+ ChatColor.BLUE + "Vote for a rapper.");
 				p.sendMessage(ChatColor.DARK_RED+"[/erb forceend] "+ ChatColor.BLUE + "Forceend the plugin.");
 				p.sendMessage(ChatColor.DARK_RED+"[/erb forcestart] "+ ChatColor.BLUE + "Forcestart the plugin.");
 				p.sendMessage(ChatColor.DARK_RED+"[/erb reload] "+ ChatColor.BLUE + "Reload the plugin.");
 				p.sendMessage(ChatColor.DARK_RED+"[/erb <on/off>] "+ ChatColor.BLUE + "Decide if a battle can be started or not.");
 				p.sendMessage(ChatColor.DARK_RED+"[/rap] "+ ChatColor.BLUE + "Rap your text.");
 				return true;
 			}
 			
 			if(args[0].equalsIgnoreCase("join")){
 				if(p.hasPermission("erb.join")){
 					if(enabled==false){
 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"The plugin is currently not enabled!");
 						return false;
 					}
 					
 					if(max==0){
 						max++;
 						rapper_1 = p;
 						hasvoted.add(p);
 						Bukkit.broadcastMessage(ChatColor.DARK_RED+"[ERB]"+ ChatColor.BLUE+"The first rapper is: "+p.getDisplayName().toString());
 					}else if(max==1){
 						max++;
 						if(!p.toString().equalsIgnoreCase(rapper_1.toString())){ //TODO: make a not
 							rapper_2 = p;
 							hasvoted.add(p);
 							Bukkit.broadcastMessage(ChatColor.DARK_RED+"[ERB]"+ ChatColor.BLUE+"The second rapper is: "+p.getDisplayName().toString());
 							getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.RED + "Prepare for rapping!");
 						}else{
 							p.sendMessage(ChatColor.DARK_RED+"[ERB]"+ ChatColor.RED + "You can't be rapper one and rapper two!");
 							return false;
 						}
 						
 						int countdown = 5;
 							
	 						taskID = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	 							int countdown = 35;
	 						    @Override 
	 						    public void run() {
	 						    	if(countdown != -1){ 						    		
	 						    	if(countdown >= 30){
		 						        getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ (countdown-30));
	 						    	}else if(countdown == 29){
	 						    		getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.BLUE + "Start rapping!");
	 						    		rap=true;	
	 						    	}else if(countdown == 9){
	 						    		getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.RED + "Rapping stopped!");
	 						    		rap = false;
	 						    		vote = true;
	 						    		getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.BLUE + "Start Voting!");
	 						    	}else if(countdown==0){
	 						    		getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.RED + "Voting has stopped!");
	 						    		if(vote_1==vote_2){
	 						    			getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.BLUE + "It's a tie!");
	 						    			rapper_1.getInventory().addItem(tie_prize);
	 						    			rapper_2.getInventory().addItem(tie_prize);
	 						    		}else if(vote_1>vote_2){
	 						    			getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.BLUE + rapper_1.getDisplayName().toString() + " won the battle!");
	 						    			rapper_1.getInventory().addItem(priceitem);
	 						    		}else if(vote_1<vote_2){
	 						    			getServer().broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ ChatColor.BLUE + rapper_2.getDisplayName().toString() + " won the battle!");
	 						    			rapper_2.getInventory().addItem(priceitem);
	 						    		}
	 						    	}
	 						    	countdown--;
	 						    	}else{	
	 						    		Bukkit.getScheduler().cancelTask(ERB.this.taskID);	 	
	 						    		System.out.println("[ERB] TASK CANCELED!");
	 						    		simpleReload();
	 						    	}	 						    	
	 						    }
	 						}, 100L, 20L);	 						
 						
 					}else if(max>1){
 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Sorry there are already 2 Rappers!");
 					}
 				}else{
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
 				} 				
 			}else if(args[0].equalsIgnoreCase("vote")){
 				if(p.hasPermission("erb.vote")){
 					if(vote==true){
 						if(hasvoted.contains(p)){
 							p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You can't vote anymore!");
 							return true;
 						}
		 					if(args[1].equalsIgnoreCase(rapper_1.getDisplayName().toString())){
		 						vote_1++;
		 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.BLUE + "You voted for: " + rapper_1.getDisplayName().toString());
		 					}else if(args[1].equalsIgnoreCase(rapper_2.getDisplayName().toString())){
		 						vote_2++;
		 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.BLUE + "You voted for: " + rapper_2.getDisplayName().toString());
		 					}else{
		 						p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You missspelled the name!");
		 					}
 					}else{
 		 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You can't vote at the moment!");
 					}
 				}else{
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
 				}
 			}else if(args[0].equalsIgnoreCase("forceend")){
 				if(p.hasPermission("erb.forceend")){
 					Bukkit.getScheduler().cancelTask(ERB.this.taskID);
 					Bukkit.broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Everything got canceled!");
 				}else{
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
 				} 	
 			}else if(args[0].equalsIgnoreCase("forcestart")){
 				if(p.hasPermission("erb.forcestart")){
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Sorry this command is not avaiable yet!");
 				}else{
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
 				} 	
 			}else if(args[0].equalsIgnoreCase("reload")){
 				if(p.hasPermission("erb.reload")){
 					onDisable();
 					onEnable();
 				}else{
 					p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"You do not have permission for this command!");
 				} 
 			}else if(args[0].equalsIgnoreCase("on")){
 				Bukkit.broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Has been enabled!");
 			}else if(args[0].equalsIgnoreCase("off")){
 				Bukkit.broadcastMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Has been disabled!");
 			}else{
 				p.sendMessage(ChatColor.DARK_RED+"[ERB] "+ChatColor.RED+"Command usage: /erb <argument>");
 			}
 			 			
 		}		
 		
 		return false;
 	}
 		
	private void loadConfig() {
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		this.saveConfig();		
	}
 	
	private void simpleReload(){
		max = 0;
		vote_1 = 0;
		vote_2 = 0;
		rapper_1 = null;
		rapper_2 = null;
		vote = false;
		rap = false;
		enabled = true;
		hasvoted.clear();
	}
	
}
