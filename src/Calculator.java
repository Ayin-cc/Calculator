import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 新建窗体
        primaryStage.setTitle("计算器");
        primaryStage.setResizable(false);

        // 网格布局
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        // 显示框
        TextField tempDisplay = new TextField();
        TextField display = new TextField();
        tempDisplay.setEditable(false);
        tempDisplay.setFocusTraversable(false);
        tempDisplay.setStyle("-fx-text-fill: gray;");
        display.setEditable(false);
        grid.add(tempDisplay, 0, 0, 4, 1);
        grid.add(display, 0, 1, 4, 1);

        // 添加按钮
        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "C", "0", "=", "/"
        };

        for (int i = 0; i < 16; i++) {
            Button button = new Button(buttonLabels[i]);
            button.setMinSize(50, 50);
            button.setOnAction(event -> handleButtonClick(button.getText(), tempDisplay, display));
            grid.add(button, i % 4, i / 4 + 2);
        }

        // 显示
        Scene scene = new Scene(grid, 265, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String value, TextField tempDisplay, TextField display) {
        if (value.equals("C")) {
            tempDisplay.clear();
            display.clear();
        } else if (value.equals("=")) {
            try {
                String result = evaluateExpression(display.getText());
                display.setText(result);
            } catch (Exception e) {
                display.setText("Error");
            }
        } else {
            display.appendText(value);
        }
    }

    private String evaluateExpression(String expression) {
        // 在这里实现表达式求值的逻辑，这里只是一个简单示例，可以使用 ScriptEngine 或自己实现一个求值器
        return "";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
