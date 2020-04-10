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


def allRoutesFormattedText(cycleData: Map[String, List[(Int, String, Float)]]): String = {
  //the entire cycling data formatted in a human-readable manner
  var formattedCyclingData =
    """|---------------------------------------""".stripMargin

  for((k,v) <- cycleData){
    formattedCyclingData = formattedCyclingData + formatSingleRoute((k,v))
  }
  println(formattedCyclingData)
  //formattedCyclingData
  ""
}

allRoutesFormattedText(mapBuffer)

/*
def allRouteSummaryText(): String ={
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
  "formattedCyclingData"
}
allRouteSummaryText()
 */

/*

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
println(averageOfAllRoutes(mapBuffer))
 */
