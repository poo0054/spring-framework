package com.poo0054.study.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:47
 */

public class EventTest extends ApplicationEvent {

    private static final long serialVersionUID = 7736675937532675739L;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with which the event is associated (never
     *            {@code null})
     */
    public EventTest(Object source) {
        super(source);
    }

}
