@NonCPS
def call(String projectName) {
  try {
    echo "fetching previous build duration"
    job = hudson.model.Hudson.instance.getItem("jenkins-v2")
    build = job.getLastBuild()
    //println("Build number is ${build.getPreviousBuild().getNumber()}")
    //println("Build duration is ${build.getPreviousBuild().getDuration()}")
    //println("Build number is ${build.getPreviousBuild().getPreviousBuild().getNumber()}")
    //println("Build duration is ${build.getPreviousBuild().getPreviousBuild().getDuration()}")
    echo "fetched previous build duration" 
  } 
  catch (Exception e) {
        echo "Caught exception"
   }
  
}
return this
