// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/mqq/IdeaProjects/ITSD-DT2022-Template/conf/routes
// @DATE:Sun Feb 12 13:31:47 GMT 2023


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
