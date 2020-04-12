import scala.collection.immutable.ListMap

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

def summariseSingleRoute (route: (String, List[(Int, String, Float)])): String = {
  //var totalRouteDistance = 0f//total distance of a route
  //TODO try fold or foldleft for this instead
  //route._2.map(n => totalRouteDistance = totalRouteDistance+ n._3)
  val totalRouteDistance = route._2.foldLeft(0f){(acc, cur) => acc+ cur._3}

  f"\t${route._1} has ${route._2.length} stages and a total distance of ${totalRouteDistance}%2.1f km\n".stripMargin
}

def getRoutesWithDistance (routes: Map[String, List[(Int, String, Float)]]) = {
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
getRoutesWithDistance(mapBuffer )
/*
def sortedRoutesSummary(sortedRoutes: Map[String, List[(Int, String, Float)]]) ={
  var formattedRouteSummary =""

  for((k,v) <- mapBuffer){//make a summary of every route
    formattedRouteSummary = formattedRouteSummary + summariseSingleRoute((k,v))
  }
  println(formattedRouteSummary)
}

def sortedRouteReport (routes: Map[String, List[(Int, String, Float)]]): Unit = {

  //ListMap(routes.toSeq.sortWith(_._2 > _._2):_*)
}*/
