package com.xl.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ui.dwr.Util;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2017-11-27
 * @Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class ChatService {
    /**
     * The current set of messages
     */
    private final LinkedList<Message> messages = new LinkedList<Message>();

    /**
     * @param text The new message text to add
     */
    public void addMessage(String text) {
        System.out.println("enter with text:" + text);
        // Make sure we have a list of the list 10 messages
        if (text != null && text.trim().length() > 0) {
            messages.addFirst(new Message(text));
            while (messages.size() > 10) {
                messages.removeLast();
            }
        }
        // Clear the input box in the browser that kicked off this page only
        Util.setValue("text", "");
        System.out.println("edited message:" + messages.toString());
        // For all the browsers on the current page:
        Browser.withCurrentPage(new Runnable() {
            @Override
            public void run() {
                // Clear the list and add in the new set of messages
                Util.removeAllOptions("chatlog");
                Util.addOptions("chatlog", messages, "text");
            }
        });
    }
}
