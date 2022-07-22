# Minecraft 1.19 Changelog

## [Unreleased](https://github.com/slymask3/instant-blocks/compare/1.19-1.6.2...HEAD)

## [1.6.3](https://github.com/slymask3/instant-blocks/compare/1.19-1.6.2...1.19-1.6.3)
### Fixed
- Recipes using forge tags on fabric

## [1.6.2](https://github.com/slymask3/instant-blocks/compare/1.19-1.6.1...1.19-1.6.2) - 2022-07-20
### Changed
- Adjusted block strengths
- Don't allow activating when the wand doesn't have enough durability (configurable)

### Fixed
- Instant tree packet

## [1.6.1](https://github.com/slymask3/instant-blocks/compare/1.19-1.6.0...1.19-1.6.1) - 2022-07-19
### Added
- Fabric support

### Changed
- Replaced skydive TP block with an RGB colored texture
- Updated for Forge 41.0.64+

### Fixed
- Third person models for instant wands
- Only show server-side schematics
- Block entity syncing
- Block drops

## [1.6.0](https://github.com/slymask3/instant-blocks/compare/1.18.2-1.6.0...1.19-1.6.0) - 2022-07-04
### Changed
- Ported to Minecraft 1.19

---

# Minecraft 1.18.2 Changelog

## [1.6.3](https://github.com/slymask3/instant-blocks/compare/1.18.2-1.6.2...1.18.2-1.6.3)
### Fixed
- Recipes using forge tags on fabric

## [1.6.2](https://github.com/slymask3/instant-blocks/compare/1.18.2-1.6.1...1.18.2-1.6.2) - 2022-07-20
### Changed
- Adjusted block strengths
- Don't allow activating when the wand doesn't have enough durability (configurable)

### Fixed
- Instant tree packet
- Block rendering

