DonutCore is a free 1.21 Core with very useful features:

- Scoreboard (PlaceholderAPI support)
- Tablist (PlaceholderAPI support)
- Gamemode managment (/gmc, /gm, etc)
- Chatemojis (e.g. replace [yay] with (╯°□°)╯)
- more soon!
- Minimessage support soon
- Everything in the Plugin is customisable, even the permissions.

Configuration (rewrite coming in the next update):

```
# DonutCore Configuration
# by Donut4GAMER
# thanks for downloading the plugin <3

# here you can configure the gamemode command form DonutCore
gamemode:
  # configure the permissions for the /gm, /gmc, ... commands
  permissions:
    # the permission users need to use the gamemode commands
    gamemode-self: "donutcore.gamemode.self"
    # the permission users need to set other players gamemode
    gamemode-others: "donutcore.gamemode.others"

  # configure the messages for the /gm, /gmc, ... commands
  messages:
    # message that appears when you execute the command as the console
    no-player: "&6DonutCore &8| &cOnly players can execute this."
    # message that appears when you do not have the permission you have set above
    no-permission: "&6DonutCore &8| &cYou do not have the permission to do that."
    # message that appears when you just type /gm, for example
    usage: "&6DonutCore &8| &cUsage: /gm <0/1/2/3|s/c/a/sp> [Player]"
    # message that appears when you do not write a valid gamemode
    invalid-gamemode: "&6DonutCore &8| &cThis gamemode does not exist."
    # message that appears when you type a username from an offline/not existing user
    player-not-found: "&6DonutCore &8| &cPlayer not found."
    # message that appears when you have changed your gamemode
    self-change: "&6DonutCore &8| &7Your gamemode has been changed to &6%gamemode%&7."
    # message that appears when you have set another users gamemode
    other-change-sender: "&6DonutCore &8| &7You have set &6%target%&7's gamemode to &6%gamemode%&7."
    # message that appears when someone set your gamemode
    other-change-target: "&6DonutCore &8| &7Your gamemode was set to &6%gamemode%&7 by &6%player%&7."

# here you can configure the chat feature from DonutCore
chat:
  # configure the text you can type that will be replaced with an emoji or something else
  replacements:
    "[o/]": "§e§lヾ(＾∇＾)"
    "[yay]": "§b§l(╯°□°)╯"
    "[lol]": "§d§l(≧▽≦)"
    "[what]": "§c§l(⊙_☉)"
    "[omg]": "§6§lΣ(°ロ°)"
    "[rip]": "§8§l(✖╭╮✖)"
    "[shrug]": "§7¯\\_(ツ)_/¯"
    "[rage]": "§4§l(ノಠ益ಠ)ノ彡┻━┻"

# here you can configure the tablist feature from DonutCore
tablist:
  # do you want to enable the DonutCore Tablist?
  enabled: true
  # after how many ticks should the Tablist update? (100 Ticks=5 Seconds)
  update-interval: 100
  # set the Header and Footer of the Tablist
  # PlaceholderAPI support
  header:
    - "&r"
    - "&7Welcome on our Server"
    - "&7User: &6%player_name%"
    - "&r"
  footer:
    - "&r"
    - "&7Thanks for Downloading"
    - "&7Discord: &6https://discord.gg/Yarpy8tAqJ"
    - "&r"

# here you can configure the scoreboard feature from DonutCore
scoreboard:
  # do you want to enable the DonutCore Scoreboard?
  enabled: true
  # after how many ticks should the Tablist update? (100 Ticks=5 Seconds)
  update-interval: 100
  # set the title of the Scoreboard
  title: "&6&lDonut&f&lCore"
  # set the Scoreboard lines (every line needs to be unique!!!)
  # PlaceholderAPI support
  lines:
    - "&r&8----------------"
    - "&7Name: &6%player_name%"
    - "&7Ping: &6%player_ping%"
    - "&r"
    - "&7Discord:"
    - "&6https://discord.gg/Yarpy8tAqJ"
    - "&8----------------"
```

Commands and Usage:
- /donutcore help - Shows the help message
- /donutcore reload - Reloads the config
- /gmc [Player] - Set the gamemode to creative (of a player)
- /gmsp [Player] - Set the gamemode to spectator (of a player)
- /gms [Player] - Set the gamemode to survival (of a player)
- /gma [Player] - Set the gamemode to adventure (of a player)
- /gm <0/1/2/3|s/c/a/sp> [Player] - Set the gamemode (of a player)

Support: 
If you need help or have a question/suggestion join my [Discord](https://dsc.gg/donutdev).
