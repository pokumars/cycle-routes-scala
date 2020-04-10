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


def allRoutesFormattedText(): String ={
  //the entire cycling data formatted in a human-readable manner
  var formattedCyclingData =
    """|---------------------------------------""".stripMargin
  for((k,v) <- mapBuffer){
    //create a string out of every Route and append to the other strings
    //formatted in a way tha
    var stageStr =  s"""
                       |    Name- $k
                       |stage  distance  stage name
                       |""".stripMargin

    v.map(n => {
      stageStr = stageStr+ f"\n| ${n._1}\t  ${n._3}%2.2f km\t${n._2}"

    })
    stageStr =stageStr + "\n ---------------------------------------\n"
    formattedCyclingData = formattedCyclingData + stageStr
  }
  println(formattedCyclingData)
  //formattedCyclingData
  ""
}

allRoutesFormattedText()

