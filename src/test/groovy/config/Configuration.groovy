package config

import common.BasePath
import common.Environment
import common.Endpoints
import groovy.json.JsonSlurper
import org.junit.Assert

class Configuration {
    static final String configFile = "src/test/groovy/config/config.json";

    static boolean loadEnvironments() {
        def jsonFile = new File(configFile)
        def parsedJson = new JsonSlurper().parseText(jsonFile.text)
        if (parsedJson != null){
            String newDefaultEnv = parsedJson.defaultEnvironment
            Environment.setDefaultEnv(newDefaultEnv)
            ArrayList<String> environments = parsedJson.environments
            Environment.setEnvs(environments)
            String defaultMode = parsedJson.defaultMode
            Environment.setDefaultMode(defaultMode)
            ArrayList<String> modes = parsedJson.modes
            Environment.setModes(modes)
            return true
        }
        else {
            return false
        }
    }

    static boolean loadPaths() {
        def jsonFile = new File(configFile)
        def parsedJson = new JsonSlurper().parseText(jsonFile.text)
        if (parsedJson != null){
            HashMap<String, HashMap<String, String>> newPaths = parsedJson.basePaths
            BasePath.setBasePaths(newPaths)
            return true
        }
        else {
            return false
        }
    }

    static boolean loadEndpoints() {
        def jsonFile = new File(configFile)
        def parsedJson = new JsonSlurper().parseText(jsonFile.text)
        if (parsedJson != null){
            HashMap<String, HashMap<String, String>> newEndpoints = parsedJson.endpoints
            Endpoints.setEndpoints(newEndpoints)
            return true
        }
        else {
            return false
        }
    }

    static void loadAllConfigs() {
        Assert.assertTrue("Failed to load environments values from file.", loadEnvironments())
        Assert.assertTrue("Failed to load base paths and uris.", loadPaths())
        Assert.assertTrue("Failed to load endpoints.", loadEndpoints())
        println "Configurations loaded."
    }

    static void main(String[] args) {
        loadAllConfigs()
        println Environment.getEnvironment()
    }

}
