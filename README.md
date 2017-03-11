[![Build Status](https://travis-ci.org/BotMill/kik-botmill.svg?branch=master)](https://travis-ci.org/BotMill/kik-botmill)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/co.aurasphere.botmill/kik-botmill/badge.svg?style=blue)](https://maven-badges.herokuapp.com/maven-central/co.aurasphere.botmill/kik-botmill)
[![Javadocs](http://www.javadoc.io/badge/co.aurasphere.botmill/kik-botmill.svg)](http://www.javadoc.io/doc/co.aurasphere.botmill/kik-botmill)
[![Gitter](https://badges.gitter.im/BotMill/kik-botmill.svg)](https://gitter.im/BotMill/kik-botmill?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# Kik-BotMill - Awesome Framework to Build Kik Bots

Inspired by our first ever Bot Framework [FB-BotMill](https://github.com/BotMill/fb-botmill)

Kik-BotMill is designed to ease the process of developing, designing and running bots that exist inside Kik Messenger. 

It provides a semantic Java API that can be imported on your Java EE Project to send and receive messages from Kik so that developers can focus on developing the actual application instead of dealing with Kik API endpoints.

**<h3>Getting Started</h3>**

x.x.x - indicates version.  

	<dependency>
	  <groupId>co.aurasphere.botmill</groupId>
	  <artifactId>kik-botmill</artifactId>
	  <version>2.0.0-RC1</version>
	</dependency>
	
Gradle
    
    compile 'co.aurasphere.botmill:kik-botmill:2.0.0-RC1'

Grovvy

    @Grapes( 
        @Grab(group='co.aurasphere.botmill', module='kik-botmill', version='2.0.0-RC1') 
    )
    
Other ways to import, visit Maven central repo [site](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22kik-botmill%22) 

Once you've imported the API. You need to register the KikBotMillServlet. To do that, create a Servlet project in your IDE and add this to your web.xml:

```xml

 <servlet>
	  <servlet-name>myKikBot</servlet-name>
	  <servlet-class>co.aurasphere.botmill.kik.KikBotMillServlet</servlet-class>
  </servlet>
  <servlet-mapping>
	  <servlet-name>myKikBot</servlet-name>
	  <url-pattern>/myKikBot</url-pattern>
  </servlet-mapping>
  
```
Take note of the **url mapping** since this will be used on your webhook configuration in Kik.  

**<h4>Creating your Bot Definition.</h4>**
The Bot Definition is the heart of your Kik ChatBot. This is where we put all other chatbot event handlers and responses.

**1st: Setup the username and apikey.**
Create botmill.properties file in your classpath and add the your tokens.

```properties
kik.user.name=<USERNAME>
kik.api.key=<API_KEY>
```

Note that you can encrypt the properties file using our built in jaspyt-based encryption. Go to our Wiki here on how to setup your encrypted **botmill.properties** file.

**2nd: Setup the KikBot Class.**
Our framework makes it easy and straightforward to define a Kik Bot Behaviour by tagging classes as behaviour objects. 

```java
@Bot
public class MyKikBot extends KikBot {
	@KikBotMillInit
	public void initialize() {
		ConfigurationBuilder.getInstance()
			.setWebhook("<webhook url>")
			.setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false)
			.setReceiveIsTyping(true)
			.setReceiveReadReceipts(false)
			.setStaticKeyboard(KeyboardBuilder.getInstance()
					.addResponse(MessageFactory.createTextResponse("BODY"))
					.setType(KeyboardType.SUGGESTED).buildKeyboard())
			.buildConfiguration();
	}
	
	@KikBotMillController(eventType=EventType.TEXT_MESSAGE, text="Hi")
	public void sendMessage() {
		reply(ReplyFactory.buildTextMessageReply("Hello World!"));	
	}
}
```

**Key components in building your ChatBot**
- @Bot - annotating a class with @Bot will mark the class as a Kik ChatBot behaviour. 
- @KikBotMillInit - can be use to annotate a method and invoke it prior to any @KikBotMillController annotated methods. 
- @KikBotMillController - Use to create a method that catches specific user-driven event (such as user entering a message, selecting a quick reply etc. 
- KikBot.reply() - allows the developers to create a response based on the @KikBotMillController event. For the list of all events and reply, go to our Wiki page [here](https://github.com/BotMill/fb-botmill/wiki/Code-Snippets)
- KikBot.botMillSession() - allows you to store and access data. __Note that you need to setup a mongodb connection to make this work, mongodb connection configuration can also be set via botmill.properties__. For more information about this, visit our [BotMillSession guide here]https://github.com/BotMill/fb-botmill/wiki/Developing-with-FB-BotMill). 


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

**or both -  with Keyboard**

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
**or catch the 'start chatting' event**

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

**or when a user sent a sticker**

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

**or if a user tries to scan the kik data code**

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


<h3>Contribution</h3>
We'd love to get more people involve in the project. Kik Interactive recently made bold investments to improve Kik and we might see a lot of improvements in the upcoming months (possibly Payments). Any contribution to this project will be highly appreciated.


<sub>Copyright (c) 2017 BotMill.io</sub>
