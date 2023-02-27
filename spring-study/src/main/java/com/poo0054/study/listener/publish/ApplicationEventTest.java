package com.poo0054.study.listener.publish;

import org.springframework.context.ApplicationEvent;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:47
 */

public class ApplicationEventTest extends ApplicationEvent {

    private static final long serialVersionUID = 7736675937532675739L;
    private Object data;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with which the event is associated (never
     *            {@code null})
     */
    public ApplicationEventTest(Object source) {
        super(source);
        this.data = source;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
