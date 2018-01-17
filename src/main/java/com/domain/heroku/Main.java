package com.domain.heroku;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java
 * command that is used for launching should fire this main method.
 */
public final class Main {
     /**
      * Main Method. Starts the Application.
      * @param args No args expected.
      * @throws Exception If Server fails to start. Check logs.
      */
    public static void main(final String[] args) throws Exception {
        /**
         *  The port can be set into an environment variable.
         *  Look for that variable and default to 8080 if it isn't there.
         */
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        final Server server = new Server(Integer.valueOf(webPort));
        final WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        /**
         *  Parent loader priority is a class loader setting that Jetty accepts.
         *  By default Jetty will behave like most web containers in that it
         *  will allow your application to replace non-server libraries that
         *  are part of the container.
         *  Setting parent loader priority to true changes this behavior.
         *  Read more http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
         */
        root.setParentLoaderPriority(true);

        final String webappDirLocation = "src/main/webapp/";
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        server.setHandler(root);

        server.start();
    }

    /**
     * Private Constructor to prevent instantiation.
     * @throws Exception Cannot be instantiated.
     */
    private Main() throws Exception {
        throw new Exception("Exposes Static Main Function");
    }

    /**
     * Public method to get instant of Main.
     * @return Main object
     * @throws Exception Cannot be instantiated.
     */
    public static Main getMainInstant() throws Exception {
        return new Main();
    }
}
