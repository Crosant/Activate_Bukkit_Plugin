package me.crosant.activate_bukkit_plugin;

import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class activate_bukkit_plugin extends JavaPlugin
{

    
	protected FileConfiguration config;
	Logger log = Logger.getLogger("Minecraft");
	

				
	
	
	public void onDisable() {
		log.info("Activate V0.1 has been disabled.");
	}

	public void onEnable() {
		

			
		
    	config = getConfig();
    	
    	this.getConfig().set("Basic.activated", true);
    	this.getConfig().set("Basic.string", "1234567890");

        //this.getConfig().set("Basic.Permission", "yes");
        this.getConfig().set("Basic.Group", "Player");
    	this.getConfig().set("Messages.nopermission", "you don't have permissions");
    	
    	
    	
    	this.saveConfig();
    	
    	
    	log.info("Activate V0.1 has been enabled.");
    	
    	
    	
	}
        
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	Player player = null;
    	if (sender instanceof Player) {
    		player = (Player) sender;
    	}
     if (player == null) {
    		sender.sendMessage("this command can only be run by a player");
    		} else {
         if(Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
    PermissionManager permissions = PermissionsEx.getPermissionManager();
         if (cmd.getName().equalsIgnoreCase("activate")) {
            if (args.length > 1) {
             if (args[0].equalsIgnoreCase("refresh") && permissions.has(player, "Activate_Bukkit_Plugin.refresh")){
                 
                 
                 String allowedChars ="0123456789abcdefghijklmnopqrstuvwxyz";
                 Random random = new Random();
                 String length1 = args[1];
                 int length = Integer.parseInt(length1);
                 String randomString = generateRandomString(allowedChars, random, length);
                 
                 this.getConfig().set("Basic.string", randomString);
                 player.sendMessage(randomString);
                 this.saveConfig();
             }
             else if(args[0].equalsIgnoreCase(this.getConfig().getString("Basic.string"))){
                 
                 player.sendMessage("You have been Activated");
                 
             }
             
             else if(args[0].equalsIgnoreCase("reload") && permissions.has(player, "Activate_Bukkit_Plugin.reload")){
                 
                 this.reloadConfig();
                 player.sendMessage("Activate Reloaded");
                 
                 
             }
             
             
                   
    		}
            else {
                player.sendMessage("To few Args");
            return false;
            }
         }
         else {
         return false;
         }
         
            
    		return true;
    	}
         else {
   Logger.getLogger("Minecraft").warning("PermissionsEx plugin are not found.");
}
    	return false;
    }
    return false;  }
  


    
    private static String generateRandomString(String allowedChars,
Random random , int length) {
int max = allowedChars.length();
StringBuffer buffer = new StringBuffer();
for (int i=0; i<length; i++) {
int value = random.nextInt(max);
buffer.append(allowedChars.charAt(value));
}
return buffer.toString();
} 
    
    
    }


