#!/usr/bin/env groovy
import hudson.model.Hudson

/**
 * @param branch_name name of the branch
 * @param number_of_builds number of builds to find the elastic time
 * @param additional_time additional time that gets added to the average build time
 * @return return the elastic build time in minutes
 */
def call(branch_name,number_of_builds,additional_time) {
    def last_successful_build=Hudson.instance.getItem(branch_name).getLastSuccessfulBuild()
    return (sumOfLastSuccessfulBuildTime(last_successful_build,number_of_builds,0.0)/number_of_builds)*additional_time
}
/**
 * Recusively calculate the sum of build duration
 * @param last_successful_build last successful build 
 * @param number_of_builds number of builds to find the elastic time
 * @param total_build_time additional time that gets added to the average build time
 * @return return the total build time in minutes
 */
def sumOfLastSuccessfulBuildTime(last_successful_build,number_of_builds,total_build_time) {
  if(last_successful_build != null && number_of_builds>0) {
    total_build_time=(last_successful_build.getDuration()/1000.0)+total_build_time
    sumOfLastSuccessfulBuildTime(last_successful_build.getPreviousSuccessfulBuild(),--number_of_builds,total_build_time)
  }
  else {
    return total_build_time/60.0
  }
}
return this

/**
#!/usr/bin/env groovy
import hudson.model.Hudson

def call(branchName,numberOfBuilds,additionalTime) {
    echo "fetching previous build duration"
    def lastBuild=Hudson.instance.getItem(branchName).getLastSuccessfulBuild()
    Float totalBuildTime=0.0
    return (totalLastSuccessfulBuildTime(lastBuild,numberOfBuilds,totalBuildTime)/numberOfBuilds)*additionalTime
}
def totalLastSuccessfulBuildTime(previousBuild,numberOfBuilds,totalBuildTime) {
  println("calling last successful build method")
  if(previousBuild != null && numberOfBuilds>0) {
    echo "Build time of last build is ${previousBuild.getDuration()/1000.0} seconds"
    totalBuildTime=(previousBuild.getDuration()/1000.0)+totalBuildTime
    totalLastSuccessfulBuildTime(previousBuild.getPreviousSuccessfulBuild(),--numberOfBuilds,totalBuildTime)
  }
  else {
    echo "Total build time of last 5 build in minutes is ${totalBuildTime/60.0}"
    return totalBuildTime/60.0
  }
}
return this
 */
