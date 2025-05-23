package ru.nsu.chepenkov.snake;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Главный класс приложения "Змейка".
 * Отвечает за запуск JavaFX приложения и настройку начального окна.
 */
public class Main extends Application {

    /**
     * Инициализирует и отображает основное окно приложения.
     *
     * @param primaryStage основная сцена приложения
     * @throws Exception если не удалось загрузить FXML-файл или произошла другая ошибка
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlResource = getClass().getResource("/game.fxml");
        if (fxmlResource == null) {
            throw new IllegalStateException("Не удалось найти FXML-файл: /game.fxml");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);
        Parent rootNode = fxmlLoader.load();
        Scene gameScene = new Scene(rootNode);

        primaryStage.setTitle("Игра Змейка");
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Точка входа для запуска приложения.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        launch(args);
    }
}