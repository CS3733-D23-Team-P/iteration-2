package edu.wpi.punchy_pegasi.frontend;

import edu.wpi.punchy_pegasi.backend.PdbController;
import edu.wpi.punchy_pegasi.frontend.controllers.LayoutController;
import edu.wpi.punchy_pegasi.frontend.navigation.Navigation;
import edu.wpi.punchy_pegasi.frontend.navigation.Screen;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

@Slf4j
public class App extends Application {
    @Getter
    private static App singleton;
    @Getter
    private PdbController pdb = new PdbController("jdbc:postgresql://database.cs.wpi.edu:5432/teampdb",
            "teamp", "teamp130");
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Setter
    @Getter
    private Stage primaryStage;
    @Getter
    @Setter
    private BorderPane viewPane;
    @Getter
    private Screen currentScreen;
    @Getter
    private Scene scene;
    public static void exit() {
        Platform.exit();
    }

    public void setCurrentScreen(Screen value) {
        support.firePropertyChange("page", currentScreen, value);
        currentScreen = value;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    @Override
    public void init() {
        singleton = this;
        log.info("Starting Up");
    }

    public static void loadStylesheet(String resourcePath) {
        App.singleton.scene.getStylesheets().add(App.class.getResource(resourcePath).toExternalForm());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        /* primaryStage is generally only used if one of your components require the stage to display */
        App.singleton.primaryStage = primaryStage;

        final var layoutLoader = new FXMLLoader(App.class.getResource("views/Layout.fxml"));
        final BorderPane loadedLayout = layoutLoader.load();
        final LayoutController layoutController = layoutLoader.getController();

        App.singleton.viewPane = layoutController.getViewPane();

        scene = new Scene(loadedLayout, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        Navigation.navigate(Screen.HOME);
        MFXThemeManager.addOn(scene, Themes.DEFAULT);

        try {
            // TODO: specify table type
//            pdb.importTable("C:\\Documents\\p2\\Nodes.csv", "nodes");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
    }
}
