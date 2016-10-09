package example.jbot.fb;

import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.facebook.Bot;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ramswaroop
 * @version 17/09/2016
 */
@JBot
public class FbBot extends Bot {

    @Value("${fbBotToken}")
    private String fbToken;

    @Override
    public String getFbToken() {
        return fbToken;
    }
}