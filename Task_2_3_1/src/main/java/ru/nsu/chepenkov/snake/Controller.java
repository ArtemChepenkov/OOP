package ru.nsu.chepenkov.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Контроллер для управления игрой "Змейка" на JavaFX.
 * Отвечает за инициализацию игры, обработку ввода пользователя и управление игровым циклом.
 */
public class Controller {
    @FXML private Canvas gameCanvas;
    @FXML private Text gameStatusText;
    private static final double FRAME_RATE = 0.1;
    private static final int TARGET_WIN = 10;
    private static final int GRID_CELL = 20;
    private Model gameModel;
    private View gameView;
    private GraphicsContext canvasContext;
    Timeline gameCycle;
    private int highScore = 0;
    boolean hasPlayed = false;

    /**
     * Инициализирует контроллер при загрузке FXML.
     * Настраивает графический контекст, модель игры, вид и запускает игровой цикл.
     */
    @FXML
    public void initialize() {
        canvasContext = gameCanvas.getGraphicsContext2D();
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::processKeyInput);

        int gridWidth = (int) (gameCanvas.getWidth() / GRID_CELL);
        int gridHeight = (int) (gameCanvas.getHeight() / GRID_CELL);
        gameModel = new Model(gridWidth, gridHeight, 5, TARGET_WIN);
        gameView = new View(canvasContext, gameCanvas, gameModel, GRID_CELL);

        setupGameCycle();
        updateStatusText();
    }

    /**
     * Настраивает игровой цикл с использованием Timeline.
     */
    private void setupGameCycle() {
        gameCycle = new Timeline(new KeyFrame(Duration.seconds(FRAME_RATE), event -> {
            gameStep();
            gameView.draw();
        }));
        gameCycle.setCycleCount(Timeline.INDEFINITE);
        gameCycle.play();
    }

    /**
     * Выполняет один шаг игры, обновляя состояние модели и проверяя условия окончания.
     */
    void gameStep() {
        gameModel.movement();
        if (gameModel.isGameOver() || gameModel.isWon()) {
            handleGameEnd();
            return;
        }
        updateStatusText();
    }

    /**
     * Обрабатывает конец игры, обновляя рекорд и отображая соответствующее сообщение.
     */
    void handleGameEnd() {
        highScore = Math.max(highScore, gameModel.getScore());
        String statusMessage = gameModel.isWon()
                ? "Победа! Счёт: " + gameModel.getScore() + " Нажмите ENTER для рестарта"
                : "Игра окончена! Нажмите ENTER для рестарта";
        gameStatusText.setText(statusMessage);
        gameCycle.stop();
        hasPlayed = true;
    }

    /**
     * Обновляет текст статуса игры в зависимости от текущего состояния и рекорда.
     */
    void updateStatusText() {
        if (!hasPlayed) {
            gameStatusText.setText("Счёт: " + gameModel.getScore());
        } else {
            gameStatusText.setText("Счёт: " + gameModel.getScore() + " | Рекорд: " + highScore);
        }
    }

    /**
     * Обрабатывает ввод с клавиатуры, управляя направлением змейки и перезапуском игры.
     *
     * @param event событие нажатия клавиши
     */
    @FXML
    void processKeyInput(KeyEvent event) {
        KeyCode key = event.getCode();

        if (key == KeyCode.ENTER) {
            resetGame();
            event.consume();
            return;
        }

        switch (key) {
            case UP -> gameModel.getSnake().setDirection(Direction.UP);
            case DOWN -> gameModel.getSnake().setDirection(Direction.DOWN);
            case LEFT -> gameModel.getSnake().setDirection(Direction.LEFT);
            case RIGHT -> gameModel.getSnake().setDirection(Direction.RIGHT);
            default -> {}
        }
    }

    /**
     * Перезапускает игру, очищая текущий игровой цикл и переинициализируя контроллер.
     */
    void resetGame() {
        if (gameCycle != null) {
            gameCycle.stop();
        }
        initialize();
    }
}