package main.java.fr.mickael.main;

import main.java.fr.mickael.controller.MainController;
import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger();

        /*if the argument is "modeDev" or modeDev is true in config_file, the game will start
        in developer mode*/
        if (Config.isDefaultModeDev() || (args.length > 0 && args[0].equals("modeDev"))){
            logger.info("Game starting in developer mode");
            Config.setModeDev(true);
        }

        MainController.getInstance().run();
    }
}
