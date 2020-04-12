var mapBuffer: Map[String, List[(Int, String, Float)]] = Map()


var key ="Oor Wullie Route (GCU)"
var newList = List((1,"City Chambers",0.75f),(2,"Sir Chris Hoy Velodrome",3.8f),(3,"People's Palace",2.7f),(4,"Riverside Museum",5.4f),(5,"Botanic Gardens",2.4f),(6,"GCU",3.4f))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Religious Route (Glasgow Cathedral)"
newList = List((1,"St Andrew's Cathedral",1.8f),(2,"Central Mosque",0.75f),(3,"University Chapel",5.4f),(4,"Om Hindu Mandir",1.3f),(5,"Gurdwara Singh Sabha",0.6f),(6,"Quaker Meeting House",1.2f),(7,"Glasgow Buddhist Centre",0.35f),(8,"Garnethill Synagogue",0.45f),(9,"Glasgow Cathderal",3.3f))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Art Route (Kelvingrove Art Gallery and Museum)"
newList = List((1,"Hunterian Art Gallery",1.2f),(2,"MacKintosh Building",2.2f),(3,"Gallery Of Modern Art",1.4f),(4,"St. Mungo Museum Of Religious Life & Art",1.3f),(5,"People's Palace",2.0f),(6,"The Burrell Collection",7.1f),(7,"House For An Art Lover",2.8f),(8,"Kelvingrove Art Gallery and Museum",5.0f))
mapBuffer = mapBuffer ++ Map(key -> newList)

key ="Education Route (GCU)"
newList = List((1,"University Of Strathclyde",0.65f),(2,"City Of Glasgow College - Riverside Campus",1.4f),(3,"School of Simulation and Visualisation",3.9f),(4,"Glasgow Science Centre",0.7f),(5,"University of Glasgow",2.4f),(6,"The Mitchell Library",1.9f),(7,"Glasgow School Of Art",0.9f),(8,"Royal Conservatoire Of Scotland",0.75f),(9,"GCU",0.6f))
mapBuffer = mapBuffer ++ Map(key -> newList)

println("mapBuffer: " + mapBuffer)


//*****************************************************************************************************
//Operational functions
//Since I will use these several times I turned them into functions

def formatSingleStage(stageTuple: (Int,String,Float)): String =  f"\n| ${stageTuple._1}\t  ${stageTuple._3}%2.2f km\t${stageTuple._2}"

def formatSingleRoute(routeTuple: (String, List[(Int, String, Float)])): String ={
  //create a string out of a single route
  var stageStr =  s"""
                     |    Name- ${routeTuple._1}
                     |stage  distance  stage name
                     |""".stripMargin

  routeTuple._2.map(n => stageStr = stageStr+ formatSingleStage(n))

  stageStr =stageStr + "\n ---------------------------------------\n"
  //formattedCyclingData = formattedCyclingData + stageStr
  stageStr
}

def summariseSingleRoute (route: (String, List[(Int, String, Float)])): String = {

  val totalRouteDistance = route._2.foldLeft(0f){(acc, cur) => acc+ cur._3}

  f"\t${route._1} has ${route._2.length} stages and a total distance of ${totalRouteDistance}%2.1f km\n".stripMargin
}
//*****************************************************************************************************



def allRoutesFormattedText(cycleData: Map[String, List[(Int, String, Float)]]) = {
  //the entire cycling data formatted in a human-readable manner
  var formattedCyclingData =
    """|---------------------------------------""".stripMargin

  for((k,v) <- cycleData){
    formattedCyclingData = formattedCyclingData + formatSingleRoute((k,v))
  }
  println(formattedCyclingData)
}
println("-------------Test number 1------------------------------")
allRoutesFormattedText(mapBuffer)


def allRouteSummaryText() ={
  var formattedRouteSummary =""

  for((k,v) <- mapBuffer){
    //make a summary of every route
    var totalRouteDistance = 0f//total distance of a route
    var routeStr =  s"\t\t$k has ${v.length} stages and a total distance of ".stripMargin

    v.map(n => totalRouteDistance = totalRouteDistance+ n._3)

    routeStr =routeStr + f"${totalRouteDistance}%2.1f km\n" //add computed distance to rest of string
    formattedRouteSummary = formattedRouteSummary + routeStr
  }
  println(formattedRouteSummary)

}
println("-------------Test number 2------------------------------")
allRouteSummaryText()

def averageOfAllRoutes(theRouteData :Map[String, List[(Int,String,Float)]]): String = {//number 4
  //3.	Get the average total distance and average number of stages of all routes.
  var stageAmount: Int = 0 //holds the sum of stages of all routes together
  var totalDistance = 0f
  val routeAmount = theRouteData.size// number of routes there are
  for ((k,v) <- theRouteData){
    stageAmount =stageAmount + v.length
    v.map(n => totalDistance = totalDistance+ n._3)
  }

  f"""  There are $routeAmount routes. On average each route has roughly ${stageAmount/routeAmount} stages.
     |  The average distance per route is ${totalDistance/routeAmount}%.1f km.
     |  The average distance per stage is ${totalDistance/stageAmount}%.1f km.
     |""".stripMargin

}
println("-------------Test number 3------------------------------")
println(averageOfAllRoutes(mapBuffer))


def sortRoutesWithDistance (routes: Map[String, List[(Int, String, Float)]]) = {
  var distances:List[Float] = List()

  for((k,v) <- routes){//make a summary of every route
    var totalRouteDistance = 0f//total distance of a route
    v.map(n => totalRouteDistance = totalRouteDistance+ n._3)
    distances =  distances::: totalRouteDistance:: Nil
  }

  val routesLongestFirst = (routes zip distances).toSeq.sortWith(_._2 > _._2).toList
  var longestFirstSummary = "--------------Routes ordered Longest distance First ------------------ \n"

  for((k,v) <- routesLongestFirst){//make a summary of every route
    longestFirstSummary = longestFirstSummary + summariseSingleRoute((k._1,k._2))
  }

  //println(distances)
  println(longestFirstSummary)
}
println("-------------Test number 4 ------------------------------")
sortRoutesWithDistance(mapBuffer )

def sortedRoutesSummary(sortedRoutes: Map[String, List[(Int, String, Float)]]) ={
  var formattedRouteSummary =""

  for((k,v) <- mapBuffer){//make a summary of every route
    formattedRouteSummary = formattedRouteSummary + summariseSingleRoute((k,v))
  }
  println(formattedRouteSummary)
}


def userSelectedRoute(routes: Map[String, List[(Int, String, Float)]], testSelection: Int) ={
  // map some numbers to the routes so the user can select a number that corresponds to a route
  val routeOptions = routes.zipWithIndex.map(_.swap).toMap

  var routeOptionsString: String = s"""select the number of the route
                                      |""".stripMargin
  for((k,v)<- routeOptions){ routeOptionsString = routeOptionsString + s"$k - ${v._1} \n"}

  println(routeOptionsString)

  val userSelection: Int = testSelection

  routeOptions.get(userSelection) match {
    case None => "This number is not an option. Please start over"
    case Some(n) => {
      println("\n\n_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_* \n")
      println(summariseSingleRoute(n).stripMargin)
      println(formatSingleRoute(n).stripMargin)
    }
  }

}
println("-------------Test number 5 ------------------------------")
//Test that an option that exists gives a route
userSelectedRoute(mapBuffer, 1)
// Test that an option that does not exits asks you to start over
userSelectedRoute(mapBuffer, 5)