## [1.6.1](https://github.com/slymask3/instant-blocks/compare/1.19-1.6.1...1.18.2-1.6.1) - 2022-07-19
### Added
- Fabric support ([#2](https://github.com/slymask3/instant-blocks/issues/2))

### Changed
- Ported to Minecraft 1.19

## [1.6.0](https://github.com/slymask3/instant-blocks/compare/1.7.10-1.5.5...1.18.2-1.6.0) - 2022-07-04
### Added
- Full localization support
- Wand damage per block
- Many more configurable options

### Changed
- Updated for Minecraft 1.18.2
- Set stone based on layer and dimension
- Wands now use vanilla durability values

### Removed
- Removed /instantblocks command

---

# Minecraft 1.7.10 Changelog

## [1.5.5](https://github.com/slymask3/instant-blocks/compare/v1.5.4...1.7.10-1.5.5) - 2022-07-04
### Added
- Color blending for instant skydive
- Support for slim model statues
- Allow adjusting instant skydive radius
- Allow disabling instant blocks
- French translation from [@Mazdallier](https://github.com/Mazdallier)

### Changed
- Reworked instant liquid blocks

### Fixed
- Fixed instant statue for new skins API
- Fixed color block metadata and transparency issues

### Removed
- Removed colored ladder block

## [1.5.4] - 2014-11-22
### Added
- Added instant schematic block
- Added instant huge tree block

## [1.5.3] - 2014-11-16
### Added
- Added instant light Block
- Added instant skydive GUI
- Added colored ladder block for rainbow skydive
- Added skydive TP block
- Added color block command (/ib colorblock)

### Removed
- Removed Skydive TP and Skydive wool color config options

## [1.5.2] - 2014-11-11
### Added
- Added Instant Harvester Block

### Fixed
- Fixed Instant Rainbow Skydive Block to replace bedrock with water
- Fixed Instant Grinder Block to generate flowing water instead of still water
- Fixed Instant Statue Block generate message, effect, and xp

## [1.5.1] - 2014-11-07
### Fixed
- Fixed Instant Farm Block to actually generate
- Fixed Instant Water/Lava/Suction Blocks to remove wand durability correctly
- Fixed error message with Instant Grinder Block

## [1.5.0] - 2014-11-05
### Added
- Added Instant Rail Block
- Added Instant Statue Block
- Added "Color Block" only available in creative
- Added Mod Gui Options

### Changed
- Updated for Minecraft 1.7.10
- Changed Instant Golden Wand uses from 2 to 16

---

# Minecraft 1.6.2 Changelog

## 1.4.7 - 2013-07-24
### Fixed
- Fixed the multi-directional Instant Blocks bug
- Fixed the bottom textures of the Instant Farm Block and Instant Wooden House Block
- Fixed the Instant Water Block sound

## 1.4.6 - 2013-07-23
### Changed
- Updated for Minecraft 1.6.2

---

# Minecraft 1.5.2 Changelog

## 1.4.5 - 2013-06-05
### Changed
- Improved /instantblocks command

### Removed
- Removed achievements

### Fixed
- Fixed a couple of bugs

## 1.4.4 - 2013-06-01
### Added
- Added Achievement IDs to the config

### Fixed
- Fixed /instantblocks

## 1.4.3 - 2013-06-01
### Added
- Added Instant Wands
- Added achievements
- Made the Instant Wooden House packable

### Changed
- Improved the Instant Blocks command

### Fixed
- Fixed some small bugs

## Minecraft 1.4.2 - 2013-05-26
### Added
- Added an in-game update checker
- Added multi-directional support for the Instant Wooden House
- Added 'Simple' option for Instant Water/Lava in the config
- Added a new Instant Blocks command (/instantblocks or /ib)
- Right-clicking Instant Blocks gives you experience (Configurable amount in the config)

### Changed
- Changed the name of the mod from "InstantBlocks" to "Instant Blocks"
- Instant Water/Lava/Suction Blocks will not create if it's attempting too many

## 1.4.1 - 2013-05-20
### Added
- Added custom wool pattern for the Instant Rainbow Skydive Block in the config file
- Added multi-directionality for all InstantBlocks except for Instant Wooden House and Instant Grinder

### Changed
- Changed the animation for the Instant Rainbow Skydive Block to go upwards

## 1.4.0 - 2013-05-17
### Added
- Added Instant Escape Ladder
- Added Instant Water Block
- Added Instant Lava Block
- Added Instant Suction Block
- Added animated textures
- Added two diving boards to the Instant Pool Block
- Added red particles on successful generation
- Added in-game descriptions for each InstantBlock
- Added ability to teleport to the top of the Instant Rainbow Skydive on successful generation
- Added tons of properties in the config file

### Changed
- Changed the sound on successful generation to the player level up sound
- Changed the Instant Rainbow Skydive Block texture
- Changed the Instant Pool Block texture

### Fixed
- Fixed tons of bugs

## 1.3.4 - 2013-05-13
### Fixed
- "The HuskyMUDKIPZ's Bug"

## 1.3.3 - 2013-05-10
### Added
- Added ability for the InstantBlocks to be generated into the bonus chest, dungeon chests, mineshaft chests, stronghold chests, temple chests, and village chests

### Changed
- Made the mod universal
- Changed properties file with a more advanced config file

## 1.3.2 - 2013-05-04
### Changed
- Updated for Minecraft 1.5.2

---

# Minecraft 1.5.1 Changelog

## 1.3.1 - 2013-04-30
### Added
- Creative tab

### Fixed
- Fixed all the shapeless recipes to shaped

## 1.3.0 - 2013-04-27
### Added
- Added 7 new instant blocks

### Changed
- Updated for Minecraft 1.5.1
- Re-coded with Forge
- Changed the name of the mod from "InstantHouse" to "InstantBlocks"

### Removed
- Removed all previous instant houses

---

# Minecraft 1.2.3 Changelog

## 1.2.1 - 2012-03-09
### Changed
- Updated multiplayer version

### Fixed
- Crafting bug

## 1.2.0 - 2012-03-04
### Changed
- Updated for Minecraft 1.2.3

---

# Minecraft 1.1.0 Changelog

## 1.1.0 - 2012-02-26
### Added
- Added a door and bed in each instant house

### Changed
- Changed recipe

## 1.0.1 - 2012-02-01
### Changed
- Updated for Minecraft 1.1

### Fixed
- Bug causing the game to crash when the instant house blocks are harvested

---

# Minecraft 1.0.0 Changelog

## 1.0.0 - 2011-12-16
### Added
- Instant house item used for crafting all instant houses

### Changed
- Updated for Minecraft 1.0.0
- Changed all recipes to use the instant house item
- Right-click to activate instead of left-click

---

# Minecraft Beta 1.8.1 Changelog

## 0.9.0 - 2011-09-25
### Changed
- Generate instant houses from the center
- Adjusted instant house textures to show where the entrance will be

## 0.8.0 - 2011-09-19
### Added
- Instant stone bricks house

### Changed
- Use glass panes instead of glass blocks

## 0.7.0 - 2011-09-18
### Changed
- Updated for Minecraft Beta 1.8.1

---

# Minecraft Beta 1.7.3 Changelog

## 0.6.0 - 2011-08-12
### Fixed
- "Saving chunks" bug

### Removed
- Reverted reversible functionality

## 0.5.0 - 2011-07-30
### Added
- Properties file

### Changed
- Every instant house block is now explosion proof

## 0.4.0 - 2011-07-22
### Added
- Instant sandstone house
- Instant cobblestone house

## 0.3.0 - 2011-07-20
### Added
- Unique textures for all instant houses

### Changed
- Now reversible by breaking the block

## 0.2.0 - 2011-07-10
### Added
- Instant stone house
- Instant brick house

## 0.1.0 - 2011-07-09
### Added
- Initial release

[1.5.4]: https://github.com/slymask3/instant-blocks/compare/v1.5.3...v1.5.4
[1.5.3]: https://github.com/slymask3/instant-blocks/compare/v1.5.2...v1.5.3
[1.5.2]: https://github.com/slymask3/instant-blocks/compare/v1.5.1...v1.5.2
[1.5.1]: https://github.com/slymask3/instant-blocks/compare/v1.5...v1.5.1
[1.5.0]: https://github.com/slymask3/instant-blocks/releases/tag/v1.5