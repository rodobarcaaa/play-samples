/*
 * Copyright (C) 2009-2016 Typesafe Inc. <http://www.typesafe.com>
 */
package controllers;

import akka.stream.javadsl.Source;
import play.libs.EventSource;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Singleton;

@Singleton
public class JavaEventSourceController extends Controller implements JavaTicker {

    public Result index() {
        return ok(views.html.javaeventsource.render());
    }

    public Result streamClock() {
        final Source eventSource = getSource().map(EventSource.Event::event);
        return ok().chunked(eventSource.via(EventSource.flow())).as(Http.MimeTypes.EVENT_STREAM);
    }

}
