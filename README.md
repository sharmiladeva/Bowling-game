Bowling Game 

This is a Spring boot application designed to calculate scores of bowlers with the help of REST APIs

The game consists of 10 frames. In each frame the player has two opportunities to knock down 10 pins. The score for the frame is the total number of pins knocked down, plus bonuses for strikes and spares.

Assumptions made :
1. 4 lanes are present.
2. In a single lane only 3 players are allowed to play. 
3. If the person scores a strike or spare in last set, then the person is provided with one more chance to bowl the pins.
4. If there is spare the player gets 5 bonus points.
5. If there is a strike the player gets 10 bonus points.
6. Random number generator is used to generate scores of the bowlers
7. Bowlers are assigned lane-wise. For ex, bowlers are assigned to lane 2 only if lane 1 is full with 3 bowlers.
8. Game will not be started unless all the bowlers can be allotted lanes.

Provided REST APIs :

1. /game/start(POST) -takes array of Bowler's firstName and lastName as input. 
2. /game/score/{bowlerId}/{gameId} - fetches the score of the bowler in a particular game(current or completed).
3. /game/winner/{gameId} - fetches the id of the winner in a particular game.
4. /game/allocatedlane/{bowlerId}/{gameId} - fetches the allocated lane of the bowler in a particular game
5. /game/strikes/{bowlerId}/{gameId} - Number of strikes scored by a bowler in a particular game.
6. /game/strikes/{bowlerId} - Number of strikes scored by a bowler in all games.
7. /bowler/list - fetches list of all bowlers.
8. /bowler/{bowlerId} - fetches a particular bowler with id
