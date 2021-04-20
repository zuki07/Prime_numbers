1. The prime List and composite List is defined in runProgram class(lines 47,48)

2. The algorithm I came up with to populate the List is in the Yes Button Lambda Event(lines 93-113) of the runProgram class. 
 Seems like it should be smaller, but it does work well.  Not sure if there is something in the book about such algorithm,
I thought of what it needed to do and started writing code.

3. The Iterator is in the print Set Method of the runProgram Class(lines 174-180)

4. I added a sleep to slow down the program to better illustrate the progress bar in the runProgram class (lines 39,114).  
Without the sleep, the program runs to fast to really see the progress bar moving.  The sleep is set to 15 milliseconds for each number it tries. 
It takes the program 8.115 seconds to find the first 100 prime numbers out of 541 numbers.  This sleep value can be changed on line 39.

5. I have a couple of sound files that play while the program is running and when it finishes.  

6. I realize the composite list is not needed, but i thought it would be interesting to see.  
The list was so long that I had to add a scroll bar to it, keeping the same height as the prime List label.