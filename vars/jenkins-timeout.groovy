def call(String projectName) {
    echo "fetching previous build duration"
    def job=hudson.model.Hudson.instance.getItem("jenkins-v2")
    //job = hudson.model.Hudson.instance.getItem("jenkins-v2")
    def build = job.getLastBuild()
    Integer averageElasticBuildTime = 30
    Integer numberOfBuilds=5
    Float totalBuildTime=0
    averageElasticBuildTime = (lastSuccessfullBuildTime(build,numberOfBuilds,totalBuildTime)/5)*0.5
    //println("Build number is ${build.getPreviousBuild().getNumber()}")
    //println("Build duration is ${build.getPreviousBuild().getDuration()}")
    //println("Build number is ${build.getPreviousBuild().getPreviousBuild().getNumber()}")
    //println("Build duration is ${build.getPreviousBuild().getPreviousBuild().getDuration()}")
    println("value of average elastic build time is ${averageElasticBuildTime}")
    echo "fetched previous build duration" 

}
def lastSuccessfullBuildTime(previousBuild,numberOfBuilds,totalBuildTime) {
  println("calling last successful build method")
  if(previousBuild != null && numberOfBuilds>0) {
    echo "Build time of last build is ${previousBuild.getDuration()/1000} seconds"
    totalBuildTime=(previousBuild.getDuration()/1000)+totalBuildTime
    lastSuccessfullBuildTime(previousBuild.getPreviousBuild(),--numberOfBuilds,totalBuildTime)
  }
  else {
    echo "Total build time of last 5 builds in minutes is ${totalBuildTime/60}"
    return totalBuildTime/60
  }
}
return this
