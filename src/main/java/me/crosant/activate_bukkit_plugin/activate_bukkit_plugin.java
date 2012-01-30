package me.crosant.activate_bukkit_plugin;

import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionEntity;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.commands.CommandsManager;
import ru.tehkode.permissions.PermissionUser;


public class activate_bukkit_plugin extends JavaPlugin
{
PermissionManager manager = PermissionsEx.getPermissionManager();
    protected CommandsManager commandsManager;
	protected FileConfiguration config;
	public static final Logger log = Logger.getLogger("Minecraft");

				
	
	
	public void onDisable() {
		log.info("Activate V0.1 has been disabled.");
	}

	public void onEnable() {
		

			
		
    	config = getConfig();
    	
    	this.getConfig().set("Basic.activated", true);
    	this.getConfig().set("Basic.string", "1234567890");

        //this.getConfig().set("Basic.Permission", "yes");
        this.getConfig().set("Activate.Group", "not supported jet");
        this.getConfig().set("Activate.Perm", "player.player");
        this.getConfig().set("Activate.World", "*");
        this.getConfig().set("Activate.Prefix", "Activated");
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
        PermissionManager pex = PermissionsEx.getPermissionManager();
        PermissionEntity entity = pex.getUser(player);
             
    PermissionManager permissions = PermissionsEx.getPermissionManager();
         if (cmd.getName().equalsIgnoreCase("activate")) {
            if (args.length > 0) {
                if (args.length > 1){
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
            else if (args[0].equalsIgnoreCase("set") && permissions.has(player, "Activate_Bukkit_Plugin.refresh")){
                 
                 
              
                 
                 this.getConfig().set("Basic.string", args[1]);
                 player.sendMessage("Setted");
                 this.saveConfig();
             }
             else if(args[0].equalsIgnoreCase(this.getConfig().getString("Basic.string"))&& permissions.has(player, "Activate_Bukkit_Plugin.activate")){
                 
                 player.sendMessage("You have been Activated");
                 //ru.tehkode.permissions.bukkit.PermissionsEx.commandsManager.execute(sender, command, args);
                 String[] args1 = new String[5];
                 args1[0] = "user";
                 args1[1] = sender.toString();
                 args1[2] = "group";
                 args1[3] = "set";
                 args1[4] = this.getConfig().getString("Activate.Group");
                 //String com1 = "pex";
                // entity.addPermission(this.getConfig().getString("Activate.Perm"));
                // entity.setPrefix(this.getConfig().getString("Activate.Prefix"), this.getConfig().getString("Activate.World"));
                 //getPluginCommand("pex")  //.execute(CommandSender, "pex", String[] args);
                 //Command command = (Command)com1;
                //return this.commandsManager.execute(sender, command, args1); // /pex user <user> group set user
               //  String perm1[] = new String[1];
               //  perm1[0] = this.getConfig().getString("Activate.Group");
                // permu.setGroups(perm1, this.getConfig().getString("Activate.World"));
             manager.getUser(player).addGroup(this.getConfig().getString("Activate.Group"));
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


