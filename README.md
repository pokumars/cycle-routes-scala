# Cycling Routes application
#### Advanced Programming Coursework March 2020
Develop a simple text-based, menu-driven interface in Scala to extract information from
cycling route data. You will be provided with the data, and your application 
should allow the user to select from the set of
options listed below and obtain the results of the chosen selection.

#### Data 
The data is supplied to you as a comma-separated text file cyclingroutedata.txt.
 A sample line from the file, edited for display purposes, is shown below: 
> Oor Wullie Route (GCU),1:City Chambers:0.75,2:Velodrome:3.8,3:People's Palace:2.7 …


#### Options
Your application should allow the user to perform the following analyses: 
1.	Get all the route values and display suitably formatted.
2.	Get the total distance and number of stages for each route, for all routes
	e.g. Oor Wullie Route has 6 stages and a total distance of 18.45km
3.	Get the average total distance and average number of stages of all routes.
4.	Get a report of all routes in descending order of total distance with a summary
 showing the overall average total distance and overall average number of stages for all routes.
5.	Get the route details for a user specified route.
6.  Allow the user to construct a personalised list of routes through selecting 
one or more routes and adding a comment to each chosen route.

Your application should read the file contents and store the data in a map structure.
The type of the structure should be Map[String, List[(Int,String,Float)]].

#### Application structure 
The application should be implemented as a Scala singleton object.
Your code should firstly read the file data into the map. 
It should then display a menu, allow the user to input a choice and invoke a suitable
handler function for that choice. The menu should have an option for each of the

 
For each analysis it is suggested that you define the following: 
-	A function that performs the required operation on the data and returns the 
results to be displayed 
-	A function, to be invoked by a menu option, that accepts user input if required
for an operation, invokes the operation function and displays the results of the
operation to the user.

The functions for each analysis should be composed to perform the operation and display
 the result. For example, the first analysis listed above could be performed using: 
-	A function that is applied to the data and returns a result of type 
Map[String, (Int,String,Float)] 
-	A function that invokes the operation and iterates through the resulting map to 
display its contents.

Each analysis will differ in terms of the user input required and the type of result
 returned by the operation. 
