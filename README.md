[![Build Status](https://travis-ci.org/BotMill/kik-botmill.svg?branch=master)](https://travis-ci.org/BotMill/kik-botmill)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/co.aurasphere.botmill/kik-botmill/badge.svg?style=blue)](https://maven-badges.herokuapp.com/maven-central/co.aurasphere.botmill/kik-botmill)
[![Javadocs](http://www.javadoc.io/badge/co.aurasphere.botmill/kik-botmill.svg)](http://www.javadoc.io/doc/co.aurasphere.botmill/kik-botmill)
[![Gitter](https://badges.gitter.im/BotMill/kik-botmill.svg)](https://gitter.im/BotMill/kik-botmill?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# Kik-BotMill - Tools to Mill Kik Bots

Inspired by our first ever Bot Framework [FB-BotMill](https://github.com/BotMill/fb-botmill)

Kik-BotMill is designed to ease the process of developing, designing and running bots that exist inside Kik Messenger. 

It provides a semantic Java API that can be imported on your Java EE Project to send and receive messages from Kik so that developers can focus on developing the actual application instead of dealing with Kik API endpoints.

**<h3>Getting Started</h3>**

x.x.x - indicates version.  

	<dependency>
	  <groupId>co.aurasphere.botmill</groupId>
	  <artifactId>kik-botmill</artifactId>
	  <version>x.x.x</version>
	</dependency>
	
Gradle
    
    compile 'co.aurasphere.botmill:kik-botmill:x.x.x'

Grovvy

    @Grapes( 
        @Grab(group='co.aurasphere.botmill', module='kik-botmill', version='x.x.x') 
    )
    
Other ways to import, visit Maven central repo [site](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22kik-botmill%22) 

Once you've imported the API. You need to register the KikBotMillServlet. To do that, create a Servlet project in your IDE and add this to your web.xml:

```xml

 <servlet>
	  <servlet-name>myKikBot</servlet-name>
	  <servlet-class>co.aurasphere.botmill.kik.KikBotMillServlet</servlet-class>
	  <init-param>
		  <param-name>bot-definition-class</param-name>
		  <param-value>com.sample.kik.demo.KikBotEntryPoint</param-value>
	  </init-param>
	  <load-on-startup>0</load-on-startup>
  </servlet>
  <servlet-mapping>
	  <servlet-name>myKikBot</servlet-name>
	  <url-pattern>/myKikBot</url-pattern>
  </servlet-mapping>
  
```

Your KikBotEntryPoint should extends KikBotMillEntry. You need to override the kikBotEntry and define your domains and behaviours.

```java

public class KikBotEntryPoint extends KikBotMillEntry {
	/**
	 * Entry point is the main method that will be called only once.
	 * This is where we define our configuration and responses.
	 */
	@Override
	protected void kikBotEntry() {
		
		//	setup
		KikBotMillContext.getInstance().setup("<USERNAME>", "<APIKEY>");
		
		//	configuration.
		ConfigurationBuilder.getInstance().setWebhook("<webhook>/myKikBot")
			.setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false)
			.setReceiveIsTyping(true)
			.setReceiveReadReceipts(false)
			.setStaticKeyboard(
				KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
				.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT))
			.buildKeyboard())
		.buildConfiguration();
			
		//	Domain > collection of responses
		KikBotMillContext.getInstance().registerDomain(new SampleKikBot());
		
	}
}

```

Your domain holds all the actions of your Bot.  

In the following example, the action will catch either a "hello" or "HELLO" response from the user and respond back a message "Hey <user>! How can I help you today?".

```java
public class SampleKikBot extends AbstractKikBot {

	@BotMillController(event = EventType.TEXT_PATTERN, pattern = "(?i:hello)")
	public void replyCatchTextPattern() {
		reply(new TextMessageReply() {
			@Override
			public TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance()
						.setBody("Hey " + ((IncomingMessage) message).getFrom() + "! How can I help you today?")
						.build();
			}
		});
	}
	
}
```

<div>
<div style="text-align:centered; width:100%;padding:5px; border:1px solid gray;">
<img src="https://dl.dropboxusercontent.com/u/1737239/botmill/kik_sample.png" height="345" width="210" />&nbsp;
</div>
</div>

<h4>The framework offers a set of builders and factories to catch and build the perfect response of your bot.</h4>

**Be it a Link**

```java
@BotMillController(event = EventType.TEXT_MESSAGE, text = "Hi")
public void replyCatchText() {
	reply(new LinkMessageReply() {
	    @Override
	    public LinkMessage processReply(Message message) {
	        return LinkMessageBuilder.getInstance()
	                .setTitle("This is a link title")
	                .setUrl("http://alvinjayreyes.com")
	                .setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
	                .build();
	    }
	});
}
```

**or a Media (picture and video)**

```java
@BotMillController(event = EventType.TEXT_MESSAGE, text = "Hi")
public void replyCatchText() {
	reply(new PictureMessageReply() {
		@Override
		public PictureMessage processReply(Message message) {
			return PictureMessageBuilder.getInstance()
					.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
					.build();
		}
	});
}
```

**or both, with Keyboard**

```java
@BotMillController(event = EventType.TEXT_MESSAGE, text = "Hi")
public void replyCatchText() {
	reply(new LinkMessageReply() {
		@Override
		public LinkMessage processReply(Message message) {
			return LinkMessageBuilder.getInstance()
					.setTitle("Title")
					.setUrl("http://alvinjayreyes.com")
					.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
					.addKeyboard(KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
						.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT)).buildKeyboard()
					)
					.build();
		}
	});
}
```

**catch a pattern from user input**
```java
@BotMillController(event = EventType.TEXT_PATTERN, text = "(?i:hello)")
public void replyCatchTextPattern() {
	reply(new LinkMessageReply() {
		@Override
		public LinkMessage processReply(Message message) {
			return LinkMessageBuilder.getInstance()
					.setTitle("Title")
					.setUrl("http://alvinjayreyes.com")
					.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
					.addKeyboard(KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
						.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT)).buildKeyboard()
					)
					.build();
		}
	});
}
```
**or catch the start chatting**

```java
@BotMillController(event = EventType.START_CHATTING)
public void replyCatchTextPattern() {
	reply(new LinkMessageReply() {
		@Override
		public LinkMessage processReply(Message message) {
			return LinkMessageBuilder.getInstance()
					.setTitle("Title")
					.setUrl("http://alvinjayreyes.com")
					.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
					.addKeyboard(KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
						.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT)).buildKeyboard()
					)
					.build();
		}
	});
}
```

**catch a sticker**

```java
@BotMillController(event = EventType.STICKER)
public void replyCatchTextPattern() {
	reply(new LinkMessageReply() {
		@Override
		public LinkMessage processReply(Message message) {
			return LinkMessageBuilder.getInstance()
					.setTitle("Title")
					.setUrl("http://alvinjayreyes.com")
					.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
					.addKeyboard(KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
						.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT)).buildKeyboard()
					)
					.build();
		}
	});
}
```

**or catch a scan data**

```java
@BotMillController(event = EventType.SCAN_DATA)
public void replyCatchTextPattern() {
	reply(new LinkMessageReply() {
		@Override
		public LinkMessage processReply(Message message) {
			return LinkMessageBuilder.getInstance()
					.setTitle("Title")
					.setUrl("http://alvinjayreyes.com")
					.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
					.addKeyboard(KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
						.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT)).buildKeyboard()
					)
					.build();
		}
	});
}
```

**<h3>How to use it on other Java Frameworks</h3>**

The KikBotMillLoad class offers a set of static methods that can be access to load Entry Points and catch WebHook URL POST Request.  

```java
// Call this upon initialization of your app (should only be called once)
KikBotMillLoader.getLoader().loadEntryPoint(new KikBotEntryPoint());

//	Call this on your callback url post handler (req = HttpRequest, Resp = HttpResponse).
KikBotMillLoader.getLoader().postHandler(req, resp); 
```

**On Spark Java**

```java
import static spark.Spark.*;
			
public class KikBot {
    public static void main(String[] args) {
		// called once.
    	KikBotMillLoader.getLoader().loadEntryPoint(new KikBotEntryPoint());
    	 
    	//	register post (use this as webhook url on the config entrypoint);
    	post("/webhook", (request, response) -> {
	    	KikBotMillLoader.getLoader().postHandler(req, resp); 
		});
    }
}
```
	
**On Spring Boot**

```java

@SpringBootApplication
public class KikBotConfiguration {

	public static void main(String[] args) {
	    //	call the loader inside the Hell
	    SpringApplication.run(KikBotConfiguration.class, args); 
	    
	    //	and load Entry Point.
	    KikBotMillLoader.getLoader().loadEntryPoint(new KikBotEntryPoint());
		
	}

}
```

```java	

@Controller
public class RestfulSourceController {

    @Autowired
    Response response;
    
	@Autowired
    Request request;
    
    @RequestMapping(value="/webhoolurl", method=RequestMethod.POST, produces="application/json")
    @ResponseBody
    public void post() {
        return KikBotMillLoader.getLoader().postHandler(request, response); 
    }
}

```

**<h3>What's currently supported</h3>**

Kik-BotMill supports this Kik Messenger Platform components:

- Authentication
- Configuration
- Sending and Receiving Messages
	- Text
	- Link
	- Picture
	- Video
	- Start Chatting
	- Scan Data
	- Sticker
	- Is Typing
	- Delivery Receipt
	- Read Receipt
	- Friend Picker
- Keyboards
- Attributions
- Broadcasting
- User Profiles
- Kik Codes  

**Coming Soon**
- Broadcast Dashboard
- Analytics Dashboard
- Payments

<h3>Contribution</h3>
We'd love to get more people involve in the project. Kik Interactive recently made bold investments to improve Kik and we might see a lot of improvements in the upcoming months (possibly Payments). Any contribution to this project will be highly appreciated.


<sub>Copyright (c) 2017 BotMill.io</sub>
