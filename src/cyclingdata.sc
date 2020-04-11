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
  var stageStr =
    s"""
     |    Name- ${routeTuple._1}
     |stage  distance  stage name
     |""".stripMargin

  routeTuple._2.map(n => stageStr = stageStr+ formatSingleStage(n))

  stageStr =stageStr + "\n ---------------------------------------\n"
  //formattedCyclingData = formattedCyclingData + stageStr
  stageStr
}

def summariseSingleRoute (route: (String, List[(Int, String, Float)])): String = {
  var totalRouteDistance = 0f//total distance of a route
  //TODO try fold or foldleft for this instead
  route._2.map(n => totalRouteDistance = totalRouteDistance+ n._3)

  val routeStr =  f"\t\t${route._1} has ${route._2.length} stages and a total distance of ${totalRouteDistance}%2.1f km\n".stripMargin
  routeStr
}



def userSelectedRoute(routes: Map[String, List[(Int, String, Float)]], userSelection: Int) ={
  // map some numbers to the routes so the user can select a number that corresponds to a route
  val routeOptions = routes.zipWithIndex.map(_.swap).toMap

  var routeOptionsString: String = s"""select the number of the route
                     |""".stripMargin
  for((k,v)<- routeOptions){ routeOptionsString = routeOptionsString + s"$k - ${v._1} \n"}

  println(routeOptionsString)

  println("\n\n_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_* \n")
  println(summariseSingleRoute(routeOptions(userSelection)))
  println(formatSingleRoute(routeOptions(userSelection)))
}
userSelectedRoute(mapBuffer, 1)
