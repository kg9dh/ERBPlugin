ERB PLUGIN
==========


Information
----------
The Bukkit forum user 3DDarren had the idea for this plugin and asked for help so I (kg9dh) coded it. During the process more and more features got added to the plugin, such as a configuration with a word blacklist, configurable prizes and timestamps. The goal of the plugin is to add some fun to the server.


Features
----------
The ERB-Plugin is a server mod which adds rap battles to the gameplay. The goal of the rap battle is to get as many votes as possible to win a prize. At the beginning two different players need to join the battle using /erb join. If there are more players they get an error message, aswell as if a player tries to join twice. A second after both players joined a timer starts counting down, and the plugin tells the “rappers” to prepare themselves. After that, “rapper one” starts with a first “session” which gives him some time to /rap his lines. When the session ends the plugin allows the second player to /rap for a certain amount of time. After that the plugin broadcasts that the first player can /rap for the second and last time. Then the plugin stops the “rapping part” of this battle and opens all other users to either vote for “rapper one” or “rapper two”. The “rappers” can’t vote, and when someone voted, he can’t vote for a second time. Then the plugin compares the votes and gives the winner a prize. If it’s a tie it gives both players a “tie prize”. If one player uses /leave while the battle is going on the other player gets a prize.


Commands
----------
/erb  Main ERB command. This will display a help page. 
/erb join	Join the battle as rapper. 
/erb vote [player]	Vote for a certain player. 
/erb leave	Leave the battle. 
/erb forceend	Admincommand: End the battle. 
/erb reload	Admincommand: Reload the plugin. 
/rap <text>	Rap something while it’s your turn.


Permissions
----------
erb.join	Permission for the “/erb join” command. 
erb.vote	Permission for the “/erb vote” command. 
erb.leave	Permission for the “/erb leave” command. 
erb.forceend	Permission for the “/erb forceend” command. 
erb.reload	Permission for the “/erb reload” command. 
erb.rap	Permission for the “/rap” command. 
erb.user	Permission for "erb.join", "erb.vote", "erb.leave" & "erb.rap". 
erb.*	Permission for everything.


Config
----------
The blacklist is a list of words and sentences (strings) which will be replaced with stars (*) when they are used when using the /rap command.

The prizes. The normal prize is the prizes which a player gets when he wins the battle by having more votes. The “tie prize” is the prize both players get if the battle ends in a tie. The last prize will be given out if a player leaves the battle. The other player basically wins and gets a certain prize defined in the configuration. Use item IDs only here.

The timestamps configure how long certain things will go. The countdown defines the normal countdown(5…4…3…etc.). On the next configuration line is the keyword “rapper_one_firstsession”. This will define how long the first rapper got time for his first session. “rapper_two” defines how long the next rapper is able to rap and “rapper_one_lastsession” defines how long the first rapper got time for his second session. On the last line you will see “vote”. There you can change the amount of time people got time to vote. All these numbers need to Integers (e.g. no 10.135) and will be handled as seconds.


Questions
----------
Either send me a message on BukkitDev, Bukkit forums or on Skype: kaygeenine.
