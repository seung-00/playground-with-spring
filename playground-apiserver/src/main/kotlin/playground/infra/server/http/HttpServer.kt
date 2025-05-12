//package playground.infra.server.http
//
//import io.undertow.Undertow
//import io.undertow.UndertowOptions
//import io.undertow.servlet.Servlets
//import io.undertow.servlet.api.ServletContainerInitializerInfo
//import io.undertow.servlet.util.ImmediateInstanceFactory
//import jakarta.servlet.ServletContainerInitializer
//import org.xnio.Options
//import java.util.concurrent.ThreadPoolExecutor
//
//class HttpServer(
//  port: Int,
//  servletExecutor: ThreadPoolExecutor,
//  servletContainerInitializer: ServletContainerInitializer,
//) : AutoCloseable {
//
//  private val deploymentManager = Servlets.defaultContainer()
//    .addDeployment(
//      Servlets.deployment()
//        .setDeploymentName("root")
//        .setContextPath("/")
//        .setDefaultEncoding(Charsets.UTF_8.name())
//        .setClassLoader(servletContainerInitializer::class.java.classLoader)
//        .setExecutor(servletExecutor)
//        .addServletContainerInitializer(
//          ServletContainerInitializerInfo(
//            servletContainerInitializer::class.java,
//            ImmediateInstanceFactory(servletContainerInitializer),
//            emptySet()
//          )
//        )
//    ).also {
//      it.deploy()
//    }
//
//  private val undertow = Undertow.builder()
//    .addHttpListener(port, "0.0.0.0")
//    .setHandler(deploymentManager.start())
//    .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
//    .setServerOption(UndertowOptions.ENABLE_STATISTICS, true)
//    // AccessLogHandler로 시간 기록하려면 필요하다.
//    .setServerOption(UndertowOptions.RECORD_REQUEST_START_TIME, true)
//    .setServerOption(UndertowOptions.NO_REQUEST_TIMEOUT, 10 * 60 * 1000)
//    .setSocketOption(Options.CONNECTION_HIGH_WATER, 16180)
//    // XNIO 버그가 의심되어 설정하지 않는다. https://github.com/xnio/xnio/commit/38d6e1cbfb1dc90f35c51c8a89386bde7ff97f94#r39086297
//    // .setSocketOption(Options.CONNECTION_LOW_WATER, 3000)
//    .setSocketOption(Options.KEEP_ALIVE, true)
//    .setSocketOption(Options.TCP_NODELAY, true)
//    .build()
//    .also {
//      it.start()
//    }
//
//  override fun close() {
//    undertow.stop()
//    deploymentManager.stop()
//    deploymentManager.undeploy()
//  }
//}
