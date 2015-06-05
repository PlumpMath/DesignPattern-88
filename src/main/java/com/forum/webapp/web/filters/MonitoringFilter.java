package com.forum.webapp.web.filters;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class MonitoringFilter implements Filter {

    private MetricRegistry registry;

    private Timer timer;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        registry = new MetricRegistry();
        // Report the monitoring to the console every 20 seconds.
        final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.MINUTES).convertDurationsTo(TimeUnit.MILLISECONDS).build();
        consoleReporter.start(20, TimeUnit.SECONDS);
        timer = registry.timer(MetricRegistry.name("web-forum", "overall-performances"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Timer.Context context = timer.time();
        try {
            chain.doFilter(request, response);
        } finally {
            context.stop();
        }
    }

    @Override
    public void destroy() {
        // Nothing to do.
    }

}
