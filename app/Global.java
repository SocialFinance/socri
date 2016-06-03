import config.AppConfig;
import config.DataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;
import play.libs.F;
import play.mvc.Http;

/**
 * Global Play configuration.
 * Fires off AppConfig and DataConfig
 * Handles unmatched routes
 */
public class Global extends GlobalSettings {

    private static final Logger logger = LoggerFactory.getLogger(Global.class);
    private ApplicationContext context;

    @Override
    public void onStart(Application a) {
        logger.info("Initializing ApplicationContext");
        context = new AnnotationConfigApplicationContext(AppConfig.class, DataConfig.class);
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        logger.trace("Getting controller instance {}", clazz.getName());
        return context.getBean(clazz);
    }

    @Override
    public F.Promise<play.mvc.Result> onHandlerNotFound(Http.RequestHeader request) {
        return F.Promise.pure(controllers.Index.goAway());
    }
}
