/**
 * 
 */
package com.synechron.blockchain.customer.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

/**
 * A class responsible to configure the Metrics Reporter.
 */
@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MetricsConfig.class);

	@Value("${customer.metrics.console.enabled}")
	private boolean consoleEnabled;

	@Value("${customer.metrics.console.interval}")
	private Long consoleInterval;

	@Override
	public void configureReporters(MetricRegistry metricRegistry) {
		logger.info("configureReporters start");

		/*if (graphiteEnabled) {
			 Configuring the Graphite Reporter. 
			final Graphite graphite = new Graphite(new InetSocketAddress(graphiteServer, graphitePort));
			final GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry).prefixedWith(metricsPrefix)
					.convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL)
					.build(graphite);
			reporter.start(1, TimeUnit.SECONDS);
			logger.info("Graphite reporter for metrics was initialized successfully.");
		}*/

		if (consoleEnabled) {
			/* Configuring the Console Reporter. */
			ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
					.convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
			consoleReporter.start(consoleInterval, TimeUnit.SECONDS);
			logger.info("Console reporter for metrics was initialized successfully.");
		}

		/*if (jmxEnabled) {
			 Configuring the Jmx Reporter. 
			final JmxReporter reporter = JmxReporter.forRegistry(metricRegistry).build();
			reporter.start();
			logger.info("JMX reporter for metrics was initialized successfully.");
		}*/

		logger.info("configureReporters end");
	}
}
