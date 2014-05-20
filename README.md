================================================================================
Age of Sail
================================================================================

A Hackathon game about bombards and broadsides.


Introduction
---------------------------------------------------------------------------------

Every player must program an AI that controls a frigate sailing the ocean in search for booty. NPC-Dumboats also sail the seas to be horribly murdered and looted by the players. Yet no wise privateer ships about for a long time laden with illegitimate gains. Raid other player's ships before they can make it to harbor and take their hard-earned Dublones. May the better sailor win!

How to connect
---------------------------------------------------------------------------------

Connect to the server. All communications happen on port 1718 (the year that marked the demise of Edward Teach, aka Blackbeard). The server and clients (the players) communicate in JSON. Every IP controls one ship - when you disconnect (perhaps to recompile), your ship remains and when you reconnect you will automatically take control of it again. The game is simulated in realtime, but your client will (for performance reasons) only receive gamestate information 5 times per second (1).


Sailing
--------------------------------------------------------------------------------

Each IP controls a single pirate frigate. Being a sailing vessel, it is important to take advantage of the wind. Your ship moves at different speeds according to its heading in relation to the wind direction. This is referred to as your point of sail. For the purposes of this primitive simulation, sailing is reduced to its bare essentials.

The points of sail are:
* In irons (directly into the wind up to 45° to the wind) - the ship cannot gain speed, will slow down and eventually stop
* Close haul (45° to 65° to the wind) 40% maximum speed
* Beam reach (65° to 115° to the wind) 60% maximum speed
* Broad reach (115° to 160° to the wind) 100% maximum speed
* Running (160° to directly with the wind) 80% maximum speed

To move at maximum speed in a straight line then, a ship must move in a zig-zag pattern to remain at broad reach for as much time as possible. To make tighter turns, or to otherwise outmaneuver an enemy, a ship can go to half sail. It will move at 50% speed (multiplicative with other speed modifiers). A ship can also haul all sail and stop.


Damage
-------------------------------------------------------------------------------------
(1)

Every frigate has a set of ten cannons on each side and two facing forward. It takes 30 cannonballs to destroy a frigate. Cannons on each side can be fired and reload independently. The dumboats have three cannons on each side and it takes 20 cannonballs to destroy them. A ship is also slowed by damage. A ship at one hit point moves at 50% the speed of a ship at full health (multiplicative with other speed modifiers). When a ship has zero or less hit points less, it is sunk and drops all its collected (and unsecured) Dublones (see below). After 10 seconds, the ship respawns at a random map edge with full health and an empty hold. A ship is also repaired when it goes to port (see below). If your ship is sunk while you are not connected, it will not respawn until you reconnect.


Loot
-------------------------------------------------------------------------------------

When a ship is sunk, it leaves a crate containing all the Dublones it had in its hold. Other ships can collect this loot. To secure the loot, a ship must sail off the edge of the map (into port). Each Dublone secured in this way scores one point. When a ship is in port, it is removed from the map for 10 seconds and respawns on a random map edge at full health. Dumboats are AI ships controlled by the server. They spawn on a random map edge and sail in a straight line to the opposite side of the map. They will only fire when a ship that has previously attacked them moves in front of their cannons. A dumboat carries one Dublone worth of booty which can be collected by sinking it and collecting the dropped plunder.


JSON messages
------------------------------------------------------------------------------------

**Sent to the server:**

    { "type":"ahoi",
      "captain":"Blackbot v.2"
    }
This message is sent when one joins the server for the first time or wants to change one's display name. The name of the captain must be unique. If it is not, it will have the title "the Copycat" appended to it (which cannot be removed except by reconnecting). If that name also exists (or you already have a derogative title), god help us all. Also, you will not be renamed. Any titles your captain has are not affected.

    { "type":"heading",
      "heading":0-360
    }
Command your ship to change its heading. This will cause your ship to start turning until it has reached the specified heading. It will turn the way that is shortest. A heading of 0 is due north, 90 is due east, 180 due south, 270 due west. Decimal places are allowed. Note that tighter cornering can be achieved by first going to half sail.

    { "type":"sail",
      "sail":"half/full/haul"
    }
Set the requested amount of sail. At half sail, you will move at half speed. Hauling sail will cause your ship to come to a stop.

    { "type":"fire!",
      "side":"port/starboard/left/right/forward"
    }
Fires the cannons on the specified of side of the ship, if ready. You may use the non-nautical directions, but this will cause the title "the Landlubber" to be appended to your captain's name, which cannot be removed by renaming the ship
but only by reconnecting.

    { "type":"abandon",
      "what":"ship/game"
    }
Choosing "ship" will sink your ship as if it had been destroyed by enemy fire. Choosing "game" will also delete your ship from the server's records, deleting your score.

    { "type":"taunt",
      "yell":"You fight like a diary farmer!"
    }
Will cause your crew to yell taunts. Additional restrictions to the text may apply. Profanity is encouraged. Passing an empty string as yell will cause a random insult to be dispatched. It will also grant you the title "the Unimaginative" to your name, which can only be removed by disconnecting.


**Sent by the server:**

Lon(gitute) and Lat(itude) are coordinates where `78;15` corresponds to the southeast corner of the map and `79;16` corresponds to the northwest corner. Headings are given in clockwise degrees where 0° is due north.

    { "type":"sighted",
      "id":77,
      "captain":"Blackbot v.2",
      "score":13
    }
Announces that a player (or dumboat) has spawned. Dumboats will always have score 0 and captain "A Spanish Trader". The id will remain the same for the lifetime of the ship, i.e. until it sinks or goes to port.

    { "type":"sunk",
      "id":77,
      "lon":78-79,
      "lat":15-16,
      "booty":2
    }
Announces that a ship has been sunk, where, and how much plunder now floats at that location.

    { "type":"collected",
      "id":45,
      "booty":2,
      "total":4
    }
Announces that a crate of floating plunder has been collected by another player, how much booty was collected and the total amount of loot that this player now carries.

    { "type":"reloaded",
      "side":"port/starboard/left/right/forward"
    }
Announces that your cannons are ready to fire again. The side information will match the one you gave when firing.

    { "type":"wind",
      "wind":0-360
    }
Announces a change in wind direction.

    { "type":"fired",
      "id":77,
      "side":"port/starboard/left/right/forward"
    }
Announces that a ship has fired cannons. It also announces your failure to comprehend nautical directions to the world at large.

    { "type":"hit",
      "id":68,
      "shooter":77,
      "damage":8,
      "hp":24
    }
Announces that a player has hit another player with cannons, how much damage was done and how many hit points the defender has remaining. (2)

    { "type":"report",
      "ships": [ 
        { "id":77
          "lon":78-79,
          "lat":15-16,
          "heading":0-360,
          "booty":5,
          "hp":30
        },
        ...
      ],
      "booty": [
        { "lon":78-79,
        "lat":15-16,
        "booty":3
        }
      ]
    }
A typical gamestate report.

Footnotes
-------------------------------------------------------------------------------------------

(1) Exact values subject to change

(2) This may or may not become a "hit summary" in case several cannonballs fired in the same salvo do not hit simultaneously.
