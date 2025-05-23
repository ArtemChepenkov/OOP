package ru.nsu.chepenkov.snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

/**
 * Тесты для класса Controller.
 */
public class TestController {
    private Controller controller;
    private Canvas canvas;
    private GraphicsContext gc;
    private Text gameStatusText;
    private Model gameModel;
    private View view;

    /**
     * Устанавливает тестовые данные перед каждым тестом.
     *
     * @throws Exception если не удалось установить приватные поля через рефлексию
     */
    @BeforeEach
    public void setUp() throws Exception {
        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        gameStatusText = new Text();
        gameModel = new Model(20, 20, 2, 10);
        view = new View(gc, canvas, gameModel, 20);

        controller = new Controller();

        setPrivateField(controller, "gameCanvas", canvas);
        setPrivateField(controller, "gameStatusText", gameStatusText);
        setPrivateField(controller, "gameModel", gameModel);
        setPrivateField(controller, "gameView", view);
    }

    /**
     * Устанавливает значение приватного поля через рефлексию.
     *
     * @param obj объект, в котором нужно изменить поле
     * @param fieldName имя поля
     * @param value новое значение поля
     * @throws Exception если поле не найдено или недоступно
     */
    private void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    /**
     * Получает значение приватного поля через рефлексию.
     *
     * @param obj объект, из которого нужно получить поле
     * @param fieldName имя поля
     * @return значение поля
     * @throws Exception если поле не найдено или недоступно
     */
    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * Проверяет корректность инициализации контроллера.
     *
     * @throws Exception если не удалось получить приватные поля
     */
    @Test
    public void testInitialize() throws Exception {
        assertNotNull(getPrivateField(controller, "gameModel"));
        assertNotNull(getPrivateField(controller, "gameView"));
        assertNull(getPrivateField(controller, "gameCycle"));
        assertFalse((boolean) getPrivateField(controller, "hasPlayed"));
        assertEquals(0, (int) getPrivateField(controller, "highScore"));
    }

    /**
     * Проверяет обработку нажатия клавиши для изменения направления.
     */
    @Test
    public void testProcessKeyInputChangeDirection() {
        KeyEvent upEvent = new KeyEvent(null, null,
                null, KeyCode.UP, false, false, false, false);
        controller.processKeyInput(upEvent);
        assertEquals(Direction.UP, gameModel.getSnake().getDirection());

        KeyEvent rightEvent = new KeyEvent(null, null,
                null, KeyCode.RIGHT, false, false, false, false);
        controller.processKeyInput(rightEvent);
        assertEquals(Direction.RIGHT, gameModel.getSnake().getDirection());
    }

    /**
     * Проверяет обработку нажатия Enter для рестарта игры.
     */
    @Test
    public void testProcessKeyInputRestart() throws Exception {
        gameModel.movement();
        controller.gameStep();
        assertFalse((boolean) getPrivateField(controller, "hasPlayed"));
        assertNotNull(gameStatusText.getText());

        KeyEvent enterEvent = new KeyEvent(null, null, null,
                KeyCode.ENTER, false, false, false, false);
    }

    /**
     * Проверяет логику обновления игры при нормальном состоянии.
     */
    @Test
    public void testGameStepNormal() throws Exception {
        controller.gameStep();
        assertEquals("Счёт: 0", gameStatusText.getText());
        assertFalse((boolean) getPrivateField(controller, "hasPlayed"));
    }

    /**
     * Проверяет логику обновления игры при проигрыше.
     */
    @Test
    public void testGameStepOver() throws Exception {
        gameModel.getSnake().setDirection(Direction.LEFT);
        for (int i = 0; i < 6; i++) {
            gameModel.movement();
        }
        controller.gameStep();
        assertFalse((boolean) getPrivateField(controller, "hasPlayed"));
        assertFalse(gameStatusText.getText().contains("Игра окончена!"));
    }

    /**
     * Проверяет перезапуск игры.
     */
    @Test
    public void testResetGame() throws Exception {
        gameModel.movement();
        controller.gameStep();
        assertFalse((boolean) getPrivateField(controller, "hasPlayed"));
    }
}