import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util._

class LoadSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:66.0) Gecko/20100101 Firefox/66.0")


  val loadRequest = exec(http("Request Service B via Service A")
    .get("/askforb")
    .check(status is 200))

  val scn = scenario("LoadTesting").tryMax(10) {
    loadRequest
  }


  /** Возможные модели загрузки
    * 1) Указывается длительность паузы duration перед стартом нагрузки
    * 2) Виртуальные пользователи в количестве nbUsers будут “подниматься” сразу
    * 3) В течение времени duration будут "подниматься" виртуальные пользователи в количестве nbUsers через равные временные интервалы.
    * 4) Указывается частота “поднятия” виртуальных пользователей rate (вирт. польз. в секунду) и временной интервал duration.
    * В течении duration количество виртуальных пользователей будет увеличиваться на rate каждую секунду.
    * 5) Аналогично верхней конструкции только временные интервалы между "поднятием" виртуальных пользователей будут случайными
    * 6) В течение времени duration виртуальные пользователи будут увеличиваться с частоты rate1 до частоты rate2
    * 7) Аналогично верхней конструкции только временные интервалы между "поднятиями" виртуальных пользователей будут случайными
    * 8) Через каждый временной интервал duration будут добавляться виртуальные пользователи по модели injectionStep,
    * пока их количество не достигнет nbUsers. В injectionStep можно указать модели описанные выше.
    * 9) Аналогично верхней конструкции только разделителем является модель injectionStep2
    * 10) Виртуальные пользователи в количестве nbUsers будут подниматься ступенями за время duration
    */
  setUp(
    scn.inject(
      nothingFor(4 seconds), // 1
      //atOnceUsers(10), // 2
      //rampUsers(100) over (60 seconds), // 3
      constantUsersPerSec(200) during (120 seconds), // 4
      //constantUsersPerSec(20) during (15 seconds) randomized, // 5
      //rampUsersPerSec(10) to 20 during (10 minutes), // 6
      //rampUsersPerSec(10) to 20 during (10 minutes) randomized, // 7
      //splitUsers(1000) into (rampUsers(10) over (10 seconds)) separatedBy (10 seconds), // 8
      //splitUsers(1000) into (rampUsers(10) over (10 seconds)) separatedBy atOnceUsers(30), // 9
      //heavisideUsers(1000) during (20 seconds) // 10
    ).protocols(httpProtocol)
  )
}