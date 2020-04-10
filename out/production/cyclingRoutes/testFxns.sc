val str: String = "Oor Wullie Route (GCU),1:City Chambers:0.75f,2:Sir Chris Hoy Velodrome:3.8f,3:People's Palace:2.7f,4:Riverside Museum:5.4f,5:Botanic Gardens:2.4f,6:GCU:3.4f"



//val stageList: List[(Int,String,Float)] = List()

val splitLine = str.split(",")

val routeName = splitLine.head.trim()
var stageList: List[(Int, String, Float)]= List()

stageList = splitLine.tail.map(figures => {
  var part = figures.split(":")

  //println((part(0).toInt, part(1), part(2).toFloat))
  (part(0).toInt, part(1), part(2).toFloat)
}).toList
Map(routeName -> stageList)


/*
var total = 0f

println(stageList)
val sum = stageList.map(f => total = total+ f._3)
total
*/









