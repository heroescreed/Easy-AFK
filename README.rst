.. image:: https://ko-fi.com/img/githubbutton_sm.svg
   :target: https://ko-fi.com/Q5Q710SOTM
   :alt: Kofi Link

.. image:: https://img.shields.io/discord/1265317002960961556?color=blue&label=discord
   :target: https://discord.gg/tT3WX2uZBC
   :alt: Discord

Easy AFK
-------

Simple, lightweight minecraft plugin to mark afk players in tab.

Features
-------------

- Auto AFK marking for users who are stood still for a customisable length of time
- Customisable checks for when to add and remove afk
- Ability to add custom AFK messages by using /afk set [message]
- Option to have AFK alerts get broadcast to the whole server
- When AFK players are mentioned in chat, player who mentioned them is alerted to their AFK status
- Opped players are able to see a list of users who are afk and their reasons for easy moderation
- Options for AFK players to be vanished, unable to take PvP and/or PvE damage, prevent hunger loss and become unable to pickup blocks for the duration of their afk
- Scoreboard team colour support

Commands
-----

- /afk help - Shows an in-game guide to commands
- /afk set [message] - Allows the user to set their own AFK, with and optional custom message
- /afk remove - Allows a user to remove their own AFK, if automatic checks are disabled
- /afk list - Allows a user to view a list of all AFK players with their reasons

Permissions
-----

- easyafk.*
    - Grants the player all easyafk command permissions
    - Granted by default to opped players
- easyafk.afk
    - Allows the player to run easy afk commands
    - Granted by default to all players
- easyafk.afk.set
    - Allows the player to run /afk set
    - Granted by default to all players
- easyafk.afk.list
    - Allows the player to run /afk list
    - Granted by default to opped players

How To Use
-----

First, download the .jar file from the `latest release <https://github.com/heroescreed/Easy-AFK/releases>`_
Alternatively, you can compile the jar file yourself from the source code using maven.

Place the .jar file into your plugins folder of your spigot or paper server and start it.

A new folder in your plugins folder will be created, inside there will be your config.yml file where you can customise the plugins behaviour

Support
----------

- `LightCreed Commissions Server <https://discord.gg/tT3WX2uZBC>`_
- Add me on Discord: ``@heroescreed``