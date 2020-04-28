def call(branchName,numberOfBuilds,additionalTime) {
    echo "fetching previous build duration"
    def lastBuild=hudson.model.Hudson.instance.getItem(branchName).getLastSuccessfulBuild()
    println("type of class is ${lastBuild.getClass()}")
    println("type of class after typecasting is ${(hudson.model.Hudson.instance)lastBuild.getClass()}")
    Float totalBuildTime=0.0
    Integer averageBuild=5
    return (lastSuccessfullBuildTime(lastBuild,numberOfBuilds,totalBuildTime)/averageBuild)*additionalTime
}
def lastSuccessfullBuildTime(previousBuild,numberOfBuilds,totalBuildTime) {
  println("calling last successful build method")
  if(previousBuild != null && numberOfBuilds>0) {
    echo "Build time of last build is ${previousBuild.getDuration()/1000.0} seconds"
    echo "Build status of last build is ${previousBuild.getLastSuccessfulBuild().getDuration()}"
    totalBuildTime=(previousBuild.getDuration()/1000.0)+totalBuildTime
    lastSuccessfullBuildTime(previousBuild.getLastSuccessfulBuild(),--numberOfBuilds,totalBuildTime)
  }
  else {
    echo "Total build time of last 5 build in minutes is ${totalBuildTime/60.0}"
    return totalBuildTime/60.0
  }
}
return this
