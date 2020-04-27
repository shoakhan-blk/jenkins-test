def call(String projectName) {
    echo "fetching previous build duration"
    def job=hudson.model.Hudson.instance.getItem("jenkins-v2")
    def build = job.getLastBuild()
    Float averageElasticBuildTime = 0.0
    Integer numberOfBuilds=5
    Float totalBuildTime=0.0
    averageElasticBuildTime = (lastSuccessfullBuildTime(build,numberOfBuilds,totalBuildTime)/5.0)*1.5
    println("value of average elastic build time is ${averageElasticBuildTime}")
    echo "fetched previous build duration" 
    return ${averageElasticBuildTime}

}
def lastSuccessfullBuildTime(previousBuild,numberOfBuilds,totalBuildTime) {
  println("calling last successful build method")
  if(previousBuild != null && numberOfBuilds>0) {
    echo "Build time of last build is ${previousBuild.getDuration()/1000.0} seconds"
    totalBuildTime=(previousBuild.getDuration()/1000.0)+totalBuildTime
    lastSuccessfullBuildTime(previousBuild.getPreviousBuild(),--numberOfBuilds,totalBuildTime)
  }
  else {
    echo "Total build time of last 5 build in minutes is ${totalBuildTime/60.0}"
    return totalBuildTime/60.0
  }
}
return this
