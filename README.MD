# What is it ?
 A treasure map game in java.
 
 The map of the game is defined from an input file that follows the following rules : 
 
 **C - {width} - {height}**
  
   indicates that we define a map of width * height size
 
 **M - {posX}  - {posY}** 
	
   indicates that we define a mountain at the coordinates posX - posY

 **T - {posX}  - {posY} - {nbTreasures}**

   indicates that we define **{nbTreasures}** treasure(s) at the coordinates **{posX}** - **{posY}**  

 **A - {name}  - {posX} - {posY} - {Direction} - {Movement sequence}**
	
   indicates that we define an adventurer at the coordinates **{posX}** - **{posY}** with the start **{direction}**
     and the sequence movement **{Movement sequence}**
 
# Rules of the game
 Adventurers walk each turn following the movement sequence :

 - **A** walk to the next position from at the direction **{direction}** 
 - **D** turn right (switch the adventurer **{direction}**
 - **G** turn left (switch the adventurer **{direction}**)

 The directions are the following :
 
 - **N** North
 - **O** West
 - **S** South
 - **E** East

 When an adventurer stops at a position of a treasure, he collects it.
 Two adventurers can't be at the same position 
 The game ends when all the adventures have walk all of their **{movement sequence}** 

 The game produces an output file that follows the definition of the input file stated above except for the adventurers
 where the movement sequence is replaced by the number of treasures collected by the adventurer. 
  **A - {name}  - {posX} - {posY} - {Direction} - {nbTreasureCollected}**
 
 For an exemple, the output file :

 C - 3 - 4 <br/>
 M - 1 - 0 <br/>
 M - 2 - 1 <br/>
 M - 1 - 2 <br/>
 T - 1 - 3 - 2 <br/>
 A - Lara - 0 - 3 - S - 3 <br/>

 Can be represented as follow : <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;**X**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**M**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**X**<br/>
 &nbsp;&nbsp;&nbsp;&nbsp;**X**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**X**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**M**<br/>
 &nbsp;&nbsp;&nbsp;&nbsp;**X**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**M**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**X**<br/>
 **A(Lara)**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**T(2)**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**X**<br/>

 Which means that Lara has collected 3 treasures and her last direction was **S**outh.

# Compilation on UNIX or MAC
`$ ./compilation.sh` in the root folder

# Compilation on WINDOWS (or if the script doesn't work :))
`$ javac Main` in the src/ folder

# Usage
`$ java Main input_file` in the bin/ folder
   Ex : `$ java Main ../testFiles/testA`

A `result.txt` file should be generated

# Documentation 
An html javadoc can be found in the /doc folder

