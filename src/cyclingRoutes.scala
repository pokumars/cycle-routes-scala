import scala.io.Source
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine

object cyclingRoutes extends App {
  //*************************************************************************
  //READ txt FILE (and print it out)
  val mapData :Map[String, List[(Int,String,Float)]] = readCycleRouteData("cyclingData.txt")
  // print data to check it's been read in correctly
  println("the whole mapData------------->" +mapData)

  //************************************************************************
  //UTILITY FUNCTIONS


  def readCycleRouteData(fileName: String):Map[String, List[(Int,String,Float)]] = {
    var mapBuffer:Map[String, List[(Int,String,Float)]] = Map()

    try{
      for(line <- Source.fromFile(fileName).getLines()){
        //println(line)
        mapBuffer = mapBuffer ++ decipherLine(line)
      }
    }
    catch {
      case ex: Exception => println("Sorry, an exception happened.", ex)
    }

    // returns what the function is supposed to return Map[String, List[(Int,String,Float)]]
    //println("mapBuffer ---------------------> " +mapBuffer)
    mapBuffer
  }

  def decipherLine(line: String): Map[String, List[(Int,String,Float)]] ={
    val splitLine = line.split(",")

    val routeName = splitLine.head.trim()
    val stageList: List[(Int, String, Float)]= splitLine.tail.map(figures => {
      val part = figures.split(":").map(_.trim)

      //println((part(0).toInt, part(1), part(2).toFloat))
      (part(0).toInt, part(1), part(2).toFloat)
    }).toList

    //println(Map(routeName -> stageList))
    Map(routeName -> stageList)
  }

  //readUserOption will tell user what their action options are. User selects one
  // of the user option numbers in the do part of the do-while and that value is saved as "opt".
  // Then the while portion of the do-while will call menu(opt) from the "actionMap" the value
  // saved as opt and unwrap it with a .get match-case. Menu() will run the function and also return
  // a boolean to the while to stop or continue it.

  val actionMap = Map[Int, ()=> Boolean](0 -> quitProgram, 1 -> showAllRoutes, 2 -> summariseRoutes, 3 -> getRouteAverages)
  def readUserOption (): Int = {
    println(
      """|Please select one of the following:
        |   0 - quit program
        |   1 - show all routes
        |   2 - summary about the routes
        |   3 - Show route averages""".stripMargin)
    readInt()
  }

  var selectedOption = 0
  do {
    selectedOption = readUserOption()
  }while(runMenuOption(selectedOption))//smth is true or false

  def runMenuOption(choice: Int): Boolean = {
    actionMap.get(choice) match {
      case None =>
        println("This option does not exit")
        true
      case Some(f) => f()
    }
  }


  //************************************************************************
  //Primary functions
  //These are the functions that the user directly invokes by making a selection.
  // They will return a boolean that will keep the do-while running or not

  def quitProgram(): Boolean = {
    println("You have chosen to quit the program")
    false
  }

  def showAllRoutes(): Boolean = {
    println(allRoutesFormattedText)
    true
  }

  def summariseRoutes(): Boolean = {
    allRouteSummaryText()
    true
  }

  //3.	Get the average total distance and average number of stages of all routes.
  def getRouteAverages (): Boolean = {
    averageOfAllRoutes(mapData)
    true
  }

  //************************************************************************
  //Secondary functions
  //These are the functions that the primary functions invoke to accomplish
  // what the user wants

  def allRoutesFormattedText(): String ={
    //the entire cycling data formatted in a human-readable manner
    var formattedCyclingData =
      """|---------------------------------------""".stripMargin
    for((k,v) <- mapData){
      //create a string out of every Route and append to the other strings
      //formatted in a way tha
      var stageStr =  s"""
                       |******** Name- $k ********
                       |stage  distance  stage name
                       |""".stripMargin

      //val sum = stageList.map(f => total = total+ f._3)
      v.map(n => {
        stageStr = stageStr+ f"\n| ${n._1}\t  ${n._3}%2.2f km\t${n._2}"
      })
      stageStr =stageStr + "\n ---------------------------------------\n"
      formattedCyclingData = formattedCyclingData + stageStr
    }
    //println(theText)
    formattedCyclingData
  }
  def allRouteSummaryText() ={
    var formattedRouteSummary =""

    for((k,v) <- mapData){
      //make a summary of every route
      var totalRouteDistance = 0f//total distance of a route
      var routeStr =  s"\t\t$k has ${v.length} stages and a total distance of ".stripMargin

      v.map(n => totalRouteDistance = totalRouteDistance+ n._3)

      routeStr =routeStr + f"${totalRouteDistance}%2.1f km\n" //add computed distance to rest of string
      formattedRouteSummary = formattedRouteSummary + routeStr
    }
    println(formattedRouteSummary)
  }

  def averageOfAllRoutes(theRouteData :Map[String, List[(Int,String,Float)]]): String = {
    //3.	Get the average total distance and average number of stages of all routes.
    var stageAmount: Int = 0 //holds the sum of stages of all routes together
    var totalDistance = 0f
    val routeAmount = theRouteData.size// number of routes there are
    for ((k,v) <- theRouteData){
      stageAmount =stageAmount + v.length
      v.map(n => totalDistance = totalDistance+ n._3)
    }

    val avg = f"""  There are $routeAmount routes. On average each route has roughly ${stageAmount/routeAmount} stages.
       |  The average distance per route is ${totalDistance/routeAmount}%.1f km.
       |  The average distance per stage is ${totalDistance/stageAmount}%.1f km.
       |""".stripMargin
    println (avg)
    avg

  }
}
