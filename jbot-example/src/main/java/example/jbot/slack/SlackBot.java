package example.jbot.slack;

import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.WebSocketSession;

import java.util.regex.Matcher;

/*
 * Answers to questions
 * Maven: a build automation tool used in Java projects
 *Jar file: JAR is a Java Archive, file format that integrates files into one executable file. Similar to ZIP files.
 *
 *
 *
 *
 *Final project Idea: Slack Bot that gives you one of two exams inside a direct message.
 *1. Integrates Slack
 *2. Uses inheritance
 *3. Reads from file/class?
 *4. Calculates grade and shows student
 *5. Writes Grade on exam taken, and USERID from the user that requested the exam.
 *
 */



/**
 * A simple Slack Bot. You can create multiple bots by just extending
 * {@link Bot} class like this one. Though it is recommended to create only bot
 * per jbot instance.
 *
 * @author ramswaroop
 * @version 1.0.0, 05/06/2016
 */
@JBot
@Profile("slack")
public class SlackBot extends Bot {
	Elements elements = new Elements();

	private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

	/**
	 * Slack token from application.properties file. You can get your slack token
	 * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
	 */
	@Value("${slackBotToken}")
	private String slackToken;

	@Override
	public String getSlackToken() {
		return slackToken;
	}

	@Override
	public Bot getSlackBot() {
		return this;
	}


	// FINAL PROJECT STARTS "Java collection unique entries" 

	@Controller(pattern = ("quiz please"), next = "giveQuiz")
	public void quizRequested(WebSocketSession session, Event event) {
		startConversation(event, "giveQuiz"); // start conversation
		reply(session, event,"Great! Would you like a quiz about Java or Geography?");
	}

	//@Controller(next = "giveQuiz")
	//public void quizSelection(WebSocketSession session, Event event) {
	//	reply(session, event,"Great! Would you like a quiz about Java or Geography?");
	//	nextConversation(event); // jump to next question in conversation
	//}

	@Controller
	public void giveQuiz(WebSocketSession session, Event event) {
		if (event.getText().equalsIgnoreCase("java")) {
			reply(session, event,("Right away!"));
			nextConversation(event); // jump to next question in conversation
		} else {
			reply(session,event,("Sure thing!"));
			nextConversation(event); 
			}
	}

	@Controller
	public void askWhetherToRepeat(WebSocketSession session, Event event) {
		if (event.getText().equalsIgnoreCase("yes")) {
			System.out.println("Great! I will remind you tomorrow before the meeting.");
		} else {
			System.out.println("Okay, don't forget to attend the meeting tomorrow :)");
		}
		stopConversation(event); // stop conversation
	
	} // END OF FINAL PROJECT


	@Controller(pattern = "(die)")
	public void onSayDie(WebSocketSession session, Event event) {
		// startConversation(event, "confirmTiming"); // start conversation
		reply(session, event, "Dont say that, that is rude.");
	}
	

	@Controller
    public void testTest(WebSocketSession session, Event event) {
    	elements.LoadElements();
    	
    	for(String e : elements.elements.keySet()) {
    		if (event.getText().equalsIgnoreCase (e)) {
    			reply(session, event, elements.elements.get(e));
    			
    		}
    	}
    	
    			
    			
    	/*if (event.getText().equalsIgnoreCase("Hydrogen")) {
    		reply(session, event, "Hydrogen is COOL.");
    	}
    	else {
    		reply(session, event, "I'm sorry, I do not recognize that element. Please try again.");
   
    	}
    	@Controller(pattern = "(Hydrogen)")
    	*/
    }

}