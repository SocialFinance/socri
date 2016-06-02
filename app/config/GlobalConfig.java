package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.libs.F;
import play.mvc.Http;

public class GlobalConfig extends GlobalSettings {

    private static final Logger logger = LoggerFactory.getLogger(GlobalConfig.class);
    private ApplicationContext context;

    @Override
    public void onStart(Application a) {
        logger.info("Initializing ApplicationContext");
        context = new AnnotationConfigApplicationContext(AppConfig.class, DataConfig.class);

        //WHY DOESN'T THIS WORRRRRK?!?!
//        if(Play.application().configuration().getBoolean("socri.debug.user.create")) {
//            User u = new User();
//            u.setUsername(getConfig("socri.debug.user.name"));
//            u.setPassword(getConfig("socri.debug.user.password"));
//            u.setAlias("Debug user");
//            u.setSpecialties("Debugging, wasting time");
//            u.setWeaknesses("Fish tacos");
//            //TODO how to actualy persist this data.
//        }
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        logger.trace("Getting controller instance {}", clazz.getName());
        return context.getBean(clazz);
    }

    @Override public F.Promise<play.mvc.Result> onHandlerNotFound(Http.RequestHeader request) {
        return F.Promise.pure(controllers.Index.goAway());
    }

    private String getConfig(String key) {
        return Play.application().configuration().getString(key);
    }
}
